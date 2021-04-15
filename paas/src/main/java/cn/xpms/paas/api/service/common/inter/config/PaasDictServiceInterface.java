package cn.xpms.paas.api.service.common.inter.config;

import cn.xpms.paas.api.dao.generator.entity.PaasDict;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.system.cache.base.CacheBaseInterface;

/**
 * @ClassName PaasDictServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/3 11:11
 * @Version 1.0
 */
public interface PaasDictServiceInterface extends CacheBaseInterface<PaasDict> {

    /**
     * @Description: 根据代码查询字典项信息
     * @Params: [code]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:12
     */
    ApiResponseEntity getDicts(String code) throws AppException;
}