package cn.xpms.sso.service.impl.login;

import cn.xpms.sso.bean.code.UserStatusEnum;
import cn.xpms.sso.bean.error.LoginErrorCode;
import cn.xpms.sso.bean.po.LoginEntity;
import cn.xpms.sso.service.inter.login.LoginServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.AuthConstant;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.CookieUtil;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.system.system.cache.base.CacheBaseUtil;
import cn.xpms.system.system.dao.generator.entity.SysUser;
import cn.xpms.system.system.dao.generator.entity.SysUserExample;
import cn.xpms.system.system.dao.generator.repository.SysUserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LoginServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/18 13:58
 * @Version 1.0
 */
@Service
public class LoginServiceImplements implements LoginServiceInterface {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(LoginServiceImplements.class);

    /** 用户信息CURD */
    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * @Description: 登录处理
     * @Params: [loginEntity]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/18 13:59
     */
    @Override
    public ApiResponseEntity doLogin(LoginEntity loginEntity) throws AppException {
        try {
            SysUserExample sysUserExample = new SysUserExample();
            // 匹配用户登录名和密码
            sysUserExample.createCriteria()
                    .andLoginidEqualTo(loginEntity.getLoginid())
                    .andPasswordEqualTo(DigestUtils.md5Hex(loginEntity.getPassword()))
                    .andValidEqualTo(SystemConstants.INT_YES);
            List<SysUser> users = sysUserMapper.selectByExample(sysUserExample);
            if (SysUtil.isEmpty(users)) {
                // 用户不存在或密码错误
                throw new AppException(LoginErrorCode.LOGIN_ID_ERROR);
            }
            SysUser user = users.get(0);

            // 用户被锁定
            if (UserStatusEnum.LOCKED.getCode().equals(user.getStatus())) {
                throw new AppException(LoginErrorCode.USERNAME_LOCKED);
            }

            // 将合法用户存入缓存中
            String token = UUID.randomUUID().toString();
            CacheBaseUtil.set(AuthConstant.TOKEN + ":" + token, user, SystemConstants.DEFAULT_LOGIN_EXPIRE_TIME, TimeUnit.MINUTES);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, token);
        } catch (AppException e) {
            logger.error("【LoginServiceImplements.doLogin】登录异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【LoginServiceImplements.doLogin】登录异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 登录
     * @Params: [request]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/11/18 17:24
     */
    @Override
    public ApiResponseEntity login(HttpServletRequest request, HttpServletResponse response,
                                   LoginEntity loginEntity) throws AppException {
        try {
            // 登录处理
            ApiResponseEntity apiResponseResultEntity = this.doLogin(loginEntity);
            if (!apiResponseResultEntity.getCode().equals(SysErrorCode.SUCCESS.getError_code())) {
                throw new AppException(SysErrorCode.UN_LOGIN);
            }
            CookieUtil.setCookie(request, response, AuthConstant.TOKEN, (String) apiResponseResultEntity.getData());
            return apiResponseResultEntity;
        } catch (AppException e) {
            logger.error("【LoginServiceImplements.login】登录异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【LoginServiceImplements.login】登录异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 根据token查询用户信息
     * @Params: [token]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/19 9:04
     */
    @Override
    public ApiResponseEntity getUserByToken(String token) throws AppException {
        try {
            SysUser user = CacheBaseUtil.getCacheData(AuthConstant.TOKEN + ":" + token);
            if (SysUtil.isEmpty(user)) {
                throw new AppException(SysErrorCode.UN_LOGIN);
            }
            // 延长用户有效期
            CacheBaseUtil.set(token, user, SystemConstants.DEFAULT_LOGIN_EXPIRE_TIME, TimeUnit.MINUTES);
            return new ApiResponseEntity(SysErrorCode.SUCCESS.getError_code(), user);
        } catch (AppException e) {
            logger.error("【LoginServiceImplements.getUserByToken】查询用户信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【LoginServiceImplements.getUserByToken】查询用户信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询已登录的用户
     * @Params: [request]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/25 10:17
     */
    @Override
    public ApiResponseEntity getUserByToken(HttpServletRequest request) throws AppException {
        try {
            String token = CookieUtil.getCookieValue(request, AuthConstant.TOKEN);
            return this.getUserByToken(token);
        } catch (AppException e) {
            logger.error("【LoginServiceImplements.getUserByToken】查询已登录的用户异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【LoginServiceImplements.getUserByToken】查询已登录的用户异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 退出登录
     * @Params: [request, response, cookie]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/24 11:21
     */
    @Override
    public ApiResponseEntity logout(HttpServletRequest request, HttpServletResponse response, String cookie) throws AppException {
        try {
            // 删除cookie
            CookieUtil.deleteCookie(request, response, cookie);
            // 删除缓存
            CacheBaseUtil.delete(AuthConstant.TOKEN + ":" + cookie);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【LoginServiceImplements.logout】退出登录失败：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【LoginServiceImplements.logout】退出登录失败：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}