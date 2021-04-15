package cn.xpms.paas.api.service.business.impl.automotion;

import cn.xpms.paas.api.bean.constant.CustomAutomationTypeEnum;
import cn.xpms.paas.api.bean.dto.automation.CustomAutomationByTemplatePo;
import cn.xpms.paas.api.bean.dto.automation.CustomAutomationPo;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.*;
import cn.xpms.paas.api.service.business.inter.automation.CustomAutomationServiceInterface;
import cn.xpms.paas.api.service.business.inter.scene.ScenesServiceInterface;
import cn.xpms.paas.api.service.business.inter.voice.DuerosServiceInterface;
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
import java.util.Date;
import java.util.List;

/**
 * @ClassName CustomAutomationServiceImplements
 * @Desc
 * @Author ycj
 * @Date 2021-01-12 14:53
 * @Version 1.0
 */
@Service
public class CustomAutomationServiceImplements implements CustomAutomationServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(CustomAutomationServiceImplements.class);

    @Autowired
    PaasCustomAutomationTypeMapper automationTypeMapper;

    @Autowired
    PaasCustomAutomationMapper automationMapper;

    @Autowired
    PaasCustomAutomationConditionMapper conditionMapper;

    @Autowired
    PaasCustomAutomationSceneMapper sceneMapper;

    @Autowired
    PaasSceneInfoMapper sceneInfoMapper;

    @Autowired
    PaasCustomAutomationTemplateMapper templateMapper;

    @Autowired
    PaasCustomAutomationTemplateConditionMapper templateConditionMapper;

    @Autowired
    PaasCustomAutomationTemplateSceneMapper templateSceneMapper;

    @Autowired
    PaasDeviceInfoMapper deviceInfoMapper;

    @Autowired
    PaasCustomAutomationRecordMapper automationRecordMapper;

    @Autowired
    ScenesServiceInterface scenesService;

    @Autowired
    DuerosServiceInterface duerosService;


    @Override
    public ApiResponseEntity getCustomAutomationTypes() throws AppException {
        try {
            PaasCustomAutomationTypeExample example = new PaasCustomAutomationTypeExample();
            example.createCriteria().andValidEqualTo(SystemConstants.INT_YES);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, automationTypeMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.getCustomAutomationTypes】查询自定义自动化类型列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getCustomAutomation(PaasCustomAutomation automation) throws AppException {
        try {
            PaasCustomAutomationExample example = new PaasCustomAutomationExample();
            automation.setValid(SystemConstants.INT_YES);
            BeanUtil.beanToExample(automation, example);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, automationMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.getCustomAutomation】查询自定义自动化列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getCustomAutomationDetail(Integer id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, automationTypeMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.getCustomAutomationDetail】查询自定义自动化详情异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getCustomAutomationConditions(Integer automation_id) throws AppException {
        try {
            PaasCustomAutomationConditionExample example = new PaasCustomAutomationConditionExample();
            example.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automation_id);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, conditionMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.getCustomAutomationTypes】查询自定义自动化条件列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getCustomAutomationScenes(Integer automation_id) throws AppException {
        try {
            PaasCustomAutomationSceneExample example = new PaasCustomAutomationSceneExample();
            example.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automation_id);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, sceneMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.getCustomAutomationTypes】查询自定义自动化场景列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity addCustomAutomation(CustomAutomationPo automationPo) throws AppException {
        try {
            PaasCustomAutomation automation = new PaasCustomAutomation();
            automation.setRoomId(automationPo.getRoom_id());
            automation.setAutomationName(automationPo.getAutomation_name());
            automation.setAutomationTypeId(automationPo.getAutomation_type_id());
            automation.setMatchType(automationPo.getMatch_type());
            automation.setConditionRule(automationPo.getCondition_rule());
            automation.setMemo(automationPo.getMemo());
            automationMapper.insertSelective(automation);

            List<PaasCustomAutomationCondition> conditions = automationPo.getConditions();
            for (PaasCustomAutomationCondition condition : conditions) {
                condition.setAutomationId(automation.getId());
                conditionMapper.insertSelective(condition);
            }

            List<PaasCustomAutomationScene> scenes = automationPo.getScenes();
            for (PaasCustomAutomationScene scene : scenes) {
                scene.setAutomationId(automation.getId());
                sceneMapper.insertSelective(scene);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.addCustomAutomation】添加自定义自动化异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity modifyCustomAutomation(CustomAutomationPo automationPo) throws AppException {
        try {
            if (automationPo.getId() == null) {
                throw new AppException(SysErrorCode.PARAMS_CHECK_ERROR);
            }

            PaasCustomAutomation automation = new PaasCustomAutomation();
            automation.setId(automationPo.getId());
            automation.setRoomId(automationPo.getRoom_id());
            automation.setAutomationName(automationPo.getAutomation_name());
            automation.setAutomationTypeId(automationPo.getAutomation_type_id());
            automation.setMatchType(automationPo.getMatch_type());
            automation.setConditionRule(automationPo.getCondition_rule());
            automation.setMemo(automationPo.getMemo());
            automationMapper.updateByPrimaryKeySelective(automation);

            List<PaasCustomAutomationCondition> conditions = automationPo.getConditions();
            List<String> deviceIds = new ArrayList<>();
            for (PaasCustomAutomationCondition condition : conditions) {
                deviceIds.add(condition.getDeviceId());

                PaasCustomAutomationConditionExample updateConditionExample = new PaasCustomAutomationConditionExample();
                updateConditionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automationPo.getId())
                        .andDeviceIdEqualTo(condition.getDeviceId());
                conditionMapper.updateByExampleSelective(condition, updateConditionExample);
            }
            if (deviceIds.size() > 0) {
                PaasCustomAutomationConditionExample removeConditionExample = new PaasCustomAutomationConditionExample();
                removeConditionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automationPo.getId())
                        .andDeviceIdNotIn(deviceIds);
                PaasCustomAutomationCondition removeCondition = new PaasCustomAutomationCondition();
                removeCondition.setValid(SystemConstants.INT_NO);
                conditionMapper.updateByExampleSelective(removeCondition, removeConditionExample);
            } else {
                PaasCustomAutomationConditionExample removeConditionExample = new PaasCustomAutomationConditionExample();
                removeConditionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automationPo.getId());
                PaasCustomAutomationCondition removeCondition = new PaasCustomAutomationCondition();
                removeCondition.setValid(SystemConstants.INT_NO);
                conditionMapper.updateByExampleSelective(removeCondition, removeConditionExample);
            }

            List<PaasCustomAutomationScene> scenes = automationPo.getScenes();
            List<String> sceneIds = new ArrayList<>();
            for (PaasCustomAutomationScene scene : scenes) {
                sceneIds.add(scene.getSceneId());

                PaasCustomAutomationSceneExample updateSceneExample = new PaasCustomAutomationSceneExample();
                updateSceneExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automationPo.getId())
                        .andSceneIdEqualTo(scene.getSceneId());
                sceneMapper.updateByExampleSelective(scene, updateSceneExample);
            }
            if (sceneIds.size() > 0) {
                PaasCustomAutomationSceneExample removeSceneExample = new PaasCustomAutomationSceneExample();
                removeSceneExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automationPo.getId())
                        .andSceneIdNotIn(sceneIds);
                PaasCustomAutomationScene removeScene = new PaasCustomAutomationScene();
                removeScene.setValid(SystemConstants.INT_NO);
                sceneMapper.updateByExampleSelective(removeScene, removeSceneExample);
            } else {
                PaasCustomAutomationSceneExample removeSceneExample = new PaasCustomAutomationSceneExample();
                removeSceneExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automationPo.getId());
                PaasCustomAutomationScene removeScene = new PaasCustomAutomationScene();
                removeScene.setValid(SystemConstants.INT_NO);
                sceneMapper.updateByExampleSelective(removeScene, removeSceneExample);
            }

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.modifyCustomAutomation】修改自定义自动化异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity deleteCustomAutomation(Integer id) throws AppException {
        try {
            PaasCustomAutomation automation = new PaasCustomAutomation();
            automation.setId(id);
            automation.setValid(SystemConstants.INT_NO);
            automationMapper.updateByPrimaryKeySelective(automation);

            PaasCustomAutomationCondition condition = new PaasCustomAutomationCondition();
            condition.setValid(SystemConstants.INT_NO);
            PaasCustomAutomationConditionExample conditionExample = new PaasCustomAutomationConditionExample();
            conditionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(id);
            conditionMapper.updateByExampleSelective(condition, conditionExample);

            PaasCustomAutomationScene scene = new PaasCustomAutomationScene();
            PaasCustomAutomationSceneExample sceneExample = new PaasCustomAutomationSceneExample();
            sceneExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(id);
            sceneMapper.updateByExampleSelective(scene, sceneExample);

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.deleteCustomAutomation】删除自定义自动化异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity addAutomationByTemplate(CustomAutomationByTemplatePo automationByTemplatePo) throws AppException {
        try {
            if (null == automationByTemplatePo.getTemplateId()) {
                throw new AppException(SysErrorCode.PARAMS_CHECK_ERROR);
            }
            Integer templateId = automationByTemplatePo.getTemplateId();

            PaasCustomAutomationTemplate template = templateMapper.selectByPrimaryKey(templateId);
            if (null == template) {
                throw new AppException(SysErrorCode.PARAMS_CHECK_ERROR.getError_code(), "未查询到对应模板", "未查询到对应模板");
            }
            String automationName = automationByTemplatePo.getAutomation_name();
            if (StringUtils.isBlank(automationName)) {
                automationName = template.getTemplateName();
            }

            // 模板条件
            PaasCustomAutomationTemplateConditionExample templateConditionExample = new PaasCustomAutomationTemplateConditionExample();
            templateConditionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(templateId);
            List<PaasCustomAutomationTemplateCondition> templateConditions = templateConditionMapper.selectByExample(templateConditionExample);

            // 模板场景
            PaasCustomAutomationTemplateSceneExample templateSceneExample = new PaasCustomAutomationTemplateSceneExample();
            templateSceneExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(templateId);
            List<PaasCustomAutomationTemplateScene> templateScenes = templateSceneMapper.selectByExample(templateSceneExample);

            List<CustomAutomationPo> automationPos = new ArrayList<>();

            // 检查房间设备和场景 构建创建自定义自动化数据
            String[] roomIds = automationByTemplatePo.getRoomIds();
            for (String room_id : roomIds) {
                List<PaasCustomAutomationCondition> conditions = new ArrayList<>();
                for (PaasCustomAutomationTemplateCondition templateCondition : templateConditions) {
                    PaasDeviceInfoExample deviceInfoExample = new PaasDeviceInfoExample();
                    deviceInfoExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andRoomIdEqualTo(room_id)
                            .andSceneEqualTo(SystemConstants.INT_YES).andNameEqualTo(templateCondition.getDeviceName());

                    List<PaasDeviceInfo> deviceInfos = deviceInfoMapper.selectByExample(deviceInfoExample);
                    if (deviceInfos == null || deviceInfos.size() < 1) {
                        String msg = "房间" + room_id + "缺少" + templateCondition.getDeviceName() + "设备";
                        throw new AppException(SysErrorCode.SYSTEM_ERROR.getError_code(), msg, msg);
                    }

                    PaasDeviceInfo deviceInfo = deviceInfos.get(0);
                    PaasCustomAutomationCondition condition = new PaasCustomAutomationCondition();
                    condition.setDeviceId(deviceInfo.getDeviceId());
                    condition.setDeviceStatusName(templateCondition.getDeviceStatusName());
                    condition.setDeviceStatusType(templateCondition.getDeviceStatusType());
                    condition.setDeviceStatusValue(templateCondition.getDeviceStatusValue());
                    condition.setDeviceStatusCount(templateCondition.getDeviceStatusCount());
                    conditions.add(condition);
                }

                List<PaasCustomAutomationScene> scenes = new ArrayList<>();
                for (PaasCustomAutomationTemplateScene templateScene : templateScenes) {
                    PaasSceneInfoExample sceneInfoExample = new PaasSceneInfoExample();
                    sceneInfoExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andRoomIdEqualTo(room_id)
                            .andSceneNameEqualTo(templateScene.getSceneName());

                    List<PaasSceneInfo> sceneInfoList = sceneInfoMapper.selectByExample(sceneInfoExample);
                    if (sceneInfoList == null || sceneInfoList.size() < 1) {
                        String msg = "房间" + room_id + "缺少" + templateScene.getSceneName() + "场景";
                        throw new AppException(SysErrorCode.SYSTEM_ERROR.getError_code(), msg, msg);
                    }

                    PaasCustomAutomationScene scene = new PaasCustomAutomationScene();
                    scene.setSceneId(sceneInfoList.get(0).getSceneId());
                    scenes.add(scene);
                }

                CustomAutomationPo automationPo = new CustomAutomationPo();
                automationPo.setRoom_id(room_id);
                automationPo.setAutomation_name(automationName);
                automationPo.setAutomation_type_id(template.getAutomationTypeId());
                automationPo.setMatch_type(template.getMatchType());
                automationPo.setCondition_rule(template.getConditionRule());
                automationPo.setConditions(conditions);
                automationPo.setScenes(scenes);

                automationPos.add(automationPo);
            }

            // 创建自定义自动化
            for (CustomAutomationPo automationPo : automationPos) {
                this.addCustomAutomation(automationPo);
            }

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException app) {
            logger.error("【CustomAutomationServiceImplements.addAutomationByTemplate】根据模板添加自定义自动化异常：" + app);
            throw app;
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.deleteCustomAutomation】根据模板添加自定义自动化异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity triggerCustomAutomation(String type, String roomId) throws AppException {
        try {
            // 自定义自动化类型
            PaasCustomAutomationTypeExample typeExample = new PaasCustomAutomationTypeExample();
            typeExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andConditionTypeEqualTo(type);
            List<PaasCustomAutomationType> types = automationTypeMapper.selectByExample(typeExample);
            if (null == types || types.size() < 1) {
                throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "未查询到退房自动化类型", "未查询到退房自动化类型");
            }

            // 自定义自动化
            PaasCustomAutomationExample automationExample = new PaasCustomAutomationExample();
            automationExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES)
                    .andRoomIdEqualTo(roomId).andAutomationTypeIdEqualTo(types.get(0).getId());
            List<PaasCustomAutomation> automationList = automationMapper.selectByExample(automationExample);
            if (null == automationList || automationList.size() < 1) {
                throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "未查询到退房自动化", "未查询到退房自动化");
            }
            PaasCustomAutomation automation = automationList.get(0);

            // 自定义自动化场景
            PaasCustomAutomationSceneExample sceneExample = new PaasCustomAutomationSceneExample();
            sceneExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automation.getId());
            List<PaasCustomAutomationScene> sceneList = sceneMapper.selectByExample(sceneExample);

            // 自定义自动化条件
            PaasCustomAutomationConditionExample conditionExample = new PaasCustomAutomationConditionExample();
            conditionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automation.getId());
            List<PaasCustomAutomationCondition> conditionList = conditionMapper.selectByExample(conditionExample);
            if (null != conditionList && conditionList.size() > 0) {
                if (sceneList != null && sceneList.size() > 0) {
                    for (PaasCustomAutomationCondition condition : conditionList) {
                        PaasCustomAutomationRecord record = new PaasCustomAutomationRecord();
                        record.setAutomationId(automation.getId());
                        record.setRoomId(roomId);
                        record.setDeviceId(condition.getDeviceId());
                        record.setDeviceStatusCode(condition.getDeviceStatusName());
                        record.setDeviceStatusValue(condition.getDeviceStatusValue());
                        record.setDeviceStatusCount(condition.getDeviceStatusCount());
                        record.setStartTime(new Date());
                        record.setSceneId(sceneList.get(0).getSceneId());
                        automationRecordMapper.insertSelective(record);
                    }
                }
                return new ApiResponseEntity(SysErrorCode.SUCCESS.getError_code(), "存在设备触发条件,待条件满足自动触发");
            }

            // 触发场景(退房 无自动化条件)
            for (PaasCustomAutomationScene scene : sceneList) {
                scenesService.trigger(roomId, scene.getSceneId());
            }

            // 移除当前房间未执行的自动化 更新当前房间的自动化条件记录(退房)
            PaasCustomAutomationRecordExample automationRecordExample = new PaasCustomAutomationRecordExample();
            automationRecordExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES)
                    .andRoomIdEqualTo(roomId).andStatusEqualTo(SystemConstants.INT_NO);
            PaasCustomAutomationRecord automationRecord = new PaasCustomAutomationRecord();
            automationRecord.setStatus(SystemConstants.INT_YES);
            automationRecordMapper.updateByExampleSelective(automationRecord, automationRecordExample);

            // 入住小度发送欢迎语
            if (CustomAutomationTypeEnum.CHECK_IN.toString().equals(type)) {
                duerosService.sendRoomWelcomeMessage(roomId);
            }

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException app) {
            logger.error("【CustomAutomationServiceImplements.triggerCustomAutomation】触发自定义自动化异常：" + app);
            throw app;
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.triggerCustomAutomation】触发自定义自动化异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity triggerCustomAutomation(String deviceId, String code, String value) throws AppException {
        try {
            // 设备自动化触发条件记录(未触发记录)
            PaasCustomAutomationRecordExample automationRecordExample = new PaasCustomAutomationRecordExample();
            automationRecordExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES)
                    .andDeviceIdEqualTo(deviceId).andDeviceStatusCodeEqualTo(code)
                    .andDeviceStatusValueEqualTo(value).andStatusEqualTo(SystemConstants.INT_NO);
            List<PaasCustomAutomationRecord> automationRecordList = automationRecordMapper.selectByExample(automationRecordExample);
            if (null != automationRecordList && automationRecordList.size() > 0) {
                for (PaasCustomAutomationRecord record : automationRecordList) {
                    // 设备需触发次数大于当前触发次数 不执行场景跳出
                    if (record.getDeviceStatusCount() - record.getDeviceCount() > 1) {
                        PaasCustomAutomationRecord upRecord = new PaasCustomAutomationRecord();
                        upRecord.setId(record.getId());
                        upRecord.setDeviceCount(record.getDeviceCount() + 1);
                        automationRecordMapper.updateByPrimaryKeySelective(upRecord);
                        continue;
                    } else {
                        // 满足触发场景条件 触发场景
                        scenesService.trigger(record.getRoomId(), record.getSceneId());

                        // 入住小度发送欢迎语
                        PaasCustomAutomation automation = automationMapper.selectByPrimaryKey(record.getAutomationId());
                        if (null != automation) {
                            PaasCustomAutomationType automationType = automationTypeMapper.selectByPrimaryKey(automation.getAutomationTypeId());
                            if (null != automationType &&
                                    CustomAutomationTypeEnum.CHECK_IN.toString().equals(automationType.getConditionType())) {
                                duerosService.sendRoomWelcomeMessage(record.getRoomId());
                            }
                        }

                        PaasCustomAutomationRecord upRecord = new PaasCustomAutomationRecord();
                        upRecord.setId(record.getId());
                        upRecord.setDeviceCount(record.getDeviceStatusCount());
                        upRecord.setStatus(SystemConstants.INT_YES);
                        automationRecordMapper.updateByPrimaryKeySelective(upRecord);
                    }
                }
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException app) {
            logger.error("【CustomAutomationServiceImplements.triggerCustomAutomation】触发自定义自动化异常：" + app);
            throw app;
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.triggerCustomAutomation】触发自定义自动化异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
