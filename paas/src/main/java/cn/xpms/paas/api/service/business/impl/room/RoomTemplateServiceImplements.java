package cn.xpms.paas.api.service.business.impl.room;

import cn.xpms.paas.api.bean.dto.automation.AutomationPo;
import cn.xpms.paas.api.bean.dto.scene.ScenePo;
import cn.xpms.paas.api.dao.generator.entity.PaasRoomTemplate;
import cn.xpms.paas.api.dao.generator.entity.PaasRoomTemplateExample;
import cn.xpms.paas.api.dao.generator.repository.PaasRoomTemplateMapper;
import cn.xpms.paas.api.service.business.inter.automation.AutomationServiceInterface;
import cn.xpms.paas.api.service.business.inter.room.RoomTemplateServiceInterface;
import cn.xpms.paas.api.service.business.inter.scene.ScenesServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RoomTemplateServiceImplements
 * @Desc
 * @Author ycj
 * @Date 2021-01-07 16:16
 * @Version 1.0
 */
@Service
public class RoomTemplateServiceImplements implements RoomTemplateServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(RoomTemplateServiceImplements.class);

    @Autowired
    PaasRoomTemplateMapper roomTemplateMapper;

    @Autowired
    ScenesServiceInterface scenesService;

    @Autowired
    AutomationServiceInterface automationService;

    @Override
    public ApiResponseEntity getRoomTemplates(PaasRoomTemplate roomTemplate) throws AppException {
        try {
            PaasRoomTemplateExample example = new PaasRoomTemplateExample();
            roomTemplate.setValid(SystemConstants.INT_YES);
            BeanUtil.beanToExample(roomTemplate, example);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, roomTemplateMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【RoomTemplateServiceImplements.getRoomTemplates】获取房间模板列表异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getRoomTemplate(Integer id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, roomTemplateMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("【RoomTemplateServiceImplements.getRoomTemplate】获取房间模板异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity addRoomTemplates(PaasRoomTemplate roomTemplate) throws AppException {
        try {
            roomTemplateMapper.insertSelective(roomTemplate);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【RoomTemplateServiceImplements.addRoomTemplates】添加房间模板异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity modifyRoomTemplates(PaasRoomTemplate roomTemplate) throws AppException {
        try {
            if (roomTemplate.getId() == null) {
                throw new AppException(SysErrorCode.PARAMS_CHECK_ERROR);
            }
            roomTemplateMapper.updateByPrimaryKeySelective(roomTemplate);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【RoomTemplateServiceImplements.modifyRoomTemplates】修改房间模板异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity deleteRoomTemplate(Integer id) throws AppException {
        try {
            PaasRoomTemplate roomTemplate = new PaasRoomTemplate();
            roomTemplate.setId(id);
            roomTemplate.setValid(SystemConstants.INT_NO);
            roomTemplateMapper.updateByPrimaryKeySelective(roomTemplate);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【RoomTemplateServiceImplements.deleteRoomTemplate】删除房间模板异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity createSceneAndAutomationByTemplate(Integer id, String room_id) throws AppException {
        try {
            PaasRoomTemplate template = roomTemplateMapper.selectByPrimaryKey(id);
            if (template == null) {
                throw new AppException(SysErrorCode.PARAMS_CHECK_ERROR);
            }

            // 获取需要添加的场景参数集合(做设备校验)
            List<ScenePo> scenePos = new ArrayList<>();
            if (StringUtils.isNotBlank(template.getSceneTemplateIds())) {
                String[] sceneTemplateArr = template.getSceneTemplateIds().split(",");
                for (String sceneTemplate : sceneTemplateArr) {
                    if (StringUtils.isNotBlank(sceneTemplate)) {
                        Integer sceneTemplateId = Integer.parseInt(sceneTemplate);
                        ScenePo scenePo = scenesService.checkSceneTemplateDevice(room_id, sceneTemplateId, null);
                        scenePos.add(scenePo);
                    }
                }
            }

            // 获取需要添加的自动化参数集合(做设备校验)
            List<AutomationPo> automationPos = new ArrayList<>();
            if (StringUtils.isNotBlank(template.getAutomationTemplateIds())) {
                String[] automationTemplateArr = template.getAutomationTemplateIds().split(",");
                for (String automationTemplate : automationTemplateArr) {
                    if (StringUtils.isNotBlank(automationTemplate)) {
                        Integer automationTemplateId = Integer.parseInt(automationTemplate);
                        AutomationPo automationPo = automationService.checkAutomationTemplateDevice(room_id, automationTemplateId, null);
                        automationPos.add(automationPo);
                    }
                }
            }

            // 添加场景
            for (ScenePo scenePo : scenePos) {
                scenesService.addScene(scenePo);
            }

            // 添加自动化
            for (AutomationPo automationPo : automationPos) {
                automationService.addAutomation(automationPo);
            }

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException app) {
            logger.error("【RoomTemplateServiceImplements.createSceneAndAutomationByTemplate】根据房间模板创建房间场景及自动化异常:" + app);
            throw app;
        } catch (Exception e) {
            logger.error("【RoomTemplateServiceImplements.createSceneAndAutomationByTemplate】根据房间模板创建房间场景及自动化异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity createSceneAndAutomationByTemplate(Integer id, String[] room_ids) throws AppException {
        try {
            if (null == id || room_ids.length < 1) {
                throw new AppException(SysErrorCode.PARAMS_CHECK_ERROR);
            }

            for (String room_id : room_ids) {
                this.createSceneAndAutomationByTemplate(id, room_id);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException app) {
            logger.error("【RoomTemplateServiceImplements.createSceneAndAutomationByTemplate】根据房间模板创建房间场景及自动化异常:" + app);
            throw app;
        } catch (Exception e) {
            logger.error("【RoomTemplateServiceImplements.createSceneAndAutomationByTemplate】根据房间模板创建房间场景及自动化异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
