package cn.lemongo97.aom.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public final class JsonUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public static <T> String toJson(T obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T fromJson(String jsonStr, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(jsonStr, clazz);
    }

    public static <T> List<T> fromJson2List(String jsonStr) throws JsonProcessingException {
        return objectMapper.readValue(jsonStr, new TypeReference<List<T>>() {
        });
    }

    public static <T, E> Map<T, E> fromJson2Map(String jsonStr) throws JsonProcessingException {
        return objectMapper.readValue(jsonStr, new TypeReference<Map<T, E>>() {
        });
    }
}