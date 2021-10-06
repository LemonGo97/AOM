package cn.lemongo97.aom.controller;

import cn.lemongo97.aom.config.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @ResponseResult
    @PostMapping("/log")
    public String getLog(){
        return "Hello World!";
    }

}
