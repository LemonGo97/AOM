package cn.lemongo97.aom.controller;

import cn.lemongo97.aom.config.ResponseResult;
import cn.lemongo97.aom.model.application.ApplicationVersionPO;
import cn.lemongo97.aom.service.ApplicationService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author LemonGo97
 */
@ResponseResult
@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/applications/{type}/info")
    public String getInfo(@PathVariable(value = "type") String type){
        // todo 获取应用概述
        return null;
    }

    @GetMapping("/applications/{type}/changeLog")
    public String changeLog(@PathVariable(value = "type") String type){
        // todo 获取应用更新日志
        return null;
    }

    @GetMapping("/applications/{type}/version")
    public IPage<ApplicationVersionPO> listVersion(@PathVariable(value = "type") String type, Page<ApplicationVersionPO> pageInfo){
        return applicationService.listVersion(type,pageInfo);
    }

}
