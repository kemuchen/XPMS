package cn.xpms.system.framework.controller;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.HttpErrorCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ErrorPageController
 * @Desc
 * @Author 柯雷
 * @Date 2020/10/29 17:12
 * @Version 1.0
 */
@RestController
public class ErrorPageController {

    /**
     * @Description: 错误处理
     * @Params: [code]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/10/29 17:13
     */
    @RequestMapping(value = "/error/{code}")
    public ApiResponseEntity error(@PathVariable int code) {
        ApiResponseEntity apiResponseResultEntity;
        switch (code) {
            case 403:
                apiResponseResultEntity = new ApiResponseEntity(HttpErrorCode.ERROR_403);
                break;
            case 404:
                apiResponseResultEntity = new ApiResponseEntity(HttpErrorCode.ERROR_404);
                break;
            case 500:
                apiResponseResultEntity = new ApiResponseEntity(HttpErrorCode.ERROR_500);
                break;
            default:
                apiResponseResultEntity = new ApiResponseEntity(HttpErrorCode.ERROR_000);
        }
        return apiResponseResultEntity;
    }
}
