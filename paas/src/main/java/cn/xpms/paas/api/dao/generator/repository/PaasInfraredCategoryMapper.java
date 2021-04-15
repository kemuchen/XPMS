package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasInfraredCategory;
import cn.xpms.paas.api.dao.generator.entity.PaasInfraredCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasInfraredCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    long countByExample(PaasInfraredCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int deleteByExample(PaasInfraredCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int insert(PaasInfraredCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int insertSelective(PaasInfraredCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    List<PaasInfraredCategory> selectByExampleWithRowbounds(PaasInfraredCategoryExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    List<PaasInfraredCategory> selectByExample(PaasInfraredCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    PaasInfraredCategory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasInfraredCategory record, @Param("example") PaasInfraredCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByExample(@Param("record") PaasInfraredCategory record, @Param("example") PaasInfraredCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByPrimaryKeySelective(PaasInfraredCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_category
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByPrimaryKey(PaasInfraredCategory record);
}