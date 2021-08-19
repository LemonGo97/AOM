package cn.lemongo97.aom.controller;

import cn.lemongo97.aom.config.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @ResponseResult
    @GetMapping("/hello")
    public String getLog(){
        return "Hello World!";
    }

}
