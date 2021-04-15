package cn.xpms.paas.api.web.voice;

import cn.xpms.paas.api.dao.generator.entity.PaasVoiceRoomRelation;
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
 * @ClassName VoiceMoldDeleteController
 * @Desc
 * @Author ycj
 * @Date 2020-12-21 14:34
 * @Version 1.0
 */
@RestController
@Api(tags = "语音")
@RequestMapping("/api/paas/voice/")
public class VoiceMoldDeleteController {

    @Autowired
    VoiceServiceInterface voiceServiceInterface;

    @ApiOperation("1.删除房间语音设备绑定")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "绑定记录ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("relation/{id}")
    public ApiResponseEntity addVoiceRoomRelation(@PathVariable("id") Integer id) throws AppException {
        return voiceServiceInterface.deleteVoiceRoomRelation(id);
    }

    @ApiOperation("2.清除三方语音开放平台授权凭证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "授权记录ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("thirdParty/authorization/{id}")
    public ApiResponseEntity deleteThirdPartyAuthorization(@PathVariable("id") Integer id) throws AppException {
        return voiceServiceInterface.deleteThirdPartyAuthorization(id);
    }
}
