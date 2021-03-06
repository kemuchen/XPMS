package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasAutomationTemplateCondition;
import cn.xpms.paas.api.dao.generator.entity.PaasAutomationTemplateConditionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasAutomationTemplateConditionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_condition
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    long countByExample(PaasAutomationTemplateConditionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_condition
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    int deleteByExample(PaasAutomationTemplateConditionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_condition
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_condition
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    int insert(PaasAutomationTemplateCondition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_condition
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    int insertSelective(PaasAutomationTemplateCondition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_condition
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    List<PaasAutomationTemplateCondition> selectByExampleWithRowbounds(PaasAutomationTemplateConditionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_condition
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    List<PaasAutomationTemplateCondition> selectByExample(PaasAutomationTemplateConditionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_condition
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    PaasAutomationTemplateCondition selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_condition
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasAutomationTemplateCondition record, @Param("example") PaasAutomationTemplateConditionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_condition
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    int updateByExample(@Param("record") PaasAutomationTemplateCondition record, @Param("example") PaasAutomationTemplateConditionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_condition
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    int updateByPrimaryKeySelective(PaasAutomationTemplateCondition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_automation_template_condition
     *
     * @mbg.generated Tue Dec 29 11:03:11 CST 2020
     */
    int updateByPrimaryKey(PaasAutomationTemplateCondition record);
}