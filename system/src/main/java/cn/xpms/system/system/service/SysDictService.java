package cn.xpms.system.system.service;

import cn.xpms.system.framework.beans.pojo.BeanFactory;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.system.cache.base.CacheBaseInterface;
import cn.xpms.system.system.dao.generator.entity.SysDict;
import cn.xpms.system.system.dao.generator.entity.SysDictExample;
import cn.xpms.system.system.dao.generator.repository.SysDictMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @ClassName SysDictService
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/18 11:48
 * @Version 1.0
 */
//@Service
public class SysDictService implements CacheBaseInterface<SysDict> {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(SysDictService.class);

    /** 字典项配置CURD */
    static SysDictMapper mapper = BeanFactory.getBean(SysDictMapper.class);

    /**
     * @Description: 查询系统字典项
     * @Params: []
     * @return: java.util.List<cn.xpms.system.system.dao.generator.entity.SysSubsysConfig>
     * @Author: 柯雷
     * @Date: 2020/11/18 11:48
     */
    @Override
    public List<SysDict> getCacheData() {
        logger.info("【SysDictService.getCacheData】获取子系统字典项缓存");
        // 获取有效的子系统配置
        SysDictExample sysDictExample = new SysDictExample();
        sysDictExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES);
        return mapper.selectByExample(sysDictExample);
    }
}
