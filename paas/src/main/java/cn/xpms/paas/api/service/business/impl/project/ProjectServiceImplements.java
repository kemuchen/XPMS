package cn.xpms.paas.api.service.business.impl.project;

import cn.xpms.paas.api.bean.constant.Constant;
import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.constant.ProjectStatusEnum;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.bean.dto.project.PaasProjectPo;
import cn.xpms.paas.api.dao.customize.repository.CustomizeProjectMapper;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.PaasProjectHotelBindMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasProjectInfoMapper;
import cn.xpms.paas.api.service.business.inter.project.ProjectServiceInterface;
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
 * @ClassName ProjectServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/28 9:00
 * @Version 1.0
 */
@Service
public class ProjectServiceImplements implements ProjectServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(ProjectServiceImplements.class);

    /**
     * 涂鸦接口调用service
     */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    /**
     * 项目信息CURD
     */
    @Autowired
    PaasProjectInfoMapper paasProjectInfoMapper;

    /**
     * 项目酒店绑定CURD
     */
    @Autowired
    PaasProjectHotelBindMapper paasProjectHotelBindMapper;

    /**
     * 自定义项目信息CURD
     */
    @Autowired
    CustomizeProjectMapper customizeProjectMapper;

    /**
     * @Description: 根据id查询项目信息
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 9:17
     */
    @Override
    public ApiResponseEntity getProject(Integer id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    this.getProjectInfo(id));
        } catch (AppException e) {
            logger.error("【ProjectServiceImplements.getProject】查询项目信息异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ProjectServiceImplements.getProject】查询项目信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getProjectByProjectId(String projectId) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    customizeProjectMapper.getProjectDetailByProject(projectId));
        } catch (Exception e) {
            logger.error("【ProjectServiceImplements.getProjectByProjectId】查询项目信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取项目信息
     * @Params: [id]
     * @return: cn.xpms.paas.api.dao.generator.entity.PassProjectInfo
     * @Author: 柯雷
     * @Date: 2020/11/30 9:39
     */
    private PaasProjectInfo getProjectInfo(Integer id) throws AppException {
        try {
            return paasProjectInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("【ProjectServiceImplements.getProject】查询项目信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询项目信息列表
     * @Params: [passProjectInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 9:19
     */
    @Override
    public ApiResponseEntity getProjects(PaasProjectInfo paasProjectInfo) throws AppException {
        try {
            PaasProjectInfoExample example = new PaasProjectInfoExample();
            paasProjectInfo.setValid(SystemConstants.INT_YES);
            BeanUtil.beanToExample(paasProjectInfo, example);
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    paasProjectInfoMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【ProjectServiceImplements.getProjects】查询项目信息列表异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询酒店绑定项目信息列表
     * @Params: [hotel_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-15 11:19
     */
    @Override
    public ApiResponseEntity getHotelBindProjects(Integer hotel_id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    customizeProjectMapper.getProjectDetail(hotel_id));
        } catch (Exception e) {
            logger.error("【ProjectServiceImplements.getHotelBindProjects】获取项目酒店绑定异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 新增项目信息
     * @Params: [passProjectInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 9:26
     */
    @Override
    public ApiResponseEntity createProject(PaasProjectPo paasProjectInfo) throws AppException {
        try {
            PaasProjectInfo projectInfo = new PaasProjectInfo();
            projectInfo.setLocationId(paasProjectInfo.getLocationId());
            projectInfo.setProjectName(paasProjectInfo.getProjectName());
            projectInfo.setAddress(paasProjectInfo.getAddress());
            projectInfo.setTimeZoneId(paasProjectInfo.getTimeZoneId());
            projectInfo.setMemo(paasProjectInfo.getMemo());

            paasProjectInfo.setTimeZoneId(Constant.DEFAULT_TIME_ZONE_ID);
            // 调用涂鸦接口新增项目
            PaasApiResponseEntity<String> apiResponseEntity = paasApiServiceInterface.call(PaasApiEnum.ADD_PROJECT,
                    BeanUtil.beanToMap(paasProjectInfo, true));

            projectInfo.setProjectId(apiResponseEntity.getResult());
            projectInfo.setStatus(ProjectStatusEnum.UNAUTHORIZED.toString());

            // 新增数据库记录
            paasProjectInfoMapper.insertSelective(projectInfo);

            // 新增项目用户绑定
            paasProjectInfo.setProjectId(apiResponseEntity.getResult());
            this.createHotelProjectBind(paasProjectInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, paasProjectInfo);
        } catch (AppException e) {
            logger.error("【ProjectServiceImplements.createProject】创建项目信息异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ProjectServiceImplements.createProject】创建项目信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修改项目信息
     * @Params: [passProjectInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 10:18
     */
    @Override
    public ApiResponseEntity modifyProject(PaasProjectInfo paasProjectInfo) throws AppException {
        try {
            // 调用涂鸦接口修改项目
            paasApiServiceInterface.call(PaasApiEnum.MODIFY_PROJECT,
                    BeanUtil.beanToMap(paasProjectInfo, true));
            // 更新数据库记录
            paasProjectInfoMapper.updateByPrimaryKeySelective(paasProjectInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ProjectServiceImplements.modifyProject】修改项目信息异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ProjectServiceImplements.modifyProject】修改项目信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除项目信息
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 10:20
     */
    @Override
    public ApiResponseEntity deleteProject(Integer id) throws AppException {
        try {
            // 获取删除的项目信息
            PaasProjectInfo passProjectInfo = this.getProjectInfo(id);
            // 调用涂鸦接口修改项目
            paasApiServiceInterface.call(PaasApiEnum.DELETE_PROJECT,
                    BeanUtil.beanToMap(passProjectInfo, true));

            // 删除数据库记录
            passProjectInfo.setValid(SystemConstants.INT_NO);
            paasProjectInfoMapper.updateByPrimaryKeySelective(passProjectInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ProjectServiceImplements.deleteProject】删除项目信息异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ProjectServiceImplements.deleteProject】删除项目信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 授权项目信息
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 10:22
     */
    @Override
    public ApiResponseEntity authorizeProject(Integer id) throws AppException {
        try {
            // 获取授权的项目信息
            PaasProjectInfo passProjectInfo = this.getProjectInfo(id);
            // 调用涂鸦接口修改项目
            paasApiServiceInterface.call(PaasApiEnum.AUTHORIZE_PROJECT,
                    BeanUtil.beanToMap(passProjectInfo, true));

            // 更新数据库记录
            passProjectInfo.setStatus(ProjectStatusEnum.AUTHORIZED.toString());
            paasProjectInfoMapper.updateByPrimaryKey(passProjectInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ProjectServiceImplements.authorizeProject】授权项目信息异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ProjectServiceImplements.authorizeProject】授权项目信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 解除项目授权
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 10:28
     */
    @Override
    public ApiResponseEntity unAuthorizeProhect(Integer id) throws AppException {
        try {
            // 获取授权的项目信息
            PaasProjectInfo passProjectInfo = this.getProjectInfo(id);
            // 调用涂鸦接口修改项目
            paasApiServiceInterface.call(PaasApiEnum.UN_AUTHORIZE_PROJECT,
                    BeanUtil.beanToMap(passProjectInfo, true));

            // 更新数据库记录
            passProjectInfo.setStatus(ProjectStatusEnum.REVOCATION_AUTHORIZATION.toString());
            paasProjectInfoMapper.updateByPrimaryKey(passProjectInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ProjectServiceImplements.unAuthorizeProhect】解除项目授权异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ProjectServiceImplements.unAuthorizeProhect】解除项目授权异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 新增项目酒店绑定信息
     * @Params: [passProjectInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-15 10:05
     */
    @Override
    public ApiResponseEntity createHotelProjectBind(PaasProjectPo paasProjectPo) throws AppException {
        try {
            PaasProjectHotelBind bind = new PaasProjectHotelBind();
            bind.setHotelId(paasProjectPo.getHotel_id());
            bind.setProjectId(paasProjectPo.getId());
            return this.createHotelProjectBind(bind);
        } catch (AppException e) {
            logger.error("【ProjectServiceImplements.createHotelProjectBind】新增项目酒店绑定信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 新增项目酒店绑定信息
     * @Params: [bind]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-15 10:03
     */
    @Override
    public ApiResponseEntity createHotelProjectBind(PaasProjectHotelBind bind) throws AppException {
        try {
            paasProjectHotelBindMapper.insertSelective(bind);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【ProjectServiceImplements.createHotelProjectBind】新增项目酒店绑定信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}