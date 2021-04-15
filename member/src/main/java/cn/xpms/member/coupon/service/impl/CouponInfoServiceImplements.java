package cn.xpms.member.coupon.service.impl;

import cn.xpms.member.common.constant.Constants;
import cn.xpms.member.coupon.dao.generator.entity.MemberCouponInfo;
import cn.xpms.member.coupon.dao.generator.entity.MemberCouponInfoExample;
import cn.xpms.member.coupon.dao.generator.repository.MemberCouponInfoMapper;
import cn.xpms.member.coupon.service.inter.CouponInfoServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.util.sys.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @ClassName CouponInfoServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/2 12:01
 * @Version 1.0
 */
@Service
public class CouponInfoServiceImplements implements CouponInfoServiceInterface {

    /** 打印日志对象 */
    Logger logger = LoggerFactory.getLogger(CouponInfoServiceImplements.class);

    /** 优惠券信息查询 */
    @Autowired
    MemberCouponInfoMapper memberCouponInfoMapper;

    /**
     * @Description: 创建优惠券
     * @Params: [memberCouponInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/3 16:19
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = AppException.class)
    public ApiResponseEntity createCouponInfo(MemberCouponInfo memberCouponInfo) throws AppException {
        try {
            // 设置优惠券code
            memberCouponInfo.setCode(UUID.randomUUID().toString().replaceAll("-", ""));
            // 设置优惠券状态为-OK
            memberCouponInfo.setStatus(Constants.COUPON_STATUS_OK);
            memberCouponInfo.setSurplusCount(memberCouponInfo.getTotalCount());
            memberCouponInfoMapper.insertSelective(memberCouponInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS.getError_code(), memberCouponInfo.getCode());
        } catch (Exception e) {
            logger.error("【CouponInfoServiceImplements.createCouponInfo】创建优惠券异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修改优惠券
     * @Params: [memberCouponInfo, memberCouponInfoExample]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/3 16:19
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = AppException.class)
    public ApiResponseEntity modifyCouponInfo(MemberCouponInfo memberCouponInfo) throws AppException {
        try {
            // 设置更新条件未根据code更新
            MemberCouponInfoExample memberCouponInfoExample = new MemberCouponInfoExample();
            memberCouponInfoExample.createCriteria().andCodeEqualTo(memberCouponInfo.getCode());
            memberCouponInfoMapper.updateByExample(memberCouponInfo, memberCouponInfoExample);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【CouponInfoServiceImplements.modifyCouponInfo】修改优惠券异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询优惠券列表
     * @Params: [memberCouponInfoExample]
     *
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/3 16:20
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = AppException.class)
    public ApiResponseEntity getCouponInfos(MemberCouponInfoExample memberCouponInfoExample) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS.getError_code(),
                    memberCouponInfoMapper.selectByExample(memberCouponInfoExample));
        } catch (Exception e) {
            logger.error("【CouponInfoServiceImplements.getCouponInfos】查询优惠券列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询优惠券列表
     * @Params: [memberCouponInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/3 16:23
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = AppException.class)
    public ApiResponseEntity getCouponInfos(MemberCouponInfo memberCouponInfo) throws AppException {
        try {
            MemberCouponInfoExample memberCouponInfoExample = new MemberCouponInfoExample();
            memberCouponInfoExample.createCriteria();
            BeanUtil.beanToExample(memberCouponInfo, memberCouponInfoExample.getOredCriteria().get(0));
            return this.getCouponInfos(memberCouponInfoExample);
        } catch (AppException e) {
            logger.error("【CouponInfoServiceImplements.getCouponInfos】查询优惠券列表异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【CouponInfoServiceImplements.getCouponInfos】查询优惠券列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
