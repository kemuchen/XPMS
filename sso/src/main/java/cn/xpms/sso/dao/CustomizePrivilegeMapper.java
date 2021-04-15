package cn.xpms.sso.dao;

import cn.xpms.system.system.dao.generator.entity.SysRight;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName CustomizePrivilegeMapper
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/25 16:17
 * @Version 1.0
 */
@Repository
public interface CustomizePrivilegeMapper {

    /**
     * @Description: 查询用户拥有的路由操作权限
     * @Params: [user_id, hotel_id, system_id]
     * @return: java.util.List<cn.xpms.system.system.dao.generator.entity.SysRight>
     * @Author: ycj
     * @Date: 2020-12-09 15:41
     */
    @Select("SELECT " +
            " id, " +
            " sub_sys_id subSysId, " +
            " NAME, " +
            " type, " +
            " parent_id parentId, " +
            " url, " +
            " authed, " +
            " sort_no  " +
            "FROM " +
            " v_user_system_right " +
            "WHERE " +
            " user_id = ${user_id}  " +
            " AND hotel_id = ${hotel_id} " +
            " AND sub_sys_id = ${system_id}  " +
            " AND operate = '1' ")
    List<SysRight> getUserRightRouter(@Param(value = "user_id") Integer user_id,
                                      @Param(value = "hotel_id") Integer hotel_id,
                                      @Param(value = "system_id") Integer system_id);

    /**
     * @Description: 查询用户拥有的菜单操作权限
     * @Params: [user_id, sub_sys_id, sub_sys_info_id]
     * @return: java.util.List<cn.xpms.system.system.dao.generator.entity.SysRight>
     * @Author: ycj
     * @Date: 2020-12-09 15:45
     */
    @Select("SELECT " +
            " id, " +
            " sub_sys_id subSysId, " +
            " NAME, " +
            " type, " +
            " parent_id parentId, " +
            " url, " +
            " authed, " +
            " sort_no  " +
            "FROM " +
            " v_user_system_right " +
            "WHERE " +
            " user_id = ${user_id}  " +
            " AND hotel_id = ${hotel_id}  " +
            " AND sub_sys_id = ${system_id} " +
            " AND operate = '1'" +
            " AND type = '1'" +
            " AND parent_id = ${parent_id} ")
    List<SysRight> getUserRightMenu(@Param(value = "user_id") Integer user_id,
                                    @Param(value = "hotel_id") Integer hotel_id,
                                    @Param(value = "system_id") Integer system_id,
                                    @Param(value = "parent_id") Integer parent_id);
}