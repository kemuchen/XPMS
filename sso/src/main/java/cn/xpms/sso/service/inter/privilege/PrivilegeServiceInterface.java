package cn.xpms.sso.service.inter.privilege;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName PrivilegeServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/25 15:54
 * @Version 1.0
 */
public interface PrivilegeServiceInterface {

    /**
     * @Description: 查询用户拥有的子系统菜单路由
     * @Params: [user_id, hotel_id, system_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-09 15:38
     */
    ApiResponseEntity getUserSystemRightOperate(Integer user_id,
                                                Integer hotel_id, Integer system_id) throws AppException;
}