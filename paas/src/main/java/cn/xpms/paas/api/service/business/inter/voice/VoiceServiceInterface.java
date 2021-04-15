package cn.xpms.paas.api.service.business.inter.voice;

import cn.xpms.paas.api.dao.generator.entity.PaasVoiceRoomRelation;
import cn.xpms.paas.api.dao.generator.entity.PaasVoiceThirdPartyAuthorization;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName VoiceServiceInterface
 * @Desc
 * @Author ycj
 * @Date 2020-12-21 11:24
 * @Version 1.0
 */
public interface VoiceServiceInterface {

    /**
     * @Description: 同步语音品牌
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-21 11:31
     */
    ApiResponseEntity synchronizeVoiceBrands() throws AppException;

    /**
     * @Description: 获取语音品牌列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:27
     */
    ApiResponseEntity getVoiceBrands() throws AppException;

    /**
     * @Description: 同步房间语音绑定关系数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:28
     */
    ApiResponseEntity synchronizeRoomRelations(String project_id) throws AppException;

    /**
     * @Description: 获取房间语音设备绑定关系列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:28
     */
    ApiResponseEntity getVoiceRoomRelations(String projectId, String roomId) throws AppException;

    /**
     * @Description: 添加房间语音设备绑定
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:28
     */
    ApiResponseEntity addVoiceRoomRelation(PaasVoiceRoomRelation voiceRoomRelation) throws AppException;

    /**
     * @Description: 修改房间语音设备绑定
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:28
     */
    ApiResponseEntity modifyVoiceRoomRelation(PaasVoiceRoomRelation voiceRoomRelation) throws AppException;

    /**
     * @Description: 删除房间语音设备绑定
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:29
     */
    ApiResponseEntity deleteVoiceRoomRelation(Integer id) throws AppException;

    /**
     * @Description: 添加三方语音设备平台授权
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:29
     */
    ApiResponseEntity thirdPartyAuthorization(PaasVoiceThirdPartyAuthorization thirdPartyAuthorization) throws AppException;

    /**
     * @Description: 删除三方语音平台授权
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:29
     */
    ApiResponseEntity deleteThirdPartyAuthorization(Integer id) throws AppException;

    /**
     * @Description: 获取三方语音平台授权列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:30
     */
    ApiResponseEntity getVoiceThirdPartyAuthorizations(String projectId, String brandId) throws AppException;
}
