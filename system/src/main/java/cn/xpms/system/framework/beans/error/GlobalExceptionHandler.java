package cn.xpms.system.framework.beans.error;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ExceptionHandler
 * @Desc
 * @Author 柯雷
 * @Date 2020/10/29 9:24
 * @Version 1.0
 */
@RestController     // 以rest形式返回异常信息
@ControllerAdvice   // 全局异常处理器
public class GlobalExceptionHandler {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @Description:
     * @Params: [appException]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/3/12 12:55
     */
    @ExceptionHandler(value = AppException.class)
    public String AppExceptionHandler(AppException e) {
        logger.error("-------------------------------------------Exception-STA-------------------------------------------------------------------");
        logger.error("【GlobalExceptionHandler.AppExceptionHandler】" + e.getError_message());
        logger.error("-------------------------------------------Exception-END-------------------------------------------------------------------");
        ApiResponseEntity apiResponseResultEntity = new ApiResponseEntity();
        apiResponseResultEntity.setCode(e.getError_code());
        apiResponseResultEntity.setMessage(e.getError_tip());
        return apiResponseResultEntity.toString();
    }
}
