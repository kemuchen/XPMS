package cn.xpms.paas.api.web.contract;

import cn.xpms.paas.api.service.business.inter.contract.ContractServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ContractMoldDeleteController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/27 9:35
 * @Version 1.0
 */
@RestController
@Api(tags = "施工商、施工人员信息删除接口")
@RequestMapping("/api/paas/contract/")
public class ContractMoldDeleteController {

    /** 施工商施工人员service */
    @Autowired
    ContractServiceInterface contractServiceInterface;

    /**
     * @Description: 解除施工商授权
     * @Params: [authorization_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:40
     */
    @ApiOperation("1.解除施工商授权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "施工商授权id", required = true,
                    paramType = "path", dataType = "Integer", defaultValue = "0")})
    @DeleteMapping("authorization/{id}")
    public ApiResponseEntity authorization(@PathVariable(value = "id")
                                                             Integer id) throws AppException {
        return contractServiceInterface.contractorUnAuthorization(id);
    }

    /**
     * @Description: 删除施工人员
     * @Params: [worker_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:41
     */
    @ApiOperation("2.删除施工人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "施工人员id", required = true,
                    paramType = "path", dataType = "Integer", defaultValue = "0")})
    @DeleteMapping("worker/{id}")
    public ApiResponseEntity woker(@PathVariable(value = "id")
                                                         Integer id) throws AppException {
        return contractServiceInterface.contractionUnWorker(id);
    }
}
