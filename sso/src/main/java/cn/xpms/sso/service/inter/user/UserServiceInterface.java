package cn.xpms.sso.service.inter.user;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName BusinessUserServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/25 11:10
 * @Version 1.0
 */
public interface UserServiceInterface {

    /**
     * @Description: 查询用户拥有操作权限的酒店(门店)
     * @Params: [user_id]
     * @return:
     * @Author: ycj
     * @Date: 2020-12-09 11:08
     */
    ApiResponseEntity getUserHotelOperate(Integer user_id) throws AppException;

    /**
     * @Description: 查询用户可授权的酒店(门店)
     * @Params: [user_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-09 11:12
     */
    ApiResponseEntity getUserHotelAuth(Integer user_id) throws AppException;

    /**
     * @Description: 查询用户门店拥有操作权限的子系统
     * @Params: [user_id, hotel_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-09 11:17
     */
    ApiResponseEntity getUserSubSystem(Integer user_id, Integer hotel_id) throws AppException;

}