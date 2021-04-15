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
public class PaasApiCallRecord implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.id
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.api_id
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    private Integer apiId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.result
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    private String params;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.start_time
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.t
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    private Integer t;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.end_time
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.result
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    private String result;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.result
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    private String response;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.message
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    private String message;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.valid
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.create_user
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.create_time
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.modify_user
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    private Integer modifyUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.modify_time
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_api_call_record.memo
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table paas_api_call_record
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_call_record
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", apiId=").append(apiId);
        sb.append(", params=").append(params);
        sb.append(", startTime=").append(startTime);
        sb.append(", t=").append(t);
        sb.append(", endTime=").append(endTime);
        sb.append(", result=").append(result);
        sb.append(", response=").append(response);
        sb.append(", message=").append(message);
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
     * This method corresponds to the database table paas_api_call_record
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
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
        PaasApiCallRecord other = (PaasApiCallRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getApiId() == null ? other.getApiId() == null : this.getApiId().equals(other.getApiId()))
            && (this.getParams() == null ? other.getParams() == null : this.getParams().equals(other.getParams()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getT() == null ? other.getT() == null : this.getT().equals(other.getT()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
            && (this.getResponse() == null ? other.getResponse() == null : this.getResponse().equals(other.getResponse()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
            && (this.getValid() == null ? other.getValid() == null : this.getValid().equals(other.getValid()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyUser() == null ? other.getModifyUser() == null : this.getModifyUser().equals(other.getModifyUser()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_call_record
     *
     * @mbg.generated Wed Dec 02 11:15:02 CST 2020
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getApiId() == null) ? 0 : getApiId().hashCode());
        result = prime * result + ((getParams() == null) ? 0 : getParams().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getT() == null) ? 0 : getT().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getResponse() == null) ? 0 : getResponse().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getValid() == null) ? 0 : getValid().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyUser() == null) ? 0 : getModifyUser().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }
}