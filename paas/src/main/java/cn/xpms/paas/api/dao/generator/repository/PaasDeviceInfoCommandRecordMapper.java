package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasDeviceInfoCommandRecord;
import cn.xpms.paas.api.dao.generator.entity.PaasDeviceInfoCommandRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasDeviceInfoCommandRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_info_command_record
     *
     * @mbg.generated Tue Dec 08 09:08:00 CST 2020
     */
    long countByExample(PaasDeviceInfoCommandRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_info_command_record
     *
     * @mbg.generated Tue Dec 08 09:08:00 CST 2020
     */
    int deleteByExample(PaasDeviceInfoCommandRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_info_command_record
     *
     * @mbg.generated Tue Dec 08 09:08:00 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_info_command_record
     *
     * @mbg.generated Tue Dec 08 09:08:00 CST 2020
     */
    int insert(PaasDeviceInfoCommandRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_info_command_record
     *
     * @mbg.generated Tue Dec 08 09:08:00 CST 2020
     */
    int insertSelective(PaasDeviceInfoCommandRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_info_command_record
     *
     * @mbg.generated Tue Dec 08 09:08:00 CST 2020
     */
    List<PaasDeviceInfoCommandRecord> selectByExampleWithRowbounds(PaasDeviceInfoCommandRecordExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_info_command_record
     *
     * @mbg.generated Tue Dec 08 09:08:00 CST 2020
     */
    List<PaasDeviceInfoCommandRecord> selectByExample(PaasDeviceInfoCommandRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_info_command_record
     *
     * @mbg.generated Tue Dec 08 09:08:00 CST 2020
     */
    PaasDeviceInfoCommandRecord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_info_command_record
     *
     * @mbg.generated Tue Dec 08 09:08:00 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasDeviceInfoCommandRecord record, @Param("example") PaasDeviceInfoCommandRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_info_command_record
     *
     * @mbg.generated Tue Dec 08 09:08:00 CST 2020
     */
    int updateByExample(@Param("record") PaasDeviceInfoCommandRecord record, @Param("example") PaasDeviceInfoCommandRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_info_command_record
     *
     * @mbg.generated Tue Dec 08 09:08:00 CST 2020
     */
    int updateByPrimaryKeySelective(PaasDeviceInfoCommandRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_device_info_command_record
     *
     * @mbg.generated Tue Dec 08 09:08:00 CST 2020
     */
    int updateByPrimaryKey(PaasDeviceInfoCommandRecord record);
}