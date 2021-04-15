package cn.xpms.paas.api.util;

import cn.xpms.paas.api.bean.constant.Constant;
import cn.xpms.paas.api.dao.generator.entity.PaasDict;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.system.system.cache.base.CacheBaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PaasDictUtil
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/3 11:14
 * @Version 1.0
 */
public class PaasDictUtil {

    /** 日志打印对象 */
    static Logger logger = LoggerFactory.getLogger(PaasDictUtil.class);

    /**
     * @Description: 获取字典项
     * @Params: [code]
     * @return: cn.xpms.paas.api.dao.generator.entity.PaasConfig
     * @Author: 柯雷
     * @Date: 2020/11/23 11:57
     */
    public static List<PaasDict> getPaasDict(String code) throws AppException {
        try {
            List<PaasDict> paasDicts = CacheBaseUtil.getCacheData(Constant.PAAS_DICT_REDIS_KEY);
            List<PaasDict> list = new ArrayList<>();
            if (!SysUtil.isEmpty(paasDicts)) {
                for (PaasDict paasDict : paasDicts) {
                    if (paasDict.getCode().equals(code)) {
                        list.add(paasDict);
                    }
                }
            }
            return list;
        } catch (Exception e) {
            logger.error("【PaasApiServiceImplements.getPaasDict】获取字典项异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}