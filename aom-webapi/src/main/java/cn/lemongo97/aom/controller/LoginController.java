package cn.lemongo97.aom.controller;


import cn.lemongo97.aom.config.ResponseResult;
import cn.lemongo97.aom.config.Result;
import cn.lemongo97.aom.model.dto.SysUserDTO;
import cn.lemongo97.aom.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@ResponseResult
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody SysUserDTO user) throws JsonProcessingException {
        return loginService.login(user.getUsername(),user.getPassword());
    }

    @PostMapping("/logout")
    public Result logout() {
        return loginService.logout();
    }

    @GetMapping("/info")
    public Map<String, Object> loginUserInfo(){
        return loginService.getPermissionInfo();
    }
}
