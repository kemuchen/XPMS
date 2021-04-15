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
public class PaasAutomationTemplatePreconditionDisplay implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_template_precondition_display.id
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_template_precondition_display.automation_procondition_id
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    private Integer automationProconditionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_template_precondition_display.start
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_template_precondition_display.end
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_template_precondition_display.loops
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    private String loops;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_template_precondition_display.timezone_id
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    private String timezoneId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_template_precondition_display.valid
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_template_precondition_display.create_user
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_template_precondition_display.create_time
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_template_precondition_display.modify_user
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    private Integer modifyUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_template_precondition_display.modify_time
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_template_precondition_display.memo
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table paas_automation_template_precondition_display
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_precondition_display
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", automationProconditionId=").append(automationProconditionId);
        sb.append(", start=").append(start);
        sb.append(", end=").append(end);
        sb.append(", loops=").append(loops);
        sb.append(", timezoneId=").append(timezoneId);
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
     * This method corresponds to the database table paas_automation_template_precondition_display
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
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
        PaasAutomationTemplatePreconditionDisplay other = (PaasAutomationTemplatePreconditionDisplay) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAutomationProconditionId() == null ? other.getAutomationProconditionId() == null : this.getAutomationProconditionId().equals(other.getAutomationProconditionId()))
            && (this.getStart() == null ? other.getStart() == null : this.getStart().equals(other.getStart()))
            && (this.getEnd() == null ? other.getEnd() == null : this.getEnd().equals(other.getEnd()))
            && (this.getLoops() == null ? other.getLoops() == null : this.getLoops().equals(other.getLoops()))
            && (this.getTimezoneId() == null ? other.getTimezoneId() == null : this.getTimezoneId().equals(other.getTimezoneId()))
            && (this.getValid() == null ? other.getValid() == null : this.getValid().equals(other.getValid()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyUser() == null ? other.getModifyUser() == null : this.getModifyUser().equals(other.getModifyUser()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_precondition_display
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAutomationProconditionId() == null) ? 0 : getAutomationProconditionId().hashCode());
        result = prime * result + ((getStart() == null) ? 0 : getStart().hashCode());
        result = prime * result + ((getEnd() == null) ? 0 : getEnd().hashCode());
        result = prime * result + ((getLoops() == null) ? 0 : getLoops().hashCode());
        result = prime * result + ((getTimezoneId() == null) ? 0 : getTimezoneId().hashCode());
        result = prime * result + ((getValid() == null) ? 0 : getValid().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyUser() == null) ? 0 : getModifyUser().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }
}