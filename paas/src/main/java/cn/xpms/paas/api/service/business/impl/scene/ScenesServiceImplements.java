package cn.xpms.paas.api.service.business.impl.scene;

import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.bean.dto.scene.ActionPo;
import cn.xpms.paas.api.bean.dto.scene.ScenePo;
import cn.xpms.paas.api.bean.dto.scene.ScenesByTemplatePo;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.*;
import cn.xpms.paas.api.service.business.inter.scene.ScenesServiceInterface;
import cn.xpms.paas.api.service.common.inter.api.PaasApiServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ScenesServiceImplements
 * @Desc
 * @Author ycj
 * @Date 2020-12-15 14:12
 * @Version 1.0
 */
@Service
public class ScenesServiceImplements implements ScenesServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(ScenesServiceImplements.class);

    /**
     * 涂鸦API接口service
     */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    @Autowired
    PaasSceneInfoMapper paasSceneInfoMapper;

    @Autowired
    PaasSceneActionMapper paasSceneActionMapper;

    @Autowired
    PaasSceneActionExecutorPropertyMapper paasSceneActionExecutorPropertyMapper;

    @Autowired
    PaasDeviceInfoMapper paasDeviceInfoMapper;

    @Autowired
    PaasSceneTemplateMapper paasSceneTemplateMapper;

    @Autowired
    PaasSceneTemplateActionMapper paasSceneTemplateActionMapper;

    @Autowired
    PaasSceneTemplateActionExecutorMapper paasSceneTemplateActionExecutorMapper;

    @Autowired
    PaasSceneTemplateSceneMapper paasSceneTemplateSceneMapper;

    /**
     * @Description: 获取场景列表
     * @Params: PaasSceneInfo
     * @return:
     * @Author: ycj
     * @Date: 2020-12-15 14:28
     */
    @Override
    public ApiResponseEntity getScenes(PaasSceneInfo paasSceneInfo) throws AppException {
        try {
            PaasSceneInfoExample example = new PaasSceneInfoExample();
            paasSceneInfo.setValid(SystemConstants.INT_YES);
            BeanUtil.beanToExample(paasSceneInfo, example);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, paasSceneInfoMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【ScenesServiceImplements.getScenes】查询场景列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取场景
     * @Params: id
     * @return:
     * @Author: ycj
     * @Date: 2020-12-15 14:42
     */
    @Override
    public ApiResponseEntity getScene(Integer id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, paasSceneInfoMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("【ScenesServiceImplements.getScene】查询场景异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取场景动作列表
     * @Params: sceneId
     * @return:
     * @Author: ycj
     * @Date: 2020-12-15 15:53
     */
    @Override
    public ApiResponseEntity getSceneActions(String sceneId) throws AppException {
        try {
            PaasSceneActionExample example = new PaasSceneActionExample();
            example.createCriteria().andSceneIdEqualTo(sceneId).andValidEqualTo(SystemConstants.INT_YES);
            List<PaasSceneAction> list = paasSceneActionMapper.selectByExample(example);
            List<Map<String, Object>> result = new ArrayList<>();
            for (PaasSceneAction action : list) {
                Map<String, Object> actionObj = JSON.parseObject(JSON.toJSONString(action));

                JSONObject executorProperty = new JSONObject();

                PaasSceneActionExecutorPropertyExample propertyExample = new PaasSceneActionExecutorPropertyExample();
                propertyExample.createCriteria().andSceneActionIdEqualTo(action.getId()).andValidEqualTo(SystemConstants.INT_YES);
                List<PaasSceneActionExecutorProperty> properties = paasSceneActionExecutorPropertyMapper.selectByExample(propertyExample);
                for (PaasSceneActionExecutorProperty property : properties) {
                    executorProperty.put(property.getKey(), property.getValue());
                }
                actionObj.put("executorProperty", executorProperty);

                result.add(actionObj);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS, result);
        } catch (Exception e) {
            logger.error("【ScenesServiceImplements.getSceneActions】查询场景动作列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 触发场景
     * @Params: [roomId, sceneId]
     * @return:
     * @Author: ycj
     * @Date: 2020-12-15 15:54
     */
    @Override
    public ApiResponseEntity trigger(String roomId, String sceneId) throws AppException {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("room_id", roomId);
            params.put("scene_id", sceneId);
            // 调用涂鸦接口触发场景
            PaasApiResponseEntity<Boolean> responseEntity = paasApiServiceInterface.call(PaasApiEnum.TRIGGER_SCENE, params);
            if (responseEntity.getResult()) {
                return new ApiResponseEntity(SysErrorCode.SUCCESS);
            } else {
                return new ApiResponseEntity(SysErrorCode.SYSTEM_ERROR, responseEntity.getMsg());
            }

        } catch (AppException e) {
            logger.error("【ScenesServiceImplements.trigger】触发场景异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ScenesServiceImplements.trigger】触发场景异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取房间场景支持设备列表
     * @Params: [roomId]
     * @return:
     * @Author: ycj
     * @Date: 2020-12-15 16:08
     */
    @Override
    public ApiResponseEntity getRoomSceneDevices(String roomId) throws AppException {
        try {
            PaasDeviceInfoExample example = new PaasDeviceInfoExample();
            example.createCriteria().andRoomIdEqualTo(roomId).andSceneEqualTo(SystemConstants.INT_YES).
                    andValidEqualTo(SystemConstants.INT_YES);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, paasDeviceInfoMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【ScenesServiceImplements.getRoomSceneDevices】查询房间场景支持设备异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 添加或修改本地场景数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:47
     */
    private void synchronizeScenesInfo(ScenePo scenePo) throws AppException {
        PaasSceneInfo sceneInfo = new PaasSceneInfo();
        sceneInfo.setSceneId(scenePo.getScene_id());
        sceneInfo.setRoomId(scenePo.getRoom_id());
        sceneInfo.setSceneName(scenePo.getScene_name());
        sceneInfo.setMemo(scenePo.getMemo());

        // 新增
        if (scenePo.getId() == null) {
            paasSceneInfoMapper.insertSelective(sceneInfo);
        } else {  // 修改
            sceneInfo.setId(scenePo.getId());
            paasSceneInfoMapper.updateByPrimaryKeySelective(sceneInfo);
        }

        synchronizeScenesAction(scenePo.getActions(), scenePo.getScene_id());
    }

    /**
     * @Description: 添加或修改本地场景动作数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:47
     */
    private void synchronizeScenesAction(List<ActionPo> actions, String sceneId) throws AppException {
        this.deleteSceneActions(sceneId);

        for (ActionPo actionPo : actions) {
            PaasSceneAction action = new PaasSceneAction();
            action.setSceneId(sceneId);
            action.setEntityId(actionPo.getEntity_id());
            action.setActionExecutor(actionPo.getAction_executor());
            action.setMemo(actionPo.getMemo());
            paasSceneActionMapper.insertSelective(action);

            if (null != actionPo.getExecutor_property()) {
                JSONObject properties = actionPo.getExecutor_property();
                for (String key : properties.keySet()) {
                    PaasSceneActionExecutorProperty property = new PaasSceneActionExecutorProperty();
                    property.setSceneActionId(action.getId());
                    property.setKey(key);
                    property.setValue(properties.getString(key));
                    paasSceneActionExecutorPropertyMapper.insertSelective(property);
                }
            }

        }
    }

    /**
     * @Description: 删除本地场景动作数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:48
     */
    private void deleteSceneActions(String sceneId) {
        PaasSceneActionExample actionExample = new PaasSceneActionExample();
        actionExample.createCriteria().andSceneIdEqualTo(sceneId).andValidEqualTo(SystemConstants.INT_YES);
        List<PaasSceneAction> actionsList = paasSceneActionMapper.selectByExample(actionExample);
        for (PaasSceneAction action : actionsList) {
            PaasSceneActionExecutorProperty property = new PaasSceneActionExecutorProperty();
            property.setValid(SystemConstants.INT_NO);
            PaasSceneActionExecutorPropertyExample propertyExample = new PaasSceneActionExecutorPropertyExample();
            propertyExample.createCriteria().andSceneActionIdEqualTo(action.getId()).andValidEqualTo(SystemConstants.INT_YES);
            paasSceneActionExecutorPropertyMapper.updateByExampleSelective(property, propertyExample);

            action.setValid(SystemConstants.INT_NO);
            paasSceneActionMapper.updateByPrimaryKeySelective(action);
        }
    }

    /**
     * @Description: 添加场景
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:47
     */
    @Override
    public ApiResponseEntity addScene(ScenePo scenePo) throws AppException {
        try {
            // 调用涂鸦接口新增场景
            PaasApiResponseEntity<String> apiResponseEntity = paasApiServiceInterface.call(PaasApiEnum.ADD_SCENE, BeanUtil.beanToMap(scenePo, false));

            scenePo.setScene_id(apiResponseEntity.getResult());
            this.synchronizeScenesInfo(scenePo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, apiResponseEntity.getResult());
        } catch (AppException e) {
            logger.error("【ScenesServiceImplements.addScene】新增场景异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ScenesServiceImplements.addScene】新增场景异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修改场景
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:48
     */
    @Override
    public ApiResponseEntity modifyScene(ScenePo scenePo) throws AppException {
        try {
            // 调用涂鸦接口修改场景
            PaasApiResponseEntity<Boolean> apiResponseEntity =
                    paasApiServiceInterface.call(PaasApiEnum.MODIFY_SCENE, BeanUtil.beanToMap(scenePo, false));
            if (apiResponseEntity.getResult()) {
                this.synchronizeScenesInfo(scenePo);
                return new ApiResponseEntity(SysErrorCode.SUCCESS);
            } else {
                return new ApiResponseEntity(SysErrorCode.SYSTEM_ERROR, apiResponseEntity.getMsg());
            }

        } catch (AppException e) {
            logger.error("【ScenesServiceImplements.addScene】新增场景异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ScenesServiceImplements.addScene】新增场景异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除场景
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:49
     */
    @Override
    public ApiResponseEntity deleteScene(Integer id) throws AppException {
        try {
            PaasSceneInfo sceneInfo = paasSceneInfoMapper.selectByPrimaryKey(id);

            Map<String, Object> params = new HashMap<>();
            params.put("room_id", sceneInfo.getRoomId());
            params.put("scene_id", sceneInfo.getSceneId());
            // 调用涂鸦接口删除场景
            paasApiServiceInterface.call(PaasApiEnum.DELETE_SCENE, params);

            this.deleteSceneActions(sceneInfo.getSceneId());

            sceneInfo.setValid(SystemConstants.INT_NO);
            paasSceneInfoMapper.updateByPrimaryKeySelective(sceneInfo);

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ScenesServiceImplements.deleteScene】删除场景异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ScenesServiceImplements.deleteScene】删除场景异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步房价场景数据
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-26 16:50
     */
    @Override
    public ApiResponseEntity synchronizeRoomScenes(String roomId) throws AppException {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("room_id", roomId);
            // 调用涂鸦接口新增场景
            PaasApiResponseEntity<List<Map<String, Object>>> apiResponseEntity = paasApiServiceInterface.call(PaasApiEnum.GET_ROOM_SCENES, params);

            // 房间场景列表
            List<Map<String, Object>> roomScenes = apiResponseEntity.getResult();
            for (Map<String, Object> scene : roomScenes) {
                PaasSceneInfo sceneInfo = JSON.parseObject(JSON.toJSONString(scene), PaasSceneInfo.class);
                PaasSceneInfoExample sceneInfoExample = new PaasSceneInfoExample();
                sceneInfoExample.createCriteria().andRoomIdEqualTo(roomId).andSceneIdEqualTo(sceneInfo.getSceneId()).
                        andValidEqualTo(SystemConstants.INT_YES);
                if (paasSceneInfoMapper.countByExample(sceneInfoExample) > 0) {
                    // 更新场景
                    paasSceneInfoMapper.updateByExampleSelective(sceneInfo, sceneInfoExample);
                    // 场景动作列表
                    if (scene.get("actions") != null) {
                        List<Map<String, Object>> actions = (List<Map<String, Object>>) scene.get("actions");
                        for (Map<String, Object> actionMap : actions) {
                            PaasSceneAction action = JSON.parseObject(JSON.toJSONString(actionMap), PaasSceneAction.class);
                            PaasSceneActionExample actionExample = new PaasSceneActionExample();
                            actionExample.createCriteria().andSceneIdEqualTo(sceneInfo.getSceneId()).
                                    andEntityIdEqualTo(action.getEntityId()).andValidEqualTo(SystemConstants.INT_YES);

                            List<PaasSceneAction> actionList = paasSceneActionMapper.selectByExample(actionExample);
                            if (actionList.size() > 0) {    // 更新场景动作
                                for (PaasSceneAction actionRecord : actionList) {
                                    action.setId(actionRecord.getId());
                                    paasSceneActionMapper.updateByPrimaryKeySelective(action);

                                    // 场景动作属性
                                    Map<String, Object> property = (Map<String, Object>) actionMap.get("executor_property");
                                    for (String key : property.keySet()) {
                                        PaasSceneActionExecutorPropertyExample propertyExample = new PaasSceneActionExecutorPropertyExample();
                                        propertyExample.createCriteria().andSceneActionIdEqualTo(action.getId()).andKeyEqualTo(key).
                                                andValidEqualTo(SystemConstants.INT_YES);

                                        PaasSceneActionExecutorProperty propertyRecord = new PaasSceneActionExecutorProperty();
                                        propertyRecord.setSceneActionId(action.getId());
                                        propertyRecord.setKey(key);
                                        propertyRecord.setValue(property.get(key).toString());

                                        List<PaasSceneActionExecutorProperty> propertyList =
                                                paasSceneActionExecutorPropertyMapper.selectByExample(propertyExample);
                                        // 更新动作属性
                                        if (propertyList.size() > 0) {
                                            for (PaasSceneActionExecutorProperty p : propertyList) {
                                                propertyRecord.setId(p.getId());
                                                paasSceneActionExecutorPropertyMapper.updateByPrimaryKeySelective(propertyRecord);
                                            }
                                        } else {  // 添加动作属性
                                            paasSceneActionExecutorPropertyMapper.insertSelective(propertyRecord);
                                        }
                                    }
                                }
                            } else {        // 添加场景动作
                                paasSceneActionMapper.insertSelective(action);
                                // 添加场景动作属性
                                Map<String, Object> property = (Map<String, Object>) actionMap.get("executor_property");
                                for (String key : property.keySet()) {
                                    PaasSceneActionExecutorProperty propertyRecord = new PaasSceneActionExecutorProperty();
                                    propertyRecord.setSceneActionId(action.getId());
                                    propertyRecord.setKey(key);
                                    propertyRecord.setValue(property.get(key).toString());
                                    paasSceneActionExecutorPropertyMapper.insertSelective(propertyRecord);
                                }
                            }
                        }
                    }
                } else {        // 添加场景
                    paasSceneInfoMapper.insertSelective(sceneInfo);

                    // 添加场景动作
                    if (scene.get("actions") != null) {
                        List<Map<String, Object>> actions = (List<Map<String, Object>>) scene.get("actions");
                        for (Map<String, Object> actionMap : actions) {
                            PaasSceneAction action = JSON.parseObject(JSON.toJSONString(actionMap), PaasSceneAction.class);
                            paasSceneActionMapper.insertSelective(action);
                            // 添加场景动作属性
                            Map<String, Object> property = (Map<String, Object>) actionMap.get("executor_property");
                            for (String key : property.keySet()) {
                                PaasSceneActionExecutorProperty propertyRecord = new PaasSceneActionExecutorProperty();
                                propertyRecord.setSceneActionId(action.getId());
                                propertyRecord.setKey(key);
                                propertyRecord.setValue(property.get(key).toString());
                                paasSceneActionExecutorPropertyMapper.insertSelective(propertyRecord);
                            }
                        }
                    }
                }
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ScenesServiceImplements.addScene】新增场景异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ScenesServiceImplements.addScene】新增场景异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ScenePo checkSceneTemplateDevice(String roomId, Integer templateId, String sceneName) throws AppException {
        try {
            // 场景模板
            PaasSceneTemplate template = paasSceneTemplateMapper.selectByPrimaryKey(templateId);
            if (null == template) {
                throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "未查询到模板", "未查询到模板");
            }
            if (StringUtils.isBlank(sceneName)) {
                sceneName = template.getSceneName();
            }

            List<ActionPo> actions = new ArrayList<>();

            // 场景模板动作
            PaasSceneTemplateActionExample actionExample = new PaasSceneTemplateActionExample();
            actionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andSceneTemplateIdEqualTo(templateId);
            List<PaasSceneTemplateAction> actionList = paasSceneTemplateActionMapper.selectByExample(actionExample);
            for (PaasSceneTemplateAction action : actionList) {
                // 房间是否拥有支持场景对应设备
                PaasDeviceInfoExample deviceInfoExample = new PaasDeviceInfoExample();
                deviceInfoExample.createCriteria().andRoomIdEqualTo(roomId).andSceneEqualTo(SystemConstants.INT_YES).
                        andNameEqualTo(action.getDeviceName()).andValidEqualTo(SystemConstants.INT_YES);
                List<PaasDeviceInfo> deviceInfos = paasDeviceInfoMapper.selectByExample(deviceInfoExample);
                if (deviceInfos == null || deviceInfos.size() < 1) {
                    String error = "房间缺少" + action.getDeviceName();
                    throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), error, error);
                }

                // 动作功能属性
                PaasSceneTemplateActionExecutorExample actionExecutorExample = new PaasSceneTemplateActionExecutorExample();
                actionExecutorExample.createCriteria().andSceneActionIdEqualTo(action.getId()).andValidEqualTo(SystemConstants.INT_YES);
                List<PaasSceneTemplateActionExecutor> actionExecutorList =
                        paasSceneTemplateActionExecutorMapper.selectByExample(actionExecutorExample);
                JSONObject executor_property = new JSONObject();
                for (PaasSceneTemplateActionExecutor executor : actionExecutorList) {
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

            ScenePo scenePo = new ScenePo();
            scenePo.setRoom_id(roomId);
            scenePo.setScene_name(sceneName);
            scenePo.setMemo("根据模板创建场景");
            scenePo.setActions(actions);

            return scenePo;
        } catch (AppException e) {
            logger.error("【ScenesServiceImplements.checkSceneTemplateDevice】根据模板获取创建场景实体异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ScenesServiceImplements.checkSceneTemplateDevice】根据模板获取创建场景实体异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity addSceneByTemplate(String roomId, Integer templateId, String sceneName) throws AppException {
        try {
            ScenePo scenePo = this.checkSceneTemplateDevice(roomId, templateId, sceneName);

            ApiResponseEntity addResponse = this.addScene(scenePo);
            if (SysErrorCode.SUCCESS.getError_code().equals(addResponse.getCode())) {
                PaasSceneTemplateScene templateScene = new PaasSceneTemplateScene();
                templateScene.setSceneId(addResponse.getData().toString());
                templateScene.setTemplateId(templateId);
                paasSceneTemplateSceneMapper.insertSelective(templateScene);
            }
            return addResponse;
        } catch (AppException e) {
            logger.error("【ScenesServiceImplements.addSceneByTemplate】根据模板新增场景异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ScenesServiceImplements.addSceneByTemplate】根据模板新增场景异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity addScenesByTemplate(ScenesByTemplatePo scenesByTemplatePo) throws AppException {
        try {
            if (scenesByTemplatePo.getTemplateId() == null || scenesByTemplatePo.getRoomIds() == null) {
                throw new AppException(SysErrorCode.PARAMS_CHECK_ERROR);
            }

            for (String roomId : scenesByTemplatePo.getRoomIds()) {
                this.addSceneByTemplate(roomId, scenesByTemplatePo.getTemplateId(), scenesByTemplatePo.getScene_name());
            }

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ScenesServiceImplements.addScenesByTemplate】根据模板新增场景异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ScenesServiceImplements.addScenesByTemplate】根据模板新增场景异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
