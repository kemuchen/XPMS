package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasCustomAutomationRecord;
import cn.xpms.paas.api.dao.generator.entity.PaasCustomAutomationRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasCustomAutomationRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    long countByExample(PaasCustomAutomationRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    int deleteByExample(PaasCustomAutomationRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    int insert(PaasCustomAutomationRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    int insertSelective(PaasCustomAutomationRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    List<PaasCustomAutomationRecord> selectByExampleWithRowbounds(PaasCustomAutomationRecordExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    List<PaasCustomAutomationRecord> selectByExample(PaasCustomAutomationRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    PaasCustomAutomationRecord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    int updateByExampleSelective(@Param("record") PaasCustomAutomationRecord record, @Param("example") PaasCustomAutomationRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    int updateByExample(@Param("record") PaasCustomAutomationRecord record, @Param("example") PaasCustomAutomationRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    int updateByPrimaryKeySelective(PaasCustomAutomationRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_custom_automation_record
     *
     * @mbg.generated Tue Jan 26 11:58:06 CST 2021
     */
    int updateByPrimaryKey(PaasCustomAutomationRecord record);
}