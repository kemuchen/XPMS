package cn.xpms.paas.api.dao.customize.repository;

import cn.xpms.paas.api.bean.dto.device.PaasDeviceQuery;
import cn.xpms.paas.api.dao.generator.entity.PaasDeviceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomizeDeviceMapper
 * @Desc
 * @Author ycj
 * @Date 2021-01-14 14:13
 * @Version 1.0
 */
@Repository
public interface CustomizeDeviceMapper {

    List<Map<String, Object>> getDeviceInfos(PaasDeviceQuery paasDeviceQuery);
}
