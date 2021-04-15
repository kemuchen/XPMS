package cn.xpms.sso.service.inter.login;

import cn.xpms.sso.bean.po.LoginEntity;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/18 13:46
 * @Version 1.0
 */
public interface LoginServiceInterface {

    /**
     * @Description: 登录处理
     * @Params: [loginEntity]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/18 13:58
     */
    ApiResponseEntity doLogin(LoginEntity loginEntity) throws AppException;

    /**
     * @Description: 登录
     * @Params: [request]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/18 17:18
     */
    ApiResponseEntity login(HttpServletRequest request, HttpServletResponse response,
                            LoginEntity loginEntity) throws AppException;

    /** 
     * @Description: 
     * @Params: [token]
     * @return: cn.xpms.system.system.dao.generator.entity.SysUser
     * @Author: 柯雷
     * @Date: 2020/11/19 9:00
     */ 
    ApiResponseEntity getUserByToken(String token) throws AppException;

    /**
     * @Description: 查询已登录的用户
     * @Params: [request]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/25 10:16
     */
    ApiResponseEntity getUserByToken(HttpServletRequest request) throws AppException;

    /**
     * @Description: 退出登录
     * @Params: [request, response, cookie]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/24 11:21
     */
    ApiResponseEntity logout(HttpServletRequest request, HttpServletResponse response,
                             String cookie) throws AppException;
}
