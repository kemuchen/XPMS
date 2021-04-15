package cn.xpms.system.system.util;

import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.beans.pojo.BeanFactory;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.system.system.cache.base.CacheBaseUtil;
import cn.xpms.system.system.dao.generator.entity.SysSubsysConfig;
import cn.xpms.system.system.dao.generator.entity.SysSubsysConfigExample;
import cn.xpms.system.system.dao.generator.repository.SysSubsysConfigMapper;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;

/**
 * @ClassName SubSysConfigUtil
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/12 9:19
 * @Version 1.0
 */
public class SubSysConfigUtil {

    /** 日志输出对象 */
    static Logger logger = LoggerFactory.getLogger(SubSysConfigUtil.class);

    /**
     * @Description: 获取子系统url
     * @Params: [code]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/11/12 9:33
     */
    public static String getSubsysUrl(String code) throws AppException {
        try {
            SysSubsysConfig sysSubsysConfig = getSubSysConfig(code);
            if (sysSubsysConfig == null) {
                throw new AppException(SysErrorCode.SUBSYS_NOT_EXIST);
            }
            return sysSubsysConfig.getUrl();
        } catch (AppException e) {
            logger.error("【SubSysConfigUtil.getSubsysUril】获取子系统url异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【SubSysConfigUtil.getSubsysUril】获取子系统url异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取子系统配置
     * @Params: [key]
     * @return: java.lang.List
     * @Author: 柯雷
     * @Date: 2020-05-04 11:03
     */
    public static SysSubsysConfig getSubSysConfig(String code) {
        logger.info("【SubSysConfigUtil.getSubSysConfig】获取子系统配置");
        List<SysSubsysConfig> subsysConfigs = CacheBaseUtil
                .getCacheData(SystemConstants.SYS_SUBSYS_CONFIG_REDIS_KEY);
        if (SysUtil.isEmpty(subsysConfigs)) {
            for (SysSubsysConfig config : subsysConfigs) {
                if (config.getSubSystemCode().equals(code)) {
                    return config;
                }
            }
        }
        return null;
    }
}
