package cn.xpms.system.system.util;

import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.system.system.cache.base.CacheBaseUtil;
import cn.xpms.system.system.dao.generator.entity.SysDict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysDictUtil
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/18 10:47
 * @Version 1.0
 */
public class SysDictUtil {

    /**
     * 日志打印对象
     */
    static Logger logger = LoggerFactory.getLogger(SysDictUtil.class);

    /**
     * @Description: 获取字典项
     * @Params: [key]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-05-04 11:03
     */
    public static List<SysDict> getSysDict(String code) {
        logger.info("【SysDictUtil.getSysDict】获取字典项");
        List<SysDict> sysDicts = CacheBaseUtil.getCacheData(SystemConstants.SYS_DICT_REDIS_KEY);
        if (SysUtil.isEmpty(sysDicts)) {
            List<SysDict> dicts = new ArrayList<SysDict>();
            for (SysDict dcit : sysDicts) {
                if (dcit.getCode().equals(code)) {
                    dicts.add(dcit);
                }
            }
            return dicts;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * @Description:
     * @Params: [dicttype]
     * @return: java.util.List<cn.xpms.system.system.dao.auto.entity.SysDictEntity>
     * @Author: 柯雷
     * @Date: 2020-05-09 10:06
     */
    public static SysDict getSysDict(String code, String value) {
        logger.info("【CouponDictUtil.getSysDict】获取字典项");
        List<SysDict> sysDicts = getSysDict(code);
        for (SysDict dcit : sysDicts) {
            if (dcit.getValue().equals(value)) {
                return dcit;
            }
        }
        return null;
    }
}
