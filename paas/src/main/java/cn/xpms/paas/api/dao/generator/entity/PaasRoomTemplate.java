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
public class PaasRoomTemplate implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_room_template.id
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_room_template.project_id
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    private String projectId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_room_template.template_name
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    private String templateName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_room_template.scene_template_ids
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    private String sceneTemplateIds;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_room_template.automation_template_ids
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    private String automationTemplateIds;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_room_template.description
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_room_template.valid
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_room_template.create_user
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_room_template.create_time
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_room_template.modify_user
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    private Integer modifyUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_room_template.modify_time
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_room_template.memo
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table paas_room_template
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_room_template
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectId=").append(projectId);
        sb.append(", templateName=").append(templateName);
        sb.append(", sceneTemplateIds=").append(sceneTemplateIds);
        sb.append(", automationTemplateIds=").append(automationTemplateIds);
        sb.append(", description=").append(description);
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
     * This method corresponds to the database table paas_room_template
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
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
        PaasRoomTemplate other = (PaasRoomTemplate) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getTemplateName() == null ? other.getTemplateName() == null : this.getTemplateName().equals(other.getTemplateName()))
            && (this.getSceneTemplateIds() == null ? other.getSceneTemplateIds() == null : this.getSceneTemplateIds().equals(other.getSceneTemplateIds()))
            && (this.getAutomationTemplateIds() == null ? other.getAutomationTemplateIds() == null : this.getAutomationTemplateIds().equals(other.getAutomationTemplateIds()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getValid() == null ? other.getValid() == null : this.getValid().equals(other.getValid()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyUser() == null ? other.getModifyUser() == null : this.getModifyUser().equals(other.getModifyUser()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_room_template
     *
     * @mbg.generated Thu Jan 07 16:21:59 CST 2021
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getTemplateName() == null) ? 0 : getTemplateName().hashCode());
        result = prime * result + ((getSceneTemplateIds() == null) ? 0 : getSceneTemplateIds().hashCode());
        result = prime * result + ((getAutomationTemplateIds() == null) ? 0 : getAutomationTemplateIds().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getValid() == null) ? 0 : getValid().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyUser() == null) ? 0 : getModifyUser().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }
}