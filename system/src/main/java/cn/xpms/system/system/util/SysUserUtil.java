package cn.xpms.system.system.util;

import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.AuthConstant;
import cn.xpms.system.framework.util.sys.CookieUtil;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.system.system.cache.base.CacheBaseUtil;
import cn.xpms.system.system.dao.generator.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SysUserUtil
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/4 13:50
 * @Version 1.0
 */
public class SysUserUtil {

    /** 日志打印对象 */
    static Logger logger = LoggerFactory.getLogger(SysUserUtil.class);

    /**
     * @Description: 获取操作员id
     * @Params: [joinPoint]
     * @return: java.lang.Integer
     * @Author: 柯雷
     * @Date: 2020/12/4 13:39
     */
    public static Integer getOperateUserId() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes)
                    RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            return getOperateUserId(request).getId();
        } catch (Exception e) {
            logger.error("【InsertOperatorAspect.getOperateUserId】获取登录用户异常：" + e);
            return 0;
        }
    }

    /**
     * @Description: 获取操作员id
     * @Params: [request]
     * @return: java.lang.Integer
     * @Author: 柯雷
     * @Date: 2020/12/4 11:59
     */
    public static SysUser getOperateUserId(HttpServletRequest request) throws AppException {
        try {
            String cookie = CookieUtil.getCookieValue(request, AuthConstant.TOKEN);
            SysUser user = CacheBaseUtil.getCacheData(AuthConstant.TOKEN + ":" + cookie);
            if (SysUtil.isEmpty(user)) {
                throw new AppException(SysErrorCode.UN_LOGIN);
            } else {
                return user;
            }
        } catch (Exception e) {
            logger.error("【InsertOperatorAspect.getOperateUserId】获取登录用户异常：" + e);
            throw new AppException(SysErrorCode.UN_LOGIN);
        }
    }
}