package cn.xpms.system.system.service;

import cn.xpms.system.framework.beans.pojo.BeanFactory;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.system.cache.base.CacheBaseInterface;
import cn.xpms.system.system.dao.generator.entity.SysSubsysConfig;
import cn.xpms.system.system.dao.generator.entity.SysSubsysConfigExample;
import cn.xpms.system.system.dao.generator.repository.SysSubsysConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SubSysConfigService
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/18 11:35
 * @Version 1.0
 */
//@Service
public class SubSysConfigService implements CacheBaseInterface<SysSubsysConfig> {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(SubSysConfigService.class);

    /** 子系统配置CURD */
    static SysSubsysConfigMapper mapper = BeanFactory.getBean(SysSubsysConfigMapper.class);

    /**
     * @Description: 获取子系统配置缓存
     * @Params: []
     * @return: java.util.List<java.lang.Object>
     * @Author: 柯雷
     * @Date: 2020/11/18 11:35
     */
    @Override
    public List<SysSubsysConfig> getCacheData() {
        logger.info("【SubSysConfigService.getCacheData】获取子系统配置缓存");
        // 获取有效的子系统配置
        SysSubsysConfigExample sysSubsysConfigExample = new SysSubsysConfigExample();
        sysSubsysConfigExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES);
        return mapper.selectByExample(sysSubsysConfigExample);
    }
}
