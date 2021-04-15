package cn.xpms.paas.api.service.common.impl.config;

import cn.xpms.paas.api.dao.generator.entity.PaasConfig;
import cn.xpms.paas.api.dao.generator.entity.PaasConfigExample;
import cn.xpms.paas.api.dao.generator.repository.PaasConfigMapper;
import cn.xpms.paas.api.service.common.inter.config.PaasConfigServiceInterface;
import cn.xpms.system.framework.constant.SystemConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PaasConfigServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 10:30
 * @Version 1.0
 */
@Service
public class PaasConfigServiceImplements implements PaasConfigServiceInterface {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(PaasConfigServiceImplements.class);

    /** 系统配置CURD */
    @Autowired
    PaasConfigMapper paasConfigMapper;

    /**
     * @Description: 查询系统配置数据
     * @Params: []
     * @return: java.util.List<cn.xpms.paas.api.dao.generator.entity.PaasConfig>
     * @Author: 柯雷
     * @Date: 2020/11/23 10:33
     */
    @Override
    public List<PaasConfig> getCacheData() {
        // 查询系统配置数据
        PaasConfigExample paasConfigExample = new PaasConfigExample();
        paasConfigExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES);
        return paasConfigMapper.selectByExample(paasConfigExample);
    }
}
