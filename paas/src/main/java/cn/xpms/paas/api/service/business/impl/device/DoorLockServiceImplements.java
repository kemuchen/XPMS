package cn.xpms.paas.api.service.business.impl.device;

import cn.xpms.paas.api.bean.constant.Constant;
import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.dao.generator.entity.PaasDoorLockPassword;
import cn.xpms.paas.api.dao.generator.repository.PaasDoorLockPasswordMapper;
import cn.xpms.paas.api.service.business.inter.device.DoorLockServiceInterface;
import cn.xpms.paas.api.service.common.inter.api.PaasApiServiceInterface;
import cn.xpms.paas.api.util.PaasConfigUtil;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.util.encrypt.aes.AESUtil;
import cn.xpms.system.framework.util.sys.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DoorLockServiceImplements
 * @Desc
 * @Author ycj
 * @Date 2021-01-04 10:12
 * @Version 1.0
 */
@Service
public class DoorLockServiceImplements implements DoorLockServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(DoorLockServiceImplements.class);

    /**
     * 涂鸦API接口service
     */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    @Autowired
    PaasDoorLockPasswordMapper doorLockPasswordMapper;

    private Map<String, Object> getTicket(String device_id) throws AppException {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("device_id", device_id);

            // 调用涂鸦接口
            PaasApiResponseEntity<Map<String, Object>> responseEntity =
                    paasApiServiceInterface.call(PaasApiEnum.GET_PASSWORD_TICKET, params, Map.class);

            if (responseEntity.getSuccess()) {
                return responseEntity.getResult();
            } else {
                return null;
            }
        } catch (AppException e) {
            logger.error("【DoorLockServiceImplements.getTicketKey】获取密码加密临时加密秘钥异常：" + e);
            throw e;
        }
    }

    @Override
    public ApiResponseEntity createTempPassword(PaasDoorLockPassword password) throws AppException {
        try {
            Map<String, Object> ticket = this.getTicket(password.getDeviceId());
            if (ticket == null) {
                throw new AppException(SysErrorCode.SYSTEM_ERROR.getError_code(), "获取密码加密临时加密秘钥失败",
                        "获取密码加密临时加密秘钥失败");
            }
            String ticket_id = ticket.get("ticket_id").toString();
            String ticket_key = ticket.get("ticket_key").toString();

            password.setTicketId(ticket_id);
            password.setType(1);            // 一次有效
            password.setPhase(1);           // 密码状态 zigbee 1待创建 2正常 3冻结 4已删除 5创建失败
            doorLockPasswordMapper.insertSelective(password);

            // accessKey配置
            String accessKey = PaasConfigUtil.getPassConfigValue(Constant.ACCESS_KEY);
            password.setPassword(AESUtil.encryptAES(password.getPassword(), AESUtil.decryptAES(ticket_key, accessKey)));

            Map<String, Object> params = BeanUtil.beanToMap(password, true);
            params.put("effective_time", password.getEffectiveTime().getTime() / 1000);
            params.put("invalid_time", password.getInvalidTime().getTime() / 1000);

            // 调用涂鸦接口
            PaasApiResponseEntity<Map<String, Object>> responseEntity = paasApiServiceInterface.call(PaasApiEnum.CREATE_TEMP_PASSWORD, params);

            PaasDoorLockPassword record = new PaasDoorLockPassword();
            record.setId(password.getId());
            if (responseEntity.getSuccess()) {
                Map<String, Object> result = responseEntity.getResult();
                String password_id = result.get("id").toString();
                record.setPasswordId(password_id);
                record.setPhase(2);
                doorLockPasswordMapper.updateByPrimaryKeySelective(record);
                return new ApiResponseEntity(SysErrorCode.SUCCESS, responseEntity.getResult());
            } else {
                record.setPhase(5);
                doorLockPasswordMapper.updateByPrimaryKeySelective(record);
                return new ApiResponseEntity(SysErrorCode.SYSTEM_ERROR.getError_code(), responseEntity.getMsg());
            }
        } catch (AppException e) {
            logger.error("【DoorLockServiceImplements.createTempPassword】创建临时密码异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【DoorLockServiceImplements.createTempPassword】创建临时密码异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity deleteTempPassword(Integer id) throws AppException {
        try {
            PaasDoorLockPassword password = doorLockPasswordMapper.selectByPrimaryKey(id);
            if (password == null) {
                throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "未查询到密码记录", "未查询到密码记录");
            }

            Map<String, Object> param = new HashMap<>();
            param.put("device_id", password.getDeviceId());
            param.put("password_id", password.getPasswordId());
            // 调用涂鸦接口
            PaasApiResponseEntity<Boolean> responseEntity = paasApiServiceInterface.call(PaasApiEnum.DELETE_TEMP_PASSWORD, param);

            if (responseEntity.getSuccess() && responseEntity.getResult()) {
                return new ApiResponseEntity(SysErrorCode.SUCCESS.getError_code(), "删除密码成功");
            } else {
                return new ApiResponseEntity(SysErrorCode.SYSTEM_ERROR.getError_code(), responseEntity.getMsg());
            }

        } catch (Exception e) {
            logger.error("【DoorLockServiceImplements.deleteTempPassword】删除临时密码异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity openWithoutPassword(String device_id) throws AppException {
        try {
            Map<String, Object> ticket = this.getTicket(device_id);
            if (ticket == null) {
                throw new AppException(SysErrorCode.SYSTEM_ERROR.getError_code(), "获取密码加密临时加密秘钥失败",
                        "获取密码加密临时加密秘钥失败");
            }
            String ticket_id = ticket.get("ticket_id").toString();

            Map<String, Object> param = new HashMap<>();
            param.put("device_id", device_id);
            param.put("ticket_id", ticket_id);
            // 调用涂鸦接口
            PaasApiResponseEntity<Boolean> responseEntity = paasApiServiceInterface.call(PaasApiEnum.OPEN_DOOR_WITHOUT_PASSWORD, param);

            if (responseEntity.getSuccess() && responseEntity.getResult()) {
                return new ApiResponseEntity(SysErrorCode.SUCCESS.getError_code(), "门锁免密开锁成功");
            } else {
                return new ApiResponseEntity(SysErrorCode.SYSTEM_ERROR.getError_code(), responseEntity.getMsg());
            }
        } catch (Exception e) {
            logger.error("【DoorLockServiceImplements.openWithoutPassword】门锁免密开锁异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getOpenLogs(String device_id, Integer page_no, Integer page_size, Date start_time, Date end_time) throws AppException {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("device_id", device_id);
            param.put("page_no", page_no);
            param.put("page_size", page_size);
            param.put("start_time", start_time.getTime() / 1000);
            param.put("end_time", end_time.getTime() / 1000);
            // 调用涂鸦接口
            PaasApiResponseEntity<Map<String, Object>> responseEntity = paasApiServiceInterface.call(PaasApiEnum.GET_DOOR_OPEN_LOGS, param);

            if (responseEntity.getSuccess()) {
                return new ApiResponseEntity(SysErrorCode.SUCCESS, responseEntity.getResult());
            } else {
                return new ApiResponseEntity(SysErrorCode.SYSTEM_ERROR.getError_code(), responseEntity.getMsg());
            }
        } catch (Exception e) {
            logger.error("【DoorLockServiceImplements.getOpenLogs】查询开门记录异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
