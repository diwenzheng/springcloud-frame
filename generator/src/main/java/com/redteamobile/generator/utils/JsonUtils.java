package com.redteamobile.generator.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

public class JsonUtils {

    private static ObjectMapper om = new ObjectMapper();

    static {
        // 对象的所有字段全部列入，还是其他的选项，可以忽略null等
        om.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 忽略空Bean转json的错误
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 忽略未知属性，防止json字符串中存在，java对象中不存在对应属性的情况出现错误
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象 => json字符串
     *
     * @param obj 源对象
     */
    public static <T> String toJson(T obj) {
        String json = null;
        if (obj != null) {
            try {
                json = om.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        return json;
    }

    /**
     * json字符串 => 对象
     *
     * @param json 源json串
     * @param clazz 对象类
     * @param <T> 泛型
     */
    public static <T> T parse(String json, Class<T> clazz) {
        return parse(json, clazz, null);
    }

    /**
     * json字符串 => 对象
     *
     * @param json 源json串
     * @param type 对象类型
     * @param <T> 泛型
     */
    public static <T> T parse(String json, TypeReference type) {
        return parse(json, null, type);
    }


    /**
     * json => 对象处理方法
     * <br>
     * 参数clazz和type必须一个为null，另一个不为null
     * <br>
     * 此方法不对外暴露，访问权限为private
     *
     * @param json 源json串
     * @param clazz 对象类
     * @param type 对象类型
     * @param <T> 泛型
     */
    private static <T> T parse(String json, Class<T> clazz, TypeReference type) {
        T obj = null;
        if (!StringUtils.isEmpty(json)) {
            try {
                if (clazz != null) {
                    obj = om.readValue(json, clazz);
                } else {
                    obj = om.readValue(json, type);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        return obj;
    }

    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        String actionAsJsonStr = toJson(map);
        if (StringUtils.isEmpty(actionAsJsonStr)) {
            return null;
        }
        return parse(actionAsJsonStr, clazz);
    }

    public static Map<String, Object> beanToMap(Object bean) {
        if (bean == null) {
            return null;
        }
        if (bean instanceof Map)
        {
            return (Map<String, Object>) bean;
        }
        String actionResponseAsJsonStr = toJson(bean);
        if (StringUtils.isEmpty(actionResponseAsJsonStr)) {
            return null;
        }
        return parse(actionResponseAsJsonStr, new TypeReference<Map<String, Object>>(){});
    }
}
