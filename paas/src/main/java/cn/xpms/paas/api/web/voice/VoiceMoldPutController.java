package cn.xpms.paas.api.web.voice;

import cn.xpms.paas.api.dao.generator.entity.PaasVoiceRoomRelation;
import cn.xpms.paas.api.dao.generator.entity.PaasVoiceThirdPartyAuthorization;
import cn.xpms.paas.api.service.business.inter.voice.VoiceServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName VoiceMoldPutController
 * @Desc
 * @Author ycj
 * @Date 2020-12-21 11:33
 * @Version 1.0
 */
@RestController
@Api(tags = "语音")
@RequestMapping("/api/paas/voice/")
public class VoiceMoldPutController {

    @Autowired
    VoiceServiceInterface voiceServiceInterface;

    @ApiOperation("1.同步语音品牌列表")
    @PutMapping("brands/synchronize")
    public ApiResponseEntity synchronizeVoiceBrands() throws AppException {
        return voiceServiceInterface.synchronizeVoiceBrands();
    }

    @ApiOperation("2.同步项目房间语音设备关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "project_id", value = "项目project_id", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @PutMapping("relations/synchronize/{project_id}")
    public ApiResponseEntity synchronizeRoomRelations(@PathVariable("project_id") String project_id) throws AppException {
        return voiceServiceInterface.synchronizeRoomRelations(project_id);
    }

    @ApiOperation("3.修改房间语音绑定")
    @PutMapping("relation")
    public ApiResponseEntity modifyVoiceRoomRelation(@RequestBody PaasVoiceRoomRelation roomRelation) throws AppException {
        return voiceServiceInterface.modifyVoiceRoomRelation(roomRelation);
    }

    @ApiOperation("4.设置三方语音开放平台授权凭证")
    @PutMapping("thirdParty/authorization")
    public ApiResponseEntity thirdPartyAuthorization(@RequestBody PaasVoiceThirdPartyAuthorization authorization) throws AppException {
        return voiceServiceInterface.thirdPartyAuthorization(authorization);
    }

}
