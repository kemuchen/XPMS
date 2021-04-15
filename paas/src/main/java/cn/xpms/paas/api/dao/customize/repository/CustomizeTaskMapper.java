package cn.xpms.paas.api.dao.customize.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTask;
import cn.xpms.paas.api.dao.generator.entity.PaasMaintenanceTask;
import cn.xpms.paas.api.dao.generator.entity.PaasRoomInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomizeTaskMapper
 * @Desc 施工任务自定义
 * @Author 柯雷
 * @Date 2020/12/5 9:53
 * @Version 1.0
 */
@Repository
public interface CustomizeTaskMapper {

    /**
     * @Description: 查询施工任务房间信息列表
     * @Params: [task_id]
     * @return: java.util.List<cn.xpms.paas.api.dao.generator.entity.PaasRoomInfo>
     * @Author: 柯雷
     * @Date: 2020/12/5 9:57
     */
    @Select("SELECT  " +
            "  a.id,  " +
            "  a.project_id projectId,  " +
            "  a.room_id roomId,  " +
            "  a.room_name roomName,  " +
            "  a.telephone,  " +
            "  a.description,  " +
            "  a.space_id spaceId,  " +
            "  a.construction_status constructionStatus,  " +
            "  a.valid,  " +
            "  a.create_user createUser,  " +
            "  a.create_time createTime,  " +
            "  a.modify_user modifyUser,  " +
            "  a.modify_time modifyTime,  " +
            "  a.memo   " +
            "FROM  " +
            "  paas_room_info a,  " +
            "  paas_construction_task_room b   " +
            "WHERE  " +
            "  a.room_id = b.room_id   " +
            "  AND b.task_id = #{task_id}   " +
            "  AND a.valid = '1'   " +
            "  AND b.valid = '1'")
    List<PaasRoomInfo> getTaskRoomInfo(String task_id);

    /**
     * @Description: 查询施工任务列表
     * @Params: [task]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: 柯雷
     * @Date: 2020/12/7 11:38
     */
    @Select("<script> " +
            "  select * from v_construction_task_info " +
            "   where project_id = #{projectId}" +
            "    <if test='templateId != null'>" +
            "     and template_id = #{templateId}" +
            "    </if>" +
            "    <if test='workerId != null'>" +
            "     and worker_id = #{workerId}" +
            "    </if>" +
            "    <if test='status != null'>" +
            "     and status = #{status}" +
            "    </if>" +
            "</script>")
    List<Map<String, Object>> getConstructionTasks(PaasConstructionTask task);

    /**
     * @Description: 查询维修任务列表
     * @Params: [task]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: 柯雷
     * @Date: 2020/12/7 15:38
     */
    @Select("<script> " +
            "  select * from v_maintenance_task_info " +
            "   where project_id = #{projectId}" +
            "    <if test='workerId != null'>" +
            "     and worker_id = #{workerId}" +
            "    </if>" +
            "    <if test='status != null'>" +
            "     and status = #{status}" +
            "    </if>" +
            "</script>")
    List<Map<String, Object>> getMaintenanceTasks(PaasMaintenanceTask task);
}