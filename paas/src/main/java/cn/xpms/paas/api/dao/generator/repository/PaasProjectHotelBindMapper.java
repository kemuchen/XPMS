package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasProjectHotelBind;
import cn.xpms.paas.api.dao.generator.entity.PaasProjectHotelBindExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasProjectHotelBindMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_project_hotel_bind
     *
     * @mbg.generated Tue Dec 15 09:59:48 CST 2020
     */
    long countByExample(PaasProjectHotelBindExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_project_hotel_bind
     *
     * @mbg.generated Tue Dec 15 09:59:48 CST 2020
     */
    int deleteByExample(PaasProjectHotelBindExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_project_hotel_bind
     *
     * @mbg.generated Tue Dec 15 09:59:48 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_project_hotel_bind
     *
     * @mbg.generated Tue Dec 15 09:59:48 CST 2020
     */
    int insert(PaasProjectHotelBind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_project_hotel_bind
     *
     * @mbg.generated Tue Dec 15 09:59:48 CST 2020
     */
    int insertSelective(PaasProjectHotelBind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_project_hotel_bind
     *
     * @mbg.generated Tue Dec 15 09:59:48 CST 2020
     */
    List<PaasProjectHotelBind> selectByExampleWithRowbounds(PaasProjectHotelBindExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_project_hotel_bind
     *
     * @mbg.generated Tue Dec 15 09:59:48 CST 2020
     */
    List<PaasProjectHotelBind> selectByExample(PaasProjectHotelBindExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_project_hotel_bind
     *
     * @mbg.generated Tue Dec 15 09:59:48 CST 2020
     */
    PaasProjectHotelBind selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_project_hotel_bind
     *
     * @mbg.generated Tue Dec 15 09:59:48 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasProjectHotelBind record, @Param("example") PaasProjectHotelBindExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_project_hotel_bind
     *
     * @mbg.generated Tue Dec 15 09:59:48 CST 2020
     */
    int updateByExample(@Param("record") PaasProjectHotelBind record, @Param("example") PaasProjectHotelBindExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_project_hotel_bind
     *
     * @mbg.generated Tue Dec 15 09:59:48 CST 2020
     */
    int updateByPrimaryKeySelective(PaasProjectHotelBind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_project_hotel_bind
     *
     * @mbg.generated Tue Dec 15 09:59:48 CST 2020
     */
    int updateByPrimaryKey(PaasProjectHotelBind record);
}