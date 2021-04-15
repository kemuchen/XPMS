package cn.xpms.paas.api.service.business.inter.automation;

import cn.xpms.paas.api.bean.dto.automation.AutomationByTemplatePo;
import cn.xpms.paas.api.bean.dto.automation.AutomationPo;
import cn.xpms.paas.api.dao.generator.entity.PaasAutomationInfo;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName AutomationServiceInterface
 * @Desc
 * @Author ycj
 * @Date 2020-12-16 10:37
 * @Version 1.0
 */
public interface AutomationServiceInterface {

    /**
     * @Description: 获取自动化列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:51
     */
    ApiResponseEntity getAutomationInfos(PaasAutomationInfo automationInfo) throws AppException;

    /**
     * @Description: 获取自动化详情
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:51
     */
    ApiResponseEntity getAutomationInfo(Integer id) throws AppException;

    /**
     * @Description: 获取自动化条件列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:51
     */
    ApiResponseEntity getAutomationConditions(String automationId) throws AppException;

    /**
     * @Description: 获取自动化前置条件列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:51
     */
    ApiResponseEntity getAutomationPreconditions(String automationId) throws AppException;

    /**
     * @Description: 获取自动化动作列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:51
     */
    ApiResponseEntity getAutomationActions(String automationId) throws AppException;

    /**
     * @Description: 获取房间支持自动化的条件设备
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:52
     */
    ApiResponseEntity getAutomationConditionDevices(String roomId) throws AppException;

    /**
     * @Description: 获取房间支持自动化的动作设备
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:52
     */
    ApiResponseEntity getAutomationActionDevices(String roomId) throws AppException;

    /**
     * @Description: 添加自动化
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:52
     */
    ApiResponseEntity addAutomation(AutomationPo automationPo) throws AppException;

    /**
     * @Description: 修改自动化
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:52
     */
    ApiResponseEntity modifyAutomation(AutomationPo automationPo) throws AppException;

    /**
     * @Description: 删除自动化
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:53
     */
    ApiResponseEntity deleteAutomation(Integer id) throws AppException;

    /**
     * @Description: 启用自动化
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:53
     */
    ApiResponseEntity enableAutomation(Integer id) throws AppException;

    /**
     * @Description: 停用自动化
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:53
     */
    ApiResponseEntity disableAutomation(Integer id) throws AppException;

    AutomationPo checkAutomationTemplateDevice(String roomId, Integer templateId, String automationName) throws AppException;

    ApiResponseEntity addAutomationByTemplate(String roomId, Integer templateId, String automationName) throws AppException;

    ApiResponseEntity addAutomationsByTemplate(AutomationByTemplatePo automationByTemplatePo) throws AppException;
}
