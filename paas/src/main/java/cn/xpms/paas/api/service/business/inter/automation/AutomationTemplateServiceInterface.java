package cn.xpms.paas.api.service.business.inter.automation;

import cn.xpms.paas.api.bean.dto.automation.AutomationTemplatePo;
import cn.xpms.paas.api.dao.generator.entity.PaasAutomationTemplate;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName AutomationTemplateServiceInterface
 * @Desc
 * @Author ycj
 * @Date 2020-12-29 15:37
 * @Version 1.0
 */
public interface AutomationTemplateServiceInterface {

    /**
     * @Description: 获取自动化模板列表
     * @Params:
     * @return: 
     * @Author: ycj
     * @Date: 2020-12-29 17:09
     */
    ApiResponseEntity getAutomationTemplates(PaasAutomationTemplate paasAutomationTemplate) throws AppException;

    /**
     * @Description: 获取自动化模板详情
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:10
     */
    ApiResponseEntity getAutomationTemplateDetail(Integer id) throws AppException;

    /**
     * @Description: 获取自动化模板动作列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:10
     */
    ApiResponseEntity getAutomationActions(Integer templateId) throws AppException;

    /**
     * @Description: 获取自动化模板条件列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:11
     */
    ApiResponseEntity getAutomationConditions(Integer templateId) throws AppException;

    /**
     * @Description: 获取自动化模板前置条件列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:12
     */
    ApiResponseEntity getAutomationPreconditions(Integer templateId) throws AppException;

    /**
     * @Description: 添加自动化模板
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:28
     */
    ApiResponseEntity addAutomationTemplate(AutomationTemplatePo templatePo) throws AppException;

    /**
     * @Description: 修改自动化模板
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:28
     */
    ApiResponseEntity modifyAutomationTemplate(AutomationTemplatePo templatePo) throws AppException;

    /**
     * @Description: 删除自动化模板
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-29 17:28
     */
    ApiResponseEntity deleteAutomationTemplate(Integer id) throws AppException;
}
