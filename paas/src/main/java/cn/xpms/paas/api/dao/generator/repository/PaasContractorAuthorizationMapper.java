package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasContractorAuthorization;
import cn.xpms.paas.api.dao.generator.entity.PaasContractorAuthorizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasContractorAuthorizationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    long countByExample(PaasContractorAuthorizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int deleteByExample(PaasContractorAuthorizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int insert(PaasContractorAuthorization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int insertSelective(PaasContractorAuthorization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    List<PaasContractorAuthorization> selectByExampleWithRowbounds(PaasContractorAuthorizationExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    List<PaasContractorAuthorization> selectByExample(PaasContractorAuthorizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    PaasContractorAuthorization selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasContractorAuthorization record, @Param("example") PaasContractorAuthorizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByExample(@Param("record") PaasContractorAuthorization record, @Param("example") PaasContractorAuthorizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByPrimaryKeySelective(PaasContractorAuthorization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_contractor_authorization
     *
     * @mbg.generated Tue Dec 01 11:13:32 CST 2020
     */
    int updateByPrimaryKey(PaasContractorAuthorization record);
}