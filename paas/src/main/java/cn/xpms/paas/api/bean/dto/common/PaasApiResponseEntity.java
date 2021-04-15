package cn.xpms.paas.api.bean.dto.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ApiResponseData
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 10:01
 * @Version 1.0
 */
@Getter
@Setter
public class PaasApiResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 响应码，成功为空
     */
    private Integer code;

    /**
     * 是否成功(true: 成功， false: 失败)
     */
    private Boolean success;

    /**
     * 异常信息，成功为空
     */
    private String msg;

    /**
     * 响应时间，13位时间戳
     */
    private Long t;

    /**
     * 业务结果对象
     */
    private T result;

    public PaasApiResponseEntity() {

    }

    /**
     * @Description: 构造函数
     * @Params: [code, success, msg, t, result]
     * @return:
     * @Author: 柯雷
     * @Date: 2020/12/16 13:49
     */
    public PaasApiResponseEntity(Integer code, Boolean success, String msg, Long t, T result) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.t = t;
        this.result = result;
    }
}
