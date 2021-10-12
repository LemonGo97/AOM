package cn.lemongo97.aom.service.impl;

import cn.lemongo97.aom.common.PageInfo;
import cn.lemongo97.aom.model.server.ServerDTO;
import cn.lemongo97.aom.model.server.ServerPO;
import cn.lemongo97.aom.repository.ServerJpaRepository;
import cn.lemongo97.aom.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lemongo97
 */
@Service
public class ServerServiceImpl implements ServerService {

    @Autowired
    private ServerJpaRepository serverJpaRepository;

    @Override
    public Page<ServerPO> listServers(ServerDTO server, PageInfo pageInfo) {
        return serverJpaRepository.findAll(pageInfo.getPageable());
    }

    @Override
    public void save(ServerPO server) {
        serverJpaRepository.save(server);
    }
}
