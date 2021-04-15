package cn.xpms.member.integral.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xpms.member.integral.service.inter.IntegraInfoServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "积分信息修改接口")
@RequestMapping("/api/member/integra/")
public class IntegralMoldPostController {
	
	/**
	 * 积分操作服务类
	 */
	@Autowired
	IntegraInfoServiceInterface integraInfoServer;

	@ApiOperation("1.积分变更(增加,消耗).")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "group_id", value = "集团ID", required = true, paramType = "path", dataType = "Integer", defaultValue = "0"),
			@ApiImplicitParam(name = "hotel_id", value = "酒店ID", required = true, paramType = "path", dataType = "Integer", defaultValue = "0"),
			@ApiImplicitParam(name = "guest_base_id", value = "人员基础信息ID", required = true, paramType = "query", dataType = "Integer", defaultValue = "0"),
			@ApiImplicitParam(name = "money", value = "变动金额", required = false, paramType = "query", dataType = "string", defaultValue = "0.0"),
			@ApiImplicitParam(name = "consume", value = "消耗积分", required = false, paramType = "query", dataType = "string", defaultValue = "0.0")
	})
	@PostMapping("post/{group_id}/{hotel_id}")
	public ApiResponseEntity getIntegraNumber(
			@PathVariable(value = "group_id") Integer group_id,
			@PathVariable(value = "hotel_id") Integer hotel_id,
			Integer guest_base_id,
			String money,
			String consume
			) throws AppException {
		return integraInfoServer.queryIntegraNumber(group_id, guest_base_id);
	}

}

