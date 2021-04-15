package cn.xpms.paas.api.service.business.impl.automotion;

import cn.xpms.paas.api.bean.dto.automation.*;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.*;
import cn.xpms.paas.api.service.business.inter.automation.AutomationTemplateServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName AutomationTemplateServiceImplements
 * @Desc
 * @Author ycj
 * @Date 2020-12-29 15:38
 * @Version 1.0
 */
@Service
public class AutomationTemplateServiceImplements implements AutomationTemplateServiceInterface {


    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(AutomationTemplateServiceImplements.class);

    @Autowired
    PaasAutomationTemplateMapper templateMapper;

    @Autowired
    PaasAutomationTemplateConditionMapper conditionMapper;

    @Autowired
    PaasAutomationTemplateConditionDisplayMapper conditionDisplayMapper;

    @Autowired
    PaasAutomationTemplatePreconditionMapper preconditionMapper;

    @Autowired
    PaasAutomationTemplatePreconditionDisplayMapper preconditionDisplayMapper;

    @Autowired
    PaasAutomationTemplateActionMapper actionMapper;

    @Autowired
    PaasAutomationTemplateActionExecutorMapper actionExecutorMapper;


    /**
     * @Description: 获取自动化模板列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:09
     */
    @Override
    public ApiResponseEntity getAutomationTemplates(PaasAutomationTemplate paasAutomationTemplate) throws AppException {
        try {
            PaasAutomationTemplateExample example = new PaasAutomationTemplateExample();
            paasAutomationTemplate.setValid(SystemConstants.INT_YES);
            BeanUtil.beanToExample(paasAutomationTemplate, example);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, templateMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【AutomationTemplateServiceImplements.getAutomationTemplates】查询自动化模板列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取自动化模板详情
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:10
     */
    @Override
    public ApiResponseEntity getAutomationTemplateDetail(Integer id) throws AppException {
        try {
            PaasAutomationTemplate template = templateMapper.selectByPrimaryKey(id);
            if (null == template) {
                throw new AppException(SysErrorCode.PARAMS_CHECK_ERROR.getError_code(), "未查询到对应模板", "未查询到对应模板");
            }

            Map<String, Object> result = BeanUtil.beanToMap(template, false);

            List<Map<String, Object>> conditions = this.getConditions(id);
            result.put("conditions", conditions);

            List<Map<String, Object>> preconditions = this.getPreconditions(id);
            result.put("preconditions", preconditions);

            List<Map<String, Object>> actions = this.getActions(id);
            result.put("actions", actions);

            return new ApiResponseEntity(SysErrorCode.SUCCESS, result);
        } catch (Exception e) {
            logger.error("【AutomationTemplateServiceImplements.getAutomationTemplates】查询自动化模板列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取自动化模板动作列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:10
     */
    private List<Map<String, Object>> getActions(Integer templateId) {
        List<Map<String, Object>> actions = new ArrayList<>();

        PaasAutomationTemplateActionExample example = new PaasAutomationTemplateActionExample();
        example.createCriteria().andTemplateIdEqualTo(templateId).andValidEqualTo(SystemConstants.INT_YES);

        List<PaasAutomationTemplateAction> list = actionMapper.selectByExample(example);
        for (PaasAutomationTemplateAction templateAction : list) {
            Map<String, Object> action = BeanUtil.beanToMap(templateAction, false);

            Map<String, Object> executorProperty = new HashMap<>();

            PaasAutomationTemplateActionExecutorExample executorExample = new PaasAutomationTemplateActionExecutorExample();
            executorExample.createCriteria().andAutomationActionIdEqualTo(templateAction.getId()).andValidEqualTo(SystemConstants.INT_YES);
            List<PaasAutomationTemplateActionExecutor> executors = actionExecutorMapper.selectByExample(executorExample);
            for (PaasAutomationTemplateActionExecutor executor : executors) {
                executorProperty.put(executor.getKey(), executor.getValue());
            }

            action.put("executorProperty", executorProperty);

            actions.add(action);
        }
        return actions;
    }

    /**
     * @Description: 获取自动化模板动作列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:10
     */
    @Override
    public ApiResponseEntity getAutomationActions(Integer templateId) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, this.getActions(templateId));
        } catch (Exception e) {
            logger.error("【AutomationTemplateServiceImplements.getAutomationConditions】查询自动化模板条件列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取自动化模板条件列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:11
     */
    private List<Map<String, Object>> getConditions(Integer templateId) {
        List<Map<String, Object>> conditions = new ArrayList<>();

        PaasAutomationTemplateConditionExample example = new PaasAutomationTemplateConditionExample();
        example.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andTemplateIdEqualTo(templateId);
        List<PaasAutomationTemplateCondition> conditionList = conditionMapper.selectByExample(example);
        for (PaasAutomationTemplateCondition templateCondition : conditionList) {
            Map<String, Object> condition = BeanUtil.beanToMap(templateCondition, false);

            PaasAutomationTemplateConditionDisplayExample displayExample = new PaasAutomationTemplateConditionDisplayExample();
            displayExample.createCriteria().andAutomationConditionIdEqualTo(templateCondition.getId()).andValidEqualTo(SystemConstants.INT_YES);
            List<PaasAutomationTemplateConditionDisplay> displays = conditionDisplayMapper.selectByExample(displayExample);
            if (displays.size() > 0) {
                condition.put("display", displays.get(0));
            }

            conditions.add(condition);
        }
        return conditions;
    }

    /**
     * @Description: 获取自动化模板条件列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:11
     */
    @Override
    public ApiResponseEntity getAutomationConditions(Integer templateId) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, this.getConditions(templateId));
        } catch (Exception e) {
            logger.error("【AutomationTemplateServiceImplements.getAutomationConditions】查询自动化模板条件列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取自动化模板前置条件列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:12
     */
    private List<Map<String, Object>> getPreconditions(Integer templateId) {
        List<Map<String, Object>> preConditions = new ArrayList<>();

        PaasAutomationTemplatePreconditionExample example = new PaasAutomationTemplatePreconditionExample();
        example.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andTemplateIdEqualTo(templateId);
        List<PaasAutomationTemplatePrecondition> preConditionList = preconditionMapper.selectByExample(example);
        for (PaasAutomationTemplatePrecondition templatePrecondition : preConditionList) {
            Map<String, Object> precondition = BeanUtil.beanToMap(templatePrecondition, false);

            PaasAutomationTemplatePreconditionDisplayExample displayExample = new PaasAutomationTemplatePreconditionDisplayExample();
            displayExample.createCriteria().andAutomationProconditionIdEqualTo(templatePrecondition.getId()).andValidEqualTo(SystemConstants.INT_YES);
            List<PaasAutomationTemplatePreconditionDisplay> displays = preconditionDisplayMapper.selectByExample(displayExample);
            if (displays.size() > 0) {
                precondition.put("display", displays.get(0));
            }

            preConditions.add(precondition);
        }
        return preConditions;
    }

    /**
     * @Description: 获取自动化模板前置条件列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:12
     */
    @Override
    public ApiResponseEntity getAutomationPreconditions(Integer templateId) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, this.getPreconditions(templateId));
        } catch (Exception e) {
            logger.error("【AutomationTemplateServiceImplements.getAutomationPreconditions】查询自动化模板前置条件列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存自动化模板数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:12
     */
    private void saveAutomationTemplate(AutomationTemplatePo automationPo) throws AppException {
        PaasAutomationTemplate template = new PaasAutomationTemplate();
        template.setProjectId(automationPo.getProject_id());
        template.setAutomationName(automationPo.getAutomation_name());
        template.setMatchType(automationPo.getMatch_type());
        template.setConditionRule(automationPo.getCondition_rule());
        template.setMemo(automationPo.getMemo());

        if (automationPo.getId() == null) {
            templateMapper.insertSelective(template);
        } else {
            template.setId(automationPo.getId());
            templateMapper.updateByPrimaryKeySelective(template);
        }
        this.saveCondition(automationPo.getConditions(), template.getId());
        this.savePrecondition(automationPo.getPreconditions(), template.getId());
        this.saveAction(automationPo.getActions(), template.getId());
    }

    /**
     * @Description: 删除本地自动化模板条件
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:13
     */
    private void deleteTemplateConditions(Integer templateId) {
        PaasAutomationTemplateConditionExample conditionExample = new PaasAutomationTemplateConditionExample();
        conditionExample.createCriteria().andTemplateIdEqualTo(templateId).andValidEqualTo(SystemConstants.INT_YES);
        List<PaasAutomationTemplateCondition> conditionList = conditionMapper.selectByExample(conditionExample);
        for (PaasAutomationTemplateCondition condition : conditionList) {
            PaasAutomationTemplateConditionDisplayExample displayExample = new PaasAutomationTemplateConditionDisplayExample();
            displayExample.createCriteria().andAutomationConditionIdEqualTo(condition.getId()).andValidEqualTo(SystemConstants.INT_YES);
            PaasAutomationTemplateConditionDisplay display = new PaasAutomationTemplateConditionDisplay();
            display.setValid(SystemConstants.INT_NO);
            conditionDisplayMapper.updateByExampleSelective(display, displayExample);

            condition.setValid(SystemConstants.INT_NO);
            conditionMapper.updateByPrimaryKeySelective(condition);
        }
    }

    /**
     * @Description: 保存本地自动化条件数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:23
     */
    private void saveCondition(List<TemplateConditionPo> conditions, Integer templateId) {
        this.deleteTemplateConditions(templateId);

        for (TemplateConditionPo conditionPo : conditions) {
            PaasAutomationTemplateCondition condition = new PaasAutomationTemplateCondition();
            condition.setTemplateId(templateId);
            condition.setEntityType(conditionPo.getEntity_type());
            condition.setDeviceName(conditionPo.getDevice_name());
            condition.setOrderNum(conditionPo.getOrder_num());
            condition.setMemo(conditionPo.getMemo());
            conditionMapper.insertSelective(condition);

            ConditionDisplayPo displayPo = conditionPo.getDisplay();

            PaasAutomationTemplateConditionDisplay display = new PaasAutomationTemplateConditionDisplay();
            display.setAutomationConditionId(condition.getId());
            display.setType(displayPo.getType());
            display.setCode(displayPo.getCode());
            display.setOperator(displayPo.getOperator());
            display.setValue(displayPo.getValue().toString());
            conditionDisplayMapper.insertSelective(display);
        }
    }

    /**
     * @Description: 删除本地自动化模板前置条件
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:24
     */
    private void deleteTemplatePreconditions(Integer templateId) {
        PaasAutomationTemplatePreconditionExample preconditionExample = new PaasAutomationTemplatePreconditionExample();
        preconditionExample.createCriteria().andTemplateIdEqualTo(templateId).andValidEqualTo(SystemConstants.INT_YES);
        List<PaasAutomationTemplatePrecondition> preconditionList = preconditionMapper.selectByExample(preconditionExample);
        for (PaasAutomationTemplatePrecondition precondition : preconditionList) {
            PaasAutomationTemplatePreconditionDisplayExample preconditionDisplayExample = new PaasAutomationTemplatePreconditionDisplayExample();
            preconditionDisplayExample.createCriteria().andAutomationProconditionIdEqualTo(precondition.getId()).
                    andValidEqualTo(SystemConstants.INT_YES);
            PaasAutomationTemplatePreconditionDisplay preconditionDisplay = new PaasAutomationTemplatePreconditionDisplay();
            preconditionDisplay.setValid(SystemConstants.INT_NO);
            preconditionDisplayMapper.updateByExampleSelective(preconditionDisplay, preconditionDisplayExample);

            precondition.setValid(SystemConstants.INT_NO);
            preconditionMapper.updateByPrimaryKeySelective(precondition);
        }
    }

    /**
     * @Description: 保存本地自动化前置条件数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:15
     */
    private void savePrecondition(List<PreconditionPo> preconditionPos, Integer templateId) {
        this.deleteTemplatePreconditions(templateId);
        for (PreconditionPo preconditionPo : preconditionPos) {
            PaasAutomationTemplatePrecondition condition = new PaasAutomationTemplatePrecondition();
            condition.setTemplateId(templateId);
            condition.setCondType(preconditionPo.getCond_type());
            condition.setMemo(preconditionPo.getMemo());
            preconditionMapper.insertSelective(condition);

            Calendar calendar = Calendar.getInstance();
            PreconditionDisplayPo displayPo = preconditionPo.getDisplay();
            PaasAutomationTemplatePreconditionDisplay display = new PaasAutomationTemplatePreconditionDisplay();
            display.setAutomationProconditionId(condition.getId());
            calendar.set(Calendar.HOUR, Integer.parseInt(displayPo.getStart().split(":")[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(displayPo.getStart().split(":")[1]));
            calendar.set(Calendar.SECOND, 0);
            display.setStart(calendar.getTime());
            calendar.set(Calendar.HOUR, Integer.parseInt(displayPo.getEnd().split(":")[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(displayPo.getEnd().split(":")[1]));
            calendar.set(Calendar.SECOND, 0);
            display.setEnd(calendar.getTime());
            display.setLoops(displayPo.getLoops());
            display.setTimezoneId(displayPo.getTimezone_id());
            preconditionDisplayMapper.insertSelective(display);
        }
    }

    /**
     * @Description: 删除本地自动化模板动作
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:26
     */
    private void deleteTemplateActions(Integer templateId) {
        PaasAutomationTemplateActionExample actionExample = new PaasAutomationTemplateActionExample();
        actionExample.createCriteria().andTemplateIdEqualTo(templateId).andValidEqualTo(SystemConstants.INT_YES);
        List<PaasAutomationTemplateAction> actionList = actionMapper.selectByExample(actionExample);
        for (PaasAutomationTemplateAction action : actionList) {
            PaasAutomationTemplateActionExecutorExample actionExecutorPropertyExample = new PaasAutomationTemplateActionExecutorExample();
            actionExecutorPropertyExample.createCriteria().andAutomationActionIdEqualTo(action.getId()).
                    andValidEqualTo(SystemConstants.INT_YES);
            PaasAutomationTemplateActionExecutor actionExecutorProperty = new PaasAutomationTemplateActionExecutor();
            actionExecutorProperty.setValid(SystemConstants.INT_NO);
            actionExecutorMapper.updateByExampleSelective(actionExecutorProperty, actionExecutorPropertyExample);

            action.setValid(SystemConstants.INT_NO);
            actionMapper.updateByPrimaryKeySelective(action);
        }
    }

    /**
     * @Description: 保存本地自动化模板动作数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:27
     */
    private void saveAction(List<TemplateActionPo> actions, Integer templateId) throws AppException {
        this.deleteTemplateActions(templateId);
        for (TemplateActionPo actionPo : actions) {
            PaasAutomationTemplateAction action = new PaasAutomationTemplateAction();
            action.setTemplateId(templateId);
            action.setDeviceName(actionPo.getDevice_name());
            action.setActionExecutor(actionPo.getAction_executor());
            action.setMemo(actionPo.getMemo());
            actionMapper.insertSelective(action);

            if (null != actionPo.getExecutor_property()) {
                JSONObject properties = actionPo.getExecutor_property();
                for (String key : properties.keySet()) {
                    PaasAutomationTemplateActionExecutor property = new PaasAutomationTemplateActionExecutor();
                    property.setAutomationActionId(action.getId());
                    property.setKey(key);
                    if (properties.get(key) instanceof Boolean) {
                        property.setType("Boolean");
                    } else if (properties.get(key) instanceof Integer) {
                        property.setType("Integer");
                    } else {
                        property.setType("String");
                    }
                    property.setValue(properties.getString(key));
                    actionExecutorMapper.insertSelective(property);
                }
            }
        }
    }


    /**
     * @Description: 添加自动化模板
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:28
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = AppException.class)
    @Override
    public ApiResponseEntity addAutomationTemplate(AutomationTemplatePo templatePo) throws AppException {
        try {
            this.saveAutomationTemplate(templatePo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【AutomationTemplateServiceImplements.addAutomationTemplate】添加自动化模板异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修改自动化模板
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:29
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = AppException.class)
    @Override
    public ApiResponseEntity modifyAutomationTemplate(AutomationTemplatePo templatePo) throws AppException {
        try {
            this.saveAutomationTemplate(templatePo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【AutomationTemplateServiceImplements.modifyAutomationTemplate】修改自动化模板异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除自动化模板
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:29
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = AppException.class)
    @Override
    public ApiResponseEntity deleteAutomationTemplate(Integer id) throws AppException {
        try {
            // 删除模板条件
            this.deleteTemplateConditions(id);
            // 删除模板前置条件
            this.deleteTemplatePreconditions(id);
            // 删除模板动作
            this.deleteTemplateActions(id);

            // 删除模板
            PaasAutomationTemplate template = new PaasAutomationTemplate();
            template.setId(id);
            template.setValid(SystemConstants.INT_NO);
            templateMapper.updateByPrimaryKeySelective(template);

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【AutomationTemplateServiceImplements.deleteAutomationTemplate】删除自动化模板异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
