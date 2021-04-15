package cn.xpms.member.integral.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
@Api(tags = "积分信息新增接口")
@RequestMapping("/api/member/integra/")
public class IntegralMoldPutController {

	/**
	 * 积分操作服务类
	 */
	@Autowired
	IntegraInfoServiceInterface integraInfoServer;

	@ApiOperation("1.添加用户积分信息(创建会员时调用).")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "group_id", value = "集团ID", required = true, paramType = "path", dataType = "Integer", defaultValue = "0"),
			@ApiImplicitParam(name = "hotel_id", value = "酒店ID", required = true, paramType = "path", dataType = "Integer", defaultValue = "0"),
			@ApiImplicitParam(name = "guest_base_id", value = "人员基础信息ID", required = true, paramType = "query", dataType = "Integer", defaultValue = "0") })
	@PutMapping("put/{group_id}/{hotel_id}")
	public ApiResponseEntity putIntegraNumber(@PathVariable(value = "group_id") Integer group_id,
			@PathVariable(value = "hotel_id") Integer hotel_id, Integer guest_base_id) throws AppException {
		return integraInfoServer.putIntegraNumber(group_id, guest_base_id);
	}
}
