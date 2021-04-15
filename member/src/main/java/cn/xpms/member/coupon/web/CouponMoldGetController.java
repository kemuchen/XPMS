package cn.xpms.member.coupon.web;

import cn.xpms.member.coupon.service.inter.CouponGetServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.util.http.HttpServiceUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CouponMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/6 9:01
 * @Version 1.0
 */
@RestController
@Api(tags = "优惠券信息查询接口")
@RequestMapping("/api/member/coupon/")
public class CouponMoldGetController {

    /** 优惠券领取service */
    @Autowired
    CouponGetServiceInterface couponGetServiceInterface;

    @ApiOperation("1.获取小程序banner广告图")
    @GetMapping("homeBanner")
    public ApiResponseEntity homeBanner()
            throws AppException {
        return HttpServiceUtil.doGet("WECHAT", "/mini/wechat/ads/homeBanner", null);
    }
}