package cn.xpms.member.coupon.service.inter;

import cn.xpms.member.coupon.bean.pojo.CouponGetPojo;
import cn.xpms.member.coupon.dao.generator.entity.MemberCouponInfo;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

import java.util.List;

/**
 * @ClassName CouponGetServiceInterface
 * @Desc 优惠券领取
 * @Author 柯雷
 * @Date 2020/11/2 8:57
 * @Version 1.0
 */
public interface CouponGetServiceInterface {

    /**
     * @Description: 用户领取优惠券
     * @Params: [card_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/2 11:59
     */
    ApiResponseEntity getCoupon(String card_id) throws AppException;

    /**
     * @Description: 用户领取优惠券
     * @Params: [memberCouponGetRecord]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/2 11:59
     */
    ApiResponseEntity getCoupon(MemberCouponInfo memberCouponInfo) throws AppException;

    /**
     * @Description: 用户领取优惠券
     * @Params: [card_ids]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/7 11:06
     */
    ApiResponseEntity getCoupon(List<CouponGetPojo> couponGetPojos) throws AppException;

    /**
     * @Description: 用户核销优惠券
     * @Params: [code]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/2 12:00
     */
    ApiResponseEntity consumeCoupon(String code) throws AppException;
}
