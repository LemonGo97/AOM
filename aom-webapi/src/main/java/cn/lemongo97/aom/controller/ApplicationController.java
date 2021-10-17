package cn.lemongo97.aom.controller;

import cn.lemongo97.aom.common.PageInfo;
import cn.lemongo97.aom.config.ResponseResult;
import cn.lemongo97.aom.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LemonGo97
 */
@ResponseResult
@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/applications")
    public Object list(String type, PageInfo pageInfo){
        return applicationService.list(type,pageInfo);
    }

}
