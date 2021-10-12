package cn.lemongo97.aom.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.lemongo97.aom.common.PageInfo;
import cn.lemongo97.aom.model.User;
import cn.lemongo97.aom.model.server.ServerDTO;
import cn.lemongo97.aom.model.server.ServerPO;
import cn.lemongo97.aom.repository.ServerJpaRepository;
import cn.lemongo97.aom.service.IAuthenticationFacade;
import cn.lemongo97.aom.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author lemongo97
 */
@Service
public class ServerServiceImpl implements ServerService {

    @Autowired
    private ServerJpaRepository serverJpaRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Override
    public Page<ServerPO> listServers(ServerDTO server, PageInfo pageInfo) {
        return serverJpaRepository.findAll(pageInfo.getPageable());
    }

    @Override
    public void save(ServerPO server) {
        Date now = DateUtil.dateSecond().toJdkDate();
        User currentUser = authenticationFacade.getSessionUser();
        server.setCreateTime(now);
        server.setUpdateTime(now);
        server.setOperator(currentUser);
        serverJpaRepository.save(server);
    }

    @Override
    public void remove(String uuid) {
        serverJpaRepository.deleteById(uuid);
    }
}
