package cn.xpms.member.coupon.dao.generator.repository;

import cn.xpms.member.coupon.dao.generator.entity.MemberCouponEventRecord;
import cn.xpms.member.coupon.dao.generator.entity.MemberCouponEventRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCouponEventRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_event_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    long countByExample(MemberCouponEventRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_event_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int deleteByExample(MemberCouponEventRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_event_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_event_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int insert(MemberCouponEventRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_event_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int insertSelective(MemberCouponEventRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_event_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    List<MemberCouponEventRecord> selectByExampleWithRowbounds(MemberCouponEventRecordExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_event_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    List<MemberCouponEventRecord> selectByExample(MemberCouponEventRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_event_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    MemberCouponEventRecord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_event_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int updateByExampleSelective(@Param("record") MemberCouponEventRecord record, @Param("example") MemberCouponEventRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_event_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int updateByExample(@Param("record") MemberCouponEventRecord record, @Param("example") MemberCouponEventRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_event_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int updateByPrimaryKeySelective(MemberCouponEventRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_coupon_event_record
     *
     * @mbg.generated Tue Nov 03 15:59:00 CST 2020
     */
    int updateByPrimaryKey(MemberCouponEventRecord record);
}