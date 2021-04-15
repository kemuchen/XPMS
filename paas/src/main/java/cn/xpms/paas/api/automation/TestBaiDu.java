package cn.xpms.paas.api.automation;

import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.util.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestBaiDu
 * @Desc
 * @Author 柯雷
 * @Date 2021/1/12 10:38
 * @Version 1.0
 */
public class TestBaiDu {

    static Logger logger = LoggerFactory.getLogger(TestBaiDu.class);

    public static void main(String[] args) throws AppException {
        try {
            Map<String, Object> paramJson = new HashMap<>(5);
            paramJson.put("method", "welcome");
            paramJson.put("outputSpeechText", "李先生，欢迎入住名巢未来酒店1003房间");
            paramJson.put("cuid", "6F191923FE6656AB");
            paramJson.put("cardContent", "李先生，欢迎入住名巢未来酒店1003房间");

            String postURL = "https://dueros.baidu.com/business/open/restful";
            PostMethod postMethod = new PostMethod(postURL) ;
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            postMethod.setRequestHeader("Authorization", "Bearer " + getToken());
            //参数设置，需要注意的就是里边不能传NULL，要传空字符串
            NameValuePair[] data = {
                    new NameValuePair("cuid", "6F191923FE6656AB"),
                    new NameValuePair("timestamp", "" + System.currentTimeMillis() / 1000),
                    new NameValuePair("v", "2.0"),
                    new NameValuePair("source", "baidu"),
                    new NameValuePair("paramJson", JSONObject.toJSONString(paramJson))
            };

            postMethod.setRequestBody(data);
            org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
            httpClient.executeMethod(postMethod); // 执行POST方法
            String response = postMethod.getResponseBodyAsString() ;

            Map<String, Object> responseMap = new Gson().fromJson(response, Map.class);
            System.err.println(response);
            System.err.println(responseMap);
        } catch (Exception e) {
            logger.error("【DuerosmessageService.handleMessage】小度消息处理出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    public static String getToken() throws AppException {
        try {
            String appid = "H6tnLlSbcUk3XEW2EwTxGPuHXihC1NwZ";
            String appsecret = "H9i0ZVOszf02EPwOSZk1oGGxXVCGi8w3";
            String url = "https://dueros.baidu.com/business/oauth/v2/token?grant_type=client_credentials&app_id=" + appid + "&app_secret=" + appsecret;
            String response = HttpUtil.get(url, null, null);
            logger.error("【DuerosmessageService.getToken】小度获取token接口返回信息：" + response);
            Map<String, Object> responseMap = new Gson().fromJson(response, Map.class);
            if ("success".equals(responseMap.get("errmsg"))) {
                return ((Map)responseMap.get("data")).get("access_token").toString();
            } else {
                throw new AppException(SysErrorCode.SYSTEM_ERROR);
            }
        } catch (AppException e) {
            logger.error("【DuerosmessageService.getToken】获取token出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【DuerosmessageService.getToken】获取token出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
