package cn.xpms.paas.mq.bean.common;


/**
 * @ClassName BizCodeEnum
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/17 14:37
 * @Version 1.0
 */
public enum BizCodeEnum {

    DEVICE("device", "设备事件推送"),
    VOICE("voice", "语音业务推送"),
    CONSTRUCTION("construction", "施工业务推送");

    /** biz_code */
    private String biz_code;

    /** biz_name */
    private String biz_name;

    BizCodeEnum(String biz_code, String biz_name) {
        this.biz_code = biz_code;
        this.biz_name = biz_name;
    }

    /**
     * @Description: 获取EventTypeEnum
     * @Params: [event_type]
     * @return: java.lang.Class
     * @Author: 柯雷
     * @Date: 2020/12/18 9:15
     */
    public static BizCodeEnum getBizCode(String biz_code) {
        for (BizCodeEnum codeEnum : BizCodeEnum.values()) {
            if (codeEnum.biz_code.equals(biz_code)) {
                return codeEnum;
            }
        }
        return null;
    }

    public String getBiz_code() {
        return biz_code;
    }

    public void setBiz_code(String biz_code) {
        this.biz_code = biz_code;
    }

    public String getBiz_name() {
        return biz_name;
    }

    public void setBiz_name(String biz_name) {
        this.biz_name = biz_name;
    }
}