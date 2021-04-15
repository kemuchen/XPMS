package cn.xpms.paas.api.service.business.impl.space;

import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.dao.generator.entity.PaasSpaceInfo;
import cn.xpms.paas.api.dao.generator.entity.PaasSpaceInfoExample;
import cn.xpms.paas.api.dao.generator.repository.PaasSpaceInfoMapper;
import cn.xpms.paas.api.service.business.inter.space.SpaceServiceInterface;
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

/**
 * @ClassName SpaceServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/1 8:36
 * @Version 1.0
 */
@Service
public class SpaceServiceImplements implements SpaceServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(SpaceServiceImplements.class);

    /**
     * 涂鸦API接口service
     */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    /**
     * 空间信息CURD
     */
    @Autowired
    PaasSpaceInfoMapper paasSpaceInfoMapper;

    /**
     * @Description: 新增空间
     * @Params: [paasSpaceInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 9:23
     */
    @Override
    public ApiResponseEntity createSapce(PaasSpaceInfo paasSpaceInfo) throws AppException {
        try {
            // 调用涂鸦接口新增项目
            PaasApiResponseEntity<String> apiResponseEntity = paasApiServiceInterface.call(PaasApiEnum.ADD_SPACE,
                    BeanUtil.beanToMap(paasSpaceInfo, true));
            paasSpaceInfo.setSpaceId(apiResponseEntity.getResult());
            // 新增数据库记录
            paasSpaceInfo.setId(paasSpaceInfoMapper.insertSelective(paasSpaceInfo));
            return new ApiResponseEntity(SysErrorCode.SUCCESS, paasSpaceInfo);
        } catch (AppException e) {
            logger.error("【SpaceServiceImplements.createSapce】新增空间异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【SpaceServiceImplements.createSapce】新增空间异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修稿空间信息
     * @Params: [paasSpaceInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 9:47
     */
    @Override
    public ApiResponseEntity modifySpace(PaasSpaceInfo paasSpaceInfo) throws AppException {
        try {
            // 调用涂鸦接口修改项目
            paasApiServiceInterface.call(PaasApiEnum.MODIFY_SPACE,
                    BeanUtil.beanToMap(paasSpaceInfo, true));
            // 更新数据库记录
            paasSpaceInfoMapper.updateByPrimaryKeySelective(paasSpaceInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【SpaceServiceImplements.modifySpace】修改项目信息异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【SpaceServiceImplements.modifySpace】修改项目信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除空间信息
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 9:49
     */
    @Override
    public ApiResponseEntity deleteSpace(Integer id) throws AppException {
        try {
            // 获取删除的项目信息
            PaasSpaceInfo paasSpaceInfo = this.getPaasSpaceInfo(id);
            // 调用涂鸦接口修改项目
            paasApiServiceInterface.call(PaasApiEnum.DELETE_SPACE,
                    BeanUtil.beanToMap(paasSpaceInfo, true));

            // 删除数据库记录
            paasSpaceInfo.setValid(SystemConstants.INT_NO);
            paasSpaceInfoMapper.updateByPrimaryKeySelective(paasSpaceInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【SpaceServiceImplements.deleteSpace】删除空间信息异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【SpaceServiceImplements.deleteSpace】删除空间信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询空间列表
     * @Params: [paasSpaceInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 9:52
     */
    @Override
    public ApiResponseEntity getSpaceInfos(PaasSpaceInfo paasSpaceInfo) throws AppException {
        try {
            PaasSpaceInfoExample example = new PaasSpaceInfoExample();
            paasSpaceInfo.setValid(SystemConstants.INT_YES);
            BeanUtil.beanToExample(paasSpaceInfo, example);
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    paasSpaceInfoMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【SpaceServiceImplements.getSpaceInfos】查询空间信息列表异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询空间信息
     * @Params: [id]
     * @return: cn.xpms.paas.api.dao.generator.entity.PaasSpaceInfo
     * @Author: 柯雷
     * @Date: 2020/12/1 9:50
     */
    private PaasSpaceInfo getPaasSpaceInfo(Integer id) throws AppException {
        try {
            return paasSpaceInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("【SpaceServiceImplements.getPaasSpaceInfo】查询空间信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}