package cn.xpms.paas.api.dao.customize.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomizeVoiceMapper
 * @Desc
 * @Author ycj
 * @Date 2020-12-22 14:28
 * @Version 1.0
 */
@Repository
public interface CustomizeVoiceMapper {

    List<Map<String, Object>> getVoiceRoomRelations(@Param("projectId") String projectId,
                                                    @Param("roomId") String roomId);

    List<Map<String, Object>> getVoiceThirdPartyAuth(@Param("projectId") String projectId,
                                                    @Param("brandId") String brandId);
}
