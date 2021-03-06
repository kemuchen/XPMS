package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasSceneTemplate;
import cn.xpms.paas.api.dao.generator.entity.PaasSceneTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasSceneTemplateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_template
     *
     * @mbg.generated Mon Dec 28 10:47:28 CST 2020
     */
    long countByExample(PaasSceneTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_template
     *
     * @mbg.generated Mon Dec 28 10:47:28 CST 2020
     */
    int deleteByExample(PaasSceneTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_template
     *
     * @mbg.generated Mon Dec 28 10:47:28 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_template
     *
     * @mbg.generated Mon Dec 28 10:47:28 CST 2020
     */
    int insert(PaasSceneTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_template
     *
     * @mbg.generated Mon Dec 28 10:47:28 CST 2020
     */
    int insertSelective(PaasSceneTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_template
     *
     * @mbg.generated Mon Dec 28 10:47:28 CST 2020
     */
    List<PaasSceneTemplate> selectByExampleWithRowbounds(PaasSceneTemplateExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_template
     *
     * @mbg.generated Mon Dec 28 10:47:28 CST 2020
     */
    List<PaasSceneTemplate> selectByExample(PaasSceneTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_template
     *
     * @mbg.generated Mon Dec 28 10:47:28 CST 2020
     */
    PaasSceneTemplate selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_template
     *
     * @mbg.generated Mon Dec 28 10:47:28 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasSceneTemplate record, @Param("example") PaasSceneTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_template
     *
     * @mbg.generated Mon Dec 28 10:47:28 CST 2020
     */
    int updateByExample(@Param("record") PaasSceneTemplate record, @Param("example") PaasSceneTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_template
     *
     * @mbg.generated Mon Dec 28 10:47:28 CST 2020
     */
    int updateByPrimaryKeySelective(PaasSceneTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_template
     *
     * @mbg.generated Mon Dec 28 10:47:28 CST 2020
     */
    int updateByPrimaryKey(PaasSceneTemplate record);
}