package cn.xpms.paas.api.web.scene;

import cn.xpms.paas.api.service.business.inter.scene.ScenesServiceInterface;
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
 * @ClassName SceneMoldDeleteController
 * @Desc
 * @Author ycj
 * @Date 2020-12-15 17:10
 * @Version 1.0
 */
@RestController
@Api(tags = "场景")
@RequestMapping("/api/paas/scene/")
public class SceneMoldDeleteController {

    @Autowired
    ScenesServiceInterface scenesService;


    @ApiOperation("1.删除场景")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景id", paramType = "path", required = true, dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("scene/{id}")
    public ApiResponseEntity deleteScene(@PathVariable("id") Integer id) throws AppException {
        return scenesService.deleteScene(id);
    }
}
