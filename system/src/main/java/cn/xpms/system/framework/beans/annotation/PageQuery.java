package cn.xpms.system.framework.beans.annotation;

import cn.xpms.system.framework.constant.SystemConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分页查询
 *
 * @ClassName PageQuery
 * @Desc
 * @Author 柯雷
 * @Date 2020/1/16 11:16
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PageQuery {

    /***
     * @Description: 页面大小
     * @Params: []
     * @return: int
     * @Author: 柯雷
     * @Date: 2020/1/16 11:18
     */
    int pageSize() default 10;

    /***
     * @Description: 当前页
     * @Params: []
     * @return: int
     * @Author: 柯雷
     * @Date: 2020/1/16 11:18
     */
    int current() default 1;
}
