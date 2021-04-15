package cn.xpms.paas.api.dao.generator.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaasDoorLockPassword implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.id
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.device_id
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private String deviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.password_id
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private String passwordId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.name
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.password
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.effective_time
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date effectiveTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.invalid_time
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date invalidTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.password_type
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private String passwordType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.ticket_id
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private String ticketId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.phone
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.type
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.phase
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private Integer phase;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.delivery_status
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private Integer deliveryStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.valid
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.create_user
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.create_time
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.modify_user
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private Integer modifyUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.modify_time
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_door_lock_password.memo
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table paas_door_lock_password
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_door_lock_password
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", passwordId=").append(passwordId);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", effectiveTime=").append(effectiveTime);
        sb.append(", invalidTime=").append(invalidTime);
        sb.append(", passwordType=").append(passwordType);
        sb.append(", ticketId=").append(ticketId);
        sb.append(", phone=").append(phone);
        sb.append(", type=").append(type);
        sb.append(", phase=").append(phase);
        sb.append(", deliveryStatus=").append(deliveryStatus);
        sb.append(", valid=").append(valid);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUser=").append(modifyUser);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", memo=").append(memo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_door_lock_password
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PaasDoorLockPassword other = (PaasDoorLockPassword) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getPasswordId() == null ? other.getPasswordId() == null : this.getPasswordId().equals(other.getPasswordId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getEffectiveTime() == null ? other.getEffectiveTime() == null : this.getEffectiveTime().equals(other.getEffectiveTime()))
            && (this.getInvalidTime() == null ? other.getInvalidTime() == null : this.getInvalidTime().equals(other.getInvalidTime()))
            && (this.getPasswordType() == null ? other.getPasswordType() == null : this.getPasswordType().equals(other.getPasswordType()))
            && (this.getTicketId() == null ? other.getTicketId() == null : this.getTicketId().equals(other.getTicketId()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPhase() == null ? other.getPhase() == null : this.getPhase().equals(other.getPhase()))
            && (this.getDeliveryStatus() == null ? other.getDeliveryStatus() == null : this.getDeliveryStatus().equals(other.getDeliveryStatus()))
            && (this.getValid() == null ? other.getValid() == null : this.getValid().equals(other.getValid()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyUser() == null ? other.getModifyUser() == null : this.getModifyUser().equals(other.getModifyUser()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_door_lock_password
     *
     * @mbg.generated Mon Jan 04 15:40:32 CST 2021
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getPasswordId() == null) ? 0 : getPasswordId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getEffectiveTime() == null) ? 0 : getEffectiveTime().hashCode());
        result = prime * result + ((getInvalidTime() == null) ? 0 : getInvalidTime().hashCode());
        result = prime * result + ((getPasswordType() == null) ? 0 : getPasswordType().hashCode());
        result = prime * result + ((getTicketId() == null) ? 0 : getTicketId().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPhase() == null) ? 0 : getPhase().hashCode());
        result = prime * result + ((getDeliveryStatus() == null) ? 0 : getDeliveryStatus().hashCode());
        result = prime * result + ((getValid() == null) ? 0 : getValid().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyUser() == null) ? 0 : getModifyUser().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }
}