package cn.lemongo97.aom.controller;

import cn.lemongo97.aom.common.PageInfo;
import cn.lemongo97.aom.config.ResponseResult;
import cn.lemongo97.aom.model.server.ServerDTO;
import cn.lemongo97.aom.model.server.ServerPO;
import cn.lemongo97.aom.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @DeleteMapping("/server/{uuid}")
    public void delServer(@PathVariable("uuid")String uuid){
        serverService.remove(uuid);
    }

    @PutMapping("/server")
    public void modifyServer(@RequestBody ServerPO server){
        serverService.modify(server);
    }

    @GetMapping("/server/{uuid}")
    public Optional<ServerPO> getServer(@PathVariable("uuid")String uuid){
       return serverService.findById(uuid);
    }
}
