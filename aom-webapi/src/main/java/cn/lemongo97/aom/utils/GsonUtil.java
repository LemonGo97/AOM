package cn.lemongo97.aom.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author lemongo97
 */
public class GsonUtil {
    private static final Gson gson = new GsonBuilder().create();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <E> E fromJson(String json, Class<E> clazz) {
        return gson.fromJson(json, clazz);
    }

}
