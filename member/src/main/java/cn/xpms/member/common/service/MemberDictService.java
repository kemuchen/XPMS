package cn.xpms.member.common.service;

import cn.xpms.member.coupon.dao.generator.entity.MemberDict;
import cn.xpms.member.coupon.dao.generator.entity.MemberDictExample;
import cn.xpms.member.coupon.dao.generator.repository.MemberDictMapper;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.system.cache.base.CacheBaseInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @ClassName MemberDictService
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/18 11:58
 * @Version 1.0
 */
@Service
public class MemberDictService implements CacheBaseInterface<MemberDict> {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(MemberDictService.class);

    /** 会员子系统字典项 */
    @Autowired
    MemberDictMapper memberDictMapper;

    /**
     * @Description: 查询会员子系统字典项
     * @Params: []
     * @return: java.util.List<cn.xpms.member.coupon.dao.generator.entity.MemberDict>
     * @Author: 柯雷
     * @Date: 2020/11/18 12:00
     */
    @Override
    public List<MemberDict> getCacheData() {
        logger.info("【MemberDictService.getCacheData】查询会员子系统字典项");
        // 查询会员子系统字典项
        MemberDictExample memberDictExample = new MemberDictExample();
        memberDictExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES);
        return memberDictMapper.selectByExample(memberDictExample);
    }
}
