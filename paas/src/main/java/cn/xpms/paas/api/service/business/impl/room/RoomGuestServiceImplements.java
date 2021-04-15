package cn.xpms.paas.api.service.business.impl.room;

import cn.xpms.paas.api.bean.constant.CustomAutomationTypeEnum;
import cn.xpms.paas.api.dao.generator.entity.PaasRoomGuest;
import cn.xpms.paas.api.dao.generator.entity.PaasRoomGuestExample;
import cn.xpms.paas.api.dao.generator.entity.PaasRoomInfo;
import cn.xpms.paas.api.dao.generator.entity.PaasRoomInfoExample;
import cn.xpms.paas.api.dao.generator.repository.PaasRoomGuestMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasRoomInfoMapper;
import cn.xpms.paas.api.service.business.inter.automation.CustomAutomationServiceInterface;
import cn.xpms.paas.api.service.business.inter.room.RoomGuestServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoomGuestServiceImplements
 * @Desc
 * @Author ycj
 * @Date 2021-01-18 16:41
 * @Version 1.0
 */
@Service
public class RoomGuestServiceImplements implements RoomGuestServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(RoomGuestServiceImplements.class);


    @Autowired
    PaasRoomGuestMapper roomGuestMapper;

    @Autowired
    PaasRoomInfoMapper roomInfoMapper;

    @Autowired
    CustomAutomationServiceInterface customAutomationService;

    @Override
    public ApiResponseEntity checkIn(PaasRoomGuest roomGuest) throws AppException {
        try {
            if (StringUtils.isBlank(roomGuest.getRoomId())) {
                if (StringUtils.isBlank(roomGuest.getRoomNo())) {
                    throw new AppException(SysErrorCode.PARAMS_CHECK_ERROR);
                }
                PaasRoomInfoExample roomInfoExample = new PaasRoomInfoExample();
                roomInfoExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andRoomNoEqualTo(roomGuest.getRoomNo());
                List<PaasRoomInfo> roomInfos = roomInfoMapper.selectByExample(roomInfoExample);
                if (null == roomInfos || roomInfos.size() < 1) {
                    throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "未查询到对应房间", "未查询到对应房间");
                }
                roomGuest.setRoomId(roomInfos.get(0).getRoomId());
            }
            roomGuestMapper.insertSelective(roomGuest);

            customAutomationService.triggerCustomAutomation(CustomAutomationTypeEnum.CHECK_IN.toString(), roomGuest.getRoomId());

            return new ApiResponseEntity(SysErrorCode.SUCCESS, roomGuest);
        } catch (Exception e) {
            logger.error("【RoomGuestServiceImplements.checkIn】客人入住异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getCheckInGuestDetailById(Integer id) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, roomGuestMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("【RoomGuestServiceImplements.getCheckInGuestDetailById】查询客人入住信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getCheckInGuestDetailByOrderNo(String orderNo) throws AppException {
        try {
            PaasRoomGuestExample example = new PaasRoomGuestExample();
            example.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andOrderNoEqualTo(orderNo);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, roomGuestMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【RoomGuestServiceImplements.getCheckInGuestDetailByOrderNo】查询客人入住信息异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity checkOut(Integer id) throws AppException {
        try {
            PaasRoomGuest roomGuest = roomGuestMapper.selectByPrimaryKey(id);
            customAutomationService.triggerCustomAutomation(CustomAutomationTypeEnum.CHECK_OUT.toString(), roomGuest.getRoomId());

            PaasRoomGuest guest = new PaasRoomGuest();
            guest.setId(id);
            guest.setIsCheckout(SystemConstants.INT_YES);
            roomGuestMapper.updateByPrimaryKeySelective(guest);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【RoomGuestServiceImplements.checkOut】客人退房异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity checkOut(String orderNo) throws AppException {
        try {
            PaasRoomGuestExample example = new PaasRoomGuestExample();
            example.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andOrderNoEqualTo(orderNo);
            List<PaasRoomGuest> guestList = roomGuestMapper.selectByExample(example);
            if (null == guestList || guestList.size() < 1) {
                throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR.getError_code(), "未查询到对应记录", "未查询到对应记录");
            }

            PaasRoomGuest guest = guestList.get(0);

            customAutomationService.triggerCustomAutomation(CustomAutomationTypeEnum.CHECK_OUT.toString(), guest.getRoomId());

            PaasRoomGuest record = new PaasRoomGuest();
            record.setId(guest.getId());
            record.setIsCheckout(SystemConstants.INT_YES);
            roomGuestMapper.updateByPrimaryKeySelective(record);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【RoomGuestServiceImplements.checkOut】客人退房异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
