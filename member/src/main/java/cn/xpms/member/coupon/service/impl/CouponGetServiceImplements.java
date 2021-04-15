package cn.xpms.member.coupon.service.impl;

import cn.xpms.member.common.constant.Constants;
import cn.xpms.member.common.error.CouponErrorCode;
import cn.xpms.member.coupon.bean.pojo.CouponGetPojo;
import cn.xpms.member.coupon.bean.vo.CouponGetVo;
import cn.xpms.member.coupon.dao.generator.entity.MemberCouponGetRecord;
import cn.xpms.member.coupon.dao.generator.entity.MemberCouponGetRecordExample;
import cn.xpms.member.coupon.dao.generator.entity.MemberCouponInfo;
import cn.xpms.member.coupon.dao.generator.entity.MemberCouponInfoExample;
import cn.xpms.member.coupon.dao.generator.repository.MemberCouponGetRecordMapper;
import cn.xpms.member.coupon.dao.generator.repository.MemberCouponInfoMapper;
import cn.xpms.member.coupon.service.inter.CouponGetServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.util.sys.DateUtil;
import cn.xpms.system.framework.util.sys.SysUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName CouponGetServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/2 12:02
 * @Version 1.0
 */
@Service
public class CouponGetServiceImplements implements CouponGetServiceInterface {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(CouponGetServiceImplements.class);

    /** 优惠券信息查询 */
    @Autowired
    MemberCouponInfoMapper memberCouponInfoMapper;

    /** 优惠券领取记录 */
    @Autowired
    MemberCouponGetRecordMapper memberCouponGetRecordMapper;

    private Lock lock = new ReentrantLock();// 锁对象

    /**
     * @Description: 领取优惠券
     * @Params: [card_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/2 13:36
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = AppException.class)
    public ApiResponseEntity getCoupon(String card_id) throws AppException {
        try {
            MemberCouponInfoExample memberCouponInfoExample = new MemberCouponInfoExample();
            memberCouponInfoExample.createCriteria().andCodeEqualTo(card_id);
            List<MemberCouponInfo> memberCouponInfos = memberCouponInfoMapper.selectByExample(memberCouponInfoExample);
            if (SysUtil.isEmpty(memberCouponInfos) || memberCouponInfos.size() == 0) {
                throw new AppException(CouponErrorCode.COUPON_ID_NOT_EXISTS);
            } else if (Constants.COUPON_DICT_DATE_FIX_TIME_RANGE.equals(memberCouponInfos.get(0).getDateType())) {
                // 当有效期类型为固定区间日期时，若当前日期超过有效截止日期，则不能领取
                if (memberCouponInfos.get(0).getDateEnd().compareTo(new Date()) < 0) {
                    throw new AppException(CouponErrorCode.COUPON_GOOVERRED);
                }
            }
            return this.getCoupon(memberCouponInfos.get(0));
        } catch (AppException e) {
            logger.error("【CouponGetServiceImplements.getCoupon】领取优惠券异常:" + e.getError_message());
            throw e;
        } catch (Exception e) {
            logger.error("【CouponGetServiceImplements.getCoupon】领取优惠券异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 领取优惠券
     * @Params: [memberCouponGetRecord]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/2 13:49
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = AppException.class)
    public ApiResponseEntity getCoupon(MemberCouponInfo memberCouponInfo) throws AppException {
        try {
            MemberCouponGetRecord memberCouponGetRecord = new MemberCouponGetRecord();
            memberCouponGetRecord.setCouponId(memberCouponInfo.getId());
            memberCouponGetRecord.setCouponCode(memberCouponInfo.getCode());
            memberCouponGetRecord.setCode(this.getCode(memberCouponInfo.getSurplusCount()));
            if (Constants.COUPON_DICT_DATE_FIX_TIME_RANGE.equals(memberCouponInfo.getDateType())) {
                memberCouponGetRecord.setDateBegin(memberCouponInfo.getDateBegin());
                memberCouponGetRecord.setDateEnd(memberCouponInfo.getDateEnd());
            } else if (Constants.COUPON_DICT_DATE_FIX_TERM.equals(memberCouponInfo.getDateType())) {
                memberCouponGetRecord.setDateBegin(DateUtil.addDate(memberCouponInfo.getDateBeginNum(), false));
                memberCouponGetRecord.setDateEnd(DateUtil.addDate(memberCouponInfo.getDateBeginNum()
                        + memberCouponInfo.getDateNum(), false));
            }
            memberCouponGetRecord.setStatus(Constants.COUPON_RECORD_STATUS_OK);
            memberCouponGetRecordMapper.insertSelective(memberCouponGetRecord);

            // 修改优惠券剩余数量
            memberCouponInfo.setSurplusCount(memberCouponInfo.getSurplusCount() - 1);
            memberCouponInfoMapper.updateByPrimaryKey(memberCouponInfo);

            // 优惠券领取成功返回数据
            CouponGetVo couponGetVo = new CouponGetVo();
            couponGetVo.setCard_id(memberCouponGetRecord.getCouponCode());
            couponGetVo.setCode(memberCouponGetRecord.getCode());
            couponGetVo.setDate_begin(memberCouponGetRecord.getDateBegin());
            couponGetVo.setDate_end(memberCouponGetRecord.getDateEnd());
            return new ApiResponseEntity(SysErrorCode.SUCCESS.getError_code(), couponGetVo);
        } catch (Exception e) {
            logger.error("【CouponGetServiceImplements.getCoupon】领取优惠券异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 用户领取优惠券
     * @Params: [card_ids]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/7 11:07
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = AppException.class)
    public ApiResponseEntity getCoupon(List<CouponGetPojo> couponGetPojos) throws AppException {
        try {
            List<CouponGetVo> list = new ArrayList<CouponGetVo>();
            for (CouponGetPojo couponGetPojo : couponGetPojos) {
                for (int i = 0; i < couponGetPojo.getCount(); i++) {
                    list.add((CouponGetVo) this.getCoupon(couponGetPojo.getCard_id()).getData());
                }
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS.getError_code(), list);
        } catch (AppException e) {
            logger.error("【CouponGetServiceImplements.getCoupon】领取优惠券异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【CouponGetServiceImplements.getCoupon】领取优惠券异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 核销优惠券
     * @Params: [code]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/3 16:18
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = AppException.class)
    public ApiResponseEntity consumeCoupon(String code) throws AppException {
        try {
            MemberCouponGetRecordExample memberCouponGetRecordExample = new MemberCouponGetRecordExample();
            memberCouponGetRecordExample.createCriteria().andCodeEqualTo(code);
            memberCouponGetRecordExample.createCriteria().andStatusEqualTo(Constants.COUPON_RECORD_STATUS_OK);
            // 根据查询条件核销优惠券
            MemberCouponGetRecord memberCouponGetRecord = new MemberCouponGetRecord();
            memberCouponGetRecord.setStatus(Constants.COUPON_RECORD_STATUS_CONSUMED);
            memberCouponGetRecordMapper.updateByExampleSelective(memberCouponGetRecord, memberCouponGetRecordExample);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【CouponGetServiceImplements.consumeCoupon】核销优惠券异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取优惠券code
     * @Params: []
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/11/2 13:51
     */
    private String getCode(Integer surplusCount) {
        lock.lock();// 得到锁
        try {
            String date = DateUtil.formatDate(new Date(), "ddHHmmss");
            return new StringBuilder(date).append(String.format("%06d", surplusCount)).toString();
        } finally {
            lock.unlock();// 释放锁
        }
    }
}
