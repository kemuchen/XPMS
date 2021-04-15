package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.bean.dto.automation.AutomationPo;
import cn.xpms.paas.api.bean.dto.scene.ScenePo;
import cn.xpms.paas.api.service.business.inter.automation.AutomationServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AutomationMoldPostController
 * @Desc
 * @Author ycj
 * @Date 2020-12-16 17:26
 * @Version 1.0
 */
@RestController
@Api(tags = "自动化")
@RequestMapping("/api/paas/automation/")
public class AutomationMoldPostController {

    @Autowired
    AutomationServiceInterface automationServiceInterface;

    @ApiOperation("1.新增自动化")
    @PostMapping(value = "automation", produces = "application/json")
    public ApiResponseEntity addAutomation(@RequestBody AutomationPo automationPo) throws AppException {
        return automationServiceInterface.addAutomation(automationPo);
    }

}
