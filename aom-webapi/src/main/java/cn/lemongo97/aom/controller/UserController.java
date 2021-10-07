package cn.lemongo97.aom.controller;

import cn.hutool.core.map.MapUtil;
import cn.lemongo97.aom.config.ResponseResult;
import cn.lemongo97.aom.config.Result;
import cn.lemongo97.aom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LemonGo97
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseResult
    @PostMapping("/log")
    public String getLog() {
        return "Hello World!";
    }

    @GetMapping("/info")
    public Result getInfo() {
        return Result.success(MapUtil.builder()
                .put("name", "admin")
                .put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif").build());
    }

}
