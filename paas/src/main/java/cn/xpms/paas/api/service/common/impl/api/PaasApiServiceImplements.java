package cn.xpms.paas.api.service.common.impl.api;

import cn.hutool.core.util.StrUtil;
import cn.xpms.paas.api.bean.annotation.PaasApiPageData;
import cn.xpms.paas.api.bean.constant.Constant;
import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.dto.common.PaasApiPageResultVo;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.bean.error.PaasApiErrorCode;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.PaasApiCallRecordMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasApiTokenMapper;
import cn.xpms.paas.api.service.common.inter.api.PaasApiServiceInterface;
import cn.xpms.paas.api.util.PaasConfigUtil;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.http.HttpUtil;
import cn.xpms.system.framework.util.sys.BeanUtil;
import cn.xpms.system.framework.util.sys.DateUtil;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.system.system.cache.base.CacheBaseUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @ClassName PaasApiServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 10:02
 * @Version 1.0
 */
@Service
public class PaasApiServiceImplements implements PaasApiServiceInterface {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(PaasApiServiceImplements.class);

    /** 涂鸦接口平台地址 */
    String APP_URL;

    /** 涂鸦接口平台ACCESS_ID */
    String ACCESS_ID;

    /** 涂鸦接口平台ACCESS_KEY */
    String ACCESS_KEY;

    /** 涂鸦接口平台SECRET_METHOD */
    String SECRET_METHOD;

    /** 涂鸦接口平台GRANT_TYPE */
    String GRANT_TYPE;

    /** api调用日志CURD */
    @Autowired
    PaasApiCallRecordMapper apiCallRecordMapper;

    /** api token CURD */
    @Autowired
    PaasApiTokenMapper paasApiTokenMapper;

    /** json对象 */
    ObjectMapper json;

    /**
     * @Description: 初始化
     * @Params: []
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/23 12:01
     */
    private void init() throws AppException {
        try {
            if (SysUtil.isEmpty(ACCESS_ID)) {
                ACCESS_ID = PaasConfigUtil.getPassConfigValue(Constant.ACCESS_ID);
            }
            if (SysUtil.isEmpty(ACCESS_KEY)) {
                ACCESS_KEY = PaasConfigUtil.getPassConfigValue(Constant.ACCESS_KEY);
            }
            if (SysUtil.isEmpty(APP_URL)) {
                APP_URL = PaasConfigUtil.getPassConfigValue(Constant.APP_URL);
            }
            if (SysUtil.isEmpty(SECRET_METHOD)) {
                SECRET_METHOD = PaasConfigUtil.getPassConfigValue(Constant.SECRET_METHOD);
            }
            if (SysUtil.isEmpty(GRANT_TYPE)) {
                GRANT_TYPE = PaasConfigUtil.getPassConfigValue(Constant.GRANT_TYPE);
            }
            if (json == null) {
                json = new ObjectMapper();
                json.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
                json.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            }
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.init】初始化：" + e.getError_message());
            throw e;
        }
    }

    /**
     * @Description: 获取接口配置
     * @Params: [name]
     * @return: cn.xpms.paas.api.dao.generator.entity.PaasApiConfig
     * @Author: 柯雷
     * @Date: 2020/11/23 11:44
     */
    private PaasApiConfig getApiConfig(PaasApiEnum name) throws AppException {
        try {
            List<PaasApiConfig> paasApiConfigs =
                    CacheBaseUtil.getCacheData(Constant.PAAS_API_CONFIG_REDIS_KEY);
            // 接口配置异常
            if (SysUtil.isEmpty(paasApiConfigs)) {
                throw new AppException(PaasApiErrorCode.INTER_CONFIG_ERR);
            }
            
            for (PaasApiConfig paasApiConfig : paasApiConfigs) {
                if (paasApiConfig.getName().equals(name.toString())) {
                    return paasApiConfig;
                }
            }
            throw new AppException(PaasApiErrorCode.INTER_CONFIG_ERR);
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.call】调用涂鸦工程施工接口出错：" + e.getError_message());
            throw e;
        }
    }

    /**
     * @Description: 调用接口
     * @Params: [name, params]
     * @return: cn.xpms.paas.common.dto.ApiResponseData
     * @Author: 柯雷
     * @Date: 2020-09-08 14:24
     */
    @Override
    public PaasApiResponseEntity call(PaasApiEnum name, Map<String, Object> params, Class clazz) throws AppException {
        try {
            // 接口配置
            PaasApiConfig paasApiConfig = this.getApiConfig(name);
            // 返回结果
            PaasApiResponseEntity paasApiResponseEntity = this.call(paasApiConfig, params);
            paasApiResponseEntity.setResult(json.readValue(json.writeValueAsString(paasApiResponseEntity.getResult()), clazz));
            return paasApiResponseEntity;
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.call】调用涂鸦工程施工接口出错：" + e.getError_message());
            throw e;
        } catch (Exception e) {
            logger.error("【PaasApiServiceImplements.call】调用涂鸦工程施工接口出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 调用接口
     * @Params: [name, params]
     * @return: cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/16 15:06
     */
    @Override
    public PaasApiResponseEntity call(PaasApiEnum name, Map<String, Object> params) throws AppException {
        try {
            // 接口配置
            PaasApiConfig paasApiConfig = this.getApiConfig(name);
            return this.call(paasApiConfig, params);
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.call】调用涂鸦工程施工接口出错：" + e.getError_message());
            throw e;
        } catch (Exception e) {
            logger.error("【PaasApiServiceImplements.call】调用涂鸦工程施工接口出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 分页接口查询
     * @Params: [name, params, clazz]
     * @return: cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity<cn.xpms.paas.api.bean.dto.common.PaasApiPageResultVo>
     * @Author: 柯雷
     * @Date: 2020/12/16 10:39
     */
    @Override
    public PaasApiResponseEntity<? extends PaasApiPageResultVo> callPage(PaasApiEnum name, Map<String, Object> params, Class clazz) throws AppException {
        try {
            // 接口配置
            PaasApiConfig paasApiConfig = this.getApiConfig(name);
            // 返回结果
            if (paasApiConfig.getPage().intValue() == SystemConstants.INT_YES.intValue()) {
                // 分页查询
                params.put("page_size", SystemConstants.DEFAULT_PAGE_SIZE);
                Integer page_no = SystemConstants.DEFAULT_PAGE_NO;

                // 分页查询，直到查询结束
                PaasApiResponseEntity<PaasApiPageResultVo> apiResponseEntity;
                PaasApiPageResultVo paasApiPageResultVo = (PaasApiPageResultVo) clazz.newInstance();
                while (true) {
                    params.put("page_no", page_no);
                    apiResponseEntity = this.call(paasApiConfig, params);
                    this.formatApiPageData(apiResponseEntity, paasApiPageResultVo);
                    // 查询结束，则跳出循环
                    if (page_no * SystemConstants.DEFAULT_PAGE_SIZE >= paasApiPageResultVo.getTotal()) {
                        break;
                    }
                    page_no++;
                }
                apiResponseEntity.setResult(paasApiPageResultVo);
                return apiResponseEntity;
            } else {
                throw new AppException(PaasApiErrorCode.INTER_CONFIG_ERR);
            }
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.call】调用涂鸦工程施工接口出错：" + e.getError_message());
            throw e;
        } catch (Exception e) {
            logger.error("【PaasApiServiceImplements.call】调用涂鸦工程施工接口出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 格式化分页查询数据
     * @Params: [apiResponseEntity, clazz]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/16 14:31
     */
    private void formatApiPageData(PaasApiResponseEntity<? extends PaasApiPageResultVo> apiResponseEntity, PaasApiPageResultVo resultVo) throws AppException {
        try {
            // json转换
            String result = json.writeValueAsString(apiResponseEntity.getResult());
            PaasApiPageResultVo pageResultVo = json.readValue(result, resultVo.getClass());

            Field[] fields = pageResultVo.getClass().getDeclaredFields();
            for (Field field : fields) {
                if(field.isAnnotationPresent(PaasApiPageData.class)){
                    PaasApiPageData pageData = field.getAnnotation(PaasApiPageData.class);
                    List data = (List) BeanUtil.getFieldValueByName(pageData.field_name(), resultVo);
                    if (data == null) {
                        data = new ArrayList();
                    }
                    data.addAll((Collection) BeanUtil.getFieldValueByName(pageData.field_name(), pageResultVo));
                    BeanUtil.setFieldValueByName(pageData.field_name(), resultVo, data, List.class);
                }
            }
            resultVo.setTotal(pageResultVo.getTotal());
            resultVo.setPage_size(pageResultVo.getPage_size());
            resultVo.setPage_no(pageResultVo.getPage_no());
        } catch (Exception e) {
            logger.error("【PaasApiServiceImplements.formatApiPageData】格式化分页查询数据出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 调用涂鸦接口
     * @Params: [paasApiConfig, params]
     * @return: cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/11 10:46
     */
    private PaasApiResponseEntity call(PaasApiConfig paasApiConfig, Map<String, Object> params) throws AppException {
        // 开始调用时间
        PaasApiCallRecord paasApiCallRecord = new PaasApiCallRecord();
        paasApiCallRecord.setStartTime(new Date());
        Long t = System.currentTimeMillis();
        try {
            init();
            paasApiCallRecord.setApiId(paasApiConfig.getId());

            // 校验uri参数
            Map<String, Object> uriParams = new HashMap<>();
            this.validateParams(paasApiConfig.getUriParams(), params, uriParams);

            // 校验body参数
            Map<String, Object> bodyParams = new HashMap<>();
            this.validateParams(paasApiConfig.getBodyParams(), params, bodyParams);

            paasApiCallRecord.setParams(JSONObject.toJSONString(uriParams)
                    .concat(JSONObject.toJSONString(bodyParams)));

            PaasApiResponseEntity apiResponseEntity = this.remote(paasApiConfig.getUrl(),
                    paasApiConfig.getMethod(), uriParams, bodyParams);
            // 涂鸦接口返回处理失败
            if (!apiResponseEntity.getSuccess()) {
                throw new AppException(apiResponseEntity.getCode().toString(),
                        apiResponseEntity.getMsg(), apiResponseEntity.getMsg());
            }

            paasApiCallRecord.setResult(apiResponseEntity.getSuccess().toString());
            paasApiCallRecord.setMessage(apiResponseEntity.getMsg());
            paasApiCallRecord.setResponse(JSONObject.toJSONString(apiResponseEntity.getResult()));
            return apiResponseEntity;
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.call】调用涂鸦工程施工接口出错：" + e.getError_message());
            paasApiCallRecord.setResult(Constant.API_CALL_RESULT_FAIL);
            paasApiCallRecord.setMessage(e.getError_message());
            paasApiCallRecord.setResponse(e.toString());
            throw e;
        } catch (Exception e) {
            logger.error("【PaasApiServiceImplements.call】调用涂鸦工程施工接口出错：" + e);
            paasApiCallRecord.setResult(Constant.API_CALL_RESULT_FAIL);
            paasApiCallRecord.setMessage(e.getMessage());
            paasApiCallRecord.setResponse(e.toString());
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        } finally {
            // 保存调用记录
            paasApiCallRecord.setT((int) (System.currentTimeMillis() - t));
            paasApiCallRecord.setEndTime(new Date());
            apiCallRecordMapper.insertSelective(paasApiCallRecord);
        }
    }

    /**
     * @Description: 校验参数
     * @Params: [paramConfig, srcParmas, destParams]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-09-08 15:25
     */
    private void validateParams(String paramConfig, Map<String, Object> srcParmas,
                                Map<String, Object> destParams) throws AppException {
        if (!SysUtil.isEmpty(paramConfig)) {
            String[] key_params = paramConfig.split(",");
            for (String key : key_params) {
                String[] keys = key.split("\\|");
                // 必传参数是否传值
                if (keys.length > 1 && !SysUtil.isEmpty(keys[1]) && Boolean.parseBoolean(keys[1])
                        && SysUtil.isEmpty(srcParmas.get(keys[0]))) {
                    throw new AppException(PaasApiErrorCode.PARAMS_ERROR.setTip(":" + keys[0]));
                }
                if (null != srcParmas.get(keys[0])) {
                    destParams.put(keys[0], srcParmas.get(keys[0]));
                }
            }
        }
    }

    /**
     * @Description: 调用接口
     * @Params: [name, uriParams, bodyParams]
     * @return: cn.xpms.paas.common.dto.ApiResponseData
     * @Author: 柯雷
     * @Date: 2020-09-08 14:15
     */
    @Override
    public PaasApiResponseEntity remote(String url, String method, Map<String, Object> uriParams,
                                        Map<String, Object> bodyParams) throws AppException {
        try {
            PaasApiResponseEntity apiResponseEntity;
            Integer paramsCount = StrUtil.count(url, "{");

            // 如果有参数则设置参数
            if (paramsCount > 0) {
                if (uriParams.keySet().size() != paramsCount) {
                    throw new AppException(PaasApiErrorCode.PARAMS_ERROR);
                }
                Set<String> keys = uriParams.keySet();
                for (String key : keys) {
                    if (!SysUtil.isEmpty(uriParams.get(key))) {
                        url = url.replaceAll("\\{" + key + "}", uriParams.get(key).toString());
                    } else {
                        throw new AppException(PaasApiErrorCode.PARAMS_ERROR.setTip(":" + key));
                    }
                }
            }

            // 根据method不同调用不同的方法
            switch (method) {
                case SystemConstants.GET: apiResponseEntity = this.get(url, bodyParams); break;
                case SystemConstants.POST: apiResponseEntity = this.post(url, bodyParams); break;
                case SystemConstants.DELETE: apiResponseEntity = this.delete(url, bodyParams); break;
                case SystemConstants.PUT: apiResponseEntity = this.put(url, bodyParams); break;
                default: throw new AppException(PaasApiErrorCode.INTER_CONFIG_ERR);
            }
            return apiResponseEntity;
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.call】调用涂鸦工程施工接口出错：" + e.getError_message());
            throw e;
        } catch (Exception e) {
            logger.error("【PaasApiServiceImplements.call】调用涂鸦工程施工接口出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 调用接口get方法
     * @Params: [url, params]
     * @return: cn.xpms.paas.common.dto.ApiResponseData
     * @Author: 柯雷
     * @Date: 2020-09-08 14:24
     */
    @Override
    public PaasApiResponseEntity get(String url, Map<String, Object> params) throws AppException {
        try {
            String response = HttpUtil.get(APP_URL + url, params, getHeader(true));
            return json.readValue(response, PaasApiResponseEntity.class);
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.get】涂鸦接口返回错误:" + e.getError_message());
            throw e;
        } catch (Exception e) {
            logger.error("【PaasApiServiceImplements.get】涂鸦接口返回错误:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 调用接口post方法
     * @Params: [url, params]
     * @return: cn.xpms.paas.common.dto.ApiResponseData
     * @Author: 柯雷
     * @Date: 2020-09-08 14:24
     */
    @Override
    public PaasApiResponseEntity post(String url, Map<String, Object> params) throws AppException {
        try {
            String response = HttpUtil.post(APP_URL + url, JSONObject.toJSONString(params),
                    getHeader(true));
            return json.readValue(response, PaasApiResponseEntity.class);
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.post】涂鸦接口返回错误:" + e.getError_message());
            throw e;
        } catch (Exception e) {
            logger.error("【PaasApiServiceImplements.post】涂鸦接口返回错误:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 调用delete方法
     * @Params: [url, params]
     * @return: cn.xpms.paas.common.dto.ApiResponseData
     * @Author: 柯雷
     * @Date: 2020-09-08 16:24
     */
    @Override
    public PaasApiResponseEntity delete(String url, Map<String, Object> params) throws AppException {
        try {
            String response = HttpUtil.delete(APP_URL + url, params, getHeader(true));
            return json.readValue(response, PaasApiResponseEntity.class);
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.delete】涂鸦接口返回错误:" + e.getError_message());
            throw e;
        } catch (Exception e) {
            logger.error("【PaasApiServiceImplements.delete】涂鸦接口返回错误:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 调用put方法
     * @Params: [url, params]
     * @return: cn.xpms.paas.common.dto.ApiResponseData
     * @Author: 柯雷
     * @Date: 2020-09-08 16:24
     */
    @Override
    public PaasApiResponseEntity put(String url, Map<String, Object> params) throws AppException {
        try {
            String response = HttpUtil.put(APP_URL + url, JSONObject.toJSONString(params), getHeader(true));
            return json.readValue(response, PaasApiResponseEntity.class);
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.put】涂鸦接口返回错误:" + e.getError_message());
            throw e;
        } catch (Exception e) {
            logger.error("【PaasApiServiceImplements.put】涂鸦接口返回错误:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取token
     * @Params: []
     * @return: cn.xpms.paas.common.dto.ApiResponseData
     * @Author: 柯雷
     * @Date: 2020-09-09 8:12
     */
    private String getAccessToken() throws AppException {
        try {
            PaasApiTokenExample example = new PaasApiTokenExample();
            example.createCriteria().andEndTimeGreaterThan(new Date());
            List<PaasApiToken> tokens = paasApiTokenMapper.selectByExample(example);
            if (SysUtil.isEmpty(tokens)) {
                Map<String, Object> params = new HashMap<>();
                params.put("grant_type", GRANT_TYPE);
                String response = HttpUtil.get(APP_URL + "/v1.0/token", params,
                        this.getHeader(false));
                PaasApiResponseEntity apiResponseEntity = json.readValue(response, PaasApiResponseEntity.class);
                if (!apiResponseEntity.getSuccess()) {
                    throw new AppException(PaasApiErrorCode.INTER_RESULT_ERROR
                            .setTip("获取token接口异常:" + apiResponseEntity.getMsg()));
                }

                Map<String, Object> token = (Map<String, Object>) apiResponseEntity.getResult();

                // 保存token
                PaasApiToken apiToken = new PaasApiToken();
                apiToken.setAccessToken((String) token.get("access_token"));
                apiToken.setStartTime(new Date());
                apiToken.setExpireTime((Integer) token.get("expire_time"));
                apiToken.setRefreshToken((String) token.get("refresh_token"));
                apiToken.setEndTime(DateUtil.addTimestamp(apiToken.getStartTime(), apiToken.getExpireTime() * 1000));
                paasApiTokenMapper.insertSelective(apiToken);

                return apiToken.getAccessToken();
            } else {
                return tokens.get(0).getAccessToken();
            }
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.getAccessToken】获取token异常：" + e.getError_message());
            throw e;
        } catch (Exception e) {
            logger.error("【PaasApiServiceImplements.getAccessToken】获取token异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 加密
     * @Params: [data, key]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-09-09 8:12
     */
    private String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * @Description: 组装header参数
     * @Params: []
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @Author: 柯雷
     * @Date: 2020-09-09 8:27
     */
    private Map<String, Object> getHeader(boolean withToken) throws Exception {
        logger.info("【PaasApiServiceImplements.getHeader】组装header参数");
        Map<String, Object> headers = new HashMap<>();
        try {
            Long t = System.currentTimeMillis();
            // 请求头信息
            headers.put("client_id", ACCESS_ID);
            headers.put("sign_method", SECRET_METHOD);
            headers.put("t", t);
            headers.put("Content-Type", Constant.API_CONTENT_TYPE);
            if (withToken) {
                String access_token = this.getAccessToken();
                headers.put("access_token", access_token);
                headers.put("sign", HMACSHA256(ACCESS_ID + access_token + t, ACCESS_KEY).toUpperCase());
            } else {
                headers.put("sign", this.HMACSHA256(ACCESS_ID + t, ACCESS_KEY).toUpperCase());
            }
            return headers;
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.getHeader】组装header参数出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【PaasApiServiceImplements.getHeader】组装header参数出错：" + e.getMessage());
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}