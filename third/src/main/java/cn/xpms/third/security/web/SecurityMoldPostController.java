package cn.xpms.third.security.web;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityUploadInfo;
import cn.xpms.third.security.service.inter.BusinessSecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SecurityMoldPostController
 * @Desc
 * @Author ycj
 * @Date 2020-11-12 11:36
 * @Version 1.0
 */
@RestController
@Api(tags = "公安网信息上传接口")
@RequestMapping("/api/security/")
public class SecurityMoldPostController {

    /** 公安网业务处理service */
    @Autowired
    BusinessSecurityService businessSecurityService;

    /**
     * @Description: 入住上传公安网接口
     * @Params: [uploadInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/13 9:18
     */
    @ApiOperation("1.入住上传公安网")
    @PostMapping("checkInUpload")
    public ApiResponseEntity checkInUploadGuest(@RequestBody PublicSecurityUploadInfo uploadInfo) throws AppException {
        return businessSecurityService.checkInUpload(uploadInfo);
    }

    /**
     * @Description: 退房上传公安网接口
     * @Params: [uploadInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/13 9:18
     */
    @ApiOperation("2.退房上传公安网")
    @PostMapping("checkOutUpload}")
    public ApiResponseEntity checkOutUploadGuest(@RequestBody PublicSecurityUploadInfo uploadInfo) throws AppException {
        return businessSecurityService.checkOutUpload(uploadInfo);
    }
}
