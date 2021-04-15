package cn.xpms.member.common.dict;

import cn.xpms.member.common.constant.Constants;
import cn.xpms.member.coupon.dao.generator.entity.MemberDict;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.system.system.cache.base.CacheBaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CouponDictUtil
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/2 9:02
 * @Version 1.0
 */
public class MemberDictUtil {

    /**
     * 日志打印对象
     */
    static Logger logger = LoggerFactory.getLogger(MemberDictUtil.class);

    /**
     * @Description: 获取字典项
     * @Params: [key]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-05-04 11:03
     */
    public static List<MemberDict> getMemberDict(String code) {
        logger.info("【CouponDictUtil.getMemberDict】获取字典项");
        List<MemberDict> couponDicts = CacheBaseUtil.getCacheData(Constants.MEMBER_DICT_REDIS_KEY);
        if (!SysUtil.isEmpty(couponDicts)) {
            List<MemberDict> dicts = new ArrayList<MemberDict>();
            for (MemberDict dcit : couponDicts) {
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
    public static MemberDict getCacheSysDict(String code, String value) {
        logger.info("【CouponDictUtil.getMemberDict】获取字典项");
        List<MemberDict> couponDicts = getMemberDict(code);
        for (MemberDict dcit : couponDicts) {
            if (dcit.getValue().equals(value)) {
                return dcit;
            }
        }
        return null;
    }
}