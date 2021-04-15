package cn.xpms.sso.bean.code;

/**
 * @ClassName UserStatusEnum
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/18 16:38
 * @Version 1.0
 */
public enum UserStatusEnum {

    NORMAL("1", "正常"),

    LOCKED("2", "锁定");

    /** 代码 */
    private String code;

    /** 描述 */
    private String desc;

    UserStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
