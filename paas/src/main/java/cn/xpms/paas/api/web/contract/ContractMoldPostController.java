package cn.xpms.paas.api.web.contract;

import cn.xpms.paas.api.dao.generator.entity.PaasContractorAuthorization;
import cn.xpms.paas.api.dao.generator.entity.PaasWorkerInfo;
import cn.xpms.paas.api.service.business.inter.contract.ContractServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ContractMoldPostController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/27 9:20
 * @Version 1.0
 */
@RestController
@Api(tags = "施工商、施工人员添加接口")
@RequestMapping("/api/paas/contract/")
public class ContractMoldPostController {

    /** 施工商、施工人员service */
    @Autowired
    ContractServiceInterface contractServiceInterface;

    /**
     * @Description: 施工商授权
     * @Params: [authorization]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:47
     */
    @ApiOperation("1.施工商授权")
    @PostMapping(value = "authorization", produces = "application/json")
    public ApiResponseEntity authorization(@RequestBody PaasContractorAuthorization
                                                             authorization) throws AppException {
        return contractServiceInterface.contractorAuthorization(authorization);
    }

    /**
     * @Description: 新增施工人员
     * @Params: [workerInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:48
     */
    @ApiOperation("2.新增施工人员")
    @PostMapping(value = "worker", produces = "application/json")
    public ApiResponseEntity worker(@RequestBody PaasWorkerInfo
                                                         workerInfo) throws AppException {
        return contractServiceInterface.contractionWorker(workerInfo);
    }
}