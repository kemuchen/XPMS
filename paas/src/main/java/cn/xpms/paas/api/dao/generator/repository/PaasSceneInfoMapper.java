package cn.xpms.paas.api.dao.generator.repository;

import cn.xpms.paas.api.dao.generator.entity.PaasSceneInfo;
import cn.xpms.paas.api.dao.generator.entity.PaasSceneInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface PaasSceneInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_info
     *
     * @mbg.generated Tue Dec 15 14:22:15 CST 2020
     */
    long countByExample(PaasSceneInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_info
     *
     * @mbg.generated Tue Dec 15 14:22:15 CST 2020
     */
    int deleteByExample(PaasSceneInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_info
     *
     * @mbg.generated Tue Dec 15 14:22:15 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_info
     *
     * @mbg.generated Tue Dec 15 14:22:15 CST 2020
     */
    int insert(PaasSceneInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_info
     *
     * @mbg.generated Tue Dec 15 14:22:15 CST 2020
     */
    int insertSelective(PaasSceneInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_info
     *
     * @mbg.generated Tue Dec 15 14:22:15 CST 2020
     */
    List<PaasSceneInfo> selectByExampleWithRowbounds(PaasSceneInfoExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_info
     *
     * @mbg.generated Tue Dec 15 14:22:15 CST 2020
     */
    List<PaasSceneInfo> selectByExample(PaasSceneInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_info
     *
     * @mbg.generated Tue Dec 15 14:22:15 CST 2020
     */
    PaasSceneInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_info
     *
     * @mbg.generated Tue Dec 15 14:22:15 CST 2020
     */
    int updateByExampleSelective(@Param("record") PaasSceneInfo record, @Param("example") PaasSceneInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_info
     *
     * @mbg.generated Tue Dec 15 14:22:15 CST 2020
     */
    int updateByExample(@Param("record") PaasSceneInfo record, @Param("example") PaasSceneInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_info
     *
     * @mbg.generated Tue Dec 15 14:22:15 CST 2020
     */
    int updateByPrimaryKeySelective(PaasSceneInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paas_scene_info
     *
     * @mbg.generated Tue Dec 15 14:22:15 CST 2020
     */
    int updateByPrimaryKey(PaasSceneInfo record);
}