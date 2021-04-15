package cn.xpms.paas.api.service.business.inter.scene;

import cn.xpms.paas.api.bean.dto.scene.SceneTemplatePo;
import cn.xpms.paas.api.bean.dto.scene.TemplateScenesPo;
import cn.xpms.paas.api.dao.generator.entity.PaasSceneTemplate;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName SceneTemplateInterface
 * @Desc
 * @Author ycj
 * @Date 2020-12-28 10:39
 * @Version 1.0
 */
public interface SceneTemplateInterface {

    /**
     * @Description: 查询场景模板列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:22
     */
    ApiResponseEntity getSceneTemplates(PaasSceneTemplate paasSceneTemplate) throws AppException;

    /**
     * @Description: 查询场景模板详情
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:23
     */
    ApiResponseEntity getSceneTemplateDetail(Integer id) throws AppException;

    /**
     * @Description: 查询场景模板动作列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:23
     */
    ApiResponseEntity getSceneTemplateActions(Integer id) throws AppException;

    /**
     * @Description: 添加场景模板
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:23
     */
    ApiResponseEntity addSceneTemplate(SceneTemplatePo sceneTemplatePo) throws AppException;

    /**
     * @Description: 修改场景模板
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:24
     */
    ApiResponseEntity modifySceneTemplate(SceneTemplatePo sceneTemplatePo) throws AppException;

    /**
     * @Description: 删除场景模板
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:24
     */
    ApiResponseEntity deleteSceneTemplate(Integer id) throws AppException;

    ApiResponseEntity getAvailableRooms(Integer templateId, String projectId) throws AppException;

    ApiResponseEntity getTemplateRooms(Integer templateId, String projectId) throws AppException;

    ApiResponseEntity deleteTemplateScenes(TemplateScenesPo templateScenesPo) throws AppException;

}
