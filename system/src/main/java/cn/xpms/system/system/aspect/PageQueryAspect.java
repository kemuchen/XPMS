package cn.xpms.system.system.aspect;

import cn.xpms.system.framework.beans.annotation.PageQuery;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PageQueryAspect
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/4 10:41
 * @Version 1.0
 */
@Aspect
@Component
public class PageQueryAspect {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(PageQueryAspect.class);

    /** 起始记录数 */
    Integer start;

    /** 结束记录数 */
    Integer end;

    /** 分页大小 */
    Integer page_size;

    /** 分页页数 */
    Integer page_no;

    /**
     * @Description: 根据自定义注解切入
     * @Params: []
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/4 10:55
     */
    @Pointcut("@annotation(cn.xpms.system.framework.beans.annotation.PageQuery)")
    public void pageQueryPointCut() {
    }

    /**
     * @Description: 初始化
     * @Params: [point]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/4 10:57
     */
    private void init(ProceedingJoinPoint point) throws AppException {
        try {
            //获取RequestAttributes
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            //从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

            // 请求中传入的参数
            String pageSize = request.getParameter(SystemConstants.PAGE_SIZE_PARAM_NAME);
            String pageNo = request.getParameter(SystemConstants.PAGE_NO_PARAM_NAME);

            // 注解中默认的参数
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            PageQuery pageQuery = method.getAnnotation(PageQuery.class);

            // 优先使用请求传入的参数
            try {
                page_size = Integer.parseInt(pageSize);
            } catch (Exception e) {
                logger.error("【PageQueryAspect.point】请求传入的页面参数异常：" + pageSize);
                page_size = pageQuery.pageSize();
            }
            try {
                page_no = Integer.parseInt(pageNo);
            } catch (Exception e) {
                logger.error("【PageQueryAspect.point】请求传入的页面参数异常：" + pageNo);
                page_no = pageQuery.current();
            }
            start = (page_no - 1) * page_size;
            end = page_no * page_size;
        } catch (Exception e) {
            logger.error("【PageQueryAspect.init】初始化异常：" + e);
            throw new AppException(SysErrorCode.PARAMS_CHECK_ERROR);
        }
    }

    /**
     * @Description: 切入操作
     * @Params: [point]
     * @return: java.lang.Object
     * @Author: 柯雷
     * @Date: 2020/12/4 10:56
     */
    @Around("pageQueryPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        logger.info("【PageQueryAspect.around】处理分页查询请求");
        try{
            // 初始化分页信息
            init(point);
            // 执行方法并处理分页结果
            return handlePageResult(point.proceed());
        } catch (AppException e) {
            logger.error("【PageQueryAspect.around】分页查询请求失败:" + e);
            return e;
        } catch (Exception e) {
            logger.error("【PageQueryAspect.around】分页查询请求失败:" + e);
            return new ApiResponseEntity(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 处理分页
     * @Params: [result]
     * @return: java.lang.Object
     * @Author: 柯雷
     * @Date: 2020/12/4 11:04
     */
    private Object handlePageResult(Object result) throws AppException {
        try {
            // 当返回为ApiResponseResultEntity时进行处理，否则不处理
            if (result instanceof ApiResponseEntity) {
                ApiResponseEntity responseEntity = (ApiResponseEntity) result;
                Object data = responseEntity.getData();
                if (data instanceof List) {
                    // 当返回的数据为List时
                    List<Object> list = (List) data;
                    if (list.size() > start) {
                        Map<String, Object> response = new HashMap<>();
                        response.put("total", list.size());
                        response.put("data", list.subList(start, Math.min(end, list.size())));
                        responseEntity.setData(response);
                    } else {
                        Map<String, Object> response = new HashMap<>();
                        response.put("total", list.size());
                        response.put("data", new ArrayList<>());
                        responseEntity.setData(response);
                    }
                }
                return responseEntity;
            } else {
                return result;
            }
        } catch (Exception e) {
            logger.error("【PageQueryAspect.around】分页查询请求失败:" + e);
            return result;
        }
    }
}