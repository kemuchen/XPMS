package cn.xpms.paas.mq.bean.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName DeviceDpReportDataBodyVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/17 14:56
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDpReportDataBodyVo implements Serializable {

    /** 设备id */
    private String devId;

    /** 产品id */
    private String productId;

    /** 数据id */
    private String dataId;

    /** 命名空间 */
    private String namespace;

    /** 设备id */
    private List<DeviceStatusVo> status;
}
