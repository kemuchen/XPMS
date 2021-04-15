package cn.xpms.paas.api.service.business.inter.construction;

import cn.xpms.paas.api.bean.dto.template.ConstructionTemplatePositionPo;
import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplate;
import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplateArea;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName TemplateServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/2 16:08
 * @Version 1.0
 */
public interface TemplateServiceInterface {

    /**
     * @Description: 查询施工模板列表
     * @Params: [template]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 16:31
     */
    ApiResponseEntity getTemplates(PaasConstructionTemplate template) throws AppException;

    /**
     * @Description: 查询施工模板详情
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 16:32
     */
    ApiResponseEntity getTemplate(Integer id) throws AppException;

    /**
     * @Description: 查询施工模板区域列表
     * @Params: [template_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 17:03
     */
    ApiResponseEntity getTemplateAreas(String template_id) throws AppException;

    /**
     * @Description: 查询施工模板位置列表
     * @Params: [area_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 17:04
     */
    ApiResponseEntity getTemplatePositions(String area_id) throws AppException;

    /**
     * @Description: 新增施工模板
     * @Params: [template]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 16:34
     */
    ApiResponseEntity createTemplate(PaasConstructionTemplate template) throws AppException;

    /**
     * @Description: 修改施工模板
     * @Params: [template]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 16:35
     */
    ApiResponseEntity modifyTemplate(PaasConstructionTemplate template) throws AppException;

    /**
     * @Description: 删除施工模板
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 16:38
     */
    ApiResponseEntity deleteTemplate(Integer id) throws AppException;

    /**
     * @Description: 新增施工模板区域
     * @Params: [area]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 16:39
     */
    ApiResponseEntity createTemplateArea(PaasConstructionTemplateArea area) throws AppException;

    /**
     * @Description: 修改施工模板区域
     * @Params: [area]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 16:40
     */
    ApiResponseEntity modifyTemplateArea(PaasConstructionTemplateArea area) throws AppException;

    /**
     * @Description: 删除施工模板区域
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 16:41
     */
    ApiResponseEntity deleteTemplateArea(Integer id) throws AppException;

    /**
     * @Description: 根据模板id删除施工模板区域信息
     * @Params: [template_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 9:28
     */
    ApiResponseEntity deleteTemplateAreaByTemplateId(String template_id) throws AppException;

    /**
     * @Description: 新建施工模板位置
     * @Params: [position]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 16:41
     */
    ApiResponseEntity createTemplatePosition(ConstructionTemplatePositionPo positionPo) throws AppException;

    /**
     * @Description: 修改施工模板区域
     * @Params: [position]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 16:42
     */
    ApiResponseEntity modifyTemplatePosition(ConstructionTemplatePositionPo positionPo) throws AppException;

    /**
     * @Description: 删除施工模板位置
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 16:42
     */
    ApiResponseEntity deleteTemplatePosition(Integer id) throws AppException;

    /**
     * @Description: 根据区域id删除施工模板设备安装位置信息
     * @Params: [area_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 9:29
     */
    ApiResponseEntity deleteTemplatePositionByAreaId(String area_id) throws AppException;

    ApiResponseEntity copyTemplate(Integer id, String templateName) throws AppException;
}