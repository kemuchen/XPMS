package cn.xpms.member.integral.dao.inter;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberInfoMapper {

	/**
	 * 查询用户积分
	 * 
	 * @param guest_base_id
	 * @return
	 */
	@Select("SELECT integral FROM member_info WHERE guest_base_id = {#guest_base_id}")
	public List<Map<String, Object>> queryIntegraNumber(Integer guest_base_id);
	
	@Insert("")
	public void insetIntegra(Map<String, Object> data);
	
}
