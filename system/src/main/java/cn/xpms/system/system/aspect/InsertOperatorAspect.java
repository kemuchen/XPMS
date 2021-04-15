package cn.xpms.system.system.aspect;

import cn.xpms.system.framework.util.sys.BeanUtil;
import cn.xpms.system.system.util.SysUserUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName OperatorSetAspect
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/4 11:44
 * @Version 1.0
 */
@Aspect
@Component
public class InsertOperatorAspect {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(InsertOperatorAspect.class);

    /**
     * @Description: 数据库新增切点
     * @Params: []
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/4 11:57
     */
    @Pointcut("execution(public * cn.xpms..*.dao..*Mapper.insert*(..))")
    public void insertExecute() {
    }

    /**
     * @Description: 新增之前织入
     * @Params: [joinPoint]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/4 11:59
     */
    @Before("insertExecute()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        try {
            Integer operator = SysUserUtil.getOperateUserId();
            Object bean = joinPoint.getArgs()[0];
            // 设置创建人, 修改人
            BeanUtil.setFieldValueByName("createUser", bean, operator, Integer.class);
            BeanUtil.setFieldValueByName("modifyUser", bean, operator, Integer.class);
        } catch (Exception e) {
            logger.error("【InsertOperatorAspect.doBefore】新增设置操作人修改人异常");
        }
    }
}