package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplateArea;
import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplateAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasConstructionTemplateAreaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_area
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    long countByExample(PaasConstructionTemplateAreaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_area
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int deleteByExample(PaasConstructionTemplateAreaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_area
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_area
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int insert(PaasConstructionTemplateArea record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_area
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int insertSelective(PaasConstructionTemplateArea record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_area
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    List<PaasConstructionTemplateArea> selectByExampleWithRowbounds(PaasConstructionTemplateAreaExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_area
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    List<PaasConstructionTemplateArea> selectByExample(PaasConstructionTemplateAreaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_area
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    PaasConstructionTemplateArea selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_area
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasConstructionTemplateArea record, @Param("example") PaasConstructionTemplateAreaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_area
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByExample(@Param("record") PaasConstructionTemplateArea record, @Param("example") PaasConstructionTemplateAreaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_area
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByPrimaryKeySelective(PaasConstructionTemplateArea record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_construction_template_area
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByPrimaryKey(PaasConstructionTemplateArea record);
}