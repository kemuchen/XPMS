package cn.xpms.paas.api.web.space;

import cn.xpms.paas.api.dao.generator.entity.PaasSpaceInfo;
import cn.xpms.paas.api.service.business.inter.space.SpaceServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SpaceMoldPutController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/1 8:31
 * @Version 1.0
 */
@RestController
@Api(tags = "修改空间信息接口")
@RequestMapping("/api/paas/space/")
public class SpaceMoldPutController {

    /** 空间管理service */
    @Autowired
    SpaceServiceInterface spaceServiceInterface;

    /**
     * @Description: 修改空间信息
     * @Params: [paasSpaceInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 9:16
     */
    @ApiOperation("1.修改空间信息")
    @PutMapping(value = "space", produces = "application/json")
    public ApiResponseEntity space(@RequestBody PaasSpaceInfo paasSpaceInfo) throws AppException {
        return spaceServiceInterface.modifySpace(paasSpaceInfo);
    }
}