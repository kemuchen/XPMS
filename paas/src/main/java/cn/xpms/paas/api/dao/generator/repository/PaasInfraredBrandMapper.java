package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasInfraredBrand;
import cn.xpms.paas.api.dao.generator.entity.PaasInfraredBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasInfraredBrandMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_brand
     *
     * @mbg.generated Wed Dec 02 10:54:15 CST 2020
     */
    long countByExample(PaasInfraredBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_brand
     *
     * @mbg.generated Wed Dec 02 10:54:15 CST 2020
     */
    int deleteByExample(PaasInfraredBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_brand
     *
     * @mbg.generated Wed Dec 02 10:54:15 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_brand
     *
     * @mbg.generated Wed Dec 02 10:54:15 CST 2020
     */
    int insert(PaasInfraredBrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_brand
     *
     * @mbg.generated Wed Dec 02 10:54:15 CST 2020
     */
    int insertSelective(PaasInfraredBrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_brand
     *
     * @mbg.generated Wed Dec 02 10:54:15 CST 2020
     */
    List<PaasInfraredBrand> selectByExampleWithRowbounds(PaasInfraredBrandExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_brand
     *
     * @mbg.generated Wed Dec 02 10:54:15 CST 2020
     */
    List<PaasInfraredBrand> selectByExample(PaasInfraredBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_brand
     *
     * @mbg.generated Wed Dec 02 10:54:15 CST 2020
     */
    PaasInfraredBrand selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_brand
     *
     * @mbg.generated Wed Dec 02 10:54:15 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasInfraredBrand record, @Param("example") PaasInfraredBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_brand
     *
     * @mbg.generated Wed Dec 02 10:54:15 CST 2020
     */
    int updateByExample(@Param("record") PaasInfraredBrand record, @Param("example") PaasInfraredBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_brand
     *
     * @mbg.generated Wed Dec 02 10:54:15 CST 2020
     */
    int updateByPrimaryKeySelective(PaasInfraredBrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_infrared_brand
     *
     * @mbg.generated Wed Dec 02 10:54:15 CST 2020
     */
    int updateByPrimaryKey(PaasInfraredBrand record);
}