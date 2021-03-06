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
public class PaasSpaceInfo implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_space_info.id
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_space_info.project_id
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String projectId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_space_info.space_id
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String spaceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_space_info.parent_space_id
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String parentSpaceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_space_info.space_name
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String spaceName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_space_info.space_type
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String spaceType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_space_info.sort
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer sort;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_space_info.valid
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_space_info.create_user
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_space_info.create_time
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_space_info.modify_user
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer modifyUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_space_info.modify_time
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_space_info.memo
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table paas_space_info
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_space_info
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
        sb.append(", spaceId=").append(spaceId);
        sb.append(", parentSpaceId=").append(parentSpaceId);
        sb.append(", spaceName=").append(spaceName);
        sb.append(", spaceType=").append(spaceType);
        sb.append(", sort=").append(sort);
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
     * This method corresponds to the database table paas_space_info
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
        PaasSpaceInfo other = (PaasSpaceInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getSpaceId() == null ? other.getSpaceId() == null : this.getSpaceId().equals(other.getSpaceId()))
            && (this.getParentSpaceId() == null ? other.getParentSpaceId() == null : this.getParentSpaceId().equals(other.getParentSpaceId()))
            && (this.getSpaceName() == null ? other.getSpaceName() == null : this.getSpaceName().equals(other.getSpaceName()))
            && (this.getSpaceType() == null ? other.getSpaceType() == null : this.getSpaceType().equals(other.getSpaceType()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getValid() == null ? other.getValid() == null : this.getValid().equals(other.getValid()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyUser() == null ? other.getModifyUser() == null : this.getModifyUser().equals(other.getModifyUser()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_space_info
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getSpaceId() == null) ? 0 : getSpaceId().hashCode());
        result = prime * result + ((getParentSpaceId() == null) ? 0 : getParentSpaceId().hashCode());
        result = prime * result + ((getSpaceName() == null) ? 0 : getSpaceName().hashCode());
        result = prime * result + ((getSpaceType() == null) ? 0 : getSpaceType().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getValid() == null) ? 0 : getValid().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyUser() == null) ? 0 : getModifyUser().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }
}