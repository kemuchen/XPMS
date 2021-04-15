package cn.xpms.paas.api.service.business.inter.room;

import cn.xpms.paas.api.dao.generator.entity.PaasRoomTemplate;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName RoomTemplateServiceInterface
 * @Desc
 * @Author ycj
 * @Date 2021-01-07 16:16
 * @Version 1.0
 */
public interface RoomTemplateServiceInterface {

    ApiResponseEntity getRoomTemplates(PaasRoomTemplate roomTemplate) throws AppException;

    ApiResponseEntity getRoomTemplate(Integer id) throws AppException;

    ApiResponseEntity addRoomTemplates(PaasRoomTemplate roomTemplate) throws AppException;

    ApiResponseEntity modifyRoomTemplates(PaasRoomTemplate roomTemplate) throws AppException;

    ApiResponseEntity deleteRoomTemplate(Integer id) throws AppException;

    ApiResponseEntity createSceneAndAutomationByTemplate(Integer id, String room_id) throws AppException;

    ApiResponseEntity createSceneAndAutomationByTemplate(Integer id, String[] room_ids) throws AppException;
}
