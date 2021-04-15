package cn.xpms.paas.api.service.common.impl.config;

import cn.xpms.paas.api.dao.generator.entity.PaasApiConfig;
import cn.xpms.paas.api.dao.generator.entity.PaasApiConfigExample;
import cn.xpms.paas.api.dao.generator.repository.PaasApiConfigMapper;
import cn.xpms.paas.api.service.common.inter.config.PaasApiConfigServiceInterface;
import cn.xpms.system.framework.constant.SystemConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PaasApiConfigServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 10:35
 * @Version 1.0
 */
@Service
public class PaasApiConfigServiceImplements implements PaasApiConfigServiceInterface {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(PaasApiConfigServiceImplements.class);

    /** paas平台接口配置CURD */
    @Autowired
    PaasApiConfigMapper paasApiConfigMapper;

    /**
     * @Description: 查询paas平台接口配置信息
     * @Params: []
     * @return: java.util.List<cn.xpms.paas.api.dao.generator.entity.PaasApiConfig>
     * @Author: 柯雷
     * @Date: 2020/11/23 10:38
     */
    @Override
    public List<PaasApiConfig> getCacheData() {
        // 查询paas平台接口配置信息
        PaasApiConfigExample paasApiConfigExample = new PaasApiConfigExample();
        paasApiConfigExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES);
        return paasApiConfigMapper.selectByExample(paasApiConfigExample);
    }
}
