package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasCategoryFunction;
import cn.xpms.paas.api.dao.generator.entity.PaasCategoryFunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasCategoryFunctionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_category_function
     *
     * @mbg.generated Fri Dec 18 11:54:50 CST 2020
     */
    long countByExample(PaasCategoryFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_category_function
     *
     * @mbg.generated Fri Dec 18 11:54:50 CST 2020
     */
    int deleteByExample(PaasCategoryFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_category_function
     *
     * @mbg.generated Fri Dec 18 11:54:50 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_category_function
     *
     * @mbg.generated Fri Dec 18 11:54:50 CST 2020
     */
    int insert(PaasCategoryFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_category_function
     *
     * @mbg.generated Fri Dec 18 11:54:50 CST 2020
     */
    int insertSelective(PaasCategoryFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_category_function
     *
     * @mbg.generated Fri Dec 18 11:54:50 CST 2020
     */
    List<PaasCategoryFunction> selectByExampleWithRowbounds(PaasCategoryFunctionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_category_function
     *
     * @mbg.generated Fri Dec 18 11:54:50 CST 2020
     */
    List<PaasCategoryFunction> selectByExample(PaasCategoryFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_category_function
     *
     * @mbg.generated Fri Dec 18 11:54:50 CST 2020
     */
    PaasCategoryFunction selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_category_function
     *
     * @mbg.generated Fri Dec 18 11:54:50 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasCategoryFunction record, @Param("example") PaasCategoryFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_category_function
     *
     * @mbg.generated Fri Dec 18 11:54:50 CST 2020
     */
    int updateByExample(@Param("record") PaasCategoryFunction record, @Param("example") PaasCategoryFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_category_function
     *
     * @mbg.generated Fri Dec 18 11:54:50 CST 2020
     */
    int updateByPrimaryKeySelective(PaasCategoryFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_category_function
     *
     * @mbg.generated Fri Dec 18 11:54:50 CST 2020
     */
    int updateByPrimaryKey(PaasCategoryFunction record);
}