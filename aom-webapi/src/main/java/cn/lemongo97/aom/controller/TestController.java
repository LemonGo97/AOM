package cn.lemongo97.aom.controller;

import cn.lemongo97.aom.config.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    @GetMapping("/t_admin")
    public Result test01(){
        return Result.success("admin success");
    }

    @GetMapping("/t_user")
    public Result test02(){
        return Result.success("user success");
    }

}
