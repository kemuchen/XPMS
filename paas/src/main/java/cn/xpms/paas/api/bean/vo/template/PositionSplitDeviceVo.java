package cn.xpms.paas.api.bean.vo.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName PositionSplitDeviceVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/5 9:15
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionSplitDeviceVo implements Serializable {

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
     * 品类代码
     */
    private String category_code;

    /**
     * 自定义设备名称
     */
    private String custom_device_name;

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
}