package cn.xpms.member.integral.service.inter;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

public interface IntegraInfoServiceInterface {

	ApiResponseEntity queryIntegraNumber(Integer group_id, Integer guest_base_id) throws AppException;

	ApiResponseEntity putIntegraNumber(Integer group_id, Integer guest_base_id) throws AppException;

}
