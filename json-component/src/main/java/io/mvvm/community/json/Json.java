package io.mvvm.community.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.Reader;
import java.util.Collection;
import java.util.Map;

/**
 * @description: JSON Util
 * @author: pan
 **/
public class Json {

    private static final Gson gson;

    static {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    private Json() {
    }

    public static <T> String toJsonString(T t) {
        if (null == t) {
            return null;
        }
        return gson.toJson(t);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        if (null == json || "".equals(json)) {
            return null;
        }
        return gson.fromJson(json, clazz);
    }

    public static <K, V> Map<K, V> toMap(String json, Class<K> key, Class<V> value) {
        if (null == json || "".equals(json)) {
            return null;
        }
        return gson.fromJson(json, new TypeToken<Map<K, V>>() {
        }.getType());
    }

    public static <T> Collection<T> toList(String json, Class<T> clazz) {
        return gson.fromJson(json, new TypeToken<Collection<T>>() {
        }.getType());
    }


    public static JsonObject parse(String json) {
        return JsonParser.parseString(json).getAsJsonObject();
    }

    public static JsonArray parseArray(String json) {
        return JsonParser.parseString(json).getAsJsonArray();
    }

    public static JsonObject parse(Reader json) {
        return JsonParser.parseReader(json).getAsJsonObject();
    }

    public static JsonObject parse(JsonReader json) {
        return JsonParser.parseReader(json).getAsJsonObject();
    }
}