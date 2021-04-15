package cn.xpms.paas.api.service.business.inter.device;

import cn.xpms.paas.api.dao.generator.entity.PaasDoorLockPassword;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

import java.util.Date;

/**
 * @ClassName DoorLockServiceInterface
 * @Desc
 * @Author ycj
 * @Date 2021-01-04 10:12
 * @Version 1.0
 */
public interface DoorLockServiceInterface {

    ApiResponseEntity createTempPassword(PaasDoorLockPassword password) throws AppException;

    ApiResponseEntity deleteTempPassword(Integer id) throws AppException;

    ApiResponseEntity openWithoutPassword(String device_id) throws AppException;

    ApiResponseEntity getOpenLogs(String device_id, Integer page_no, Integer page_size, Date start_time, Date end_time) throws AppException;
}
