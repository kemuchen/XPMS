package cn.xpms.paas.api.bean.vo.template;

import lombok.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ConstructionTemplatePositionInfraredDeviceVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/5 8:56
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionInfraredDeviceVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 位置唯一标识
     */
    private String position_id;

    /**
     * 品类唯一标识
     */
    private String category_id;

    /**
     * 品牌唯一标识
     */
    private String brand_id;

    /**
     * 遥控器索引
     */
    private String remote;

    /**
     * 区域唯一标识
     */
    private String area_id;

    /**
     * 自定义设备名称
     */
    private String custom_device_name;

    /**
     * 供应商唯一标识
     */
    private String operator_id;

    /**
     * 是否有效
     */
    private Integer valid;

    /**
     * 创建人
     */
    private Integer create_user;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 修改人
     */
    private Integer modify_user;

    /**
     * 修改时间
     */
    private Date modify_time;

    /**
     * 备注
     */
    private String memo;

    /**
     * 品类名称
     */
    private String category_name;

    /**
     * 品牌名称
     */
    private String brand_name;
}