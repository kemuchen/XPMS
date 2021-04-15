package cn.xpms.paas.api.dao.customize.repository;

import cn.xpms.paas.api.bean.vo.template.PositionInfraredDeviceVo;
import cn.xpms.paas.api.bean.vo.template.PositionSplitDeviceVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomizeCategoryMapper
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/3 11:51
 * @Version 1.0
 */
@Repository
public interface CustomizeCategoryMapper {
    
    /** 
     * @Description: 查询施工支持的设备品类类型
     * @Params: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: 柯雷
     * @Date: 2020/12/3 11:55
     */ 
    @Select("SELECT DISTINCT type `value`, type `name` FROM `paas_device_category`")
    List<Map<String, Object>> getDeviceCategoryType();

    /**
     * @Description: 查询施工支持的设备品类
     * @Params: [type]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: 柯雷
     * @Date: 2020/12/3 11:57
     */
    @Select("SELECT DISTINCT t.category_name `name`,c.category `value` " +
            "FROM `paas_device_category` c LEFT JOIN `paas_device_category_type` t on c.category=t.category " +
            "where type = #{type}")
    List<Map<String, Object>> getDeviceCategory(String type);

    /**
     * @Description: 查询施工模板安装位置红外设备信息
     * @Params: [position_id]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: 柯雷
     * @Date: 2020/12/5 8:54
     */
    @Select("SELECT " +
            " a.*, " +
            " b.category_name, " +
            " b.brand_name  " +
            "FROM " +
            " paas_construction_template_infrared_device a, " +
            " v_infrared_category_brand_remote b  " +
            "WHERE " +
            " a.category_id = b.category_id  " +
            " AND a.brand_id = b.brand_id  " +
            " AND a.remote = b.remote" +
            " AND a.position_id = #{position_id}")
    List<PositionInfraredDeviceVo> getTemplateInfraredCategoryBrandRemote(String position_id);

    /**
     * @Description: 查询施工模板安装位置拆分设备信息
     * @Params: [position_id]
     * @return: java.util.List<cn.xpms.paas.api.bean.vo.category.PositionInfraredDeviceVo>
     * @Author: 柯雷
     * @Date: 2020/12/5 9:20
     */
    @Select("SELECT  " +
            "  a.*,  " +
            "  b.category_name   " +
            "FROM  " +
            "  paas_construction_template_split_device a,  " +
            "  paas_split_device_category b   " +
            "WHERE  " +
            "  a.category_code = b.category_code   " +
            "  AND a.valid = '1'   " +
            "  AND b.valid = '1'" +
            "  AND a.position_id = #{position_id}")
    List<PositionSplitDeviceVo> getTemplateSplitDevice(String position_id);
}