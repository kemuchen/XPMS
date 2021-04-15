package cn.xpms.member.coupon.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName CouponGetVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/7 11:33
 * @Version 1.0
 */
@ApiModel(value = "优惠券领取VO", description = "CouponGetVo")
@Getter
@Setter
public class CouponGetVo {

    @ApiModelProperty(value = "优惠券id", name = "card_id", dataType = "String", required = true)
    String card_id;

    @ApiModelProperty(value = "优惠券code", name = "code", dataType = "String", required = true)
    String code;

    @ApiModelProperty(value = "优惠券有效开始日期", name = "dateBegin")
    Date date_begin;

    @ApiModelProperty(value = "优惠券有效开始日期", name = "dateBegin")
    Date date_end;
}

