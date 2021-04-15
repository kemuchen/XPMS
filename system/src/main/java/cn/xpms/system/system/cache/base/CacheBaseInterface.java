package cn.xpms.system.system.cache.base;

import cn.xpms.system.system.dao.generator.entity.SysDict;
import cn.xpms.system.system.dao.generator.entity.SysSubsysConfig;

import java.util.List;

/**
 * @ClassName CacheBaseInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/18 11:15
 * @Version 1.0
 */
public interface CacheBaseInterface<T> {

    /**
     * @Description: 查询缓存数据接口
     * @Params: []
     * @return: java.util.List<java.lang.Object>
     * @Author: 柯雷
     * @Date: 2020/11/18 11:05
     */
    List<T> getCacheData();
}