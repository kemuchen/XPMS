package cn.xpms.paas.api.dao.customize.repository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomizeProjectMapper
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/1 10:14
 * @Version 1.0
 */
@Repository
public interface CustomizeProjectMapper {

    /**
     * @Description: 查询用户项目绑定信息
     * @Params: [user_id]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: 柯雷
     * @Date: 2020/12/1 10:22
     */
    @Select("select * from v_paas_project_info where hotelId = ${hotelId}")
    List<Map<String, Object>> getProjectDetail(@Param(value = "hotelId") Integer hotelId);

    @Select("select * from v_paas_project_info where projectId = ${projectId} limit 1")
    Map<String, Object> getProjectDetailByProject(@Param(value = "projectId") String projectId);
}