package cn.xpms.paas.api.web.voice;

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
 * @ClassName VoiceMoldGetController
 * @Desc
 * @Author ycj
 * @Date 2020-12-21 14:33
 * @Version 1.0
 */
@RestController
@Api(tags = "语音")
@RequestMapping("/api/paas/voice/")
public class VoiceMoldGetController {

    @Autowired
    VoiceServiceInterface voiceServiceInterface;

    @ApiOperation("1.获取语音品牌列表")
    @GetMapping("brands")
    public ApiResponseEntity getVoiceBrands() throws AppException {
        return voiceServiceInterface.getVoiceBrands();
    }

    @ApiOperation("2.获取房间语音绑定关系列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目projectId", paramType = "query", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "roomId", value = "房间roomId", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @GetMapping("relations")
    public ApiResponseEntity addVoiceRoomRelation(String projectId, String roomId) throws AppException {
        return voiceServiceInterface.getVoiceRoomRelations(projectId, roomId);
    }

    @ApiOperation("3.获取三方语音平台授权列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目projectId", paramType = "query", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "brandId", value = "语音品牌brandId", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @GetMapping("thirdParty/authorizations")
    public ApiResponseEntity getVoiceThirdPartyAuthorizations(String projectId, String brandId) throws AppException {
        return voiceServiceInterface.getVoiceThirdPartyAuthorizations(projectId, brandId);
    }
}
