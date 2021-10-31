package cn.lemongo97.aom.controller;

import cn.lemongo97.aom.config.ResponseResult;
import cn.lemongo97.aom.model.application.ApplicationVersionPO;
import cn.lemongo97.aom.service.ApplicationService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @GetMapping("/applications/version")
    public IPage<ApplicationVersionPO> listVersion(String type, Page<ApplicationVersionPO> pageInfo){
        return applicationService.listVersion(type,pageInfo);
    }

}
