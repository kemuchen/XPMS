package cn.xpms.system.framework.filter;

import cn.xpms.system.framework.constant.AuthConstant;
import cn.xpms.system.framework.util.sys.CookieUtil;
import cn.xpms.system.framework.util.sys.SysUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LogoutFilter
 * @Desc
 * @Author 柯雷
 * @Date 2020/10/28 15:20
 * @Version 1.0
 */
@Component
public class LogoutFilter extends HandlerInterceptorAdapter {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(LogoutFilter.class);

    /** 访问域名 */
    @Value("${system.sso.domain}")
    private String sso_domain;

    /**
     * @Description: 退出拦截器
     * @Params: [request, response, handler]
     * @return: boolean
     * @Author: 柯雷
     * @Date: 2020/10/28 15:06
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("【LogoutFilter.preHandle】退出拦截器");

        String cookie = CookieUtil.getCookieValue(request, AuthConstant.TOKEN);
        if (!SysUtil.isEmpty(cookie) && request.getRequestURI().contains(AuthConstant.LOGOUT_URI)) {
            // 重定向到认证中心的退出
            response.sendRedirect(sso_domain + AuthConstant.LOGOUT_URI);
        }
        return false;
    }
}