package io.mvvm.community.infra.model;

import io.mvvm.community.infra.utils.Convert;
import io.mvvm.community.infra.utils.Json;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Pan
 **/
public final class KVBuilder<T> {

    private final Map<String, T> data;

    public KVBuilder() {
        data = new HashMap<>();
    }

    public static <T> KVBuilder<T> builder() {
        return new KVBuilder<>();
    }

    public KVBuilder<T> append(String key, T value) {
        data.put(key, value);
        return this;
    }

    public KVBuilder<T> remove(String key) {
        data.remove(key);
        return this;
    }

    public String getString(String key) {
        return Convert.parseString(data.get(key));
    }

    public String asJson() {
        return Json.toJsonString(data);
    }

    public Map<String, T> get() {
        return this.data;
    }

}