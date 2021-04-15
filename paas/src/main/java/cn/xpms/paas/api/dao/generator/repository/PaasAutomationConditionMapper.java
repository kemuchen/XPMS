package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasAutomationCondition;
import cn.xpms.paas.api.dao.generator.entity.PaasAutomationConditionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasAutomationConditionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    long countByExample(PaasAutomationConditionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    int deleteByExample(PaasAutomationConditionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    int insert(PaasAutomationCondition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    int insertSelective(PaasAutomationCondition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    List<PaasAutomationCondition> selectByExampleWithRowbounds(PaasAutomationConditionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    List<PaasAutomationCondition> selectByExample(PaasAutomationConditionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    PaasAutomationCondition selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasAutomationCondition record, @Param("example") PaasAutomationConditionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    int updateByExample(@Param("record") PaasAutomationCondition record, @Param("example") PaasAutomationConditionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    int updateByPrimaryKeySelective(PaasAutomationCondition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_condition
     *
     * @mbg.generated Thu Dec 17 10:57:49 CST 2020
     */
    int updateByPrimaryKey(PaasAutomationCondition record);
}