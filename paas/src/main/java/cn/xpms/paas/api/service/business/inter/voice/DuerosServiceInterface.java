package cn.xpms.paas.api.service.business.inter.voice;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName DuerosServiceInterface
 * @Desc
 * @Author ycj
 * @Date 2021-02-01 8:40
 * @Version 1.0
 */
public interface DuerosServiceInterface {

    String getToken() throws AppException;

    ApiResponseEntity handleMessage(String cuid, String method, String outputSpeechText, String cardContent) throws AppException;

    ApiResponseEntity sendRoomWelcomeMessage(String roomId) throws AppException;
}
