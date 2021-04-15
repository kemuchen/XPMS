package cn.xpms.paas.api.service.business.inter.task;

import cn.xpms.paas.api.bean.dto.task.ConstructionTemplateTaskPo;
import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTask;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName ConstructionTaskServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/4 8:58
 * @Version 1.0
 */
public interface ConstructionTaskServiceInterface {

    /**
     * @Description: 获取施工任务列表
     * @Params: [task]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/4 9:42
     */
    ApiResponseEntity getTasks(PaasConstructionTask task) throws AppException;

    /**
     * @Description: 获取施工任务详情
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/4 9:42
     */
    ApiResponseEntity getTask(Integer id) throws AppException;

    /**
     * @Description: 创建模板施工任务
     * @Params: [taskVo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/4 9:40
     */
    ApiResponseEntity createTemplateTask(ConstructionTemplateTaskPo taskPo) throws AppException;

    /**
     * @Description: 施工任务重开房
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/4 9:43
     */
    ApiResponseEntity reopenTask(Integer id) throws AppException;

    /**
     * @Description: 施工任务验收
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/4 10:34
     */
    ApiResponseEntity acceptanceTask(Integer id) throws AppException;

    /**
     * @Description: 删除施工任务
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 12:03
     */
    ApiResponseEntity deleteTask(Integer id) throws AppException;
}