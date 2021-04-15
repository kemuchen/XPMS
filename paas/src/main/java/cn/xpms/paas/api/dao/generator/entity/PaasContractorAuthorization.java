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
public class PaasContractorAuthorization implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_contractor_authorization.id
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_contractor_authorization.user_id
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_contractor_authorization.country_code
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String countryCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_contractor_authorization.username
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_contractor_authorization.authorization_id
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String authorizationId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_contractor_authorization.enterprise_name
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String enterpriseName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_contractor_authorization.cooperation_time
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer cooperationTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_contractor_authorization.valid
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_contractor_authorization.create_user
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_contractor_authorization.create_time
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_contractor_authorization.modify_user
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private Integer modifyUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_contractor_authorization.modify_time
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_contractor_authorization.memo
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
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
        sb.append(", userId=").append(userId);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", username=").append(username);
        sb.append(", authorizationId=").append(authorizationId);
        sb.append(", enterpriseName=").append(enterpriseName);
        sb.append(", cooperationTime=").append(cooperationTime);
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
     * This method corresponds to the database table paas_contractor_authorization
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
        PaasContractorAuthorization other = (PaasContractorAuthorization) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCountryCode() == null ? other.getCountryCode() == null : this.getCountryCode().equals(other.getCountryCode()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getAuthorizationId() == null ? other.getAuthorizationId() == null : this.getAuthorizationId().equals(other.getAuthorizationId()))
            && (this.getEnterpriseName() == null ? other.getEnterpriseName() == null : this.getEnterpriseName().equals(other.getEnterpriseName()))
            && (this.getCooperationTime() == null ? other.getCooperationTime() == null : this.getCooperationTime().equals(other.getCooperationTime()))
            && (this.getValid() == null ? other.getValid() == null : this.getValid().equals(other.getValid()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyUser() == null ? other.getModifyUser() == null : this.getModifyUser().equals(other.getModifyUser()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCountryCode() == null) ? 0 : getCountryCode().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getAuthorizationId() == null) ? 0 : getAuthorizationId().hashCode());
        result = prime * result + ((getEnterpriseName() == null) ? 0 : getEnterpriseName().hashCode());
        result = prime * result + ((getCooperationTime() == null) ? 0 : getCooperationTime().hashCode());
        result = prime * result + ((getValid() == null) ? 0 : getValid().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyUser() == null) ? 0 : getModifyUser().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }
}