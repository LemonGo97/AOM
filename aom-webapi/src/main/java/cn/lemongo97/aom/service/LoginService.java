package cn.lemongo97.aom.service;

import cn.lemongo97.aom.config.Result;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface LoginService {
    Result login(String username, String password) throws JsonProcessingException;

    Result logout();

    Map<String, Object> getPermissionInfo();

}