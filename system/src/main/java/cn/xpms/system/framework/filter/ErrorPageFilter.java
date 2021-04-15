package cn.xpms.system.framework.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ErrorPageInterceptor
 * @Desc
 * @Author 柯雷
 * @Date 2020/10/29 17:08
 * @Version 1.0
 */
@Component
public class ErrorPageFilter extends HandlerInterceptorAdapter {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(ErrorPageFilter.class);

    /** 错误码 */
    private List<Integer> errorCodeList = Arrays.asList(404, 403, 500);

    /**
     * @Description: 拦截错误信息自定义处理
     * @Params: [request, response, handler]
     * @return: boolean
     * @Author: 柯雷
     * @Date: 2020/10/29 17:11
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        logger.info("【ErrorPageInterceptor.preHandle】请求异常：" + response.getStatus());
        if (errorCodeList.contains(response.getStatus())) {
            //捕获异常后进行重定向，controller对应的requestMapping为/error/{code}
            response.sendRedirect("/error/" + response.getStatus());
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
