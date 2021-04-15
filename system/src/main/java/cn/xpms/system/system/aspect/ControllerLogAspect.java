/**  
 * flies   
 */
package cn.xpms.system.system.aspect;

import cn.xpms.system.framework.util.sys.SysUtil;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * @Package: cn.xpms
 * @ClassName: WebLogAspect
 * @Description:AOP切面打印日志
 * @Author: Yk
 * @Date: 2020年4月11日 下午6:29:02
 * @Version 1.0
 * @Copyright:
 */
//@Aspect
@Component
public class ControllerLogAspect {
	
	/**	
	* 	打印格式解释:
	* 	URL: 请求接口地址；
	*	HTTP Method: 请求的方法，是 POST, GET, 还是 DELETE 等；
	*	Class Method: 对应 Controller 的全路径以及调用的哪个方法;
	*	IP: 请求 IP 地址；
	*	Request Args: 请求入参，以 JSON 格式输出；
	*	Response Args: 响应出参，以 JSON 格式输出；
	*	Time-Consuming: 请求耗时；
	*/
	private final static Logger logger = LoggerFactory
			.getLogger(ControllerLogAspect.class);

	/** 以 controller 包下定义的所有请求为切入点 */
	@Pointcut("execution(public * cn.xpms..*..web..*.*(..))")
	public void webLog() {
	}

	/**
	 * 在切点之前织入
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 开始打印请求日志
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 打印请求相关参数
		logger.info("========================================== Controller-Start ==========================================");
		// 打印请求 url
		logger.info("URL            : {}", request.getRequestURL().toString());
		// 打印 Http method
		logger.info("HTTP Method    : {}", request.getMethod());
		// 打印调用 controller 的全路径以及执行方法
		logger.info("Class Method   : {}.{}", joinPoint.getSignature()
				.getDeclaringTypeName(), joinPoint.getSignature().getName());
		// 打印请求的 IP
		logger.info("IP             : {}", SysUtil.getIpAddr(request));
		try {
			// 打印请求入参
			logger.info("Request Args   : {}",
					new Gson().toJson(joinPoint.getArgs()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	/**
	 * 在切点之后织入
	 * 
	 * @throws Throwable
	 */
	@After("webLog()")
	public void doAfter() throws Throwable {
		logger.info("=========================================== Controller-End ===========================================");
		// 每个请求之间空一行
		logger.info("");
	}

	/**
	 * 环绕
	 *
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("webLog()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {
		long startTime = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		// 打印出参
		logger.info("Response Args  : {}", new Gson().toJson(result));
		// 执行耗时
		logger.info("Time-Consuming : {} ms", System.currentTimeMillis()
				- startTime);
		return result;
	}
}