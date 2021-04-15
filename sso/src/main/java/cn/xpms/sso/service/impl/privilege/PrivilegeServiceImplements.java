package cn.xpms.sso.service.impl.privilege;

import cn.xpms.sso.bean.vo.SysMenuVo;
import cn.xpms.sso.bean.vo.SysRightVo;
import cn.xpms.sso.dao.CustomizePrivilegeMapper;
import cn.xpms.sso.service.inter.privilege.PrivilegeServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.system.system.dao.generator.entity.SysRight;
import cn.xpms.system.system.dao.generator.entity.SysRightExample;
import cn.xpms.system.system.dao.generator.repository.SysRightMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BusinessPrivilegeServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/25 16:02
 * @Version 1.0
 */
@Service
public class PrivilegeServiceImplements implements PrivilegeServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(PrivilegeServiceImplements.class);

    /**
     * 自定义权限查询CURD
     */
    @Autowired
    CustomizePrivilegeMapper customizePrivilegeMapper;

    /**
     * 菜单CURD
     */
    @Autowired
    SysRightMapper sysRightMapper;

    /**
     * @Description: 添加菜单
     * @Params: [right]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @Author: 柯雷
     * @Date: 2020/11/26 9:09
     */
    private SysMenuVo addMenu(SysRight right) throws AppException {
        try {
            SysMenuVo menu = new SysMenuVo();
            menu.setName(right.getName());
            menu.setPath(right.getUrl());

            // 遍历子菜单
            List<SysRight> childRights = child(right.getId());
            if (!SysUtil.isEmpty(childRights)) {
                List<SysMenuVo> childs = new ArrayList<>();
                for (SysRight child : childRights) {
                    // 递归调用生成子菜单
                    childs.add(addMenu(child));
                }
                menu.setRoutes(childs);
            }
            return menu;
        } catch (AppException e) {
            logger.error("【PrivilegeServiceImplements.addMenu】添加菜单异常" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【PrivilegeServiceImplements.addMenu】添加菜单异常" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询子菜单
     * @Params: [right_id]
     * @return: boolean
     * @Author: 柯雷
     * @Date: 2020/11/26 9:01
     */
    private List<SysRight> child(Integer right_id) throws AppException {
        try {
            SysRightExample sysRightExample = new SysRightExample();
            sysRightExample.createCriteria().andParentIdEqualTo(right_id);
            return sysRightMapper.selectByExample(sysRightExample);
        } catch (Exception e) {
            logger.error("【PrivilegeServiceImplements.child】查询子菜单异常" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }


    /**
     * @Description: 查询用户拥有的子系统菜单路由
     * @Params: [user_id, hotel_id, system_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-09 15:40
     */
    @Override
    public ApiResponseEntity getUserSystemRightOperate(Integer user_id, Integer hotel_id, Integer system_id) throws AppException {
        try {
            // 查询路由权限
            SysRightVo sysRightVo = new SysRightVo();
            // 路由权限
            sysRightVo.setAuthRouters(customizePrivilegeMapper.getUserRightRouter(user_id, hotel_id, system_id));
            //查询菜单权限
            List<SysRight> rights = customizePrivilegeMapper.getUserRightMenu(user_id, hotel_id, system_id, SystemConstants.INT_NO);
            List<SysMenuVo> menus = new ArrayList<>();
            for (SysRight right : rights) {
                menus.add(addMenu(right));
            }
            sysRightVo.setMenus(menus);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, sysRightVo);
        } catch (Exception e) {
            logger.error("【PrivilegeServiceImplements.getUserSubSysRight】查询用户拥有的操作权限异常" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
