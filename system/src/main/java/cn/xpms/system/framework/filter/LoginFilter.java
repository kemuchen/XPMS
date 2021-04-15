package cn.xpms.system.framework.filter;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.AuthConstant;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.http.HttpUtil;
import cn.xpms.system.framework.util.sys.CookieUtil;
import cn.xpms.system.framework.util.sys.SysUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @ClassName LoginFilter
 * @Desc
 * @Author 柯雷
 * @Date 2020/10/28 15:02
 * @Version 1.0
 */
@Component
public class LoginFilter extends HandlerInterceptorAdapter {

    /**
     * 日志输出对象
     */
    Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    /** 校验登录url */
    @Value("${system.sso.domain}")
    private String sso_domain;

    /**
     * @Description: 登录拦截器
     * @Params: [request, response, handler]
     * @return: boolean
     * @Author: 柯雷
     * @Date: 2020/10/28 15:06
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ApiResponseEntity responseResultEntity = new ApiResponseEntity();
        try {
            logger.info("【LoginFilter.preHandle】登录拦截器");
            // 获取cookie
            String cookie = CookieUtil.getCookieValue(request, AuthConstant.TOKEN);
            // 未登录
            if (SysUtil.isEmpty(cookie)) {
                responseResultEntity = new ApiResponseEntity(SysErrorCode.UN_LOGIN);
            } else {
                String ret = HttpUtil.get(sso_domain + AuthConstant.CHECK_LOGIN_URI + cookie);
                responseResultEntity = new Gson().fromJson(
                        ret, ApiResponseEntity.class);
            }
            // 判断如果未通过登录校验，则返回错误
            if (responseResultEntity.getCode().equals(SysErrorCode.SUCCESS.getError_code())) {
                return true;
            } else {
                response.setCharacterEncoding(SystemConstants.DEFAULT_CHARSET);
                Writer writer = response.getWriter();
                writer.write(responseResultEntity.toString());
                writer.flush();
                writer.close();
                return false;
            }
        } catch (Exception e) {
            logger.error("【LoginFilter.preHandle】登录拦截器，系统异常");
            responseResultEntity = new ApiResponseEntity(SysErrorCode.SYSTEM_ERROR);
            response.setCharacterEncoding(SystemConstants.DEFAULT_CHARSET);
            Writer writer = response.getWriter();
            writer.write(responseResultEntity.toString());
            writer.flush();
            writer.close();
            return false;
        }
    }
}
