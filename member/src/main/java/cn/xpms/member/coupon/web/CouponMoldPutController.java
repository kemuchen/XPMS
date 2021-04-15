package cn.xpms.member.coupon.web;

import cn.xpms.member.coupon.dao.generator.entity.MemberCouponInfo;
import cn.xpms.member.coupon.service.inter.CouponGetServiceInterface;
import cn.xpms.member.coupon.bean.pojo.CouponGetPojo;
import cn.xpms.member.coupon.service.inter.CouponInfoServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CouponMoldPutController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/7 10:52
 * @Version 1.0
 */
@RestController
@Api(tags = "优惠券信息新增接口")
@RequestMapping("/api/member/coupon/")
public class CouponMoldPutController {

    /** 优惠券领取service */
    @Autowired
    CouponGetServiceInterface couponGetServiceInterface;

    /** 优惠券基础信息service */
    @Autowired
    CouponInfoServiceInterface couponInfoServiceInterface;


    /**
     * @Description: 领取优惠券
     * @Params: [card_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/6 9:13
     */
    @ApiOperation("1.领取优惠券")
    @PutMapping(value = "coupons", produces = "application/json")
    public ApiResponseEntity getCoupon(@RequestBody List<CouponGetPojo> couponGetPojos) throws AppException {
        return couponGetServiceInterface.getCoupon(couponGetPojos);
    }


    /**
     * @Description: 领取优惠券
     * @Params: [card_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/6 9:13
     */
    @ApiOperation("2.领取优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "card_id", value = "优惠券id", required = true, paramType = "path", dataType = "string", defaultValue = "0")})
    @PutMapping("coupon/{card_id}")
    public ApiResponseEntity getCoupon(@PathVariable(value = "card_id") String card_id) throws AppException {
        return couponGetServiceInterface.getCoupon(card_id);
    }

    /**
     * @Description: 创建优惠券
     * @Params: [card_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/7 11:15
     */
    @ApiOperation("3.创建优惠券")
    @PutMapping("coupon_info")
    public ApiResponseEntity getCoupon(@RequestBody MemberCouponInfo memberCouponInfo) throws AppException {
        return couponInfoServiceInterface.createCouponInfo(memberCouponInfo);
    }
}
