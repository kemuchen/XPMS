package cn.xpms.paas.api.service.common.impl.config;

import cn.xpms.paas.api.dao.generator.entity.PaasDict;
import cn.xpms.paas.api.dao.generator.entity.PaasDictExample;
import cn.xpms.paas.api.dao.generator.repository.PaasDictMapper;
import cn.xpms.paas.api.service.common.inter.config.PaasDictServiceInterface;
import cn.xpms.paas.api.util.PaasDictUtil;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PaasDictServiceService
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/3 11:13
 * @Version 1.0
 */
@Service
public class PaasDictServiceServiceImplements implements PaasDictServiceInterface {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(PaasDictServiceServiceImplements.class);

    /** paas字典项CURD */
    @Autowired
    PaasDictMapper paasDictMapper;

    /**
     * @Description: 查询字典项
     * @Params: [code]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:14
     */
    @Override
    public ApiResponseEntity getDicts(String code) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, PaasDictUtil.getPaasDict(code));
        } catch (AppException e) {
            logger.error("【PaasDictServiceServiceImplements.getDicts】查询字典项异常：" + e);
            throw e;
        }

    }

    /**
     * @Description: 初始化呢字典项缓存查询
     * @Params: []
     * @return: java.util.List<cn.xpms.paas.api.dao.generator.entity.PaasDict>
     * @Author: 柯雷
     * @Date: 2020/12/3 11:23
     */
    @Override
    public List<PaasDict> getCacheData() {
        // 初始化呢字典项缓存查询
        PaasDictExample paasDictExample = new PaasDictExample();
        paasDictExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES);
        return paasDictMapper.selectByExample(paasDictExample);
    }
}