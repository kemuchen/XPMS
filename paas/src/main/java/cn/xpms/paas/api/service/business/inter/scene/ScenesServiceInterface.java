package cn.xpms.paas.api.service.business.inter.scene;

import cn.xpms.paas.api.bean.dto.scene.ScenePo;
import cn.xpms.paas.api.bean.dto.scene.ScenesByTemplatePo;
import cn.xpms.paas.api.dao.generator.entity.PaasSceneInfo;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName ScenesServiceInterface
 * @Desc
 * @Author ycj
 * @Date 2020-12-15 14:11
 * @Version 1.0
 */
public interface ScenesServiceInterface {

    /**
     * @Description: 获取场景列表
     * @Params: PaasSceneInfo
     * @return:
     * @Author: ycj
     * @Date: 2020-12-15 14:25
     */
    ApiResponseEntity getScenes(PaasSceneInfo paasSceneInfo) throws AppException;

    /**
     * @Description: 获取场景
     * @Params: id 主键ID
     * @return:
     * @Author: ycj
     * @Date: 2020-12-15 15:35
     */
    ApiResponseEntity getScene(Integer id) throws AppException;

    /**
     * @Description: 获取场景动作列表
     * @Params: sceneId
     * @return:
     * @Author: ycj
     * @Date: 2020-12-15 15:35
     */
    ApiResponseEntity getSceneActions(String sceneId) throws AppException;


    /**
     * @Description: 触发场景
     * @Params: [roomId, sceneId]
     * @return:
     * @Author: ycj
     * @Date: 2020-12-15 15:37
     */
    ApiResponseEntity trigger(String roomId, String sceneId) throws AppException;

    /**
     * @Description: 获取房间场景支持设备列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:46
     */
    ApiResponseEntity getRoomSceneDevices(String roomId) throws AppException;

    /**
     * @Description: 添加场景
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:47
     */
    ApiResponseEntity addScene(ScenePo scenePo) throws AppException;

    /**
     * @Description: 修改场景
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:48
     */
    ApiResponseEntity modifyScene(ScenePo scenePo) throws AppException;

    /**
     * @Description: 删除场景
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:48
     */
    ApiResponseEntity deleteScene(Integer id) throws AppException;

    /**
     * @Description: 同步房价场景数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:48
     */
    ApiResponseEntity synchronizeRoomScenes(String roomId) throws AppException;

    ScenePo checkSceneTemplateDevice(String roomId, Integer templateId, String sceneName) throws AppException;

    ApiResponseEntity addSceneByTemplate(String roomId, Integer templateId, String sceneName) throws AppException;

    ApiResponseEntity addScenesByTemplate(ScenesByTemplatePo scenesByTemplatePo) throws AppException;
}
