package cn.xpms.paas.api.util;

import cn.xpms.paas.api.bean.constant.Constant;
import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.error.PaasApiErrorCode;
import cn.xpms.paas.api.dao.generator.entity.PaasApiToken;
import cn.xpms.paas.api.dao.generator.entity.PaasConfig;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.system.system.cache.base.CacheBaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @ClassName PaasConfigUtil
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 11:54
 * @Version 1.0
 */
public class PaasConfigUtil {

    /** 日志打印对象 */
    static Logger logger = LoggerFactory.getLogger(PaasConfigUtil.class);

    /**
     * @Description: 获取系统配置
     * @Params: [code]
     * @return: cn.xpms.paas.api.dao.generator.entity.PaasConfig
     * @Author: 柯雷
     * @Date: 2020/11/23 11:57
     */
    public static PaasConfig getPaasConfig(String code) throws AppException {
        try {
            List<PaasConfig> paasConfigs = CacheBaseUtil.getCacheData(Constant.PAAS_CONFIG_REDIS_KEY);
            if (!SysUtil.isEmpty(paasConfigs)) {
                for (PaasConfig paasConfig : paasConfigs) {
                    if (paasConfig.getCode().equals(code)) {
                        return paasConfig;
                    }
                }
            }
            throw new AppException(PaasApiErrorCode.NO_SYS_CONFIG.setTip(code));
        } catch (AppException e) {
            logger.error("【PaasApiServiceImplements.getPaasConfig】获取系统配置：" + e);
            throw e;
        }
    }

    /**
     * @Description: 获取系统配置
     * @Params: [code]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/11/23 11:58
     */
    public static String getPassConfigValue(String code) throws AppException {
        return getPaasConfig(code).getValue();
    }
}