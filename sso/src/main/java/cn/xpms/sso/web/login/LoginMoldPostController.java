package cn.xpms.sso.web.login;

import cn.xpms.sso.bean.po.LoginEntity;
import cn.xpms.sso.service.inter.login.LoginServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginMoldPostController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/24 11:06
 * @Version 1.0
 */
@RestController()
@Api(tags = "用户登录")
@RequestMapping("/api/sso/login/")
public class LoginMoldPostController {


    /** 登录service */
    @Autowired
    LoginServiceInterface loginServiceInterface;

    /**
     * @Description: 登录
     * @Params: [request]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/11/18 17:22
     */
    @ApiOperation("1.用户登录")
    @PostMapping(value = "login", produces = "application/json")
    public ApiResponseEntity login(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody LoginEntity loginEntity) throws AppException {
        return loginServiceInterface.login(request, response, loginEntity);
    }
}