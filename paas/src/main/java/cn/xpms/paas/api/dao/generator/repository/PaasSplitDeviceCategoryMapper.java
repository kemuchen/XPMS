package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasSplitDeviceCategory;
import cn.xpms.paas.api.dao.generator.entity.PaasSplitDeviceCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasSplitDeviceCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_split_device_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    long countByExample(PaasSplitDeviceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_split_device_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int deleteByExample(PaasSplitDeviceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_split_device_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_split_device_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int insert(PaasSplitDeviceCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_split_device_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int insertSelective(PaasSplitDeviceCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_split_device_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    List<PaasSplitDeviceCategory> selectByExampleWithRowbounds(PaasSplitDeviceCategoryExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_split_device_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    List<PaasSplitDeviceCategory> selectByExample(PaasSplitDeviceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_split_device_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    PaasSplitDeviceCategory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_split_device_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasSplitDeviceCategory record, @Param("example") PaasSplitDeviceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_split_device_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByExample(@Param("record") PaasSplitDeviceCategory record, @Param("example") PaasSplitDeviceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_split_device_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByPrimaryKeySelective(PaasSplitDeviceCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_split_device_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByPrimaryKey(PaasSplitDeviceCategory record);
}