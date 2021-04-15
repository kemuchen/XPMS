package cn.xpms.sso.service.impl.user;

import cn.xpms.sso.dao.CustomizeUserMapper;
import cn.xpms.sso.service.inter.user.UserServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BusinessUserServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/25 11:12
 * @Version 1.0
 */
@Service
public class UserServiceImplements implements UserServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(UserServiceImplements.class);

    /**
     * 自定义用户子系统CURD
     */
    @Autowired
    CustomizeUserMapper customizeUserSubSysMapper;

    /**
     * @Description: 查询用户可操作性的酒店(门店)
     * @Params: [user_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020/12/09 10:12
     */
    @Override
    public ApiResponseEntity getUserHotelOperate(Integer user_id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, customizeUserSubSysMapper.getUserHotels(user_id));
        } catch (Exception e) {
            logger.error("【BusinessUserServiceImplements.getUserHotelOperate】查询用户操作酒店(门店)异常" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询用户可授权的酒店(门店)
     * @Params: [user_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-09 11:13
     */
    @Override
    public ApiResponseEntity getUserHotelAuth(Integer user_id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, customizeUserSubSysMapper.getUserHotelsAuth(user_id));
        } catch (Exception e) {
            logger.error("【BusinessUserServiceImplements.getUserHotelAuth】查询用户可授权的酒店(门店)异常" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询用户门店拥有操作权限的子系统
     * @Params: [user_id, hotel_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-09 11:17
     */
    @Override
    public ApiResponseEntity getUserSubSystem(Integer user_id, Integer hotel_id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, customizeUserSubSysMapper.getUserSubSystem(user_id, hotel_id));
        } catch (Exception e) {
            logger.error("【BusinessUserServiceImplements.getUserSubSystem】查询用户门店拥有操作权限的子系统异常" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
