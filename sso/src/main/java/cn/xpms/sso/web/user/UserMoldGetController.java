package cn.xpms.sso.web.user;

import cn.xpms.sso.service.inter.user.UserServiceInterface;
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
 * @ClassName UserMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/25 11:09
 * @Version 1.0
 */
@RestController
@Api(tags = "用户相关信息查询")
@RequestMapping("/api/system/user")
public class UserMoldGetController {

    /**
     * 业务操作service
     */
    @Autowired
    UserServiceInterface userServiceInterface;

    /**
     * @Description: 查询用户拥有操作权限的酒店(门店)
     * @Params: [user_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-09 11:09
     */
    @ApiOperation("1.查询用户拥有操作权限的酒店(门店)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户ID", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")})
    @GetMapping("/hotels/operate/{user_id}")
    public ApiResponseEntity operateHotels(@PathVariable(value = "user_id") Integer user_id) throws AppException {
        return userServiceInterface.getUserHotelOperate(user_id);
    }

    /**
     * @Description: 查询用户可授权的酒店(门店)
     * @Params: [user_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-09 11:13
     */
    @ApiOperation("2.查询用户可授权的酒店(门店)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户ID", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")})
    @GetMapping("/hotels/auth/{user_id}")
    public ApiResponseEntity authHotels(@PathVariable(value = "user_id") Integer user_id) throws AppException {
        return userServiceInterface.getUserHotelAuth(user_id);
    }

    @ApiOperation("3.查询用户门店拥有操作权限的子系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户ID", required = true, paramType = "path", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "hotel_id", value = "酒店ID", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")})
    @GetMapping("/hotel/subsys/operate/{user_id}/{hotel_id}")
    public ApiResponseEntity operateSubSys(@PathVariable(value = "user_id") Integer user_id,
                                           @PathVariable(value = "hotel_id") Integer hotel_id) throws AppException {
        return userServiceInterface.getUserSubSystem(user_id, hotel_id);
    }
}