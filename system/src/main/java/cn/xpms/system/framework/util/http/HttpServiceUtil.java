package cn.xpms.system.framework.util.http;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.HttpErrorCode;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.system.system.util.SubSysConfigUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HttpService
 * @Desc
 * @Author 柯雷
 * @Date 2020-07-01 11:11
 * @Version 1.0
 */
@Service
public class HttpServiceUtil {

    /** 日志输出对象 */
    static Logger logger = LoggerFactory.getLogger(HttpServiceUtil.class);

    /**
     * @Description: get请求
     * @Params: [url, params]
     * @return: cn.xpms.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-07-01 11:16
     */
    public static ApiResponseEntity doGet(String sub_system, String url, Map<String, Object> params) throws AppException {
        try {
            if (SysUtil.isEmpty(params)) {
                params = new HashMap<>();
            }
            return new Gson().fromJson(HttpUtil.get(SubSysConfigUtil.getSubsysUrl(sub_system) + url, params), ApiResponseEntity.class);
        } catch (AppException e) {
            logger.error("【HttpServiceImplements.doGet】get请求异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【HttpServiceImplements.doGet】get请求异常：" + e);
            throw new AppException(HttpErrorCode.ERROR_000);
        }
    }

    /**
     * @Description: post请求
     * @Params: [url, params, header]
     * @return: cn.xpms.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-07-01 11:20
     */
    public static ApiResponseEntity doPost(String sub_system, String url, String params, Map<String, Object> header) throws AppException {
        try {
            if (SysUtil.isEmpty(params)) {
                header = new HashMap<>();
            }
            return new Gson().fromJson(HttpUtil.post(SubSysConfigUtil.getSubsysUrl(sub_system) + url, params, header), ApiResponseEntity.class);
        } catch (AppException e) {
            logger.error("【HttpServiceImplements.doPost】post请求异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【HttpServiceImplements.doPost】post请求异常：" + e);
            throw new AppException(HttpErrorCode.ERROR_000);
        }
    }

    /**
     * @Description: put请求
     * @Params: [url, params, header]
     * @return: cn.xpms.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-07-01 11:20
     */
    public static ApiResponseEntity doPut(String sub_system, String url, String params, Map<String, Object> header) throws AppException {
        try {
            if (SysUtil.isEmpty(params)) {
                header = new HashMap<>();
            }
            return new Gson().fromJson(HttpUtil.put(SubSysConfigUtil.getSubsysUrl(sub_system) + url, params, header), ApiResponseEntity.class);
        } catch (AppException e) {
            logger.error("【HttpServiceImplements.doPut】put请求异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【HttpServiceImplements.doPut】put请求异常：" + e);
            throw new AppException(HttpErrorCode.ERROR_000);
        }
    }

    /**
     * @Description: delete请求
     * @Params: [url, params, header]
     * @return: cn.xpms.framework.common.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020-07-01 11:20
     */
    public static ApiResponseEntity doDelete(String sub_system, String url, Map<String, Object> params, Map<String, Object> header) throws AppException {
        try {
            if (SysUtil.isEmpty(params)) {
                header = new HashMap<>();
            }
            return new Gson().fromJson(HttpUtil.delete(SubSysConfigUtil.getSubsysUrl(sub_system) + url, params, header), ApiResponseEntity.class);
        } catch (AppException e) {
            logger.error("【HttpServiceImplements.doDelete】delete请求异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【HttpServiceImplements.doDelete】delete请求异常：" + e);
            throw new AppException(HttpErrorCode.ERROR_000);
        }
    }
}
