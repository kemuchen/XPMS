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
public class PaasCustomAutomationRecord implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.id
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.automation_id
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private Integer automationId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.room_id
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private String roomId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.device_id
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private String deviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.device_status_code
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private String deviceStatusCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.device_status_value
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private String deviceStatusValue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.device_status_count
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private Integer deviceStatusCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.start_time
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.device_count
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private Integer deviceCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.scene_id
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private String sceneId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.status
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.valid
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.create_user
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.create_time
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.modify_user
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private Integer modifyUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.modify_time
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_custom_automation_record.memo
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", automationId=").append(automationId);
        sb.append(", roomId=").append(roomId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", deviceStatusCode=").append(deviceStatusCode);
        sb.append(", deviceStatusValue=").append(deviceStatusValue);
        sb.append(", deviceStatusCount=").append(deviceStatusCount);
        sb.append(", startTime=").append(startTime);
        sb.append(", deviceCount=").append(deviceCount);
        sb.append(", sceneId=").append(sceneId);
        sb.append(", status=").append(status);
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
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
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
        PaasCustomAutomationRecord other = (PaasCustomAutomationRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAutomationId() == null ? other.getAutomationId() == null : this.getAutomationId().equals(other.getAutomationId()))
            && (this.getRoomId() == null ? other.getRoomId() == null : this.getRoomId().equals(other.getRoomId()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getDeviceStatusCode() == null ? other.getDeviceStatusCode() == null : this.getDeviceStatusCode().equals(other.getDeviceStatusCode()))
            && (this.getDeviceStatusValue() == null ? other.getDeviceStatusValue() == null : this.getDeviceStatusValue().equals(other.getDeviceStatusValue()))
            && (this.getDeviceStatusCount() == null ? other.getDeviceStatusCount() == null : this.getDeviceStatusCount().equals(other.getDeviceStatusCount()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getDeviceCount() == null ? other.getDeviceCount() == null : this.getDeviceCount().equals(other.getDeviceCount()))
            && (this.getSceneId() == null ? other.getSceneId() == null : this.getSceneId().equals(other.getSceneId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getValid() == null ? other.getValid() == null : this.getValid().equals(other.getValid()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyUser() == null ? other.getModifyUser() == null : this.getModifyUser().equals(other.getModifyUser()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAutomationId() == null) ? 0 : getAutomationId().hashCode());
        result = prime * result + ((getRoomId() == null) ? 0 : getRoomId().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getDeviceStatusCode() == null) ? 0 : getDeviceStatusCode().hashCode());
        result = prime * result + ((getDeviceStatusValue() == null) ? 0 : getDeviceStatusValue().hashCode());
        result = prime * result + ((getDeviceStatusCount() == null) ? 0 : getDeviceStatusCount().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getDeviceCount() == null) ? 0 : getDeviceCount().hashCode());
        result = prime * result + ((getSceneId() == null) ? 0 : getSceneId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getValid() == null) ? 0 : getValid().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyUser() == null) ? 0 : getModifyUser().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }
}