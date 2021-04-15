package cn.xpms.sso.web.privilege;

import cn.xpms.sso.service.inter.privilege.PrivilegeServiceInterface;
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
 * @ClassName PrivilegeMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/25 14:58
 * @Version 1.0
 */
@RestController
@Api(tags = "权限查询相关接口")
@RequestMapping("/api/system/privilege/")
public class PrivilegeMoldGetController {

    /**
     * 权限业务处理service
     */
    @Autowired
    PrivilegeServiceInterface privilegeServiceInterface;


    /**
     * @Description: 查询用户拥有的子系统菜单路由
     * @Params: [user_id, hotel_id, system_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-09 15:35
     */
    @ApiOperation("1.查询用户拥有的子系统菜单路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户ID", required = true, paramType = "path", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "hotel_id", value = "酒店ID", required = true, paramType = "path", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "system_id", value = "子系统ID", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")})
    @GetMapping("/menuRouter/{user_id}/{hotel_id}/{system_id}")
    public ApiResponseEntity getUserSystemRightOperate(@PathVariable(value = "user_id") Integer user_id,
                                                       @PathVariable(value = "hotel_id") Integer hotel_id,
                                                       @PathVariable(value = "system_id") Integer system_id) throws AppException {
        return privilegeServiceInterface.getUserSystemRightOperate(user_id, hotel_id, system_id);
    }

}