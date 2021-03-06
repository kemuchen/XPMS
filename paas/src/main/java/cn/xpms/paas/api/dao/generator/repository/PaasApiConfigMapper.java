package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasApiConfig;
import cn.xpms.paas.api.dao.generator.entity.PaasApiConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasApiConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_config
     *
     * @mbg.generated Fri Dec 11 10:38:56 CST 2020
     */
    long countByExample(PaasApiConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_config
     *
     * @mbg.generated Fri Dec 11 10:38:56 CST 2020
     */
    int deleteByExample(PaasApiConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_config
     *
     * @mbg.generated Fri Dec 11 10:38:56 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_config
     *
     * @mbg.generated Fri Dec 11 10:38:56 CST 2020
     */
    int insert(PaasApiConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_config
     *
     * @mbg.generated Fri Dec 11 10:38:56 CST 2020
     */
    int insertSelective(PaasApiConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_config
     *
     * @mbg.generated Fri Dec 11 10:38:56 CST 2020
     */
    List<PaasApiConfig> selectByExampleWithRowbounds(PaasApiConfigExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_config
     *
     * @mbg.generated Fri Dec 11 10:38:56 CST 2020
     */
    List<PaasApiConfig> selectByExample(PaasApiConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_config
     *
     * @mbg.generated Fri Dec 11 10:38:56 CST 2020
     */
    PaasApiConfig selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_config
     *
     * @mbg.generated Fri Dec 11 10:38:56 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasApiConfig record, @Param("example") PaasApiConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_config
     *
     * @mbg.generated Fri Dec 11 10:38:56 CST 2020
     */
    int updateByExample(@Param("record") PaasApiConfig record, @Param("example") PaasApiConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_config
     *
     * @mbg.generated Fri Dec 11 10:38:56 CST 2020
     */
    int updateByPrimaryKeySelective(PaasApiConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_api_config
     *
     * @mbg.generated Fri Dec 11 10:38:56 CST 2020
     */
    int updateByPrimaryKey(PaasApiConfig record);
}