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
public class PaasAutomationInfo implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_info.id
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_info.automation_id
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    private String automationId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_info.room_id
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    private String roomId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_info.automation_name
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    private String automationName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_info.enabled
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    private String enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_info.match_type
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    private Integer matchType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_info.condition_rule
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    private String conditionRule;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_info.valid
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_info.create_user
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_info.create_time
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_info.modify_user
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    private Integer modifyUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_info.modify_time
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_info.memo
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table paas_automation_info
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_info
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
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
        sb.append(", automationName=").append(automationName);
        sb.append(", enabled=").append(enabled);
        sb.append(", matchType=").append(matchType);
        sb.append(", conditionRule=").append(conditionRule);
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
     * This method corresponds to the database table paas_automation_info
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
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
        PaasAutomationInfo other = (PaasAutomationInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAutomationId() == null ? other.getAutomationId() == null : this.getAutomationId().equals(other.getAutomationId()))
            && (this.getRoomId() == null ? other.getRoomId() == null : this.getRoomId().equals(other.getRoomId()))
            && (this.getAutomationName() == null ? other.getAutomationName() == null : this.getAutomationName().equals(other.getAutomationName()))
            && (this.getEnabled() == null ? other.getEnabled() == null : this.getEnabled().equals(other.getEnabled()))
            && (this.getMatchType() == null ? other.getMatchType() == null : this.getMatchType().equals(other.getMatchType()))
            && (this.getConditionRule() == null ? other.getConditionRule() == null : this.getConditionRule().equals(other.getConditionRule()))
            && (this.getValid() == null ? other.getValid() == null : this.getValid().equals(other.getValid()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyUser() == null ? other.getModifyUser() == null : this.getModifyUser().equals(other.getModifyUser()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_info
     *
     * @mbg.generated Thu Dec 17 10:42:38 CST 2020
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAutomationId() == null) ? 0 : getAutomationId().hashCode());
        result = prime * result + ((getRoomId() == null) ? 0 : getRoomId().hashCode());
        result = prime * result + ((getAutomationName() == null) ? 0 : getAutomationName().hashCode());
        result = prime * result + ((getEnabled() == null) ? 0 : getEnabled().hashCode());
        result = prime * result + ((getMatchType() == null) ? 0 : getMatchType().hashCode());
        result = prime * result + ((getConditionRule() == null) ? 0 : getConditionRule().hashCode());
        result = prime * result + ((getValid() == null) ? 0 : getValid().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyUser() == null) ? 0 : getModifyUser().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }
}