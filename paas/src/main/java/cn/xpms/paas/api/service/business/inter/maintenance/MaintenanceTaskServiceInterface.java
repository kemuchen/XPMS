package cn.xpms.paas.api.service.business.inter.maintenance;

import cn.xpms.paas.api.bean.dto.task.MaintenanceTaskPo;
import cn.xpms.paas.api.dao.generator.entity.PaasMaintenanceTask;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName MaintenanceTaskServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/5 14:46
 * @Version 1.0
 */
public interface MaintenanceTaskServiceInterface {

    /**
     * @Description: 获取维修任务列表
     * @Params: [task]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/7 15:31
     */
    ApiResponseEntity getTasks(PaasMaintenanceTask task) throws AppException;

    /**
     * @Description: 获取维修任务详情
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/7 15:31
     */
    ApiResponseEntity getTask(Integer id) throws AppException;

    /**
     * @Description: 创建维修任务
     * @Params: [maintenanceTaskPo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 15:16
     */
    ApiResponseEntity createTask(MaintenanceTaskPo maintenanceTaskPo) throws AppException;

    /**
     * @Description: 维修任务重开放
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 15:17
     */
    ApiResponseEntity reopenTask(Integer id) throws AppException;

    /**
     * @Description: 验收维修任务
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 15:18
     */
    ApiResponseEntity acceptanceTask(Integer id) throws AppException;

    /**
     * @Description: 删除维修任务
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 15:19
     */
    ApiResponseEntity deleteTask(Integer id) throws AppException;
}