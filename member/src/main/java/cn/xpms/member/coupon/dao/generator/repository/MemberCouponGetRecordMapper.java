package cn.xpms.member.coupon.dao.generator.repository;

import cn.xpms.member.coupon.dao.generator.entity.MemberCouponGetRecord;
import cn.xpms.member.coupon.dao.generator.entity.MemberCouponGetRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCouponGetRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_get_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    long countByExample(MemberCouponGetRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_get_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int deleteByExample(MemberCouponGetRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_get_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_get_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int insert(MemberCouponGetRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_get_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int insertSelective(MemberCouponGetRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_get_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    List<MemberCouponGetRecord> selectByExampleWithRowbounds(MemberCouponGetRecordExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_get_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    List<MemberCouponGetRecord> selectByExample(MemberCouponGetRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_get_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    MemberCouponGetRecord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_get_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int updateByExampleSelective(@Param("record") MemberCouponGetRecord record, @Param("example") MemberCouponGetRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_get_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int updateByExample(@Param("record") MemberCouponGetRecord record, @Param("example") MemberCouponGetRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_get_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int updateByPrimaryKeySelective(MemberCouponGetRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_get_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int updateByPrimaryKey(MemberCouponGetRecord record);
}