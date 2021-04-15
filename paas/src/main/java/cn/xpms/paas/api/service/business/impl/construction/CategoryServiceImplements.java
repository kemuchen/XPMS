package cn.xpms.paas.api.service.business.impl.construction;

import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.dao.customize.repository.CustomizeCategoryMapper;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.*;
import cn.xpms.paas.api.service.business.inter.construction.CategoryServiceInterface;
import cn.xpms.paas.api.service.common.inter.api.PaasApiServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.BeanUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/2 9:07
 * @Version 1.0
 */
@Service
public class CategoryServiceImplements implements CategoryServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(CategoryServiceImplements.class);

    /**
     * 涂鸦API接口service
     */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    /**
     * 设备品类列表CURD
     */
    @Autowired
    PaasDeviceCategoryMapper paasDeviceCategoryMapper;

    /**
     * 红外设备品类列表
     */
    @Autowired
    PaasInfraredCategoryMapper paasInfraredCategoryMapper;

    /**
     * 拆分设备品类列表
     */
    @Autowired
    PaasSplitDeviceCategoryMapper paasSplitDeviceCategoryMapper;

    /**
     * 红外设备品牌列表
     */
    @Autowired
    PaasInfraredBrandMapper paasInfraredBrandMapper;

    /**
     * 红外设备品牌支持的遥控器索引CURD
     */
    @Autowired
    PaasInfraredBrandRemoteMapper paasInfraredBrandRemoteMapper;

    /**
     * 设备品类自定义CURD
     */
    @Autowired
    CustomizeCategoryMapper customizeCategoryMapper;

    @Autowired
    PaasCategoryFunctionMapper paasCategoryFunctionMapper;


    /**
     * @Description: 同步施工支持的设备品类列表
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 9:26
     */
    @Override
    public ApiResponseEntity synchroDeviceCategory() throws AppException {
        try {
            PaasApiResponseEntity<List<Map<String, Object>>> responseData =
                    paasApiServiceInterface.call(PaasApiEnum.DEVICE_CATEGORY, null);

            // 更新数据库数据
            this.updateDeviceCategory(responseData.getResult());
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【CategoryServiceImplements.synchroDeviceCategory】同步施工支持的设备品类列表异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.synchroDeviceCategory】同步施工支持的设备品类列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步品类支持的功能集
     * @Params: [category]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/10 17:11
     */
    @Override
    public ApiResponseEntity synchroDeviceCategoryFunctions(String category) throws AppException {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("category", category);
            PaasApiResponseEntity<Map<String, Object>> responseEntity = paasApiServiceInterface.call(PaasApiEnum.GET_CATEGORY_FUNCTIONS, params);

            Map<String, Object> categoryFunctions = responseEntity.getResult();

            this.updateDeviceCategoryFunctions((List<Map<String, Object>>) categoryFunctions.get("functions"), category);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, responseEntity);
        } catch (AppException e) {
            logger.error("【CategoryServiceImplements.synchroDeviceCategoryFunctions】同步品类支持的功能集异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.synchroDeviceCategoryFunctions】同步品类支持的功能集异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 更新品类功能集
     * @Params: [functions, category]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/10 17:17
     */
    private void updateDeviceCategoryFunctions(List<Map<String, Object>> functions, String category) throws AppException {
        try {
            // 遍历
            for (Map<String, Object> function : functions) {
                PaasCategoryFunction categoryFunction = JSON.parseObject(JSON.toJSONString(function), PaasCategoryFunction.class);
                categoryFunction.setCategory(category);
                categoryFunction.setDescription(function.get("desc").toString());

                // 校验是否存在
                PaasCategoryFunctionExample example = new PaasCategoryFunctionExample();
                example.createCriteria().andCategoryEqualTo(category).andCodeEqualTo(categoryFunction.getCode()).
                        andValidEqualTo(SystemConstants.INT_YES);
                if (paasCategoryFunctionMapper.countByExample(example) != 0) {
                    // 修改
                    paasCategoryFunctionMapper.updateByExampleSelective(categoryFunction, example);
                } else {
                    paasCategoryFunctionMapper.insertSelective(categoryFunction);
                }
            }
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.updateDeviceCategoryFunctions】更新设备品类功能集异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 更新设备品类
     * @Params: [params]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/2 10:13
     */
    private void updateDeviceCategory(List<Map<String, Object>> params) throws AppException {
        try {
            PaasDeviceCategory paasDeviceCategory;
            PaasDeviceCategoryExample example;
            // 遍历
            for (Map<String, Object> category : params) {
                paasDeviceCategory = BeanUtil.mapToBean(category, PaasDeviceCategory.class, true);
                // 校验是否存在
                example = new PaasDeviceCategoryExample();
                example.createCriteria().andConstructionCategoryEqualTo(paasDeviceCategory.getConstructionCategory());
                if (paasDeviceCategoryMapper.countByExample(example) != 0) {
                    // 修改
                    paasDeviceCategoryMapper.updateByExampleSelective(paasDeviceCategory, example);
                } else {
                    paasDeviceCategoryMapper.insertSelective(paasDeviceCategory);
                }

                try {
                    this.synchroDeviceCategoryFunctions(paasDeviceCategory.getCategory());
                } catch (Exception e) {
                    logger.error("【CategoryServiceImplements.updateDeviceCategory】同步设备品类功能集异常：" + e);
                }
            }
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.updateDeviceCategory】更新设备品类异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步施工支持的红外设备品类列表
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 9:36
     */
    @Override
    public ApiResponseEntity synchroInfraredCategory() throws AppException {
        try {
            PaasApiResponseEntity<List<Map<String, Object>>> responseData =
                    paasApiServiceInterface.call(PaasApiEnum.INFRARED_CATEGORY, null);
            // 更新数据库红外设备品类
            this.updateInfraredCategory(responseData.getResult());
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【CategoryServiceImplements.synchroInfraredCategory】同步施工支持的红外设备品类列表异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.synchroInfraredCategory】同步施工支持的红外设备品类列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 更新红外设备品类
     * @Params: [params]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/2 10:24
     */
    private void updateInfraredCategory(List<Map<String, Object>> params) throws AppException {
        try {
            PaasInfraredCategory paasInfraredCategory;
            PaasInfraredCategoryExample example;

            // 遍历
            for (Map<String, Object> infrared : params) {
                paasInfraredCategory = BeanUtil.mapToBean(infrared, PaasInfraredCategory.class, true);
                // 校验是否存在
                example = new PaasInfraredCategoryExample();
                example.createCriteria().
                        andCategoryIdEqualTo(paasInfraredCategory.getCategoryId());
                if (paasInfraredCategoryMapper.countByExample(example) != 0) {
                    // 修改
                    paasInfraredCategoryMapper.updateByExampleSelective(paasInfraredCategory, example);
                } else {
                    paasInfraredCategoryMapper.insertSelective(paasInfraredCategory);
                }
                try {
                    // 同步红外设备品牌列表
                    this.synchroInfraredCategoryBrand(paasInfraredCategory);
                } catch (Exception e) {
                    logger.error("【CategoryServiceImplements.updateInfraredCategory】更新红外设备品类异常：" + paasInfraredCategory);
                }
            }
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.updateInfraredCategory】更新红外设备品类异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步红外设备品类品牌
     * @Params: [category_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 10:30
     */
    @Override
    public ApiResponseEntity synchroInfraredCategoryBrand(PaasInfraredCategory paasInfraredCategory) throws AppException {
        try {
            PaasApiResponseEntity<List<Map<String, Object>>> responseData =
                    paasApiServiceInterface.call(PaasApiEnum.INFRARED_CATEGORY_BRAND, BeanUtil.beanToMap(paasInfraredCategory, true));
            // 更新数据库红外设备品类品牌
            this.updateInfraredCategoryBrand(responseData.getResult(),
                    paasInfraredCategory.getCategoryId());
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【CategoryServiceImplements.synchroInfraredCategoryBrand】同步红外设备品类品牌异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.synchroInfraredCategoryBrand】同步红外设备品类品牌异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 更新数据库红外设备品类品牌
     * @Params: [parmas]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/2 10:45
     */
    private void updateInfraredCategoryBrand(List<Map<String, Object>> params, String category_id) throws AppException {
        try {
            PaasInfraredBrand paasInfraredBrand;
            PaasInfraredBrandExample example;

            // 遍历
            for (Map<String, Object> brand : params) {
                paasInfraredBrand = BeanUtil.mapToBean(brand, PaasInfraredBrand.class, true);
                // 校验是否存在
                example = new PaasInfraredBrandExample();
                example.createCriteria().
                        andBrandIdEqualTo(paasInfraredBrand.getBrandId());
                paasInfraredBrand.setCategoryId(category_id);
                if (paasInfraredBrandMapper.countByExample(example) != 0) {
                    // 修改
                    paasInfraredBrandMapper.updateByExampleSelective(paasInfraredBrand, example);
                } else {
                    paasInfraredBrandMapper.insertSelective(paasInfraredBrand);
                }
                try {
                    // 同步红外设备品牌列表
                    this.synchroInfraredCategoryBrandRemote(paasInfraredBrand);
                } catch (Exception e) {
                    logger.error("【CategoryServiceImplements.updateInfraredCategoryBrand】更新数据库红外设备品类品牌异常：" + paasInfraredBrand);
                }
            }
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.updateInfraredCategoryBrand】更新数据库红外设备品类品牌异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步红外设备品牌支持的索引
     * @Params: [paasInfraredBrand]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 10:50
     */
    @Override
    public ApiResponseEntity synchroInfraredCategoryBrandRemote(PaasInfraredBrand paasInfraredBrand) throws AppException {
        try {
            PaasApiResponseEntity<List<String>> responseData = paasApiServiceInterface.call(PaasApiEnum.INFRARED_CATEGORY_BRAND_REMOTE,
                    BeanUtil.beanToMap(paasInfraredBrand, true));
            // 更新数据库红外设备品类品牌
            this.updateInfraredBrandRemote(responseData.getResult(), paasInfraredBrand);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【CategoryServiceImplements.synchroInfraredCategoryBrand】同步红外设备品类品牌异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.synchroInfraredCategoryBrand】同步红外设备品类品牌异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 更新红外设备品牌遥控器索引
     * @Params: [params, brand]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/2 10:55
     */
    private void updateInfraredBrandRemote(List<String> params, PaasInfraredBrand brand) throws AppException {
        try {
            PaasInfraredBrandRemote paasInfraredBrandRemote;
            PaasInfraredBrandRemoteExample example;

            // 遍历
            for (String remote : params) {
                paasInfraredBrandRemote = new PaasInfraredBrandRemote();
                paasInfraredBrandRemote.setRemote(remote);
                paasInfraredBrandRemote.setCategoryId(brand.getCategoryId());
                paasInfraredBrandRemote.setBrandId(brand.getBrandId());

                // 校验是否存在
                example = new PaasInfraredBrandRemoteExample();
                example.createCriteria().
                        andRemoteEqualTo(paasInfraredBrandRemote.getRemote());
                long count = paasInfraredBrandRemoteMapper.countByExample(example);
                if (paasInfraredBrandRemoteMapper.countByExample(example) != 0) {
                    // 修改
                    paasInfraredBrandRemoteMapper.updateByExampleSelective(paasInfraredBrandRemote, example);
                } else {
                    paasInfraredBrandRemoteMapper.insertSelective(paasInfraredBrandRemote);
                }
            }
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.updateInfraredBrandRemote】更新红外设备品牌遥控器索引异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步施工支持的拆分设备品类列表
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 9:37
     */
    @Override
    public ApiResponseEntity synchroSplitDeviceCategory() throws AppException {
        try {
            PaasApiResponseEntity<List<Map<String, Object>>> responseData = paasApiServiceInterface.call(PaasApiEnum.SPLIT_DEVICE_CATEGORY,
                    null);
            // 更新数据库数据
            this.updateSplitDeviceCategory(responseData.getResult());
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【CategoryServiceImplements.synchroSplitDeviceCategory】同步施工支持的拆分设备品类列表异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.synchroSplitDeviceCategory】同步施工支持的拆分设备品类列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工支持的设备品类类型
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:51
     */
    @Override
    public ApiResponseEntity getDeviceCategoryType() throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    customizeCategoryMapper.getDeviceCategoryType());
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.getDeviceCategoryType】查询施工支持的设备品类类型异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工支持的设备品类
     * @Params: [type]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:55
     */
    @Override
    public ApiResponseEntity getDeviceCategory(String type) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    customizeCategoryMapper.getDeviceCategory(type));
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.getDeviceCategory】查查询施工支持的设备品类异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工支持的设备施工品类
     * @Params: [type, category]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:57
     */
    @Override
    public ApiResponseEntity getDeviceConstructionCategory(String type, String category) throws AppException {
        try {
            PaasDeviceCategoryExample example = new PaasDeviceCategoryExample();
            example.createCriteria().andTypeEqualTo(type).andCategoryEqualTo(category);
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    paasDeviceCategoryMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.getDeviceConstructionCategory】查询施工支持的设备施工品类异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工支持的拆分设备品类
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:59
     */
    @Override
    public ApiResponseEntity getDeviceSplitCategory() throws AppException {
        try {
            PaasSplitDeviceCategoryExample example = new PaasSplitDeviceCategoryExample();
            example.createCriteria();
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    paasSplitDeviceCategoryMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.getDeviceSplitCategory】查询施工支持的拆分设备品类异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工支持的红外设备品类
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 12:00
     */
    @Override
    public ApiResponseEntity getDeviceInfraredCategory() throws AppException {
        try {
            PaasInfraredCategoryExample example = new PaasInfraredCategoryExample();
            example.createCriteria();
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    paasInfraredCategoryMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.getDeviceInfraredCategory】查询施工支持的红外设备品类异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工支持的红外设备品牌
     * @Params: [brand]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 12:01
     */
    @Override
    public ApiResponseEntity getDeviceInfraredCategoryBrand(PaasInfraredBrand brand) throws AppException {
        try {
            PaasInfraredBrandExample example = new PaasInfraredBrandExample();
//            brand.setValid(SystemConstants.INT_YES);
//            BeanUtil.beanToExample(brand, example);
            if (StringUtils.isNotBlank(brand.getBrandName())) {
                example.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andBrandNameLike("%" + brand.getBrandName() + "%");
            } else {
                example.createCriteria().andValidEqualTo(SystemConstants.INT_YES);
            }

            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    paasInfraredBrandMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.getDeviceInfraredCategoryBrand】查询施工支持的红外设备品牌异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工支持的红外设备品牌遥控器索引
     * @Params: [category_id, brand_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 12:02
     */
    @Override
    public ApiResponseEntity getDeviceInfraredCategoryBrandRemote(String category_id, String brand_id) throws AppException {
        try {
            PaasInfraredBrandRemoteExample example = new PaasInfraredBrandRemoteExample();
            example.createCriteria().andCategoryIdEqualTo(category_id).andBrandIdEqualTo(brand_id);
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    paasInfraredBrandRemoteMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.getDeviceInfraredCategoryBrandRemote】查询施工支持的红外设备品牌遥控器索引异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 更新拆分设备品类数据
     * @Params: [params]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/2 13:37
     */
    private void updateSplitDeviceCategory(List<Map<String, Object>> params) throws AppException {
        try {
            PaasSplitDeviceCategory paasSplitDeviceCategory;
            PaasSplitDeviceCategoryExample example;
            // 遍历
            for (Map<String, Object> split : params) {
                paasSplitDeviceCategory = BeanUtil.mapToBean(split, PaasSplitDeviceCategory.class, true);
                // 校验是否存在
                example = new PaasSplitDeviceCategoryExample();
                example.createCriteria().andCategoryCodeEqualTo(paasSplitDeviceCategory.getCategoryCode());
                if (paasSplitDeviceCategoryMapper.countByExample(example) != 0) {
                    // 修改
                    paasSplitDeviceCategoryMapper.updateByExampleSelective(paasSplitDeviceCategory, example);
                } else {
                    paasSplitDeviceCategoryMapper.insertSelective(paasSplitDeviceCategory);
                }
            }
        } catch (Exception e) {
            logger.error("【CategoryServiceImplements.updateSplitDeviceCategory】更新拆分设备品类异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}