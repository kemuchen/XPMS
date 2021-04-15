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
public class PaasConstructionTemplatePosition implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.id
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.project_id
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String projectId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.template_id
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String templateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.area_id
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String areaId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.position_name
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String positionName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.construction_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String constructionCategory;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.custom_device_name
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String customDeviceName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.position_id
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String positionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.valid
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.create_user
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.create_time
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.modify_user
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer modifyUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.modify_time
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_construction_template_position.memo
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table paas_construction_template_position
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_position
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectId=").append(projectId);
        sb.append(", templateId=").append(templateId);
        sb.append(", areaId=").append(areaId);
        sb.append(", positionName=").append(positionName);
        sb.append(", constructionCategory=").append(constructionCategory);
        sb.append(", customDeviceName=").append(customDeviceName);
        sb.append(", positionId=").append(positionId);
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
     * This method corresponds to the database table paas_construction_template_position
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
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
        PaasConstructionTemplatePosition other = (PaasConstructionTemplatePosition) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()))
            && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
            && (this.getPositionName() == null ? other.getPositionName() == null : this.getPositionName().equals(other.getPositionName()))
            && (this.getConstructionCategory() == null ? other.getConstructionCategory() == null : this.getConstructionCategory().equals(other.getConstructionCategory()))
            && (this.getCustomDeviceName() == null ? other.getCustomDeviceName() == null : this.getCustomDeviceName().equals(other.getCustomDeviceName()))
            && (this.getPositionId() == null ? other.getPositionId() == null : this.getPositionId().equals(other.getPositionId()))
            && (this.getValid() == null ? other.getValid() == null : this.getValid().equals(other.getValid()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyUser() == null ? other.getModifyUser() == null : this.getModifyUser().equals(other.getModifyUser()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_position
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getPositionName() == null) ? 0 : getPositionName().hashCode());
        result = prime * result + ((getConstructionCategory() == null) ? 0 : getConstructionCategory().hashCode());
        result = prime * result + ((getCustomDeviceName() == null) ? 0 : getCustomDeviceName().hashCode());
        result = prime * result + ((getPositionId() == null) ? 0 : getPositionId().hashCode());
        result = prime * result + ((getValid() == null) ? 0 : getValid().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyUser() == null) ? 0 : getModifyUser().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }
}