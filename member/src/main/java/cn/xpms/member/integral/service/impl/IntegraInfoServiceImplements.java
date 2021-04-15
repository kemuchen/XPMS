package cn.xpms.member.integral.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xpms.member.common.error.CouponErrorCode;
import cn.xpms.member.integral.dao.inter.MemberInfoMapper;
import cn.xpms.member.integral.service.inter.IntegraInfoServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;

@Service
public class IntegraInfoServiceImplements implements IntegraInfoServiceInterface {
	
	Logger logger = LoggerFactory.getLogger(IntegraInfoServiceImplements.class);
	
	@Autowired
	MemberInfoMapper memberInfoMapper;
	/**
	 * 积分查询
	 */
	@Override
	public ApiResponseEntity queryIntegraNumber(Integer group_id, Integer guest_base_id) throws AppException{
		try {
			//根据用户ID查询
			List<Map<String, Object>> data = memberInfoMapper.queryIntegraNumber(guest_base_id);
			if(data.size() == 0) {//判断是否存在数据
				logger.error("【IntegraInfoServiceImplements.queryIntegraNumber】查询用户积分无数据:" + "查询用户ID" + guest_base_id);
				throw new AppException(CouponErrorCode.INTEGRAL_SELECT_NULL);
			}
			return new ApiResponseEntity(SysErrorCode.SUCCESS.getError_code(), data);
		} catch (Exception e) {
			logger.error("【IntegraInfoServiceImplements.queryIntegraNumber】查询用户积分异常:" + e);
            throw new AppException(CouponErrorCode.INTEGRAL_SELECT_EXISTS);
		}
	}
	/**
	 * 添加用户积分信息
	 */
	@Override
	public ApiResponseEntity putIntegraNumber(Integer group_id, Integer guest_base_id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}
}
