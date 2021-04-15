package cn.xpms.system.framework.util.sys;

import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.system.dao.generator.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName BeanUtil
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/3 17:08
 * @Version 1.0
 */
public class BeanUtil {

    /** 日志打印对象 */
    static Logger logger = LoggerFactory.getLogger(BeanUtil.class);


    /**
     * 驼峰转下划线
     */
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     */
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 获取属性名数组
     */
    public static String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for(int i=0;i<fields.length;i++){
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 获取属性名类型
     */
    public static Class getFiledTypeByName(Object o, String fieldName){
        Field[] fields=o.getClass().getDeclaredFields();
        for(int i = 0; i < fields.length; i++){
            if (fieldName.equals(fields[i].getName())) {
                return fields[i].getType();
            }
        }
        return null;
    }

    /**
     * 根据属性名获取属性值
     * */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            logger.error("【BeanUtil.getFieldValueByName】获取属性值异常：" + e);
            return null;
        }
    }

    /**
     * @Description: 设置属性值
     * @Params: [fieldName, o, value, type]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/3 17:25
     */
    public static void setFieldValueByName(String fieldName, Object o, Object value, Class type) throws Exception {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "set" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] { type });
            method.invoke(o, new Object[] { value });
        } catch (Exception e) {
            logger.error("【BeanUtil.getFieldValueByName】获取属性值异常：" + e);
            throw e;
        }
    }

    /**
     * @Description: 设置example值
     * @Params: [fieldName, o, value, type]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/3 17:31
     */
    public static void setExampleValueByName(String fieldName, Object o, Object value, Class type) throws Exception {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "and" + firstLetter + fieldName.substring(1) + "EqualTo";
            Method method = o.getClass().getMethod(getter, new Class[] { type });
            method.invoke(o, new Object[] { value });
        } catch (Exception e) {
            logger.error("【BeanUtil.getFieldValueByName】获取属性值异常：" + e);
            throw e;
        }
    }

    /**
     * @Description:  DAO bean转换为example
     * @Params: [o]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/3 17:25
     */
    public static void beanToExample(Object bean, Object example) throws AppException {
        try {
            Field[] fields = bean.getClass().getDeclaredFields();

            // 创建比较对象
            Method createCriteria = example.getClass().getMethod("createCriteria", new Class[] { });
            createCriteria.invoke(example, new Object[] { });

            // 获取比较对象
            Method getOredCriteria = example.getClass().getMethod("getOredCriteria", new Class[] { });
            List<Object> criteria = (List<Object>) getOredCriteria.invoke(example, new Object[] { });

            // 遍历所有属性
            for (Field field : fields) {
                if (!SystemConstants.DEFAULT_SERIAL_VERSION_UID.equals(field.getName())) {
                    Object value = getFieldValueByName(field.getName(), bean);
                    if (!SysUtil.isEmpty(value)) {
                        setExampleValueByName(field.getName(), criteria.get(0), value, field.getType());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("【BeanUtil.beanToExample】DAO bean转换为example异常：" + e);
            throw new AppException(SysErrorCode.SERVICE_CHECK_ERROR);
        }
    }

    /**
     * @Description: Entity转Map
     * @Params: [entity, humpToLine]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: 柯雷
     * @Date: 2020/11/30 9:52
     */
    public static <T> Map<String, Object> beanToMap(T bean, boolean humpToLine) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 获取Entity所有method，遍历method得到field
            String[] fieldNames = getFiledName(bean);
            for (String fielddName : fieldNames) {
                if (!SystemConstants.DEFAULT_SERIAL_VERSION_UID.equals(fielddName)) {
                    Object fieldValue = getFieldValueByName(fielddName, bean);
                    if (SysUtil.isEmpty(fieldValue)) {
                        continue;
                    }
                    if (humpToLine) {
                        // 驼峰转下划线
                        resultMap.put(humpToLine(fielddName), fieldValue);
                    } else {
                        resultMap.put(fielddName, fieldValue);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("【EntityMapUtil.entityToMap】Entity转Map出错：" + e);
        }
        return resultMap;
    }

    /**
     * @Description: Map转Entity
     * @Params: [map]
     * @return: cn.xpms.system.framework.beans.entity.BaseEntity
     * @Author: 柯雷
     * @Date: 2020/3/5 9:46
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<? extends T> clazz, boolean lineToHump) {
        T bean = null;
        try {
            bean = clazz.newInstance();

            // 获取Entity所有method，遍历method得到field
            Set<String> keys = map.keySet();
            for (String key : keys) {
                if (SysUtil.isEmpty(map.get(key))) {
                    continue;
                }
                String fielddName;
                if (lineToHump) {
                    fielddName = lineToHump(key);
                } else {
                    fielddName = key;
                }
                setFieldValueByName(lineToHump(key), bean, map.get(key), getFiledTypeByName(bean, fielddName));
            }
        } catch (Exception e) {
            logger.error("【EntityMapUtil.mapToEntity】Map转Entity出错:" + e);
        }
        return bean;
    }

    /**
     * @Description: 驼峰转下划线
     * @Params: [str]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/3/5 10:37
     */
    public static String humpToLine(String str, boolean... firstIsLowerCase) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(stringBuffer);
        String string = stringBuffer.toString();
        if (firstIsLowerCase.length != 0 && firstIsLowerCase[0]) {
            string = string.substring(0, 1).toLowerCase() + string.substring(1);
        }
        return string;
    }

    /**
     * @Description: 下划线转驼峰
     * @Params: [str]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/3/5 10:38
     */
    public static String lineToHump(String str, boolean... firstIsUpperCase) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(stringBuffer);
        String string = stringBuffer.toString();
        if (firstIsUpperCase.length != 0 && firstIsUpperCase[0]) {
            string = string.substring(0, 1).toUpperCase() + string.substring(1);
        }
        return string;
    }
}
