package cn.xpms.paas.api.service.business.impl.contract;

import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.PaasContractorAuthorizationMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasWorkerInfoMapper;
import cn.xpms.paas.api.service.business.inter.contract.ContractServiceInterface;
import cn.xpms.paas.api.service.common.inter.api.PaasApiServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @ClassName ContractServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/27 9:22
 * @Version 1.0
 */
@Service
public class ContractServiceImplements implements ContractServiceInterface {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(ContractServiceImplements.class);

    /** api接口调用service */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    /** 施工商授权CURD */
    @Autowired
    PaasContractorAuthorizationMapper paasContractorAuthorizationMapper;

    /** 施工人员CURD */
    @Autowired
    PaasWorkerInfoMapper paasWorkerInfoMapper;

    /**
     * @Description: 施工商授权
     * @Params: [authorization]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:33
     */
    @Override
    public ApiResponseEntity contractorAuthorization(PaasContractorAuthorization authorization) throws AppException {
        try {
            // 调用涂鸦接口
            PaasApiResponseEntity<String> apiResponseData = paasApiServiceInterface.call(PaasApiEnum.CONTRACTOR_AUTHORIZATION,
                    BeanUtil.beanToMap(authorization, true), String.class);
            authorization.setAuthorizationId(apiResponseData.getResult());
            authorization.setUserId(authorization.getCreateUser());
            authorization.setModifyUser(authorization.getModifyUser());
            // 新增施工商授权
            paasContractorAuthorizationMapper.insertSelective(authorization);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ContractServiceImplements.contractorAuthorization】新增施工商授权异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ContractServiceImplements.contractorAuthorization】新增施工商授权异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 根据id查询施工商授权信息
     * @Params: [id]
     * @return: cn.xpms.paas.api.dao.generator.entity.PaasContractorAuthorization
     * @Author: 柯雷
     * @Date: 2020/11/30 10:38
     */
    private PaasContractorAuthorization getContractorAuthorization(Integer id) throws AppException {
        try {
            return paasContractorAuthorizationMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("【ContractServiceImplements.getContractorAuthorization】根据id查询施工商授权信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 解除施工商授权
     * @Params: [authorization_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:34
     */
    @Override
    public ApiResponseEntity contractorUnAuthorization(Integer id) throws AppException {
        try {
            PaasContractorAuthorization authorization = this.getContractorAuthorization(id);

            // 调用涂鸦接口
            paasApiServiceInterface.call(PaasApiEnum.CONTRACTOR_UN_AUTHORIZATION,
                    BeanUtil.beanToMap(authorization, true));
            // 解除施工商授权
            authorization.setValid(SystemConstants.INT_NO);
            paasContractorAuthorizationMapper.updateByPrimaryKey(authorization);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ContractServiceImplements.contractorUnAuthorization】解除施工商授权异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ContractServiceImplements.contractorUnAuthorization】解除施工商授权异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工商列表
     * @Params: [user_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:34
     */
    @Override
    public ApiResponseEntity contractorAuthorizations(Integer user_id) throws AppException {
        try {
            PaasContractorAuthorizationExample example = new PaasContractorAuthorizationExample();
            example.createCriteria().andUserIdEqualTo(user_id).andValidEqualTo(SystemConstants.INT_YES);
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    paasContractorAuthorizationMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【ContractServiceImplements.contractorAuthorizations】查询施工商列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 新增施工人员
     * @Params: [paasWorkerInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:34
     */
    @Override
    public ApiResponseEntity contractionWorker(PaasWorkerInfo paasWorkerInfo) throws AppException {
        try {
            // 调用涂鸦接口
            PaasApiResponseEntity<String> apiResponseData = paasApiServiceInterface.call(PaasApiEnum.CONSTRUCTION_WORKER,
                    BeanUtil.beanToMap(paasWorkerInfo, true));
            paasWorkerInfo.setWorkerId(apiResponseData.getResult());
            // 新增施工人员
            paasWorkerInfoMapper.insertSelective(paasWorkerInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ContractServiceImplements.contractionWorker】新增施工人员异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ContractServiceImplements.contractionWorker】新增施工人员异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 根据id查询施工人员信息
     * @Params: [id]
     * @return: cn.xpms.paas.api.dao.generator.entity.PaasWorkerInfo
     * @Author: 柯雷
     * @Date: 2020/11/30 10:45
     */
    private PaasWorkerInfo getWorkerInfo(Integer id) throws AppException {
        try {
            return paasWorkerInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("【ContractServiceImplements.getWorkerInfo】根据id查询施工人员信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除施工人员
     * @Params: [worker_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:34
     */
    @Override
    public ApiResponseEntity contractionUnWorker(Integer id) throws AppException {
        try {
            PaasWorkerInfo workerInfo = this.getWorkerInfo(id);
            // 调用涂鸦接口
            paasApiServiceInterface.call(PaasApiEnum.CONSTRUCTION_UN_WORKER,
                    BeanUtil.beanToMap(workerInfo, true));
            // 删除施工人员
            workerInfo.setValid(SystemConstants.INT_NO);
            paasWorkerInfoMapper.updateByPrimaryKeySelective(workerInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ContractServiceImplements.contractionUnWorker】删除施工人员异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ContractServiceImplements.contractionUnWorker】删除施工人员异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工人员列表
     * @Params: [user_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:34
     */
    @Override
    public ApiResponseEntity contractionWokers(Integer user_id) throws AppException {
        try {
            PaasWorkerInfoExample example = new PaasWorkerInfoExample();
            example.createCriteria().andCreateUserEqualTo(user_id).andValidEqualTo(SystemConstants.INT_YES);
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    paasWorkerInfoMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【ContractServiceImplements.contractionWokers】查询施工人员列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}