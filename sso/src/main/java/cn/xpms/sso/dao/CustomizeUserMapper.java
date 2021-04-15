package cn.xpms.sso.dao;

import cn.xpms.system.system.dao.generator.entity.SysSubsysConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomizeUserSubSysMapper
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/25 13:49
 * @Version 1.0
 */
@Repository
public interface CustomizeUserMapper {

    /**
     * @Description: 查询用户可以操作的酒店
     * @Params:  [user_id]
     * @return:  List<Map<String, Object>>
     * @Author: ycj
     * @Date: 2020-12-15 10:18
     */
    @Select("SELECT h.* from v_hotel_info h ,sys_user_hotel uh WHERE h.id=uh.hotel_id AND h.valid=1 AND uh.valid=1 AND uh.operate=1 AND uh.user_id=${user_id}")
    List<Map<String, Object>> getUserHotels(@Param(value = "user_id") Integer user_id);

    /**
     * @Description: 查询用户可以授权的酒店
     * @Params:  [user_id]
     * @return:  List<Map<String, Object>>
     * @Author: ycj
     * @Date: 2020-12-15 10:18
     */
    @Select("SELECT h.* from v_hotel_info h ,sys_user_hotel uh WHERE h.id=uh.hotel_id AND h.valid=1 AND uh.valid=1 AND uh.auth=1 AND uh.user_id=${user_id}")
    List<Map<String, Object>> getUserHotelsAuth(@Param(value = "user_id") Integer user_id);

    /**
     * @Description: 查询用户对应酒店可以操作的子系统
     * @Params:  [user_id,hotel_id]
     * @return:  List<Map<String, Object>>
     * @Author: ycj
     * @Date: 2020-12-15 10:18
     */
    @Select("SELECT s.id, s.sub_system_code subSystemCode, s.sub_system_name subSystemName, s.url, s.href " +
            "from sys_subsys_config s, sys_user_hotel_system u " +
            "where s.id=u.system_id and s.valid=1 and u.valid=1 and u.user_id=${user_id} and u.hotel_id=#{hotel_id}")
    List<SysSubsysConfig> getUserSubSystem(@Param("user_id") Integer user_id, @Param("hotel_id") Integer hotel_id);
}