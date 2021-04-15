package cn.xpms.paas.api.service.business.inter.automation;

import cn.xpms.paas.api.bean.dto.automation.CustomAutomationTemplatePo;
import cn.xpms.paas.api.dao.generator.entity.PaasCustomAutomationTemplate;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName CustomAutomationTemplateInterface
 * @Desc
 * @Author ycj
 * @Date 2021-01-12 16:24
 * @Version 1.0
 */
public interface CustomAutomationTemplateServiceInterface {

    ApiResponseEntity getCustomAutomationTemplates(PaasCustomAutomationTemplate template) throws AppException;

    ApiResponseEntity getCustomAutomationTemplateDetail(Integer id) throws AppException;

    ApiResponseEntity getCustomAutomationTemplateConditions(Integer automation_id) throws AppException;

    ApiResponseEntity getCustomAutomationTemplateScenes(Integer automation_id) throws AppException;

    ApiResponseEntity addCustomAutomationTemplate(CustomAutomationTemplatePo automationPo) throws AppException;

    ApiResponseEntity modifyCustomAutomationTemplate(CustomAutomationTemplatePo automationPo) throws AppException;

    ApiResponseEntity deleteCustomAutomationTemplate(Integer id) throws AppException;
}
