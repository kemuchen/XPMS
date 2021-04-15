package cn.xpms.paas.api.web.space;

import cn.xpms.paas.api.dao.generator.entity.PaasSpaceInfo;
import cn.xpms.paas.api.service.business.inter.space.SpaceServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SpaceMoldPostController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/1 8:31
 * @Version 1.0
 */
@RestController
@Api(tags = "新增空间相关接口")
@RequestMapping("/api/paas/space/")
public class SpaceMoldPostController {

    /** 空间管理service */
    @Autowired
    SpaceServiceInterface spaceServiceInterface;

    /**
     * @Description: 新增空间信息
     * @Params: [paasSpaceInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 9:13
     */
    @ApiOperation("1.新增空间信息")
    @PostMapping(value = "space", produces = "application/json")
    public ApiResponseEntity space(@RequestBody PaasSpaceInfo paasSpaceInfo) throws AppException {
        return spaceServiceInterface.createSapce(paasSpaceInfo);
    }
}