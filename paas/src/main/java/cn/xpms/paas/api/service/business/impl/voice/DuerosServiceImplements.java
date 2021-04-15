package cn.xpms.paas.api.service.business.impl.voice;

import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.PaasProjectInfoMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasRoomGuestMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasVoiceRoomRelationMapper;
import cn.xpms.paas.api.service.business.inter.voice.DuerosServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DuerosServiceImplements
 * @Desc
 * @Author ycj
 * @Date 2021-02-01 8:41
 * @Version 1.0
 */
@Service
public class DuerosServiceImplements implements DuerosServiceInterface {
    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(DuerosServiceImplements.class);

    private static final String APP_ID = "H6tnLlSbcUk3XEW2EwTxGPuHXihC1NwZ";
    private static final String APP_SECRET = "H9i0ZVOszf02EPwOSZk1oGGxXVCGi8w3";
    private static final String BRAND_ID = "30";
    private static final String WELCOME_METHOD = "welcome";

    @Autowired
    PaasVoiceRoomRelationMapper roomRelationMapper;

    @Autowired
    PaasRoomGuestMapper roomGuestMapper;

    @Autowired
    PaasProjectInfoMapper projectInfoMapper;

    public String getToken() throws AppException {
        try {
            String url = "https://dueros.baidu.com/business/oauth/v2/token?grant_type=client_credentials&app_id=" + APP_ID
                    + "&app_secret=" + APP_SECRET;
            String response = HttpUtil.get(url, null, null);
            logger.error("【DuerosmessageService.getToken】小度获取token接口返回信息：" + response);
            Map<String, Object> responseMap = new Gson().fromJson(response, Map.class);
            if ("success".equals(responseMap.get("errmsg"))) {
                return ((Map) responseMap.get("data")).get("access_token").toString();
            } else {
                throw new AppException(SysErrorCode.SYSTEM_ERROR);
            }
        } catch (AppException e) {
            logger.error("【DuerosServiceImplements.getToken】获取token出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【DuerosServiceImplements.getToken】获取token出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity handleMessage(String cuid, String method, String outputSpeechText, String cardContent) throws AppException {
        try {
            Map<String, Object> paramJson = new HashMap<>(5);
            paramJson.put("method", method);
            paramJson.put("outputSpeechText", outputSpeechText);
            paramJson.put("cuid", cuid);
            paramJson.put("cardContent", cardContent);

            String postURL = "https://dueros.baidu.com/business/open/restful";
            PostMethod postMethod = new PostMethod(postURL);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            postMethod.setRequestHeader("Authorization", "Bearer " + getToken());
            //参数设置，需要注意的就是里边不能传NULL，要传空字符串
            NameValuePair[] data = {
                    new NameValuePair("cuid", cuid),
                    new NameValuePair("timestamp", "" + System.currentTimeMillis() / 1000),
                    new NameValuePair("v", "2.0"),
                    new NameValuePair("source", "baidu"),
                    new NameValuePair("paramJson", JSONObject.toJSONString(paramJson))
            };

            postMethod.setRequestBody(data);
            org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
            httpClient.executeMethod(postMethod); // 执行POST方法
            String response = postMethod.getResponseBodyAsString();

            Map<String, Object> responseMap = new Gson().fromJson(response, Map.class);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, responseMap);
        } catch (Exception e) {
            logger.error("【DuerosServiceImplements.handleMessage】小度消息处理出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR.getError_code(), "小度消息处理出错", "小度消息处理出错");
        }
    }

    @Override
    public ApiResponseEntity sendRoomWelcomeMessage(String roomId) throws AppException {
        try {
            PaasVoiceRoomRelationExample roomRelationExample = new PaasVoiceRoomRelationExample();
            roomRelationExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES)
                    .andRoomIdEqualTo(roomId).andBrandIdEqualTo(BRAND_ID);
            List<PaasVoiceRoomRelation> roomRelations = roomRelationMapper.selectByExample(roomRelationExample);
            if (roomRelations == null || roomRelations.size() < 1) {
//                throw new AppException(SysErrorCode.SYSTEM_ERROR.getError_code(), "该房间没有小度设备", "该房间没有小度设备");
                return new ApiResponseEntity(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "该房间没有小度设备");
            }
            PaasVoiceRoomRelation roomRelation = roomRelations.get(0);

            PaasRoomGuestExample roomGuestExample = new PaasRoomGuestExample();
            roomGuestExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andRoomIdEqualTo(roomId);
            List<PaasRoomGuest> roomGuests = roomGuestMapper.selectByExample(roomGuestExample);
            if (roomGuests == null || roomGuests.size() < 1) {
//                throw new AppException(SysErrorCode.SYSTEM_ERROR.getError_code(), "该房间没有入住客人", "该房间没有入住客人");
                return new ApiResponseEntity(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "该房间没有入住客人");
            }
            PaasRoomGuest guest = roomGuests.get(0);

            PaasProjectInfoExample projectInfoExample = new PaasProjectInfoExample();
            projectInfoExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andProjectIdEqualTo(guest.getProjectId());
            List<PaasProjectInfo> projectInfos = projectInfoMapper.selectByExample(projectInfoExample);
            if (projectInfos == null || projectInfos.size() < 1) {
//                throw new AppException(SysErrorCode.SYSTEM_ERROR.getError_code(), "未查询到对应项目", "未查询到对应项目");
                return new ApiResponseEntity(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "未查询到对应项目");
            }
            PaasProjectInfo projectInfo = projectInfos.get(0);

            String msg = guest.getGuestName() + "，欢迎入住" + projectInfo.getProjectName() + guest.getRoomNo() + "房间";
            return this.handleMessage(roomRelation.getVoiceId(), WELCOME_METHOD, msg, msg);
        } catch (AppException app) {
            logger.error("【DuerosServiceImplements.sendRoomWelcomeMessage】房间小度消息处理出错：" + app);
            throw app;
        } catch (Exception e) {
            logger.error("【DuerosServiceImplements.sendRoomWelcomeMessage】房间小度消息处理出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR.getError_code(), "房间小度消息处理出错", "房间小度消息处理出错");
        }
    }
}
