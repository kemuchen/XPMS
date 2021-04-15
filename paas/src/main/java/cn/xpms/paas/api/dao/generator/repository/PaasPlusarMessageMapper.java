package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasPlusarMessage;
import cn.xpms.paas.api.dao.generator.entity.PaasPlusarMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasPlusarMessageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_plusar_message
     *
     * @mbg.generated Thu Dec 17 14:35:13 CST 2020
     */
    long countByExample(PaasPlusarMessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_plusar_message
     *
     * @mbg.generated Thu Dec 17 14:35:13 CST 2020
     */
    int deleteByExample(PaasPlusarMessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_plusar_message
     *
     * @mbg.generated Thu Dec 17 14:35:13 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_plusar_message
     *
     * @mbg.generated Thu Dec 17 14:35:13 CST 2020
     */
    int insert(PaasPlusarMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_plusar_message
     *
     * @mbg.generated Thu Dec 17 14:35:13 CST 2020
     */
    int insertSelective(PaasPlusarMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_plusar_message
     *
     * @mbg.generated Thu Dec 17 14:35:13 CST 2020
     */
    List<PaasPlusarMessage> selectByExampleWithRowbounds(PaasPlusarMessageExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_plusar_message
     *
     * @mbg.generated Thu Dec 17 14:35:13 CST 2020
     */
    List<PaasPlusarMessage> selectByExample(PaasPlusarMessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_plusar_message
     *
     * @mbg.generated Thu Dec 17 14:35:13 CST 2020
     */
    PaasPlusarMessage selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_plusar_message
     *
     * @mbg.generated Thu Dec 17 14:35:13 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasPlusarMessage record, @Param("example") PaasPlusarMessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_plusar_message
     *
     * @mbg.generated Thu Dec 17 14:35:13 CST 2020
     */
    int updateByExample(@Param("record") PaasPlusarMessage record, @Param("example") PaasPlusarMessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_plusar_message
     *
     * @mbg.generated Thu Dec 17 14:35:13 CST 2020
     */
    int updateByPrimaryKeySelective(PaasPlusarMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_plusar_message
     *
     * @mbg.generated Thu Dec 17 14:35:13 CST 2020
     */
    int updateByPrimaryKey(PaasPlusarMessage record);
}