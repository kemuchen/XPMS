package cn.xpms.system.framework.beans.pojo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName BeanFactory
 * @Desc
 * @Author 柯雷
 * @Date 2019/10/20 17:14
 * @Version 1.0
 */
@Component
public class BeanFactory implements ApplicationContextAware {

    /**
     * spring上下文
     */
    private static ApplicationContext applicationContext;

    /**
     * @Description: 设置spring上下文
     * @Params: [applicationContext]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-05-09 15:12
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (BeanFactory.applicationContext == null) {
            BeanFactory.applicationContext = applicationContext;
        }
    }

    public static void setApplication(ApplicationContext application) {
        applicationContext = application;
    }

    // 通过name获取 Bean.
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    // 通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /** 
     * @Description: 
     * @Params: [source]
     * @return: java.lang.String[]
     * @Author: 柯雷
     * @Date: 2020/11/3 10:01
     */ 
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * @Description: 拷贝忽略null
     * @Params: [src, target]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-07-01 15:03
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
}
