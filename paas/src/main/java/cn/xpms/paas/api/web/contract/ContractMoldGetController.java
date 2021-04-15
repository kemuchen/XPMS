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
 * @ClassName ContractMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/27 9:35
 * @Version 1.0
 */
@RestController
@Api(tags = "施工商、施工人员查询接口")
@RequestMapping("/api/paas/contract/")
public class ContractMoldGetController {

    /** 施工商、施工人员service */
    @Autowired
    ContractServiceInterface contractServiceInterface;

    /**
     * @Description: 查询施工商列表
     * @Params: [user_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:43
     */
    @ApiOperation("1.查询施工商列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户id", required = true,
                    paramType = "path", dataType = "Integer", defaultValue = "0")})
    @GetMapping("authorization/{user_id}")
    public ApiResponseEntity authorization(@PathVariable(value = "user_id")
                                                 Integer user_id) throws AppException {
        return contractServiceInterface.contractorAuthorizations(user_id);
    }

    /**
     * @Description: 查询施工人员列表
     * @Params: [user_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/27 9:44
     */
    @ApiOperation("2.查询施工人员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户id", required = true,
                    paramType = "path", dataType = "Integer", defaultValue = "0")})
    @GetMapping("worker/{user_id}")
    public ApiResponseEntity woker(@PathVariable(value = "user_id")
                                                     Integer user_id) throws AppException {
        return contractServiceInterface.contractionWokers(user_id);
    }
}
