package cn.xpms.third.security.consumer;

import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.queue.BaseConsumer;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.third.common.constant.Constants;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityUploadInfo;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityUploadRecord;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityUploadRecordExample;
import cn.xpms.third.security.dao.generator.repository.PublicSecurityUploadInfoMapper;
import cn.xpms.third.security.dao.generator.repository.PublicSecurityUploadRecordMapper;
import cn.xpms.third.security.service.inter.CommonSecurityService;
import cn.xpms.third.security.service.inter.SecurityRecordServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @ClassName CheckoutUploadConsumer
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/13 9:43
 * @Version 1.0
 */
@Component
public class CheckoutUploadConsumer extends BaseConsumer<Integer> {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(CheckoutUploadConsumer.class);

    /**
     * 上传记录service
     */
    @Autowired
    SecurityRecordServiceInterface securityRecordServiceInterface;

    /**
     * 公安网上传信息DAO
     */
    @Autowired
    PublicSecurityUploadInfoMapper publicSecurityUploadInfoMapper;

    /**
     * 公安网信息上传记录DAO
     */
    @Autowired
    PublicSecurityUploadRecordMapper publicSecurityUploadRecordMapper;

    /**
     * 公安网上传基础类
     */
    @Autowired
    CommonSecurityService commonSecurityService;

    /**
     * @Description: 退房信息上传
     * @Params: [data]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/13 14:44
     */
    @Override
    protected void handle(Integer data) throws AppException {
        try {
            // 查询公安网上传信息
            PublicSecurityUploadInfo publicSecurityUploadInfo = publicSecurityUploadInfoMapper.selectByPrimaryKey(data);
            commonSecurityService.checkOutUpload(publicSecurityUploadInfo);

            // 更新上传信息上传状态
            PublicSecurityUploadInfo updateInfo = new PublicSecurityUploadInfo();
            updateInfo.setId(data);
            updateInfo.setCheckoutUpload(Constants.PUBLIC_SECURITY_UPLOAD_SUCCESS_RESULT);
            securityRecordServiceInterface.saveUpInfo(updateInfo);

            // 保存上传记录
            publicSecurityUploadInfo.setCheckoutUpload(Constants.PUBLIC_SECURITY_UPLOAD_SUCCESS_RESULT);
            securityRecordServiceInterface.saveRecord(Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKOUT,
                    publicSecurityUploadInfo);

            // 回调
            securityRecordServiceInterface.notifyUploadResult(Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKOUT,
                    publicSecurityUploadInfo);
        } catch (AppException e) {
            logger.error("【CheckoutUploadConsumer.handle】公安网上传异常，数据：" + data + "；异常信息：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【CheckoutUploadConsumer.handle】公安网上传异常，数据：" + data + "；异常信息：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 处理上传失败
     * @Params: [data]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/13 14:44
     */
    @Override
    protected void handleError(Integer data) {
        try {
            // 查询公安网上传信息
            PublicSecurityUploadInfo info = publicSecurityUploadInfoMapper.selectByPrimaryKey(data);

            // 更新上传信息上传状态
            PublicSecurityUploadInfo updateInfo = new PublicSecurityUploadInfo();
            updateInfo.setId(data);
            updateInfo.setCheckoutUpload(Constants.PUBLIC_SECURITY_UPLOAD_FAIL_RESULT);
            securityRecordServiceInterface.saveUpInfo(updateInfo);

            // 保存上传记录
            info.setCheckoutUpload(Constants.PUBLIC_SECURITY_UPLOAD_FAIL_RESULT);
            securityRecordServiceInterface.saveRecord(Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKOUT, info);

            // 查询上传次数
            PublicSecurityUploadRecordExample recordExample = new PublicSecurityUploadRecordExample();
            recordExample.createCriteria().andInfoIdEqualTo(data)
                    .andUploadTypeEqualTo(Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKOUT)
                    .andValidEqualTo(SystemConstants.INT_YES);
            List<PublicSecurityUploadRecord> recordList = publicSecurityUploadRecordMapper.selectByExample(recordExample);
            if (!SysUtil.isEmpty(recordList)) {
                if (recordList.get(0).getUploadTimes() == Constants.PUBLIC_SECURITY_UPLOAD_DEFAULT_TIMES) {
                    // 达到最大上传次数则回调
                    securityRecordServiceInterface.notifyUploadResult(Constants.
                            PUBLIC_SECURITY_UPLOAD_TYPE_CHECKOUT, info);
                    return;
                }
            }
        } catch (Exception e) {
            logger.error("【CheckoutUploadConsumer.handleError】公安网上传异常，数据：" + data + "；异常信息：" + e);
        } finally {
            // 查询上传次数
            PublicSecurityUploadRecordExample recordExample = new PublicSecurityUploadRecordExample();
            recordExample.createCriteria().andInfoIdEqualTo(data)
                    .andUploadTypeEqualTo(Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKOUT)
                    .andValidEqualTo(SystemConstants.INT_YES);
            List<PublicSecurityUploadRecord> recordList = publicSecurityUploadRecordMapper.selectByExample(recordExample);

            if (!SysUtil.isEmpty(recordList)) {
                PublicSecurityUploadRecord record = recordList.get(0);
                if (record.getUploadTimes() < Constants.PUBLIC_SECURITY_UPLOAD_DEFAULT_TIMES &&
                        Constants.PUBLIC_SECURITY_UPLOAD_FAIL_RESULT.equals(record.getUploadResult())) {
                    // 加入队列等待再次处理
                    this.offer(data);
                }
            }
        }
    }
}
