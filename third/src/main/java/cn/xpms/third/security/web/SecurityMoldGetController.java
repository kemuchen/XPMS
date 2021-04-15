package cn.xpms.third.security.web;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.third.security.service.inter.BusinessSecurityService;
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
 * @ClassName SecurityMoldGetController
 * @Desc
 * @Author ycj
 * @Date 2020-11-14 15:04
 * @Version 1.0
 */
@RestController
@Api(tags = "公安网信息上传接口")
@RequestMapping("/api/security/")
public class SecurityMoldGetController {

    @Autowired
    BusinessSecurityService businessSecurityService;

    @ApiOperation("1.查询公安网上传记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "单号", required = true, paramType = "path", dataType = "string", defaultValue = "0")
    })
    @GetMapping("uploadInfo/{orderNo}")
    public ApiResponseEntity getUpInfosByOrderNo(@PathVariable("orderNo") String orderNo) throws AppException {
        return businessSecurityService.getUpInfosByOrderNo(orderNo);
    }
}
