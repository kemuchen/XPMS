package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasMaintenanceTaskRoomDevice;
import cn.xpms.paas.api.dao.generator.entity.PaasMaintenanceTaskRoomDeviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasMaintenanceTaskRoomDeviceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_maintenance_task_room_device
     *
     * @mbg.generated Mon Dec 07 11:59:51 CST 2020
     */
    long countByExample(PaasMaintenanceTaskRoomDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_maintenance_task_room_device
     *
     * @mbg.generated Mon Dec 07 11:59:51 CST 2020
     */
    int deleteByExample(PaasMaintenanceTaskRoomDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_maintenance_task_room_device
     *
     * @mbg.generated Mon Dec 07 11:59:51 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_maintenance_task_room_device
     *
     * @mbg.generated Mon Dec 07 11:59:51 CST 2020
     */
    int insert(PaasMaintenanceTaskRoomDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_maintenance_task_room_device
     *
     * @mbg.generated Mon Dec 07 11:59:51 CST 2020
     */
    int insertSelective(PaasMaintenanceTaskRoomDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_maintenance_task_room_device
     *
     * @mbg.generated Mon Dec 07 11:59:51 CST 2020
     */
    List<PaasMaintenanceTaskRoomDevice> selectByExampleWithRowbounds(PaasMaintenanceTaskRoomDeviceExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_maintenance_task_room_device
     *
     * @mbg.generated Mon Dec 07 11:59:51 CST 2020
     */
    List<PaasMaintenanceTaskRoomDevice> selectByExample(PaasMaintenanceTaskRoomDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_maintenance_task_room_device
     *
     * @mbg.generated Mon Dec 07 11:59:51 CST 2020
     */
    PaasMaintenanceTaskRoomDevice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_maintenance_task_room_device
     *
     * @mbg.generated Mon Dec 07 11:59:51 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasMaintenanceTaskRoomDevice record, @Param("example") PaasMaintenanceTaskRoomDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_maintenance_task_room_device
     *
     * @mbg.generated Mon Dec 07 11:59:51 CST 2020
     */
    int updateByExample(@Param("record") PaasMaintenanceTaskRoomDevice record, @Param("example") PaasMaintenanceTaskRoomDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_maintenance_task_room_device
     *
     * @mbg.generated Mon Dec 07 11:59:51 CST 2020
     */
    int updateByPrimaryKeySelective(PaasMaintenanceTaskRoomDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_maintenance_task_room_device
     *
     * @mbg.generated Mon Dec 07 11:59:51 CST 2020
     */
    int updateByPrimaryKey(PaasMaintenanceTaskRoomDevice record);
}