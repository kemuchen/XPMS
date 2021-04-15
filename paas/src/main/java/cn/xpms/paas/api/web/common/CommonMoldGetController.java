package cn.xpms.paas.api.web.common;

import cn.xpms.paas.api.service.common.inter.config.PaasDictServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CommonMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/3 11:25
 * @Version 1.0
 */
@RestController
@Api(tags = "公共查询相关接口")
@RequestMapping("/api/paas/common/")
public class CommonMoldGetController {

    /** 字典项查询service */
    @Autowired
    PaasDictServiceInterface paasDictServiceInterface;

    /**
     * @Description: 查询字典项
     * @Params: [code]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:28
     */
    @ApiOperation("1.查询字典项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "字典项类别代码", required = true, paramType = "path", dataType = "string", defaultValue = "0")
    })
    @GetMapping("dicts/{code}")
    public ApiResponseEntity dicts(@PathVariable("code") String code) throws AppException {
        return  paasDictServiceInterface.getDicts(code);
    }
}