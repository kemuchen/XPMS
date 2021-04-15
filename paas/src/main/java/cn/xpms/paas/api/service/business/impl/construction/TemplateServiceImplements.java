package cn.xpms.paas.api.service.business.impl.construction;

import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.dto.template.ConstructionTemplatePositionPo;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.bean.vo.template.*;
import cn.xpms.paas.api.dao.customize.repository.CustomizeCategoryMapper;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.*;
import cn.xpms.paas.api.service.business.inter.construction.TemplateServiceInterface;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TemplateServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/2 16:44
 * @Version 1.0
 */
@Service
public class TemplateServiceImplements implements TemplateServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(TemplateServiceImplements.class);

    /**
     * 涂鸦API接口service
     */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    /**
     * 施工模板CURD
     */
    @Autowired
    PaasConstructionTemplateMapper templateMapper;

    /**
     * 施工模板区域CURD
     */
    @Autowired
    PaasConstructionTemplateAreaMapper templateAreaMapper;

    /**
     * 施工模板位置CURD
     */
    @Autowired
    PaasConstructionTemplatePositionMapper templatePositionMapper;

    /**
     * 施工模板拆分设备CURD
     */
    @Autowired
    PaasConstructionTemplateSplitDeviceMapper templateSplitDeviceMapper;

    /**
     * 施工模板红外设备CURD
     */
    @Autowired
    PaasConstructionTemplateInfraredDeviceMapper templateInfraredDeviceMapper;

    /**
     * 设备品类自定义CURD
     */
    @Autowired
    CustomizeCategoryMapper customizeCategoryMapper;

    /**
     * @Description: 查询模板列表
     * @Params: [template]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 16:49
     */
    @Override
    public ApiResponseEntity getTemplates(PaasConstructionTemplate template) throws AppException {
        try {
            PaasConstructionTemplateExample example = new PaasConstructionTemplateExample();
            template.setValid(SystemConstants.INT_YES);
            BeanUtil.beanToExample(template, example);
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    templateMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.getTemplates】查询模板列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工模板详情
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 16:55
     */
    @Override
    public ApiResponseEntity getTemplate(Integer id) throws AppException {
        try {
            // 查询模板信息
            ConstructionTemplateVo templateVo = new ConstructionTemplateVo();
            templateVo.setTemplate(templateMapper.selectByPrimaryKey(id));
            templateVo.setAreas(this.getTemplateAreasData(templateVo.getTemplate().getTemplateId()));
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    templateVo);
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.getTemplate】查询施工模板详情异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.getTemplate】查询施工模板详情异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工模板区域
     * @Params: [template_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 17:34
     */
    @Override
    public ApiResponseEntity getTemplateAreas(String template_id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    this.getTemplateAreasData(template_id));
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.getTemplateAreas】查询施工模板区域异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.getTemplateAreas】查询施工模板区域异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工模板位置列表
     * @Params: [template_id]
     * @return: cn.xpms.paas.api.bean.vo.category.ConstructionTemplateAreaVo
     * @Author: 柯雷
     * @Date: 2020/12/2 17:08
     */
    private List<ConstructionTemplateAreaVo> getTemplateAreasData(String template_id) throws AppException {
        try {
            // 区域信息列表
            PaasConstructionTemplateAreaExample areaExample = new PaasConstructionTemplateAreaExample();
            areaExample.createCriteria().andTemplateIdEqualTo(template_id).andValidEqualTo(SystemConstants.INT_YES);
            List<PaasConstructionTemplateArea> areas = templateAreaMapper.selectByExample(areaExample);

            List<ConstructionTemplateAreaVo> areaVos = new ArrayList<>();

            // 遍历
            for (PaasConstructionTemplateArea area : areas) {
                ConstructionTemplateAreaVo areaVo = new ConstructionTemplateAreaVo();
                areaVo.setArea(area);

                // 查询位置列表
                areaVo.setPositions(this.getTemplatePositionsData(area.getAreaId()));
                areaVos.add(areaVo);
            }
            return areaVos;
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.getTemplateAreasData】查询施工模板位置列表异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.getTemplateAreasData】查询施工模板位置列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工模板位置
     * @Params: [area_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 17:35
     */
    @Override
    public ApiResponseEntity getTemplatePositions(String area_id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    this.getTemplatePositionsData(area_id));
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.getTemplatePosition】查询施工模板位置异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.getTemplatePosition】查询施工模板位置异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工模板位置信息
     * @Params: [area_id]
     * @return: java.util.List<cn.xpms.paas.api.bean.vo.category.ConstructionTemplatePositionVo>
     * @Author: 柯雷
     * @Date: 2020/12/2 17:11
     */
    private List<ConstructionTemplatePositionVo> getTemplatePositionsData(String area_id) throws AppException {
        try {
            // 位置信息列表
            PaasConstructionTemplatePositionExample example = new PaasConstructionTemplatePositionExample();
            example.createCriteria().andAreaIdEqualTo(area_id).andValidEqualTo(SystemConstants.INT_YES);
            List<PaasConstructionTemplatePosition> positions = templatePositionMapper.selectByExample(example);

            List<ConstructionTemplatePositionVo> positionVos = new ArrayList<>();
            // 遍历位置列表，查询设备信息
            for (PaasConstructionTemplatePosition position : positions) {
                ConstructionTemplatePositionVo positionVo = new ConstructionTemplatePositionVo();
                positionVo.setPosition(position);
                // 拆分设备信息
                positionVo.setSplitDevices(this.getTemplateSplitDevice(position.getPositionId()));
                // 红外设备信息
                positionVo.setInfraredDevices(this.getTemplateInfraredDevice(position.getPositionId()));
                positionVos.add(positionVo);
            }
            return positionVos;
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.getTemplatePositionsData】查询施工模板位置信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.getTemplatePositionsData】查询施工模板位置信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工模板拆分设备信息
     * @Params: [position_id]
     * @return: java.util.List<cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplateSplitDevice>
     * @Author: 柯雷
     * @Date: 2020/12/3 10:03
     */
    private List<PositionSplitDeviceVo> getTemplateSplitDevice(String position_id) throws AppException {
        try {
            // 查询拆分设备信息
            return customizeCategoryMapper.getTemplateSplitDevice(position_id);
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.getTemplateSplitDevice】查询施工模板拆分设备信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工模板红外设备信息
     * @Params: [position_id]
     * @return: java.util.List<cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplateSplitDevice>
     * @Author: 柯雷
     * @Date: 2020/12/3 10:05
     */
    private List<PositionInfraredDeviceVo> getTemplateInfraredDevice(String position_id) throws AppException {
        try {
            // 查询红外设备信息
            return customizeCategoryMapper.getTemplateInfraredCategoryBrandRemote(position_id);
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.getTemplateInfraredDevice】查询施工模板红外设备信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 创建施工模板
     * @Params: [template]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 8:34
     */
    @Override
    public ApiResponseEntity createTemplate(PaasConstructionTemplate template) throws AppException {
        try {
            // 调用涂鸦接口
            PaasApiResponseEntity<String> responseData = paasApiServiceInterface.call(PaasApiEnum.ADD_TEMPLATE,
                    BeanUtil.beanToMap(template, true));
            // 新增施工模板
            template.setBindingStatus(SystemConstants.STR_FALSE);
            template.setTemplateId(responseData.getResult());
            templateMapper.insertSelective(template);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, template);
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.createTemplate】创建施工模板异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.createTemplate】创建施工模板异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修改施工模板
     * @Params: [template]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 9:17
     */
    @Override
    public ApiResponseEntity modifyTemplate(PaasConstructionTemplate template) throws AppException {
        try {
            // 调用涂鸦接口
            paasApiServiceInterface.call(PaasApiEnum.MODIFY_TEMPLATE,
                    BeanUtil.beanToMap(template, true));
            // 更新施工模板
            templateMapper.updateByPrimaryKeySelective(template);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, template);
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.createTemplate】创建施工模板异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.createTemplate】创建施工模板异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除施工模板
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 9:19
     */
    @Override
    public ApiResponseEntity deleteTemplate(Integer id) throws AppException {
        try {
            PaasConstructionTemplate template = templateMapper.selectByPrimaryKey(id);

            // 删除模板下的区域信息
            this.deleteTemplateAreaByTemplateId(template.getTemplateId());

            // 调用涂鸦接口删除模板
            paasApiServiceInterface.call(PaasApiEnum.DELETE_TEMPLATE,
                    BeanUtil.beanToMap(template, true));

            // 删除数据库施工模板信息
            template.setValid(SystemConstants.INT_NO);
            templateMapper.updateByPrimaryKeySelective(template);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.deleteTemplate】删除施工模板异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.deleteTemplate】删除施工模板异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 新增施工模板区域位置信息
     * @Params: [area]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 9:48
     */
    @Override
    public ApiResponseEntity createTemplateArea(PaasConstructionTemplateArea area) throws AppException {
        try {
            // 调用涂鸦接口
            PaasApiResponseEntity<String> responseData = paasApiServiceInterface.call(PaasApiEnum.ADD_TEMPLATE_AREA,
                    BeanUtil.beanToMap(area, true));

            // 新增数据库记录
            area.setAreaId(responseData.getResult());
            templateAreaMapper.insertSelective(area);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, area);
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.createTemplateArea】新增施工模板区域位置信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.createTemplateArea】新增施工模板区域位置信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修改施工模板区域信息
     * @Params: [area]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 9:52
     */
    @Override
    public ApiResponseEntity modifyTemplateArea(PaasConstructionTemplateArea area) throws AppException {
        try {
            // 调用涂鸦接口
            paasApiServiceInterface.call(PaasApiEnum.MODIFY_TEMPLATE_AREA,
                    BeanUtil.beanToMap(area, true));

            // 更新数据库记录
            templateAreaMapper.updateByPrimaryKeySelective(area);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, area);
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.createTemplateArea】新增施工模板区域位置信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.createTemplateArea】新增施工模板区域位置信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除施工模板区域
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 9:34
     */
    @Override
    public ApiResponseEntity deleteTemplateArea(Integer id) throws AppException {
        try {
            PaasConstructionTemplateArea area = templateAreaMapper.selectByPrimaryKey(id);
            this.deleteTemplateArea(area);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.deleteTemplateArea】删除施工模板区域异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.deleteTemplateArea】删除施工模板区域异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 根据模板id删除施工模板区域信息
     * @Params: [template_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 9:26
     */
    @Override
    public ApiResponseEntity deleteTemplateAreaByTemplateId(String template_id) throws AppException {
        try {
            // 根据模板id查询区域列表
            PaasConstructionTemplateAreaExample example = new PaasConstructionTemplateAreaExample();
            example.createCriteria().andTemplateIdEqualTo(template_id);
            List<PaasConstructionTemplateArea> areas = templateAreaMapper.selectByExample(example);

            // 遍历区域列表进行删除
            for (PaasConstructionTemplateArea area : areas) {
                this.deleteTemplateArea(area);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.deleteTemplateAreaByTemplateId】根据模板id删除施工模板区域信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.deleteTemplateAreaByTemplateId】根据模板id删除施工模板区域信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除施工模板区域信息
     * @Params: [area]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/3 9:30
     */
    private void deleteTemplateArea(PaasConstructionTemplateArea area) throws AppException {
        try {
            // 删除施工模板区域下的设备安装位置信息
            this.deleteTemplatePositionByAreaId(area.getAreaId());

            paasApiServiceInterface.call(PaasApiEnum.DELETE_TEMPLATE_AREA,
                    BeanUtil.beanToMap(area, true));

            // 删除数据库施工模板区域信息
            area.setValid(SystemConstants.INT_NO);
            templateAreaMapper.updateByPrimaryKeySelective(area);
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.deleteTemplateArea】删除施工模板区域信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.deleteTemplateArea】删除施工模板区域信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 创建施工模板位置
     * @Params: [position]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 9:56
     */
    @Override
    public ApiResponseEntity createTemplatePosition(ConstructionTemplatePositionPo positionPo) throws AppException {
        try {
            PaasApiResponseEntity<String> responseData = paasApiServiceInterface.call(PaasApiEnum.ADD_TEMPLATE_AREA_POSITION,
                    this.createPositionParmas(positionPo));

            PaasConstructionTemplatePosition position = positionPo.getPosition();
            position.setPositionId(responseData.getResult());
            // 新增数据库数据
            templatePositionMapper.insertSelective(position);
            // 新增拆分设备
            this.createTemplatePositionSplitDevice(positionPo.getSplitDevices(), responseData.getResult().toString());
            // 新增红外设备
            this.createTemplatePositionInfraredDevice(positionPo.getInfraredDevices(), responseData.getResult().toString());
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.createTemplatePosition】创建施工模板位置异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.createTemplatePosition】创建施工模板位置异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 构建施工模板位置信息接口参数
     * @Params: [positionVo]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @Author: 柯雷
     * @Date: 2020/12/3 10:14
     */
    private Map<String, Object> createPositionParmas(ConstructionTemplatePositionPo positionPo) throws AppException {
        try {
            Map<String, Object> params = BeanUtil.beanToMap(positionPo.getPosition(), true);
            params.put("split_devices", positionPo.getSplitDevices());
            params.put("infrared_devices", positionPo.getInfraredDevices());
            return params;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.createPositionParmas】构建施工模板位置信息接口参数异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 新增施工模板位置拆分设备
     * @Params: [splitDevices]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/3 10:23
     */
    private void createTemplatePositionSplitDevice(List<PaasConstructionTemplateSplitDevice> splitDevices, String position_id) throws AppException {
        try {
            // 遍历新增
            for (PaasConstructionTemplateSplitDevice splitDevice : splitDevices) {
                splitDevice.setPositionId(position_id);
                templateSplitDeviceMapper.insertSelective(splitDevice);
            }
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.createTemplatePositionSplitDevice】新增施工模板位置拆分设备异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 新增施工模板位置红外设备
     * @Params: [splitDevices]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/3 10:24
     */
    private void createTemplatePositionInfraredDevice(List<PaasConstructionTemplateInfraredDevice> infraredDevices, String position_id) throws AppException {
        try {
            // 遍历新增
            for (PaasConstructionTemplateInfraredDevice infraredDevice : infraredDevices) {
                infraredDevice.setPositionId(position_id);
                templateInfraredDeviceMapper.insertSelective(infraredDevice);
            }
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.createTemplatePositionInfraredDevice】新增施工模板位置红外设备异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修改施工模板位置
     * @Params: [positionVo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 9:57
     */
    @Override
    public ApiResponseEntity modifyTemplatePosition(ConstructionTemplatePositionPo positionPo) throws AppException {
        try {
            paasApiServiceInterface.call(PaasApiEnum.MODIFY_TEMPLATE_AREA_POSITION,
                    this.createPositionParmas(positionPo));

            PaasConstructionTemplatePosition position = positionPo.getPosition();
            // 更新数据库数据
            templatePositionMapper.updateByPrimaryKeySelective(position);
            // 修改拆分设备
            this.modifyTemplatePositionSplitDevice(positionPo.getSplitDevices());
            // 修改红外设备
            this.modifyTemplatePositionInfraredDevice(positionPo.getInfraredDevices());
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.modifyTemplatePosition】修改施工模板位置异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.modifyTemplatePosition】修改施工模板位置异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修改施工模板位置拆分设备
     * @Params: [splitDevices]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/3 10:23
     */
    private void modifyTemplatePositionSplitDevice(List<PaasConstructionTemplateSplitDevice> splitDevices) throws AppException {
        try {
            // 遍历新增
            for (PaasConstructionTemplateSplitDevice splitDevice : splitDevices) {
                templateSplitDeviceMapper.updateByPrimaryKeySelective(splitDevice);
            }
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.modifyTemplatePositionSplitDevice】修改施工模板位置拆分设备异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修改施工模板位置红外设备
     * @Params: [splitDevices]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/3 10:24
     */
    private void modifyTemplatePositionInfraredDevice(List<PaasConstructionTemplateInfraredDevice> infraredDevices) throws AppException {
        try {
            // 遍历新增
            for (PaasConstructionTemplateInfraredDevice infraredDevice : infraredDevices) {
                templateInfraredDeviceMapper.updateByPrimaryKeySelective(infraredDevice);
            }
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.modifyTemplatePositionInfraredDevice】修改施工模板位置红外设备异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除施工模板设备安装位置信息
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 9:48
     */
    @Override
    public ApiResponseEntity deleteTemplatePosition(Integer id) throws AppException {
        try {
            PaasConstructionTemplatePosition position = templatePositionMapper.selectByPrimaryKey(id);
            this.deleteTemplaePosition(position);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.deleteTemplatePosition】删除施工模板设备安装位置信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.deleteTemplatePosition】删除施工模板设备安装位置信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 根据区域id删除施工设备安装位置信息
     * @Params: [area_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 9:46
     */
    @Override
    public ApiResponseEntity deleteTemplatePositionByAreaId(String area_id) throws AppException {
        try {
            // 根据模板id查询区域列表
            PaasConstructionTemplatePositionExample example = new PaasConstructionTemplatePositionExample();
            example.createCriteria().andAreaIdEqualTo(area_id);
            List<PaasConstructionTemplatePosition> positions = templatePositionMapper.selectByExample(example);

            // 遍历区域列表进行删除
            for (PaasConstructionTemplatePosition position : positions) {
                this.deleteTemplaePosition(position);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.deleteTemplatePositionByAreaId】根据区域id删除施工设备安装位置信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.deleteTemplatePositionByAreaId】根据区域id删除施工设备安装位置信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除施工模板设备安装位置信息
     * @Params: [position]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/3 9:42
     */
    private void deleteTemplaePosition(PaasConstructionTemplatePosition position) throws AppException {
        try {
            // 调用涂鸦删除接口
            paasApiServiceInterface.call(PaasApiEnum.DELETE_TEMPLATE_AREA_POSITION,
                    BeanUtil.beanToMap(position, true));

            // 删除施工模板位置数据记录
            position.setValid(SystemConstants.INT_NO);
            templatePositionMapper.updateByPrimaryKeySelective(position);
            // 删除拆分设备信息
            this.deleteTemplatePositionSplitDevice(position.getPositionId());
            // 删除红外设备信息
            this.deleteTemplatePositionInfraredDevice(position.getPositionId());
        } catch (AppException e) {
            logger.error("【TemplateServiceImplements.deleteTemplateArea】删除施工模板区域信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.deleteTemplateArea】删除施工模板区域信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除拆分设备信息
     * @Params: [position_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/3 10:08
     */
    private void deleteTemplatePositionSplitDevice(String position_id) throws AppException {
        try {
            PaasConstructionTemplateSplitDeviceExample example = new PaasConstructionTemplateSplitDeviceExample();
            example.createCriteria().andPositionIdEqualTo(position_id);

            PaasConstructionTemplateSplitDevice device = new PaasConstructionTemplateSplitDevice();
            device.setValid(SystemConstants.INT_NO);
            templateSplitDeviceMapper.updateByExampleSelective(device, example);
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.deleteTemplatePositionSplitDevice】删除拆分设备信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除红外设备信息
     * @Params: [position_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/3 10:09
     */
    private void deleteTemplatePositionInfraredDevice(String position_id) throws AppException {
        try {
            PaasConstructionTemplateInfraredDeviceExample example = new PaasConstructionTemplateInfraredDeviceExample();
            example.createCriteria().andPositionIdEqualTo(position_id);

            PaasConstructionTemplateInfraredDevice device = new PaasConstructionTemplateInfraredDevice();
            device.setValid(SystemConstants.INT_NO);
            templateInfraredDeviceMapper.updateByExampleSelective(device, example);
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.deleteTemplatePositionInfraredDevice】删除红外设备信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity copyTemplate(Integer id, String templateName) throws AppException {
        try {
            PaasConstructionTemplate template = templateMapper.selectByPrimaryKey(id);
            if (template == null) {
                throw new AppException(SysErrorCode.PARAMS_CHECK_ERROR.getError_code(), "未查询到对应模板", "未查询到对应模板");
            }

            // 新增施工模板数据
            PaasConstructionTemplate newTemplate = new PaasConstructionTemplate();
            newTemplate.setTemplateName(templateName);
            newTemplate.setProjectId(template.getProjectId());
            newTemplate.setDescription(template.getDescription());
            newTemplate.setMemo("复制模板" + template.getId());

            // 新增施工模板
            ApiResponseEntity templateResponse = this.createTemplate(newTemplate);
            if (templateResponse.getCode().equals(SysErrorCode.SUCCESS.getError_code())) {
                newTemplate = (PaasConstructionTemplate) templateResponse.getData();

                // 模板区域列表
                PaasConstructionTemplateAreaExample areaExample = new PaasConstructionTemplateAreaExample();
                areaExample.createCriteria().andTemplateIdEqualTo(template.getTemplateId()).andValidEqualTo(SystemConstants.INT_YES);
                List<PaasConstructionTemplateArea> areas = templateAreaMapper.selectByExample(areaExample);
                for (PaasConstructionTemplateArea area : areas) {
                    // 新增区域数据
                    PaasConstructionTemplateArea newArea = new PaasConstructionTemplateArea();
                    newArea.setProjectId(template.getProjectId());
                    newArea.setTemplateId(newTemplate.getTemplateId());
                    newArea.setAreaName(area.getAreaName());
                    newArea.setMemo("复制模板区域");

                    // 新增区域
                    ApiResponseEntity areaResponse = this.createTemplateArea(newArea);
                    if (areaResponse.getCode().equals(SysErrorCode.SUCCESS.getError_code())) {
                        newArea = (PaasConstructionTemplateArea) areaResponse.getData();

                        // 模板位置列表
                        PaasConstructionTemplatePositionExample positionExample = new PaasConstructionTemplatePositionExample();
                        positionExample.createCriteria().andTemplateIdEqualTo(template.getTemplateId()).andAreaIdEqualTo(area.getAreaId())
                                .andValidEqualTo(SystemConstants.INT_YES);
                        List<PaasConstructionTemplatePosition> positions = templatePositionMapper.selectByExample(positionExample);
                        for (PaasConstructionTemplatePosition position : positions) {
                            ConstructionTemplatePositionPo positionPo = new ConstructionTemplatePositionPo();

                            // 新增位置数据
                            PaasConstructionTemplatePosition newPosition = new PaasConstructionTemplatePosition();
                            newPosition.setProjectId(template.getProjectId());
                            newPosition.setTemplateId(newTemplate.getTemplateId());
                            newPosition.setAreaId(newArea.getAreaId());
                            newPosition.setPositionName(position.getPositionName());
                            newPosition.setConstructionCategory(position.getConstructionCategory());
                            newPosition.setCustomDeviceName(position.getCustomDeviceName());
                            newPosition.setMemo("复制模板位置");

                            positionPo.setPosition(newPosition);

                            // 新增位置红外设备数据
                            List<PaasConstructionTemplateInfraredDevice> newInfraredDevices = new ArrayList<>();
                            PaasConstructionTemplateInfraredDeviceExample infraredDeviceExample = new PaasConstructionTemplateInfraredDeviceExample();
                            infraredDeviceExample.createCriteria().andPositionIdEqualTo(position.getPositionId()).andValidEqualTo(SystemConstants.INT_YES);
                            List<PaasConstructionTemplateInfraredDevice> infraredDevices = templateInfraredDeviceMapper.selectByExample(infraredDeviceExample);
                            for (PaasConstructionTemplateInfraredDevice infraredDevice : infraredDevices) {
                                PaasConstructionTemplateInfraredDevice newInfraredDevice = new PaasConstructionTemplateInfraredDevice();
                                newInfraredDevice.setCategoryId(infraredDevice.getCategoryId());
                                newInfraredDevice.setBrandId(infraredDevice.getBrandId());
                                newInfraredDevice.setRemote(infraredDevice.getRemote());
                                newInfraredDevice.setAreaId(newArea.getAreaId());
                                newInfraredDevice.setCustomDeviceName(infraredDevice.getCustomDeviceName());
                                newInfraredDevice.setOperatorId(infraredDevice.getOperatorId());

                                newInfraredDevices.add(newInfraredDevice);
                            }

                            positionPo.setInfraredDevices(newInfraredDevices);

                            // 新增位置拆分设备数据
                            List<PaasConstructionTemplateSplitDevice> newSplitDevices = new ArrayList<>();
                            PaasConstructionTemplateSplitDeviceExample splitDeviceExample = new PaasConstructionTemplateSplitDeviceExample();
                            splitDeviceExample.createCriteria().andPositionIdEqualTo(position.getPositionId()).andValidEqualTo(SystemConstants.INT_YES);
                            List<PaasConstructionTemplateSplitDevice> splitDevices = templateSplitDeviceMapper.selectByExample(splitDeviceExample);
                            for (PaasConstructionTemplateSplitDevice splitDevice : splitDevices) {
                                PaasConstructionTemplateSplitDevice newSplitDevice = new PaasConstructionTemplateSplitDevice();
                                newSplitDevice.setCategoryCode(splitDevice.getCategoryCode());
                                newSplitDevice.setCustomDeviceName(splitDevice.getCustomDeviceName());

                                newSplitDevices.add(newSplitDevice);
                            }

                            positionPo.setSplitDevices(newSplitDevices);

                            // 新增位置
                            this.createTemplatePosition(positionPo);
                        }
                    }
                }
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【TemplateServiceImplements.copyTemplate】复制施工模板异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}