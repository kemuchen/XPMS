package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplate;
import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasConstructionTemplateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    long countByExample(PaasConstructionTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int deleteByExample(PaasConstructionTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int insert(PaasConstructionTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int insertSelective(PaasConstructionTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    List<PaasConstructionTemplate> selectByExampleWithRowbounds(PaasConstructionTemplateExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    List<PaasConstructionTemplate> selectByExample(PaasConstructionTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    PaasConstructionTemplate selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasConstructionTemplate record, @Param("example") PaasConstructionTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByExample(@Param("record") PaasConstructionTemplate record, @Param("example") PaasConstructionTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByPrimaryKeySelective(PaasConstructionTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByPrimaryKey(PaasConstructionTemplate record);
}