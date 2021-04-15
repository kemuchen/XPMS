package cn.xpms.paas.api.service.business.impl.location;

import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.dao.generator.entity.PaasLocationInfo;
import cn.xpms.paas.api.dao.generator.entity.PaasLocationInfoExample;
import cn.xpms.paas.api.dao.generator.repository.PaasLocationInfoMapper;
import cn.xpms.paas.api.service.business.inter.location.LocationServiceInterface;
import cn.xpms.paas.api.service.common.inter.api.PaasApiServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PaasLocationServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 15:29
 * @Version 1.0
 */
@Service
public class LocationServiceImplements implements LocationServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(LocationServiceImplements.class);

    /**
     * paas平台接口service
     */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    /**
     * 地理位置信息CURD
     */
    @Autowired
    PaasLocationInfoMapper paasLocationInfoMapper;

    /**
     * @Description: 查询地理位置信息列表
     * @Params: [parent_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/23 15:30
     */
    @Override
    public ApiResponseEntity getLocations(PaasLocationInfo locationInfo) throws AppException {
        try {
            PaasLocationInfoExample example = new PaasLocationInfoExample();
            locationInfo.setValid(SystemConstants.INT_YES);
            BeanUtil.beanToExample(locationInfo, example);
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    paasLocationInfoMapper.selectByExample(example));
        } catch (AppException e) {
            logger.error("【PaasLocationServiceImplements.getLocations】获取地理位置信息异常：" + e.getError_message());
            throw e;
        } catch (Exception e) {
            logger.error("【PaasLocationServiceImplements.getLocations】获取地理位置信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步地理位置信息
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 12:01
     */
    @Override
    public ApiResponseEntity synchroLocation() throws AppException {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("parent_id", SystemConstants.INT_NO);
            PaasApiResponseEntity<List<Map<String, Object>>> apiResponseData = paasApiServiceInterface.call(PaasApiEnum.LOCATIONS, params);

            // 更新数据库
            this.synchroLocation(apiResponseData.getResult());
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【LocationServiceImplements.synchroLocation】同步地理位置信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【LocationServiceImplements.synchroLocation】同步地理位置信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步地理位置信息
     * @Params: [locations]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/1 11:53
     */
    private void synchroLocation(List<Map<String, Object>> locations) throws AppException {
        try {
            PaasLocationInfo paasLocationInfo = new PaasLocationInfo();
            PaasLocationInfoExample example = new PaasLocationInfoExample();
            for (Map<String, Object> location : locations) {
                example.createCriteria().andLocationIdEqualTo(location.get("id").toString());

                paasLocationInfo.setParentId(location.get("parent_id").toString());
                paasLocationInfo.setFullId(location.get("id").toString());
                paasLocationInfo.setFullName(location.get("name").toString());
                paasLocationInfo.setName(location.get("name").toString());
                paasLocationInfo.setLevel(location.get("level").toString());
                paasLocationInfo.setLocationId(location.get("id").toString());

                if (paasLocationInfoMapper.countByExample(example) != 0) {
                    // 新增
                    paasLocationInfoMapper.updateByExampleSelective(paasLocationInfo, example);
                } else {
                    // 修改
                    paasLocationInfoMapper.insertSelective(paasLocationInfo);
                }
            }
        } catch (Exception e) {
            logger.error("【LocationServiceImplements.synchroLocation】同步地理位置信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
