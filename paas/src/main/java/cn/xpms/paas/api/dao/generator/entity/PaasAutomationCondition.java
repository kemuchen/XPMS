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
public class PaasAutomationCondition implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_condition.id
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_condition.automation_id
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    private String automationId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_condition.entity_type
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    private Integer entityType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_condition.entity_id
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    private String entityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_condition.order_num
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    private Integer orderNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_condition.valid
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_condition.create_user
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_condition.create_time
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_condition.modify_user
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    private Integer modifyUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_condition.modify_time
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_automation_condition.memo
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", automationId=").append(automationId);
        sb.append(", entityType=").append(entityType);
        sb.append(", entityId=").append(entityId);
        sb.append(", orderNum=").append(orderNum);
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
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
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
        PaasAutomationCondition other = (PaasAutomationCondition) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAutomationId() == null ? other.getAutomationId() == null : this.getAutomationId().equals(other.getAutomationId()))
            && (this.getEntityType() == null ? other.getEntityType() == null : this.getEntityType().equals(other.getEntityType()))
            && (this.getEntityId() == null ? other.getEntityId() == null : this.getEntityId().equals(other.getEntityId()))
            && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(other.getOrderNum()))
            && (this.getValid() == null ? other.getValid() == null : this.getValid().equals(other.getValid()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyUser() == null ? other.getModifyUser() == null : this.getModifyUser().equals(other.getModifyUser()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAutomationId() == null) ? 0 : getAutomationId().hashCode());
        result = prime * result + ((getEntityType() == null) ? 0 : getEntityType().hashCode());
        result = prime * result + ((getEntityId() == null) ? 0 : getEntityId().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getValid() == null) ? 0 : getValid().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyUser() == null) ? 0 : getModifyUser().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }
}