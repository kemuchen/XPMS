package cn.xpms.paas.api.service.business.inter.automation;

import cn.xpms.paas.api.bean.dto.automation.CustomAutomationByTemplatePo;
import cn.xpms.paas.api.bean.dto.automation.CustomAutomationPo;
import cn.xpms.paas.api.dao.generator.entity.PaasCustomAutomation;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName CustomAutomationServiceInterface
 * @Desc
 * @Author ycj
 * @Date 2021-01-12 14:52
 * @Version 1.0
 */
public interface CustomAutomationServiceInterface {

    ApiResponseEntity getCustomAutomationTypes() throws AppException;

    ApiResponseEntity getCustomAutomation(PaasCustomAutomation automation) throws AppException;

    ApiResponseEntity getCustomAutomationDetail(Integer id) throws AppException;

    ApiResponseEntity getCustomAutomationConditions(Integer automation_id) throws AppException;

    ApiResponseEntity getCustomAutomationScenes(Integer automation_id) throws AppException;

    ApiResponseEntity addCustomAutomation(CustomAutomationPo automationPo) throws AppException;

    ApiResponseEntity modifyCustomAutomation(CustomAutomationPo automationPo) throws AppException;

    ApiResponseEntity deleteCustomAutomation(Integer id) throws AppException;

    ApiResponseEntity addAutomationByTemplate(CustomAutomationByTemplatePo automationByTemplatePo) throws AppException;

    ApiResponseEntity triggerCustomAutomation(String type, String roomId) throws AppException;

    ApiResponseEntity triggerCustomAutomation(String deviceId, String code, String value) throws AppException;
}
