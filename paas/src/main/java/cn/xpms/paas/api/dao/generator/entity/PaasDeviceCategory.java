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
public class PaasDeviceCategory implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_device_category.id
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_device_category.category
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    private String category;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_device_category.construction_category
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    private String constructionCategory;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_device_category.category_name
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    private String categoryName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_device_category.icon
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    private String icon;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_device_category.type
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_device_category.split_num
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    private Integer splitNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_device_category.valid
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_device_category.create_user
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_device_category.create_time
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_device_category.modify_user
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    private Integer modifyUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_device_category.modify_time
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paas_device_category.memo
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table paas_device_category
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_category
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", category=").append(category);
        sb.append(", constructionCategory=").append(constructionCategory);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", icon=").append(icon);
        sb.append(", type=").append(type);
        sb.append(", splitNum=").append(splitNum);
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
     * This method corresponds to the database table paas_device_category
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
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
        PaasDeviceCategory other = (PaasDeviceCategory) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getConstructionCategory() == null ? other.getConstructionCategory() == null : this.getConstructionCategory().equals(other.getConstructionCategory()))
            && (this.getCategoryName() == null ? other.getCategoryName() == null : this.getCategoryName().equals(other.getCategoryName()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getSplitNum() == null ? other.getSplitNum() == null : this.getSplitNum().equals(other.getSplitNum()))
            && (this.getValid() == null ? other.getValid() == null : this.getValid().equals(other.getValid()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyUser() == null ? other.getModifyUser() == null : this.getModifyUser().equals(other.getModifyUser()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_category
     *
     * @mbg.generated Tue Jan 12 09:56:01 CST 2021
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getConstructionCategory() == null) ? 0 : getConstructionCategory().hashCode());
        result = prime * result + ((getCategoryName() == null) ? 0 : getCategoryName().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getSplitNum() == null) ? 0 : getSplitNum().hashCode());
        result = prime * result + ((getValid() == null) ? 0 : getValid().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyUser() == null) ? 0 : getModifyUser().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }
}