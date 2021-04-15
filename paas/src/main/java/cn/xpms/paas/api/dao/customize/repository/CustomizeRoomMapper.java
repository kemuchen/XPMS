package cn.xpms.paas.api.dao.customize.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasRoomInfo;
import cn.xpms.paas.api.dao.generator.entity.PaasSceneTemplate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomizeRoomMapper
 * @Desc
 * @Author ycj
 * @Date 2020-12-12 10:37
 * @Version 1.0
 */
@Repository
public interface CustomizeRoomMapper {

    List<Map<String, Object>> getProjectDetail(PaasRoomInfo roomInfo);

    List<PaasRoomInfo> getAvailableTemplateRooms(@Param("templateId") Integer templateId,
                                                       @Param("projectId") String projectId);

    List<Map<String, Object>> getTemplateRooms(@Param("templateId") Integer templateId,
                                                       @Param("projectId") String projectId);

    List<Map<String,Object>> getSceneTemplates(PaasSceneTemplate sceneTemplate);

}
