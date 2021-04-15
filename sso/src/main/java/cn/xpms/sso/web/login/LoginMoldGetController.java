package cn.xpms.sso.web.login;

import cn.xpms.sso.service.inter.login.LoginServiceInterface;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录和注销控制器
 * 
 * @author sheefee
 * @date 2017年9月12日 下午2:09:47
 *
 */
@RestController()
@Api(tags = "用户登录校验及退出")
@RequestMapping("/api/sso/login/")
public class LoginMoldGetController {

	/** 登录service */
	@Autowired
	LoginServiceInterface loginServiceInterface;

	/**
	 * @Description: 用户登录校验
	 * @Params: [cookie]
	 * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
	 * @Author: 柯雷
	 * @Date: 2020/11/24 11:14
	 */
	@ApiOperation("1.用户登录校验")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cookie", value = "cookie", required = true, paramType = "path", dataType = "string", defaultValue = "0")})
	@GetMapping("/check/{cookie}")
	public ApiResponseEntity check(@PathVariable(value = "cookie") String cookie) throws AppException {
		return loginServiceInterface.getUserByToken(cookie);
	}

	/**
	 * @Description: 注销
	 * @Params: [request, response]
	 * @return: java.lang.String
	 * @Author: 柯雷
	 * @Date: 2020/11/19 8:59
	 */
	@ApiOperation("2.退出登录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cookie", value = "cookie", required = true, paramType = "path", dataType = "string", defaultValue = "0")})
	@GetMapping("/logout/{cookie}")
	public ApiResponseEntity logout(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable(value = "cookie") String cookie) throws AppException {
		return loginServiceInterface.logout(request, response, cookie);
	}

	/**
	 * @Description: 查询登录用户
	 * @Params: [request]
	 * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
	 * @Author: 柯雷
	 * @Date: 2020/11/25 10:15
	 */
	@ApiOperation("3.查询已登录的用户")
	@GetMapping("/user")
	public ApiResponseEntity userByToken(HttpServletRequest request) throws AppException {
		return loginServiceInterface.getUserByToken(request);
	}
}