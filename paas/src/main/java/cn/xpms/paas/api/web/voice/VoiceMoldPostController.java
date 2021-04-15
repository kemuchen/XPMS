package cn.xpms.paas.api.web.voice;

import cn.xpms.paas.api.dao.generator.entity.PaasVoiceRoomRelation;
import cn.xpms.paas.api.service.business.inter.voice.VoiceServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
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
public class VoiceMoldPostController {

    @Autowired
    VoiceServiceInterface voiceServiceInterface;

    @ApiOperation("1.添加房间语音绑定")
    @PostMapping("relation")
    public ApiResponseEntity addVoiceRoomRelation(@RequestBody PaasVoiceRoomRelation roomRelation) throws AppException {
        return voiceServiceInterface.addVoiceRoomRelation(roomRelation);
    }
}
