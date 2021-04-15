package cn.xpms.member.coupon.web;

import cn.xpms.member.coupon.dao.generator.entity.MemberCouponInfo;
import cn.xpms.member.coupon.service.inter.CouponGetServiceInterface;
import cn.xpms.member.coupon.service.inter.CouponInfoServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CouponMoldPostController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/7 10:53
 * @Version 1.0
 */
@RestController
@Api(tags = "优惠券信息修改接口")
@RequestMapping("/api/member/coupon/")
public class CouponMoldPostController {

    /** 优惠券领取service */
    @Autowired
    CouponGetServiceInterface couponGetServiceInterface;

    /** 优惠券基本信息service */
    @Autowired
    CouponInfoServiceInterface couponInfoServiceInterface;

    /**
     * @Description: 核销优惠券
     * @Params: [card_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/6 9:13
     */
    @ApiOperation("1.核销优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "优惠券code", required = true, paramType = "path", dataType = "string", defaultValue = "0")})
    @PostMapping("coupon/{code}")
    public ApiResponseEntity consumeCoupon(@PathVariable(value = "code") String code) throws AppException {
        return couponGetServiceInterface.consumeCoupon(code);
    }

    /**
     * @Description: 修改优惠券
     * @Params: [memberCouponInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/7 11:17
     */
    @ApiOperation("2.修改优惠券")
    @PostMapping(value = "coupon_info", produces = "application/json")
    public ApiResponseEntity getCoupon(@RequestBody MemberCouponInfo memberCouponInfo) throws AppException {
        return couponInfoServiceInterface.modifyCouponInfo(memberCouponInfo);
    }
}
