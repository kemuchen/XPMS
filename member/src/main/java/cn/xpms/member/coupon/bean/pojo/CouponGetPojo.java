package cn.xpms.member.coupon.bean.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName CouponGetPojo
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/7 10:54
 * @Version 1.0
 */
@ApiModel(value = "优惠券领取POJO", description = "CouponGetPojo")
@Getter
@Setter
public class CouponGetPojo {

    @ApiModelProperty(value = "优惠券id", name = "card_id", dataType = "String", required = true)
    String card_id;

    @ApiModelProperty(value = "优惠券数量", name = "count", dataType = "Integer", required = true)
    Integer count;
}
