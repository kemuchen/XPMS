package cn.xpms.paas.api.service.business.impl.automotion;

import cn.xpms.paas.api.bean.dto.automation.CustomAutomationTemplatePo;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.PaasCustomAutomationTemplateConditionMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasCustomAutomationTemplateMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasCustomAutomationTemplateSceneMapper;
import cn.xpms.paas.api.service.business.inter.automation.CustomAutomationTemplateServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CustomAutomationTemplateServiceImplements
 * @Desc
 * @Author ycj
 * @Date 2021-01-12 16:35
 * @Version 1.0
 */
@Service
public class CustomAutomationTemplateServiceImplements implements CustomAutomationTemplateServiceInterface {
    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(CustomAutomationServiceImplements.class);

    @Autowired
    PaasCustomAutomationTemplateMapper templateMapper;

    @Autowired
    PaasCustomAutomationTemplateConditionMapper conditionMapper;

    @Autowired
    PaasCustomAutomationTemplateSceneMapper sceneMapper;

    @Override
    public ApiResponseEntity getCustomAutomationTemplates(PaasCustomAutomationTemplate template) throws AppException {
        try {
            PaasCustomAutomationTemplateExample example = new PaasCustomAutomationTemplateExample();
            template.setValid(SystemConstants.INT_YES);
            BeanUtil.beanToExample(template, example);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, templateMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【CustomAutomationTemplateServiceImplements.getCustomAutomationTemplates】查询自定义自动化模板列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getCustomAutomationTemplateDetail(Integer id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, templateMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("【CustomAutomationTemplateServiceImplements.getCustomAutomationTemplateDetail】查询自定义自动化模板详情异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getCustomAutomationTemplateConditions(Integer automation_id) throws AppException {
        try {
            PaasCustomAutomationTemplateConditionExample example = new PaasCustomAutomationTemplateConditionExample();
            example.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automation_id);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, conditionMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【CustomAutomationTemplateServiceImplements.getCustomAutomationTemplateConditions】查询自定义自动化模板条件列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getCustomAutomationTemplateScenes(Integer automation_id) throws AppException {
        try {
            PaasCustomAutomationTemplateSceneExample example = new PaasCustomAutomationTemplateSceneExample();
            example.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automation_id);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, sceneMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【CustomAutomationTemplateServiceImplements.getCustomAutomationTemplateScenes】查询自定义自动化模板场景列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity addCustomAutomationTemplate(CustomAutomationTemplatePo automationPo) throws AppException {
        try {
            PaasCustomAutomationTemplateExample existQuery = new PaasCustomAutomationTemplateExample();
            existQuery.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andTemplateNameEqualTo(automationPo.getTemplate_name());
            if (templateMapper.countByExample(existQuery) > 0) {
                return new ApiResponseEntity(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "自定义自动化模板名称重复");
            }

            PaasCustomAutomationTemplate automation = new PaasCustomAutomationTemplate();
            automation.setProjectId(automationPo.getProject_id());
            automation.setRoomId(automationPo.getRoom_id());
            automation.setTemplateName(automationPo.getTemplate_name());
            automation.setAutomationTypeId(automationPo.getAutomation_type_id());
            automation.setMatchType(automationPo.getMatch_type());
            automation.setConditionRule(automationPo.getCondition_rule());
            automation.setMemo(automationPo.getMemo());
            templateMapper.insertSelective(automation);

            List<PaasCustomAutomationTemplateCondition> conditions = automationPo.getConditions();
            for (PaasCustomAutomationTemplateCondition condition : conditions) {
                condition.setAutomationId(automation.getId());
                conditionMapper.insertSelective(condition);
            }

            List<PaasCustomAutomationTemplateScene> scenes = automationPo.getScenes();
            for (PaasCustomAutomationTemplateScene scene : scenes) {
                scene.setAutomationId(automation.getId());
                sceneMapper.insertSelective(scene);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.addCustomAutomation】添加自定义自动化模板异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity modifyCustomAutomationTemplate(CustomAutomationTemplatePo automationPo) throws AppException {
        try {
            if (automationPo.getId() == null) {
                throw new AppException(SysErrorCode.PARAMS_CHECK_ERROR);
            }

            PaasCustomAutomationTemplateExample existQuery = new PaasCustomAutomationTemplateExample();
            existQuery.createCriteria().andValidEqualTo(SystemConstants.INT_YES)
                    .andTemplateNameEqualTo(automationPo.getTemplate_name()).andIdNotEqualTo(automationPo.getId());
            if (templateMapper.countByExample(existQuery) > 0) {
                return new ApiResponseEntity(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "自定义自动化模板名称重复");
            }

            PaasCustomAutomationTemplate automation = new PaasCustomAutomationTemplate();
            automation.setId(automationPo.getId());
            automation.setProjectId(automationPo.getProject_id());
            automation.setRoomId(automationPo.getRoom_id());
            automation.setTemplateName(automationPo.getTemplate_name());
            automation.setAutomationTypeId(automationPo.getAutomation_type_id());
            automation.setMatchType(automationPo.getMatch_type());
            automation.setConditionRule(automationPo.getCondition_rule());
            automation.setMemo(automationPo.getMemo());
            templateMapper.updateByPrimaryKeySelective(automation);

            List<PaasCustomAutomationTemplateCondition> conditions = automationPo.getConditions();
            List<String> deviceNames = new ArrayList<>();
            for (PaasCustomAutomationTemplateCondition condition : conditions) {
                deviceNames.add(condition.getDeviceName());

                PaasCustomAutomationTemplateConditionExample updateConditionExample = new PaasCustomAutomationTemplateConditionExample();
                updateConditionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automationPo.getId())
                        .andDeviceNameEqualTo(condition.getDeviceName());
                if (conditionMapper.countByExample(updateConditionExample) > 0) {
                    conditionMapper.updateByExampleSelective(condition, updateConditionExample);
                } else {
                    condition.setAutomationId(automationPo.getId());
                    conditionMapper.insertSelective(condition);
                }

            }
            if (deviceNames.size() > 0) {
                PaasCustomAutomationTemplateConditionExample removeConditionExample = new PaasCustomAutomationTemplateConditionExample();
                removeConditionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automationPo.getId())
                        .andDeviceNameNotIn(deviceNames);
                PaasCustomAutomationTemplateCondition removeCondition = new PaasCustomAutomationTemplateCondition();
                removeCondition.setValid(SystemConstants.INT_NO);
                conditionMapper.updateByExampleSelective(removeCondition, removeConditionExample);
            } else {
                PaasCustomAutomationTemplateConditionExample removeConditionExample = new PaasCustomAutomationTemplateConditionExample();
                removeConditionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automationPo.getId());
                PaasCustomAutomationTemplateCondition removeCondition = new PaasCustomAutomationTemplateCondition();
                removeCondition.setValid(SystemConstants.INT_NO);
                conditionMapper.updateByExampleSelective(removeCondition, removeConditionExample);
            }


            List<PaasCustomAutomationTemplateScene> scenes = automationPo.getScenes();
            List<String> sceneNames = new ArrayList<>();
            for (PaasCustomAutomationTemplateScene scene : scenes) {
                sceneNames.add(scene.getSceneName());

                PaasCustomAutomationTemplateSceneExample existSceneExample = new PaasCustomAutomationTemplateSceneExample();
                existSceneExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automationPo.getId())
                        .andSceneNameEqualTo(scene.getSceneName());
                if (sceneMapper.countByExample(existSceneExample) > 0) {
                    sceneMapper.updateByExampleSelective(scene, existSceneExample);
                } else {
                    scene.setAutomationId(automationPo.getId());
                    sceneMapper.insertSelective(scene);
                }
            }
            if (sceneNames.size() > 0) {
                PaasCustomAutomationTemplateSceneExample removeSceneExample = new PaasCustomAutomationTemplateSceneExample();
                removeSceneExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automationPo.getId())
                        .andSceneNameNotIn(sceneNames);
                PaasCustomAutomationTemplateScene removeScene = new PaasCustomAutomationTemplateScene();
                removeScene.setValid(SystemConstants.INT_NO);
                sceneMapper.updateByExampleSelective(removeScene, removeSceneExample);
            } else {
                PaasCustomAutomationTemplateSceneExample removeSceneExample = new PaasCustomAutomationTemplateSceneExample();
                removeSceneExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automationPo.getId());
                PaasCustomAutomationTemplateScene removeScene = new PaasCustomAutomationTemplateScene();
                removeScene.setValid(SystemConstants.INT_NO);
                sceneMapper.updateByExampleSelective(removeScene, removeSceneExample);
            }

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.modifyCustomAutomation】修改自定义自动化模板异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity deleteCustomAutomationTemplate(Integer id) throws AppException {
        try {
            PaasCustomAutomationTemplate automationTemplate = new PaasCustomAutomationTemplate();
            automationTemplate.setId(id);
            automationTemplate.setValid(SystemConstants.INT_NO);
            templateMapper.updateByPrimaryKeySelective(automationTemplate);

            PaasCustomAutomationTemplateCondition condition = new PaasCustomAutomationTemplateCondition();
            condition.setValid(SystemConstants.INT_NO);
            PaasCustomAutomationTemplateConditionExample conditionExample = new PaasCustomAutomationTemplateConditionExample();
            conditionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(id);
            conditionMapper.updateByExampleSelective(condition, conditionExample);

            PaasCustomAutomationTemplateScene scene = new PaasCustomAutomationTemplateScene();
            PaasCustomAutomationTemplateSceneExample sceneExample = new PaasCustomAutomationTemplateSceneExample();
            sceneExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(id);
            sceneMapper.updateByExampleSelective(scene, sceneExample);

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【CustomAutomationServiceImplements.deleteCustomAutomation】删除自定义自动化模板异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
