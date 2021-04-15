package cn.xpms.paas.api.service.business.impl.scene;

import cn.xpms.paas.api.bean.dto.scene.SceneTemplateActionPo;
import cn.xpms.paas.api.bean.dto.scene.SceneTemplatePo;
import cn.xpms.paas.api.bean.dto.scene.TemplateScenesPo;
import cn.xpms.paas.api.dao.customize.repository.CustomizeRoomMapper;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.*;
import cn.xpms.paas.api.service.business.inter.scene.SceneTemplateInterface;
import cn.xpms.paas.api.service.business.inter.scene.ScenesServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SceneTemplateImplements
 * @Desc
 * @Author ycj
 * @Date 2020-12-28 10:39
 * @Version 1.0
 */
@Service
public class SceneTemplateImplements implements SceneTemplateInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(SceneTemplateImplements.class);

    @Autowired
    PaasSceneTemplateMapper paasSceneTemplateMapper;

    @Autowired
    PaasSceneTemplateActionMapper paasSceneTemplateActionMapper;

    @Autowired
    PaasSceneTemplateActionExecutorMapper paasSceneTemplateActionExecutorMapper;

    @Autowired
    CustomizeRoomMapper customizeRoomMapper;

    @Autowired
    ScenesServiceInterface scenesServiceInterface;

    @Autowired
    PaasSceneInfoMapper sceneInfoMapper;

    @Autowired
    PaasSceneTemplateSceneMapper sceneTemplateSceneMapper;

    /**
     * @Description: 查询场景模板列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:23
     */
    @Override
    public ApiResponseEntity getSceneTemplates(PaasSceneTemplate paasSceneTemplate) throws AppException {
        try {
//            PaasSceneTemplateExample example = new PaasSceneTemplateExample();
//            paasSceneTemplate.setValid(SystemConstants.INT_YES);
//            BeanUtil.beanToExample(paasSceneTemplate, example);
//            return new ApiResponseEntity(SysErrorCode.SUCCESS, paasSceneTemplateMapper.selectByExample(example));
            return new ApiResponseEntity(SysErrorCode.SUCCESS, customizeRoomMapper.getSceneTemplates(paasSceneTemplate));
        } catch (Exception e) {
            logger.error("【SceneTemplateImplements.getSceneTemplates】查询场景模板列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询场景模板详情
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:23
     */
    @Override
    public ApiResponseEntity getSceneTemplateDetail(Integer id) throws AppException {
        try {
            PaasSceneTemplate template = paasSceneTemplateMapper.selectByPrimaryKey(id);
            if (null == template) {
                return new ApiResponseEntity(SysErrorCode.SUCCESS, "未查询到对应模板");
            }
            Map<String, Object> result = JSON.parseObject(JSON.toJSONString(template));

            PaasSceneTemplateActionExample actionExample = new PaasSceneTemplateActionExample();
            actionExample.createCriteria().andSceneTemplateIdEqualTo(template.getId()).andValidEqualTo(SystemConstants.INT_YES);
            List<PaasSceneTemplateAction> actions = paasSceneTemplateActionMapper.selectByExample(actionExample);
            JSONArray actionList = new JSONArray();
            for (PaasSceneTemplateAction action : actions) {
                JSONObject actionJson = JSON.parseObject(JSON.toJSONString(action));

                PaasSceneTemplateActionExecutorExample executorExample = new PaasSceneTemplateActionExecutorExample();
                executorExample.createCriteria().andSceneActionIdEqualTo(action.getId()).andValidEqualTo(SystemConstants.INT_YES);
                List<PaasSceneTemplateActionExecutor> executors = paasSceneTemplateActionExecutorMapper.selectByExample(executorExample);
                JSONObject executor_property = new JSONObject();
                for (PaasSceneTemplateActionExecutor executor : executors) {
                    executor_property.put(executor.getKey(), executor.getValue());
                }

                actionJson.put("executor_property", executor_property);
                actionList.add(actionJson);
            }
            result.put("actions", actionList);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, result);
        } catch (Exception e) {
            logger.error("【SceneTemplateImplements.getSceneTemplateDetail】查询场景模板详情异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询场景模板动作列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:23
     */
    @Override
    public ApiResponseEntity getSceneTemplateActions(Integer id) throws AppException {
        try {
            JSONArray result = new JSONArray();
            PaasSceneTemplateActionExample actionExample = new PaasSceneTemplateActionExample();
            actionExample.createCriteria().andSceneTemplateIdEqualTo(id).andValidEqualTo(SystemConstants.INT_YES);
            List<PaasSceneTemplateAction> actions = paasSceneTemplateActionMapper.selectByExample(actionExample);
            for (PaasSceneTemplateAction action : actions) {
                JSONObject actionJson = JSON.parseObject(JSON.toJSONString(action));

                PaasSceneTemplateActionExecutorExample executorExample = new PaasSceneTemplateActionExecutorExample();
                executorExample.createCriteria().andSceneActionIdEqualTo(action.getId()).andValidEqualTo(SystemConstants.INT_YES);
                List<PaasSceneTemplateActionExecutor> executors = paasSceneTemplateActionExecutorMapper.selectByExample(executorExample);
                JSONObject executor_property = new JSONObject();
                for (PaasSceneTemplateActionExecutor executor : executors) {
                    executor_property.put(executor.getKey(), executor.getValue());
                }

                actionJson.put("executor_property", executor_property);
                result.add(actionJson);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS, result);
        } catch (Exception e) {
            logger.error("【SceneTemplateImplements.getSceneTemplateDetail】查询场景模板详情异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 添加场景模板
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:24
     */
    @Override
    public ApiResponseEntity addSceneTemplate(SceneTemplatePo sceneTemplatePo) throws AppException {
        try {
            this.saveSceneRecord(sceneTemplatePo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【SceneTemplateImplements.modifySceneTemplate】添加场景模板异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修改场景模板
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:24
     */
    @Override
    public ApiResponseEntity modifySceneTemplate(SceneTemplatePo sceneTemplatePo) throws AppException {
        try {
            this.saveSceneRecord(sceneTemplatePo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【SceneTemplateImplements.modifySceneTemplate】修改场景模板异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除场景模板
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:24
     */
    @Override
    public ApiResponseEntity deleteSceneTemplate(Integer id) throws AppException {
        try {
            this.deleteActionsRecord(id);

            PaasSceneTemplate template = new PaasSceneTemplate();
            template.setId(id);
            template.setValid(SystemConstants.INT_NO);
            paasSceneTemplateMapper.updateByPrimaryKeySelective(template);

            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【SceneTemplateImplements.deleteSceneTemplate】删除场景模板异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存场景模板记录
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:24
     */
    private void saveSceneRecord(SceneTemplatePo sceneTemplatePo) {
        PaasSceneTemplate template = new PaasSceneTemplate();
        template.setProjectId(sceneTemplatePo.getProject_id());
        template.setTemplateName(sceneTemplatePo.getTemplate_name());
        template.setSceneName(sceneTemplatePo.getScene_name());
        template.setMemo(sceneTemplatePo.getMemo());
        if (sceneTemplatePo.getId() == null) {
            paasSceneTemplateMapper.insertSelective(template);
        } else {
            template.setId(sceneTemplatePo.getId());
            paasSceneTemplateMapper.updateByPrimaryKeySelective(template);
        }
        saveActionsRecord(sceneTemplatePo.getActions(), template.getId());
    }

    /**
     * @Description: 保存场景模板动作列表记录
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:25
     */
    private void saveActionsRecord(List<SceneTemplateActionPo> actionPos, Integer templateId) {
        this.deleteActionsRecord(templateId);
        for (SceneTemplateActionPo actionPo : actionPos) {
            PaasSceneTemplateAction action = new PaasSceneTemplateAction();
            action.setSceneTemplateId(templateId);
            action.setDeviceName(actionPo.getDevice_name());
            action.setActionExecutor(actionPo.getAction_executor());
            action.setMemo(actionPo.getMemo());
            paasSceneTemplateActionMapper.insertSelective(action);

            JSONObject property = actionPo.getExecutor_property();
            if (null != property) {
                for (String key : property.keySet()) {
                    PaasSceneTemplateActionExecutor executor = new PaasSceneTemplateActionExecutor();
                    executor.setSceneActionId(action.getId());
                    executor.setKey(key);
                    if (property.get(key) instanceof Boolean) {
                        executor.setType("Boolean");
                    } else if (property.get(key) instanceof Integer) {
                        executor.setType("Integer");
                    } else {
                        executor.setType("String");
                    }
                    executor.setValue(property.get(key).toString());
                    paasSceneTemplateActionExecutorMapper.insertSelective(executor);
                }
            }
        }
    }

    /**
     * @Description: 删除场景模板指定动作记录
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:25
     */
    private void deleteActionRecord(Integer id) {
        PaasSceneTemplateActionExecutorExample executorExample = new PaasSceneTemplateActionExecutorExample();
        executorExample.createCriteria().andSceneActionIdEqualTo(id).andValidEqualTo(SystemConstants.INT_YES);
        PaasSceneTemplateActionExecutor executor = new PaasSceneTemplateActionExecutor();
        executor.setValid(SystemConstants.INT_NO);
        paasSceneTemplateActionExecutorMapper.updateByExampleSelective(executor, executorExample);

        PaasSceneTemplateAction action = new PaasSceneTemplateAction();
        action.setId(id);
        action.setValid(SystemConstants.INT_NO);
        paasSceneTemplateActionMapper.updateByPrimaryKeySelective(action);
    }

    /**
     * @Description: 删除场景模板所有动作记录
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2020-12-28 17:25
     */
    private void deleteActionsRecord(Integer sceneTemplateId) {
        PaasSceneTemplateActionExample actionExample = new PaasSceneTemplateActionExample();
        actionExample.createCriteria().andSceneTemplateIdEqualTo(sceneTemplateId).andValidEqualTo(SystemConstants.INT_YES);
        List<PaasSceneTemplateAction> actions = paasSceneTemplateActionMapper.selectByExample(actionExample);
        for (PaasSceneTemplateAction action : actions) {
            this.deleteActionRecord(action.getId());
        }
    }


    @Override
    public ApiResponseEntity getAvailableRooms(Integer templateId, String projectId) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, customizeRoomMapper.getAvailableTemplateRooms(templateId, projectId));
        } catch (Exception e) {
            logger.error("【SceneTemplateImplements.getAvailableRoom】查询模板可关联房间列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getTemplateRooms(Integer templateId, String projectId) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, customizeRoomMapper.getTemplateRooms(templateId, projectId));
        } catch (Exception e) {
            logger.error("【SceneTemplateImplements.getTemplateRooms】查询模板关联房间列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity deleteTemplateScenes(TemplateScenesPo templateScenesPo) throws AppException {
        try {
            Integer templateId = templateScenesPo.getTemplateId();
            String[] sceneIds = templateScenesPo.getSceneIds();
            for (String sceneId : sceneIds) {
                PaasSceneInfoExample example = new PaasSceneInfoExample();
                example.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andSceneIdEqualTo(sceneId);
                List<PaasSceneInfo> sceneInfos = sceneInfoMapper.selectByExample(example);
                if (sceneInfos != null && sceneInfos.size() > 0) {
                    scenesServiceInterface.deleteScene(sceneInfos.get(0).getId());

                    PaasSceneTemplateSceneExample sceneExample = new PaasSceneTemplateSceneExample();
                    sceneExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES)
                            .andTemplateIdEqualTo(templateId).andSceneIdEqualTo(sceneId);
                    PaasSceneTemplateScene sceneTemplateScene = new PaasSceneTemplateScene();
                    sceneTemplateScene.setValid(SystemConstants.INT_NO);
                    sceneTemplateSceneMapper.updateByExampleSelective(sceneTemplateScene, sceneExample);
                }
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException app) {
            logger.error("【SceneTemplateImplements.deleteTemplateScenes】解除场景模板关联异常：" + app);
            throw app;
        } catch (Exception e) {
            logger.error("【SceneTemplateImplements.deleteTemplateScenes】解除场景模板关联异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
