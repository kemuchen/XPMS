package cn.xpms.system.system.dao.generator.entity;

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
public class SysRole2subsys implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role2subsys.id
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role2subsys.role_id
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
     */
    private Integer roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role2subsys.sub_sys_id
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
     */
    private Integer subSysId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role2subsys.sub_sys_info_id
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
     */
    private Integer subSysInfoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role2subsys.valid
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role2subsys.create_user
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role2subsys.create_time
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role2subsys.modify_user
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
     */
    private Integer modifyUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role2subsys.modify_time
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role2subsys.memo
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_role2subsys
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role2subsys
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", subSysId=").append(subSysId);
        sb.append(", subSysInfoId=").append(subSysInfoId);
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
     * This method corresponds to the database table sys_role2subsys
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
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
        SysRole2subsys other = (SysRole2subsys) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getSubSysId() == null ? other.getSubSysId() == null : this.getSubSysId().equals(other.getSubSysId()))
            && (this.getSubSysInfoId() == null ? other.getSubSysInfoId() == null : this.getSubSysInfoId().equals(other.getSubSysInfoId()))
            && (this.getValid() == null ? other.getValid() == null : this.getValid().equals(other.getValid()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyUser() == null ? other.getModifyUser() == null : this.getModifyUser().equals(other.getModifyUser()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role2subsys
     *
     * @mbg.generated Wed Nov 25 11:55:57 CST 2020
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getSubSysId() == null) ? 0 : getSubSysId().hashCode());
        result = prime * result + ((getSubSysInfoId() == null) ? 0 : getSubSysInfoId().hashCode());
        result = prime * result + ((getValid() == null) ? 0 : getValid().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyUser() == null) ? 0 : getModifyUser().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }
}