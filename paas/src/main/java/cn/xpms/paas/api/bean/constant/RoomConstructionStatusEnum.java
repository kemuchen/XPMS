package cn.xpms.paas.api.bean.constant;

/**
 * @ClassName RoomConstructionStatusEnum
 * @Desc 房间施工状态
 * @Author 柯雷
 * @Date 2020/12/1 9:40
 * @Version 1.0
 */
public enum RoomConstructionStatusEnum {

    /** 未授权 */
    UNAUTHORIZED,

    /** 待授权 */
    PENDING_CONSTRUCTION,

    /** 施工中 */
    IN_CONSTRUCTION,

    /** 待验收 */
    PENDING_ACCEPTANCE,

    /** 验收完成 */
    ACCEPTANCE_COMPLETE
}