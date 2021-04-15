package cn.xpms.paas.api.bean.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName DeviceApiVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/11 9:33
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaasApiPageResultVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 页码 */
    protected Integer page_no;

    /** 页条数 */
    protected Integer page_size;

    /** 总条数 */
    protected Integer total;
}
