package cn.xpms.paas.api.service.business.impl.room;

import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.constant.RoomTaskStatusEnum;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.dao.customize.repository.CustomizeRoomMapper;
import cn.xpms.paas.api.dao.generator.entity.PaasRoomInfo;
import cn.xpms.paas.api.dao.generator.entity.PaasRoomInfoExample;
import cn.xpms.paas.api.dao.generator.repository.PaasRoomInfoMapper;
import cn.xpms.paas.api.service.business.inter.room.RoomServiceInterface;
import cn.xpms.paas.api.service.common.inter.api.PaasApiServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RoomServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/1 15:01
 * @Version 1.0
 */
@Service

public class RoomServiceImplements implements RoomServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(RoomServiceImplements.class);

    /**
     * 涂鸦API接口service
     */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    /**
     * 房间信息CURD
     */
    @Autowired
    PaasRoomInfoMapper paasRoomInfoMapper;

    @Autowired
    CustomizeRoomMapper customizeRoomMapper;

    /**
     * @Description: 新增房间信息
     * @Params: [paasRoomInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 15:34
     */
    @Override
    public ApiResponseEntity createRoom(PaasRoomInfo paasRoomInfo) throws AppException {
        try {
            // 调用涂鸦接口新增项目
            PaasApiResponseEntity<String> apiResponseEntity = paasApiServiceInterface.call(PaasApiEnum.ADD_ROOM,
                    BeanUtil.beanToMap(paasRoomInfo, true));
            paasRoomInfo.setRoomId(apiResponseEntity.getResult());
            paasRoomInfo.setConstructionStatus(RoomTaskStatusEnum.UNAUTHORIZED.toString());
            // 新增数据库记录
            paasRoomInfoMapper.insertSelective(paasRoomInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, paasRoomInfo);
        } catch (AppException e) {
            logger.error("【RoomServiceImplements.createRoom】新增房间信息异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【RoomServiceImplements.createRoom】新增房间信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取房间列表
     * @Params: [paasRoomInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 15:38
     */
    @Override
    public ApiResponseEntity getRooms(PaasRoomInfo paasRoomInfo) throws AppException {
        try {
            PaasRoomInfoExample example = new PaasRoomInfoExample();
            paasRoomInfo.setValid(SystemConstants.INT_YES);
            BeanUtil.beanToExample(paasRoomInfo, example);
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    paasRoomInfoMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【RoomServiceImplements.getRooms】获取房间列表异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取房间详细信息
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 15:39
     */
    @Override
    public ApiResponseEntity getRoomDetail(Integer id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS,
                    paasRoomInfoMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("【RoomServiceImplements.getRoomDetail】获取房间详细信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 修改房间信息
     * @Params: [paasRoomInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 15:40
     */
    @Override
    public ApiResponseEntity modifyRoom(PaasRoomInfo paasRoomInfo) throws AppException {
        try {
            // 调用涂鸦接口新增项目
            paasApiServiceInterface.call(PaasApiEnum.MODIFY_ROOM,
                    BeanUtil.beanToMap(paasRoomInfo, true));

            // 更新数据库记录
            paasRoomInfoMapper.updateByPrimaryKeySelective(paasRoomInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【RoomServiceImplements.modifyRoom】修改房间信息异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【RoomServiceImplements.modifyRoom】修改房间信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除房间
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 15:43
     */
    @Override
    public ApiResponseEntity deleteRoom(Integer id) throws AppException {
        try {
            PaasRoomInfo paasRoomInfo = paasRoomInfoMapper.selectByPrimaryKey(id);
            // 调用涂鸦接口新增项目
            paasApiServiceInterface.call(PaasApiEnum.DELETE_ROOM,
                    BeanUtil.beanToMap(paasRoomInfo, true));

            // 更新数据库记录
            paasRoomInfo.setValid(SystemConstants.INT_NO);
            paasRoomInfoMapper.updateByPrimaryKeySelective(paasRoomInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【RoomServiceImplements.deleteRoom】删除房间异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【RoomServiceImplements.deleteRoom】删除房间异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取房间状态信息
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 15:45
     */
    @Override
    public ApiResponseEntity getRoomStatus(Integer id) throws AppException {
        try {
            PaasRoomInfo paasRoomInfo = paasRoomInfoMapper.selectByPrimaryKey(id);
            // 调用涂鸦接口新增项目
            PaasApiResponseEntity<String> apiResponseEntity = paasApiServiceInterface.call(PaasApiEnum.ROOM_CONSTRUCTION_STATUS,
                    BeanUtil.beanToMap(paasRoomInfo, true));

            // 更新数据库记录
            paasRoomInfo.setConstructionStatus(apiResponseEntity.getResult());
            paasRoomInfoMapper.updateByPrimaryKeySelective(paasRoomInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, paasRoomInfo);
        } catch (AppException e) {
            logger.error("【RoomServiceImplements.getRoomStatus】获取房间状态信息异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【RoomServiceImplements.getRoomStatus】获取房间状态信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询房态
     * @Params: PaasRoomInfo
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-12 10:34
     */
    @Override
    public ApiResponseEntity getRoomsStatus(PaasRoomInfo roomInfo) throws AppException {
        try {
            List<Map<String, Object>> list = customizeRoomMapper.getProjectDetail(roomInfo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("【RoomServiceImplements.getRoomsStatus】查询房态异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
