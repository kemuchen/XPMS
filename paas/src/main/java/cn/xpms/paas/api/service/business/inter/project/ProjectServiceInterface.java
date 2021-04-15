package cn.xpms.paas.api.service.business.inter.project;

import cn.xpms.paas.api.bean.dto.project.PaasProjectPo;
import cn.xpms.paas.api.dao.generator.entity.PaasProjectHotelBind;
import cn.xpms.paas.api.dao.generator.entity.PaasProjectInfo;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName ProjectServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/28 9:00
 * @Version 1.0
 */
public interface ProjectServiceInterface {

    /**
     * @Description: 根据id查询项目信息
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 8:57
     */
    ApiResponseEntity getProject(Integer id) throws AppException;

    ApiResponseEntity getProjectByProjectId(String projectId) throws AppException;

    /**
     * @Description: 根据条件查询项目列表
     * @Params: [passProjectInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 8:57
     */
    ApiResponseEntity getProjects(PaasProjectInfo paasProjectInfo) throws AppException;

    /**
     * @Description: 查询酒店绑定项目信息列表
     * @Params: [hotel_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-15 11:18
     */
    ApiResponseEntity getHotelBindProjects(Integer hotel_id) throws AppException;

    /**
     * @Description: 创建项目
     * @Params: [passProjectInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 8:44
     */
    ApiResponseEntity createProject(PaasProjectPo paasProjectInfo) throws AppException;


    /**
     * @Description: 修改项目
     * @Params: [passProjectInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 8:45
     */
    ApiResponseEntity modifyProject(PaasProjectInfo paasProjectInfo) throws AppException;

    /**
     * @Description: 删除项目
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 8:48
     */
    ApiResponseEntity deleteProject(Integer id) throws AppException;

    /**
     * @Description: 授权项目
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 8:48
     */
    ApiResponseEntity authorizeProject(Integer id) throws AppException;

    /**
     * @Description: 解除项目授权
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 8:49
     */
    ApiResponseEntity unAuthorizeProhect(Integer id) throws AppException;

    /**
     * @Description: 新增酒店项目绑定
     * @Params: [PaasProjectPo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-15 9:55
     */
    ApiResponseEntity createHotelProjectBind(PaasProjectPo paasProjectPo) throws AppException;


    /**
     * @Description: 新增酒店项目绑定
     * @Params: [bind]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-15 10:00
     */
    ApiResponseEntity createHotelProjectBind(PaasProjectHotelBind bind) throws AppException;
}