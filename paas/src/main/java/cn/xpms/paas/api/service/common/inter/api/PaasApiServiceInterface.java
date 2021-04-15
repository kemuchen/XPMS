package cn.xpms.paas.api.service.common.inter.api;

import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.dto.common.PaasApiPageResultVo;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @ClassName PaasApiServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 10:00
 * @Version 1.0
 */
public interface PaasApiServiceInterface {

    /**
     * @Description: 调用接口
     * @Params: [name, params]
     * @return: cn.xpms.paas.common.dto.ProjectInterResponse
     * @Author: 柯雷
     * @Date: 2020-09-08 14:21
     */
    PaasApiResponseEntity call(PaasApiEnum name, Map<String, Object> params, Class clazz) throws AppException;

    /**
     * @Description: 调用接口
     * @Params: [name, params]
     * @return: cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/16 15:06
     */
    PaasApiResponseEntity call(PaasApiEnum name, Map<String, Object> params) throws AppException;

    /**
     * @Description: 调用接口
     * @Params: [name, params]
     * @return: cn.xpms.paas.common.dto.ProjectInterResponse
     * @Author: 柯雷
     * @Date: 2020-09-08 14:21
     */
    PaasApiResponseEntity callPage(PaasApiEnum name, Map<String, Object> params, Class clazz) throws AppException;

    /**
     * @Description: 调用接口
     * @Params: [name, uriParams, bodyParams]
     * @return: cn.xpms.paas.common.dto.ProjectInterResponse
     * @Author: 柯雷
     * @Date: 2020-09-08 14:13
     */
    PaasApiResponseEntity remote(String url, String method, Map<String, Object> uriParams,
                                 Map<String, Object> bodyParams) throws AppException;

    /**
     * @Description: 调用get方法
     * @Params: [url, params]
     * @return: cn.xpms.paas.common.dto.ProjectInterResponse
     * @Author: 柯雷
     * @Date: 2020-09-08 14:17
     */
    PaasApiResponseEntity get(String url, Map<String, Object> params) throws AppException;

    /**
     * @Description: 调用post方法
     * @Params: [url, params]
     * @return: cn.xpms.paas.common.dto.ProjectInterResponse
     * @Author: 柯雷
     * @Date: 2020-09-08 14:18
     */
    PaasApiResponseEntity post(String url, Map<String, Object> params) throws AppException;

    /**
     * @Description: 调用delete方法
     * @Params: [url, params]
     * @return: cn.xpms.paas.common.dto.ProjectInterResponse
     * @Author: 柯雷
     * @Date: 2020-09-08 14:18
     */
    PaasApiResponseEntity delete(String url, Map<String, Object> params) throws AppException;

    /**
     * @Description: 调用put方法
     * @Params: [url, params]
     * @return: cn.xpms.paas.common.dto.ProjectInterResponse
     * @Author: 柯雷
     * @Date: 2020-09-08 14:18
     */
    PaasApiResponseEntity put(String url, Map<String, Object> params) throws AppException;
}