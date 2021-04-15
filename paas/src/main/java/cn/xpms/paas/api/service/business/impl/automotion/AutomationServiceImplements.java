package cn.xpms.paas.api.service.business.impl.automotion;

import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.dto.automation.*;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.*;
import cn.xpms.paas.api.service.business.inter.automation.AutomationServiceInterface;
import cn.xpms.paas.api.service.common.inter.api.PaasApiServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName AutomationServiceImplements
 * @Desc
 * @Author ycj
 * @Date 2020-12-16 10:38
 * @Version 1.0
 */
@Service
public class AutomationServiceImplements implements AutomationServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(AutomationServiceImplements.class);

    /**
     * 涂鸦API接口service
     */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    @Autowired
    PaasAutomationInfoMapper paasAutomationInfoMapper;

    @Autowired
    PaasAutomationConditionMapper paasAutomationConditionMapper;

    @Autowired
    PaasAutomationPreconditionMapper paasAutomationPreconditionMapper;

    @Autowired
    PaasAutomationActionMapper paasAutomationActionMapper;

    @Autowired
    PaasDeviceInfoMapper paasDeviceInfoMapper;

    @Autowired
    PaasAutomationConditionDisplayMapper paasAutomationConditionDisplayMapper;

    @Autowired
    PaasAutomationPreconditionDisplayMapper paasAutomationPreconditionDisplayMapper;

    @Autowired
    PaasAutomationActionExecutorPropertyMapper paasAutomationActionExecutorPropertyMapper;

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

    @Autowired
    PaasAutomationTemplateAutomationMapper automationTemplateAutomationMapper;

    /**
     * @Description: 获取自动化列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:53
     */
    @Override
    public ApiResponseEntity getAutomationInfos(PaasAutomationInfo automationInfo) throws AppException {
        try {
            PaasAutomationInfoExample example = new PaasAutomationInfoExample();
            automationInfo.setValid(SystemConstants.INT_YES);
            BeanUtil.beanToExample(automationInfo, example);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, paasAutomationInfoMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.getAutomationInfos】查询房间自动化列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取自动化详情
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:53
     */
    @Override
    public ApiResponseEntity getAutomationInfo(Integer id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, paasAutomationInfoMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.getAutomationInfo】查询房间自动化详情异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取自动化条件列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:54
     */
    @Override
    public ApiResponseEntity getAutomationConditions(String automation_id) throws AppException {
        try {
            PaasAutomationConditionExample example = new PaasAutomationConditionExample();
            example.createCriteria().andAutomationIdEqualTo(automation_id).andValidEqualTo(SystemConstants.INT_YES);
            List<Map<String, Object>> result = new ArrayList<>();
            List<PaasAutomationCondition> conditionList = paasAutomationConditionMapper.selectByExample(example);
            for (PaasAutomationCondition condition : conditionList) {
                Map<String, Object> conditionObj = JSON.parseObject(JSON.toJSONString(condition));

                PaasAutomationConditionDisplayExample displayExample = new PaasAutomationConditionDisplayExample();
                displayExample.createCriteria().andAutomationConditionIdEqualTo(condition.getId()).andValidEqualTo(SystemConstants.INT_YES);
                List<PaasAutomationConditionDisplay> displays = paasAutomationConditionDisplayMapper.selectByExample(displayExample);
                if (displays.size() > 0) {
                    conditionObj.put("display", displays.get(0));
                }

                result.add(conditionObj);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS, result);
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.getAutomationConditions】查询房间自动化条件列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取自动化前置条件列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:54
     */
    @Override
    public ApiResponseEntity getAutomationPreconditions(String automation_id) throws AppException {
        try {
            PaasAutomationPreconditionExample example = new PaasAutomationPreconditionExample();
            example.createCriteria().andAutomationIdEqualTo(automation_id).andValidEqualTo(SystemConstants.INT_YES);
            List<Map<String, Object>> result = new ArrayList<>();
            List<PaasAutomationPrecondition> conditionList = paasAutomationPreconditionMapper.selectByExample(example);
            for (PaasAutomationPrecondition condition : conditionList) {
                Map<String, Object> conditionObj = JSON.parseObject(JSON.toJSONString(condition));

                PaasAutomationPreconditionDisplayExample displayExample = new PaasAutomationPreconditionDisplayExample();
                displayExample.createCriteria().andAutomationProconditionIdEqualTo(condition.getId()).andValidEqualTo(SystemConstants.INT_YES);
                List<PaasAutomationPreconditionDisplay> displays = paasAutomationPreconditionDisplayMapper.selectByExample(displayExample);
                if (displays.size() > 0) {
                    conditionObj.put("display", displays.get(0));
                }

                result.add(conditionObj);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS, result);
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.getAutomationPreconditions】查询房间自动化前置条件列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取自动化动作列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:54
     */
    @Override
    public ApiResponseEntity getAutomationActions(String automation_id) throws AppException {
        try {
            PaasAutomationActionExample example = new PaasAutomationActionExample();
            example.createCriteria().andAutomationIdEqualTo(automation_id).andValidEqualTo(SystemConstants.INT_YES);

            List<Map<String, Object>> result = new ArrayList<>();
            List<PaasAutomationAction> list = paasAutomationActionMapper.selectByExample(example);
            for (PaasAutomationAction action : list) {
                Map<String, Object> actionObj = JSON.parseObject(JSON.toJSONString(action));

                JSONObject executorProperty = new JSONObject();

                PaasAutomationActionExecutorPropertyExample propertyExample = new PaasAutomationActionExecutorPropertyExample();
                propertyExample.createCriteria().andAutomationActionIdEqualTo(action.getId()).andValidEqualTo(SystemConstants.INT_YES);
                List<PaasAutomationActionExecutorProperty> properties = paasAutomationActionExecutorPropertyMapper.selectByExample(propertyExample);
                for (PaasAutomationActionExecutorProperty property : properties) {
                    executorProperty.put(property.getKey(), property.getValue());
                }
                actionObj.put("executorProperty", executorProperty);

                result.add(actionObj);
            }

            return new ApiResponseEntity(SysErrorCode.SUCCESS, result);
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.getAutomationActions】查询房间自动化动作列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取房间支持自动化的条件设备
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:54
     */
    @Override
    public ApiResponseEntity getAutomationConditionDevices(String room_id) throws AppException {
        try {
            PaasDeviceInfoExample example = new PaasDeviceInfoExample();
            example.createCriteria().andRoomIdEqualTo(room_id).andConditionEqualTo(SystemConstants.INT_YES).
                    andValidEqualTo(SystemConstants.INT_YES);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, paasDeviceInfoMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.getAutomationConditionDevices】查询房间下支持自动化条件的设备列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取房间支持自动化的动作设备
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:54
     */
    @Override
    public ApiResponseEntity getAutomationActionDevices(String room_id) throws AppException {
        try {
            PaasDeviceInfoExample example = new PaasDeviceInfoExample();
            example.createCriteria().andRoomIdEqualTo(room_id).andActionEqualTo(SystemConstants.INT_YES).
                    andValidEqualTo(SystemConstants.INT_YES);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, paasDeviceInfoMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.getAutomationConditionDevices】查询房间下支持自动化动作的设备列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步本地自动化数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:55
     */
    private void synchronizeAutomationInfo(AutomationPo automationPo) throws AppException {
        PaasAutomationInfo automationInfo = new PaasAutomationInfo();
        automationInfo.setAutomationId(automationPo.getAutomation_id());
        automationInfo.setRoomId(automationPo.getRoom_id());
        automationInfo.setAutomationName(automationPo.getAutomation_name());
        automationInfo.setMatchType(automationPo.getMatch_type());
        automationInfo.setConditionRule(automationPo.getCondition_rule());
        automationInfo.setMemo(automationPo.getMemo());

        if (automationPo.getId() == null) {
            paasAutomationInfoMapper.insertSelective(automationInfo);
        } else {
            automationInfo.setId(automationPo.getId());
            paasAutomationInfoMapper.updateByPrimaryKeySelective(automationInfo);
        }
        this.synchronizeCondition(automationPo.getConditions(), automationPo.getAutomation_id());
        this.synchronizePrecondition(automationPo.getPreconditions(), automationPo.getAutomation_id());
        this.synchronizeAction(automationPo.getActions(), automationPo.getAutomation_id());
    }

    /**
     * @Description: 删除本地自动化条件
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:00
     */
    private void deleteConditions(String automationId) {
        PaasAutomationConditionExample conditionExample = new PaasAutomationConditionExample();
        conditionExample.createCriteria().andAutomationIdEqualTo(automationId).andValidEqualTo(SystemConstants.INT_YES);
        List<PaasAutomationCondition> conditionList = paasAutomationConditionMapper.selectByExample(conditionExample);
        for (PaasAutomationCondition condition : conditionList) {
            PaasAutomationConditionDisplayExample conditionDisplayExample = new PaasAutomationConditionDisplayExample();
            conditionDisplayExample.createCriteria().andAutomationConditionIdEqualTo(condition.getId()).andValidEqualTo(SystemConstants.INT_YES);
            PaasAutomationConditionDisplay conditionDisplay = new PaasAutomationConditionDisplay();
            conditionDisplay.setValid(SystemConstants.INT_NO);
            paasAutomationConditionDisplayMapper.updateByExampleSelective(conditionDisplay, conditionDisplayExample);

            condition.setValid(SystemConstants.INT_NO);
            paasAutomationConditionMapper.updateByPrimaryKeySelective(condition);
        }
    }

    /**
     * @Description: 同步本地自动化条件数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:55
     */
    private void synchronizeCondition(List<ConditionPo> conditions, String automationId) {
        this.deleteConditions(automationId);

        for (ConditionPo conditionPo : conditions) {
            PaasAutomationCondition condition = new PaasAutomationCondition();
            condition.setAutomationId(automationId);
            condition.setEntityType(conditionPo.getEntity_type());
            condition.setEntityId(conditionPo.getEntity_id());
            condition.setOrderNum(conditionPo.getOrder_num());
            condition.setMemo(conditionPo.getMemo());
            paasAutomationConditionMapper.insertSelective(condition);

            ConditionDisplayPo displayPo = conditionPo.getDisplay();

            PaasAutomationConditionDisplay display = new PaasAutomationConditionDisplay();
            display.setAutomationConditionId(condition.getId());
            display.setAutomationId(automationId);
            display.setType(displayPo.getType());
            display.setCode(displayPo.getCode());
            display.setOperator(displayPo.getOperator());
            display.setValue(displayPo.getValue().toString());
            paasAutomationConditionDisplayMapper.insertSelective(display);
        }
    }

    /**
     * @Description: 删除本地自动化前置条件
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:15
     */
    private void deletePreconditions(String automationId) {
        PaasAutomationPreconditionExample preconditionExample = new PaasAutomationPreconditionExample();
        preconditionExample.createCriteria().andAutomationIdEqualTo(automationId).andValidEqualTo(SystemConstants.INT_YES);
        List<PaasAutomationPrecondition> preconditionList = paasAutomationPreconditionMapper.selectByExample(preconditionExample);
        for (PaasAutomationPrecondition precondition : preconditionList) {
            PaasAutomationPreconditionDisplayExample preconditionDisplayExample = new PaasAutomationPreconditionDisplayExample();
            preconditionDisplayExample.createCriteria().andAutomationProconditionIdEqualTo(precondition.getId()).
                    andValidEqualTo(SystemConstants.INT_YES);
            PaasAutomationPreconditionDisplay preconditionDisplay = new PaasAutomationPreconditionDisplay();
            preconditionDisplay.setValid(SystemConstants.INT_NO);
            paasAutomationPreconditionDisplayMapper.updateByExampleSelective(preconditionDisplay, preconditionDisplayExample);

            precondition.setValid(SystemConstants.INT_NO);
            paasAutomationPreconditionMapper.updateByPrimaryKeySelective(precondition);
        }
    }

    /**
     * @Description: 同步本地自动化前置条件数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:19
     */
    private void synchronizePrecondition(List<PreconditionPo> preconditionPos, String automationId) {
        this.deletePreconditions(automationId);
        for (PreconditionPo preconditionPo : preconditionPos) {
            PaasAutomationPrecondition condition = new PaasAutomationPrecondition();
            condition.setAutomationId(automationId);
            condition.setCondType(preconditionPo.getCond_type());
            condition.setMemo(preconditionPo.getMemo());
            paasAutomationPreconditionMapper.insertSelective(condition);

            Calendar calendar = Calendar.getInstance();
            PreconditionDisplayPo displayPo = preconditionPo.getDisplay();
            PaasAutomationPreconditionDisplay display = new PaasAutomationPreconditionDisplay();
            display.setAutomationId(automationId);
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
            paasAutomationPreconditionDisplayMapper.insertSelective(display);
        }
    }

    /**
     * @Description: 删除本地自动化动作
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:23
     */
    private void deleteActions(String automationId) {
        PaasAutomationActionExample actionExample = new PaasAutomationActionExample();
        actionExample.createCriteria().andAutomationIdEqualTo(automationId).andValidEqualTo(SystemConstants.INT_YES);
        List<PaasAutomationAction> actionList = paasAutomationActionMapper.selectByExample(actionExample);
        for (PaasAutomationAction action : actionList) {
            PaasAutomationActionExecutorPropertyExample actionExecutorPropertyExample = new PaasAutomationActionExecutorPropertyExample();
            actionExecutorPropertyExample.createCriteria().andAutomationActionIdEqualTo(action.getId()).
                    andValidEqualTo(SystemConstants.INT_YES);
            PaasAutomationActionExecutorProperty actionExecutorProperty = new PaasAutomationActionExecutorProperty();
            actionExecutorProperty.setValid(SystemConstants.INT_NO);
            paasAutomationActionExecutorPropertyMapper.updateByExampleSelective(actionExecutorProperty, actionExecutorPropertyExample);

            action.setValid(SystemConstants.INT_NO);
            paasAutomationActionMapper.updateByPrimaryKeySelective(action);
        }
    }

    /**
     * @Description: 同步本地自动化动作数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:26
     */
    private void synchronizeAction(List<ActionPo> actions, String automationId) throws AppException {
        this.deleteActions(automationId);
        for (ActionPo actionPo : actions) {
            PaasAutomationAction action = new PaasAutomationAction();
            action.setAutomationId(automationId);
            action.setEntityId(actionPo.getEntity_id());
            action.setActionExecutor(actionPo.getAction_executor());
            action.setMemo(actionPo.getMemo());
            paasAutomationActionMapper.insertSelective(action);

            if (null != actionPo.getExecutor_property()) {
                JSONObject properties = actionPo.getExecutor_property();
                for (String key : properties.keySet()) {
                    PaasAutomationActionExecutorProperty property = new PaasAutomationActionExecutorProperty();
                    property.setAutomationActionId(action.getId());
                    property.setKey(key);
                    property.setValue(properties.getString(key));
                    paasAutomationActionExecutorPropertyMapper.insertSelective(property);
                }
            }
        }
    }

    /**
     * @Description: 添加自动化
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:26
     */
    @Override
    public ApiResponseEntity addAutomation(AutomationPo automationPo) throws AppException {
        try {
            // 调用涂鸦接口新增自动化
            PaasApiResponseEntity<String> apiResponseEntity =
                    paasApiServiceInterface.call(PaasApiEnum.ADD_AUTOMATION, BeanUtil.beanToMap(automationPo, false));

            automationPo.setAutomation_id(apiResponseEntity.getResult());
            this.synchronizeAutomationInfo(automationPo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, apiResponseEntity.getResult());
        } catch (AppException e) {
            logger.error("【AutomationServiceImplements.addAutomation】新增自动化异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.addAutomation】新增自动化异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修改自动化
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:27
     */
    @Override
    public ApiResponseEntity modifyAutomation(AutomationPo automationPo) throws AppException {
        try {
            // 调用涂鸦接口修改自动化
            PaasApiResponseEntity<String> apiResponseEntity =
                    paasApiServiceInterface.call(PaasApiEnum.MODIFY_AUTOMATION, BeanUtil.beanToMap(automationPo, false));

            this.synchronizeAutomationInfo(automationPo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【AutomationServiceImplements.addAutomation】新增自动化异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.addAutomation】新增自动化异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除自动化
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:27
     */
    @Override
    public ApiResponseEntity deleteAutomation(Integer id) throws AppException {
        try {
            PaasAutomationInfo automationInfo = paasAutomationInfoMapper.selectByPrimaryKey(id);

            Map<String, Object> params = new HashMap<>();
            params.put("room_id", automationInfo.getRoomId());
            params.put("automation_id", automationInfo.getAutomationId());
            // 调用涂鸦接口删除自动化
            paasApiServiceInterface.call(PaasApiEnum.DELETE_AUTOMATION, params);

            PaasAutomationInfoExample automationInfoExample = new PaasAutomationInfoExample();
            automationInfoExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationIdEqualTo(automationInfo.getAutomationId());
            List<PaasAutomationInfo> automationInfos = paasAutomationInfoMapper.selectByExample(automationInfoExample);
            for (PaasAutomationInfo automation : automationInfos) {
                // 条件
                this.deleteConditions(automation.getAutomationId());

                // 前置条件
                this.deletePreconditions(automation.getAutomationId());

                // 动作
                this.deleteActions(automation.getAutomationId());

                PaasAutomationInfo record = new PaasAutomationInfo();
                record.setId(automation.getId());
                record.setValid(SystemConstants.INT_NO);
                paasAutomationInfoMapper.updateByPrimaryKeySelective(record);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【AutomationServiceImplements.deleteAutomation】删除场景异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.deleteAutomation】删除自动化异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 启用自动化
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:27
     */
    @Override
    public ApiResponseEntity enableAutomation(Integer id) throws AppException {
        try {
            PaasAutomationInfo automationInfo = paasAutomationInfoMapper.selectByPrimaryKey(id);

            Map<String, Object> params = new HashMap<>();
            params.put("room_id", automationInfo.getRoomId());
            params.put("automation_id", automationInfo.getAutomationId());
            // 调用涂鸦接口启用自动化
            paasApiServiceInterface.call(PaasApiEnum.ENABLE_AUTOMATION, params);

            automationInfo.setEnabled(SystemConstants.STR_YES);
            paasAutomationInfoMapper.updateByPrimaryKeySelective(automationInfo);

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【AutomationServiceImplements.enableAutomation】启用自动化异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.enableAutomation】启用自动化异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 停用自动化
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 17:27
     */
    @Override
    public ApiResponseEntity disableAutomation(Integer id) throws AppException {
        try {
            PaasAutomationInfo automationInfo = paasAutomationInfoMapper.selectByPrimaryKey(id);

            Map<String, Object> params = new HashMap<>();
            params.put("room_id", automationInfo.getRoomId());
            params.put("automation_id", automationInfo.getAutomationId());
            // 调用涂鸦接口停用自动化
            paasApiServiceInterface.call(PaasApiEnum.DISABLE_AUTOMATION, params);

            automationInfo.setEnabled(SystemConstants.STR_NO);
            paasAutomationInfoMapper.updateByPrimaryKeySelective(automationInfo);

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【AutomationServiceImplements.disableAutomation】停用自动化异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.disableAutomation】停用自动化异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public AutomationPo checkAutomationTemplateDevice(String roomId, Integer templateId, String automationName) throws AppException {
        try {
            // 场景模板
            PaasAutomationTemplate template = templateMapper.selectByPrimaryKey(templateId);
            if (null == template) {
                throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "未查询到模板", "未查询到模板");
            }
            if (StringUtils.isBlank(automationName)) {
                automationName = template.getAutomationName();
            }

            // 自动化条件
            List<ConditionPo> conditionPos = new ArrayList<>();
            PaasAutomationTemplateConditionExample conditionExample = new PaasAutomationTemplateConditionExample();
            conditionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andTemplateIdEqualTo(templateId);
            List<PaasAutomationTemplateCondition> conditionList = conditionMapper.selectByExample(conditionExample);
            for (PaasAutomationTemplateCondition condition : conditionList) {
                // 房间是否拥有支持自动化对应设备
                PaasDeviceInfoExample deviceInfoExample = new PaasDeviceInfoExample();
                deviceInfoExample.createCriteria().andRoomIdEqualTo(roomId).andConditionEqualTo(SystemConstants.INT_YES).
                        andNameEqualTo(condition.getDeviceName()).andValidEqualTo(SystemConstants.INT_YES);
                List<PaasDeviceInfo> deviceInfos = paasDeviceInfoMapper.selectByExample(deviceInfoExample);
                if (deviceInfos == null || deviceInfos.size() < 1) {
                    String error = "房间缺少" + condition.getDeviceName();
                    throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), error, error);
                }

                ConditionPo conditionPo = new ConditionPo();
                conditionPo.setEntity_id(deviceInfos.get(0).getDeviceId());
                conditionPo.setEntity_type(condition.getEntityType());
                conditionPo.setOrder_num(condition.getOrderNum());
                conditionPo.setMemo("根据模板创建条件");

                PaasAutomationTemplateConditionDisplayExample displayExample = new PaasAutomationTemplateConditionDisplayExample();
                displayExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationConditionIdEqualTo(condition.getId());
                List<PaasAutomationTemplateConditionDisplay> displays = conditionDisplayMapper.selectByExample(displayExample);
                if (displays != null && displays.size() > 0) {
                    PaasAutomationTemplateConditionDisplay display = displays.get(0);
                    ConditionDisplayPo conditionDisplayPo = new ConditionDisplayPo();
                    conditionDisplayPo.setCode(display.getCode());
                    conditionDisplayPo.setOperator(display.getOperator());
                    String type = display.getType();
                    conditionDisplayPo.setType(type);
                    if ("Boolean".equals(type) || "BOOLEAN".equals(type)) {
                        conditionDisplayPo.setValue(Boolean.parseBoolean(display.getValue()));
                    } else if ("Integer".equals(type) || "ENUM".equals(type)) {
                        conditionDisplayPo.setValue(Integer.parseInt(display.getValue()));
                    } else {
                        conditionDisplayPo.setValue(display.getValue());
                    }

                    conditionPo.setDisplay(conditionDisplayPo);
                }
                conditionPos.add(conditionPo);
            }

            // 自动化前置条件
            List<PreconditionPo> preconditionPos = new ArrayList<>();
            PaasAutomationTemplatePreconditionExample preconditionExample = new PaasAutomationTemplatePreconditionExample();
            preconditionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andTemplateIdEqualTo(templateId);
            List<PaasAutomationTemplatePrecondition> preconditionList = preconditionMapper.selectByExample(preconditionExample);
            for (PaasAutomationTemplatePrecondition precondition : preconditionList) {
                PreconditionPo preconditionPo = new PreconditionPo();
                preconditionPo.setCond_type(precondition.getCondType());
                preconditionPo.setMemo("根据模板创建前置条件");

                PaasAutomationTemplatePreconditionDisplayExample displayExample = new PaasAutomationTemplatePreconditionDisplayExample();
                displayExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andAutomationProconditionIdEqualTo(precondition.getId());
                List<PaasAutomationTemplatePreconditionDisplay> displays = preconditionDisplayMapper.selectByExample(displayExample);
                if (displays != null && displays.size() > 0) {
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");

                    PaasAutomationTemplatePreconditionDisplay display = displays.get(0);
                    PreconditionDisplayPo preconditionDisplayPo = new PreconditionDisplayPo();
                    preconditionDisplayPo.setStart(format.format(display.getStart()));
                    preconditionDisplayPo.setEnd(format.format(display.getEnd()));
                    preconditionDisplayPo.setLoops(display.getLoops());
                    preconditionDisplayPo.setTimezone_id(display.getTimezoneId());

                    preconditionPo.setDisplay(preconditionDisplayPo);
                }
                preconditionPos.add(preconditionPo);
            }

            // 自动化模板动作
            List<ActionPo> actions = new ArrayList<>();
            PaasAutomationTemplateActionExample actionExample = new PaasAutomationTemplateActionExample();
            actionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andTemplateIdEqualTo(templateId);
            List<PaasAutomationTemplateAction> actionList = actionMapper.selectByExample(actionExample);
            for (PaasAutomationTemplateAction action : actionList) {
                // 房间是否拥有支持自动化对应设备
                PaasDeviceInfoExample deviceInfoExample = new PaasDeviceInfoExample();
                deviceInfoExample.createCriteria().andRoomIdEqualTo(roomId).andActionEqualTo(SystemConstants.INT_YES).
                        andNameEqualTo(action.getDeviceName()).andValidEqualTo(SystemConstants.INT_YES);
                List<PaasDeviceInfo> deviceInfos = paasDeviceInfoMapper.selectByExample(deviceInfoExample);
                if (deviceInfos == null || deviceInfos.size() < 1) {
                    String error = "房间缺少" + action.getDeviceName();
                    throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), error, error);
                }

                // 动作功能属性
                PaasAutomationTemplateActionExecutorExample actionExecutorExample = new PaasAutomationTemplateActionExecutorExample();
                actionExecutorExample.createCriteria().andAutomationActionIdEqualTo(action.getId()).andValidEqualTo(SystemConstants.INT_YES);
                List<PaasAutomationTemplateActionExecutor> actionExecutorList = actionExecutorMapper.selectByExample(actionExecutorExample);
                JSONObject executor_property = new JSONObject();
                for (PaasAutomationTemplateActionExecutor executor : actionExecutorList) {
                    if ("Boolean".equals(executor.getType())) {
                        executor_property.put(executor.getKey(), Boolean.parseBoolean(executor.getValue()));
                    } else if ("Integer".equals(executor.getType())) {
                        executor_property.put(executor.getKey(), Integer.parseInt(executor.getValue()));
                    } else {
                        executor_property.put(executor.getKey(), executor.getValue());
                    }
                }

                ActionPo actionPo = new ActionPo();
                actionPo.setEntity_id(deviceInfos.get(0).getDeviceId());
                actionPo.setMemo("根据模板创建动作");
                actionPo.setExecutor_property(executor_property);
                actions.add(actionPo);
            }

            AutomationPo automationPo = new AutomationPo();
            automationPo.setRoom_id(roomId);
            automationPo.setAutomation_name(automationName);
            automationPo.setMatch_type(template.getMatchType());
            automationPo.setCondition_rule(template.getConditionRule());
            automationPo.setMemo("根据模板创建自动化");

            automationPo.setConditions(conditionPos);
            automationPo.setPreconditions(preconditionPos);
            automationPo.setActions(actions);

            return automationPo;
        } catch (AppException e) {
            logger.error("【AutomationServiceImplements.checkAutomationTemplateDevice】根据模板获取新增自动化参数异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.checkAutomationTemplateDevice】根据模板获取新增自动化参数异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity addAutomationByTemplate(String roomId, Integer templateId, String automationName) throws AppException {
        try {
            AutomationPo automationPo = this.checkAutomationTemplateDevice(roomId, templateId, automationName);

            ApiResponseEntity addResponse = this.addAutomation(automationPo);
            if (SysErrorCode.SUCCESS.getError_code().equals(addResponse.getCode())) {
                PaasAutomationTemplateAutomation templateAutomation = new PaasAutomationTemplateAutomation();
                templateAutomation.setAutomationId(addResponse.getData().toString());
                templateAutomation.setTemplateId(templateId);
                automationTemplateAutomationMapper.insertSelective(templateAutomation);
            }
            return addResponse;
        } catch (AppException e) {
            logger.error("【AutomationServiceImplements.addAutomationByTemplate】根据模板新增自动化异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.addAutomationByTemplate】根据模板新增自动化异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity addAutomationsByTemplate(AutomationByTemplatePo automationByTemplatePo) throws AppException {
        try {
            if (automationByTemplatePo.getTemplateId() == null || automationByTemplatePo.getRoomIds() == null) {
                throw new AppException(SysErrorCode.PARAMS_CHECK_ERROR);
            }

            for (String roomId : automationByTemplatePo.getRoomIds()) {
                this.addAutomationByTemplate(roomId, automationByTemplatePo.getTemplateId(), automationByTemplatePo.getAutomation_name());
            }

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【AutomationServiceImplements.addAutomationsByTemplate】根据模板新增自动化异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【AutomationServiceImplements.addAutomationsByTemplate】根据模板新增自动化异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
