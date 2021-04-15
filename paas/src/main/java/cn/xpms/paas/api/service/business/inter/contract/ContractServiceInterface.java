package cn.xpms.paas.api.service.business.inter.contract;

import cn.xpms.paas.api.dao.generator.entity.PaasContractorAuthorization;
import cn.xpms.paas.api.dao.generator.entity.PaasWorkerInfo;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName ConstructionWorkerServiceInterface
 * @Desc 施工人员
 * @Author 柯雷
 * @Date 2020/11/27 9:11
 * @Version 1.0
 */
public interface ContractServiceInterface {

    /**
     * @Description: 施工商授权
     * @Params: [authorization]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:26
     */
    ApiResponseEntity contractorAuthorization(PaasContractorAuthorization authorization) throws AppException;

    /**
     * @Description: 解除施工商授权
     * @Params: [authorization_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:28
     */
    ApiResponseEntity contractorUnAuthorization(Integer id) throws AppException;

    /** 
     * @Description: 查询施工商列表
     * @Params: [user_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:30
     */ 
    ApiResponseEntity contractorAuthorizations(Integer user_id) throws AppException;
    
    /**
     * @Description: 新增施工人员
     * @Params: [paasWorkerInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:27
     */
    ApiResponseEntity contractionWorker(PaasWorkerInfo paasWorkerInfo) throws AppException;

    /**
     * @Description: 删除施工人员
     * @Params: [worker_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:29
     */
    ApiResponseEntity contractionUnWorker(Integer id) throws AppException;

    /**
     * @Description: 查询施工人员列表
     * @Params: [user_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:31
     */
    ApiResponseEntity contractionWokers(Integer user_id) throws AppException;
}