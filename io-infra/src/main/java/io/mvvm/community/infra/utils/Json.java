package io.mvvm.community.infra.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * Json 转换工具
 *
 * @author: Pan
 **/
@Slf4j
public abstract class Json {

    private static final ObjectMapper MAPPER = new ObjectMapper();
//    private static final ObjectMapper MAPPER = SpringContextHolder.getBean(ObjectMapper.class);

    static {
        // 忽略未定义字段
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.registerModule(new JavaTimeModule());
    }

    /**
     * 对象转JSON
     * @param t     对象
     * @param <T>   类型
     * @return      JSON
     */
    public static <T> String toJsonString(T t) {
        try {
            return MAPPER.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            log.warn("对象转JSON异常: [ {} ]", e.getMessage());
        }
        return Convert.EMPTY;
    }

    /**
     * JSON转对象
     * @param json  JSON
     * @param clazz class
     * @param <T>   类型
     * @return      T
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            log.warn("JSON转对象异常: [ {} ]", e.getMessage());
        }
        return null;
    }

    /**
     * JSON 转集合
     * @param json  JSON
     * @param clazz class
     * @param <T>   类型
     * @return      T
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            log.warn("JSON转集合异常: [ {} ]", e.getMessage());
        }
        return null;
    }

    /**
     * JSON 转 Map
     * @param json  JSON
     * @param ks    KEY     类型
     * @param vs    VALUE   类型
     * @param <K>   KEY     类型
     * @param <V>   VALUE   类型
     * @return      {@link Map >}
     */
    public static <K, V> Map<K, V> parseMap(String json, Class<K> ks, Class<V> vs) {
        JavaType javaType = MAPPER.getTypeFactory().constructMapType(Map.class, ks, vs);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            log.warn("JSON转Map异常: [ {} ]", e.getMessage());
        }
        return null;
    }

    /**
     * 将value转换为指定类型
     * @param value Object
     * @param clazz Class
     * @param <T>   Class<T>
     * @return      T
     */
    public static <T> T convertValue(Object value, Class<T> clazz) {
        return value == null ? null : MAPPER.convertValue(value, clazz);
    }

}
