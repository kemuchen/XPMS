package cn.xpms.paas.api.web.space;

import cn.xpms.paas.api.service.business.inter.space.SpaceServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SpaceMoldDeleteController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/1 8:31
 * @Version 1.0
 */
@RestController
@Api(tags = "空间信息删除接口")
@RequestMapping("/api/paas/space/")
public class SpaceMoldDeleteController {

    /** 空间管理service */
    @Autowired
    SpaceServiceInterface spaceServiceInterface;

    /**
     * @Description: 删除空间
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 8:51
     */
    @ApiOperation("1.删除空间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "空间id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("space/{id}")
    public ApiResponseEntity space(@PathVariable("id") Integer id) throws AppException {
        return spaceServiceInterface.deleteSpace(id);
    }
}