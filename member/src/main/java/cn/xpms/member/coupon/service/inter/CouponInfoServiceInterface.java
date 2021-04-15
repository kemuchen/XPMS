package cn.xpms.member.coupon.service.inter;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.member.coupon.dao.generator.entity.MemberCouponInfo;
import cn.xpms.member.coupon.dao.generator.entity.MemberCouponInfoExample;

/**
 * @ClassName CouponServiceInterface
 * @Desc 优惠券
 * @Author 柯雷
 * @Date 2020/11/2 8:51
 * @Version 1.0
 */
public interface CouponInfoServiceInterface {

    /**
     * @Description: 创建优惠券
     * @Params: [memberCouponInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/2 8:52
     */
    ApiResponseEntity createCouponInfo(MemberCouponInfo memberCouponInfo) throws AppException;

    /**
     * @Description: 修改优惠券
     * @Params: [memberCouponInfo, memberCouponInfoExample]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/2 8:54
     */
    ApiResponseEntity modifyCouponInfo(MemberCouponInfo memberCouponInfo) throws AppException;

    /**
     * @Description: 查询优惠券列表
     * @Params: [memberCouponInfoExample]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/2 8:55
     */
    ApiResponseEntity getCouponInfos(MemberCouponInfoExample memberCouponInfoExample) throws AppException;

    /**
     * @Description: 查询优惠券列表
     * @Params: [memberCouponInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/3 16:23
     */
    ApiResponseEntity getCouponInfos(MemberCouponInfo memberCouponInfo) throws AppException;
}