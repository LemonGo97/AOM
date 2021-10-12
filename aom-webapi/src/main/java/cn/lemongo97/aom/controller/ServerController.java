package cn.lemongo97.aom.controller;

import cn.lemongo97.aom.common.PageInfo;
import cn.lemongo97.aom.config.ResponseResult;
import cn.lemongo97.aom.model.server.ServerDTO;
import cn.lemongo97.aom.model.server.ServerPO;
import cn.lemongo97.aom.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author lemongo97
 */
@ResponseResult
@RestController
public class ServerController {

    @Autowired
    ServerService serverService;

    @GetMapping("/servers")
    public Page<ServerPO> listServers(ServerDTO server, PageInfo pageInfo){
        return serverService.listServers(server, pageInfo);
    }

    @PostMapping("/server")
    public void addServer(@RequestBody ServerPO server){
        serverService.save(server);
    }

}
