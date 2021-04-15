package cn.xpms.paas.api.bean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName PaasApiErrorCode
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 10:05
 * @Version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PaasApiPageData {

    String field_name();
}