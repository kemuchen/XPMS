package cn.xpms.paas.api.service.business.impl.voice;

import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.dao.customize.repository.CustomizeVoiceMapper;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.PaasVoiceBrandMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasVoiceRoomRelationMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasVoiceThirdPartyAuthorizationMapper;
import cn.xpms.paas.api.service.business.inter.voice.VoiceServiceInterface;
import cn.xpms.paas.api.service.common.inter.api.PaasApiServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName VoiceServiceImplements
 * @Desc
 * @Author ycj
 * @Date 2020-12-21 11:24
 * @Version 1.0
 */
@Service
public class VoiceServiceImplements implements VoiceServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(VoiceServiceImplements.class);

    /**
     * 涂鸦API接口service
     */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    @Autowired
    PaasVoiceBrandMapper paasVoiceBrandMapper;

    @Autowired
    PaasVoiceRoomRelationMapper paasVoiceRoomRelationMapper;

    @Autowired
    PaasVoiceThirdPartyAuthorizationMapper paasVoiceThirdPartyAuthorizationMapper;

    @Autowired
    CustomizeVoiceMapper customizeVoiceMapper;

    /**
     * @Description: 同步语音品牌
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-21 11:31
     */
    @Override
    public ApiResponseEntity synchronizeVoiceBrands() throws AppException {
        try {
            // 调用涂鸦接口查询语音品牌
            PaasApiResponseEntity<List<Map<String, Object>>> responseEntity = paasApiServiceInterface.call(PaasApiEnum.GET_VOICE_BRAND, null);
            List<Map<String, Object>> voiceBrands = responseEntity.getResult();

            for (Map<String, Object> brand : voiceBrands) {
                PaasVoiceBrand voiceBrand = new PaasVoiceBrand();
                voiceBrand.setBrandId(brand.get("brand_id").toString());
                voiceBrand.setBrandName(brand.get("brand_name").toString());
                PaasVoiceBrandExample example = new PaasVoiceBrandExample();

                example.createCriteria().andBrandIdEqualTo(voiceBrand.getBrandId()).andValidEqualTo(SystemConstants.INT_YES);
                if (paasVoiceBrandMapper.countByExample(example) > 0) {
                    paasVoiceBrandMapper.updateByExampleSelective(voiceBrand, example);
                } else {
                    paasVoiceBrandMapper.insertSelective(voiceBrand);
                }
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【VoiceServiceImplements.synchronizeVoiceBrands】同步语音品牌异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【VoiceServiceImplements.synchronizeVoiceBrands】同步语音品牌异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取语音品牌列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:30
     */
    @Override
    public ApiResponseEntity getVoiceBrands() throws AppException {
        try {
            PaasVoiceBrandExample example = new PaasVoiceBrandExample();
            example.createCriteria().andValidEqualTo(SystemConstants.INT_YES);
            List<PaasVoiceBrand> brands = paasVoiceBrandMapper.selectByExample(example);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, brands);
        } catch (Exception e) {
            logger.error("【VoiceServiceImplements.getVoiceBrands】获取语音品牌异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步房间语音绑定关系数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:30
     */
    @Override
    public ApiResponseEntity synchronizeRoomRelations(String project_id) throws AppException {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("project_id", project_id);

            // 调用涂鸦接口查询项目房间语音设备关系
            PaasApiResponseEntity<Map<String, Object>> responseEntity =
                    paasApiServiceInterface.call(PaasApiEnum.GET_PROJECT_VOICE_RELATIONS, params);
            List<Map<String, Object>> relations = (List<Map<String, Object>>) responseEntity.getResult().get("relations");

            for (Map<String, Object> relation : relations) {
                PaasVoiceRoomRelation voiceRoomRelation = new PaasVoiceRoomRelation();
                voiceRoomRelation.setRelationId(relation.get("relation_id").toString());
                voiceRoomRelation.setBrandId(relation.get("brand_id").toString());
                voiceRoomRelation.setRoomId(relation.get("room_id").toString());
                voiceRoomRelation.setVoiceId(relation.get("voice_id").toString());
                PaasVoiceRoomRelationExample example = new PaasVoiceRoomRelationExample();

                example.createCriteria().andRelationIdEqualTo(voiceRoomRelation.getRelationId()).andValidEqualTo(SystemConstants.INT_YES);
                if (paasVoiceRoomRelationMapper.countByExample(example) > 0) {
                    paasVoiceRoomRelationMapper.updateByExampleSelective(voiceRoomRelation, example);
                } else {
                    paasVoiceRoomRelationMapper.insertSelective(voiceRoomRelation);
                }
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【VoiceServiceImplements.synchronizeRoomRelations】同步项目房间语音设备关系异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【VoiceServiceImplements.synchronizeRoomRelations】同步项目房间语音设备关系异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取房间语音设备绑定关系列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:30
     */
    @Override
    public ApiResponseEntity getVoiceRoomRelations(String projectId, String roomId) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, customizeVoiceMapper.getVoiceRoomRelations(projectId, roomId));
        } catch (Exception e) {
            logger.error("【VoiceServiceImplements.getVoiceRoomRelations】获取房间语音设备关系异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存本地房间语音设备关系数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:32
     */
    private void saveRoomRelation(PaasVoiceRoomRelation voiceRoomRelation) throws AppException {
        if (voiceRoomRelation.getId() == null) {
            paasVoiceRoomRelationMapper.insertSelective(voiceRoomRelation);
        } else {
            paasVoiceRoomRelationMapper.updateByPrimaryKeySelective(voiceRoomRelation);
        }
    }

    /**
     * @Description: 添加房间语音设备绑定
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:31
     */
    @Override
    public ApiResponseEntity addVoiceRoomRelation(PaasVoiceRoomRelation voiceRoomRelation) throws AppException {
        try {
            // 调用涂鸦接口添加房间语音设备绑定
            PaasApiResponseEntity<String> responseEntity =
                    paasApiServiceInterface.call(PaasApiEnum.ADD_ROOM_VOICE_BIND, BeanUtil.beanToMap(voiceRoomRelation, true));

            if (responseEntity.getSuccess()) {
                voiceRoomRelation.setRelationId(responseEntity.getResult());
                this.saveRoomRelation(voiceRoomRelation);
                return new ApiResponseEntity(SysErrorCode.SUCCESS, "新增房间语音设备绑定成功");
            } else {
                return new ApiResponseEntity(SysErrorCode.SYSTEM_ERROR.getError_code(), responseEntity.getMsg());
            }
        } catch (AppException e) {
            logger.error("【VoiceServiceImplements.addVoiceRoomRelation】新增房间语音设备绑定成功异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【VoiceServiceImplements.addVoiceRoomRelation】新增房间语音设备绑定成功异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修改房间语音设备绑定
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:31
     */
    @Override
    public ApiResponseEntity modifyVoiceRoomRelation(PaasVoiceRoomRelation voiceRoomRelation) throws AppException {
        try {
            // 调用涂鸦接口修改房间语音设备绑定
            PaasApiResponseEntity<Boolean> responseEntity =
                    paasApiServiceInterface.call(PaasApiEnum.MODIFY_ROOM_VOICE_BIND, BeanUtil.beanToMap(voiceRoomRelation, true));

            if (responseEntity.getResult()) {
                this.saveRoomRelation(voiceRoomRelation);
                return new ApiResponseEntity(SysErrorCode.SUCCESS, "修改房间语音设备绑定成功");
            } else {
                return new ApiResponseEntity(SysErrorCode.SYSTEM_ERROR.getError_code(), responseEntity.getMsg());
            }
        } catch (AppException e) {
            logger.error("【VoiceServiceImplements.modifyVoiceRoomRelation】修改房间语音设备绑定成功异常:" + e);
            throw e;
        } catch (
                Exception e) {
            logger.error("【VoiceServiceImplements.modifyVoiceRoomRelation】修改房间语音设备绑定成功异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }

    }

    /**
     * @Description: 删除房间语音设备绑定
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:31
     */
    @Override
    public ApiResponseEntity deleteVoiceRoomRelation(Integer id) throws AppException {
        try {
            PaasVoiceRoomRelation voiceRoomRelation = paasVoiceRoomRelationMapper.selectByPrimaryKey(id);

            if (voiceRoomRelation == null) {
                return new ApiResponseEntity(SysErrorCode.PARAMS_CHECK_ERROR, "未查询到对应绑定关系");
            }

            Map<String, Object> params = new HashMap<>();
            params.put("room_id", voiceRoomRelation.getRoomId());
            params.put("relation_id", voiceRoomRelation.getRelationId());
            // 调用涂鸦接口删除房间语音设备绑定
            PaasApiResponseEntity<Boolean> responseEntity = paasApiServiceInterface.call(PaasApiEnum.DELETE_ROOM_VOICE_BIND, params);

            if (responseEntity.getResult()) {
                PaasVoiceRoomRelation record = new PaasVoiceRoomRelation();
                record.setId(voiceRoomRelation.getId());
                record.setValid(SystemConstants.INT_NO);
                this.saveRoomRelation(record);
                return new ApiResponseEntity(SysErrorCode.SUCCESS, "删除房间语音设备绑定成功");
            } else {
                return new ApiResponseEntity(SysErrorCode.SYSTEM_ERROR, responseEntity.getMsg());
            }
        } catch (AppException e) {
            logger.error("【VoiceServiceImplements.deleteVoiceRoomRelation】删除房间语音设备绑定成功异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【VoiceServiceImplements.deleteVoiceRoomRelation】删除房间语音设备绑定成功异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 添加三方语音设备平台授权
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:31
     */
    @Override
    public ApiResponseEntity thirdPartyAuthorization(PaasVoiceThirdPartyAuthorization thirdPartyAuthorization) throws AppException {
        try {
            // 调用涂鸦接口设置三方语音开放平台授权凭证
            PaasApiResponseEntity<Boolean> responseEntity =
                    paasApiServiceInterface.call(PaasApiEnum.ADD_THIRD_PARTY_VOICE_AUTHORIZATION, BeanUtil.beanToMap(thirdPartyAuthorization, true));

            if (responseEntity.getResult()) {
                paasVoiceThirdPartyAuthorizationMapper.insertSelective(thirdPartyAuthorization);
                return new ApiResponseEntity(SysErrorCode.SUCCESS, "设置三方语音开放平台授权凭证成功");
            } else {
                return new ApiResponseEntity(SysErrorCode.SUCCESS, responseEntity.getMsg());
            }
        } catch (AppException e) {
            logger.error("【VoiceServiceImplements.thirdPartyAuthorization】设置三方语音开放平台授权凭证异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【VoiceServiceImplements.thirdPartyAuthorization】设置三方语音开放平台授权凭证异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除三方语音平台授权
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:31
     */
    @Override
    public ApiResponseEntity deleteThirdPartyAuthorization(Integer id) throws AppException {
        try {
            PaasVoiceThirdPartyAuthorization thirdPartyAuthorization = paasVoiceThirdPartyAuthorizationMapper.selectByPrimaryKey(id);

            if (thirdPartyAuthorization == null) {
                return new ApiResponseEntity(SysErrorCode.PARAMS_CHECK_ERROR, "未查询到对应授权凭证");
            }

            Map<String, Object> params = new HashMap<>();
            params.put("project_id", thirdPartyAuthorization.getProjectId());
            params.put("brand_id", thirdPartyAuthorization.getBrandId());
            // 调用涂鸦接口清除三方语音开放平台授权凭证
            PaasApiResponseEntity<Boolean> responseEntity = paasApiServiceInterface.call(PaasApiEnum.DELETE_THIRD_PARTY_VOICE_AUTHORIZATION, params);

            if (responseEntity.getResult()) {
                PaasVoiceThirdPartyAuthorization record = new PaasVoiceThirdPartyAuthorization();
                record.setId(thirdPartyAuthorization.getId());
                record.setValid(SystemConstants.INT_NO);
                paasVoiceThirdPartyAuthorizationMapper.updateByPrimaryKeySelective(record);
                return new ApiResponseEntity(SysErrorCode.SUCCESS, "清除三方语音开放平台授权凭证成功");
            } else {
                return new ApiResponseEntity(SysErrorCode.SYSTEM_ERROR, responseEntity.getMsg());
            }
        } catch (AppException e) {
            logger.error("【VoiceServiceImplements.deleteThirdPartyAuthorization】清除三方语音开放平台授权凭证异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【VoiceServiceImplements.deleteThirdPartyAuthorization】清除三方语音开放平台授权凭证异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取三方语音平台授权列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:32
     */
    @Override
    public ApiResponseEntity getVoiceThirdPartyAuthorizations(String projectId, String brandId) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, customizeVoiceMapper.getVoiceThirdPartyAuth(projectId, brandId));
        } catch (Exception e) {
            logger.error("【VoiceServiceImplements.getVoiceThirdPartyAuthorizations】获取三方语音平台授权列表异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
