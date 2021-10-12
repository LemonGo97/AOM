package cn.lemongo97.aom.service;

import cn.lemongo97.aom.common.PageInfo;
import cn.lemongo97.aom.model.server.ServerDTO;
import cn.lemongo97.aom.model.server.ServerPO;
import org.springframework.data.domain.Page;

/**
 * @author lemongo97
 */
public interface ServerService {
    Page<ServerPO> listServers(ServerDTO server, PageInfo pageInfo);

    void save(ServerPO server);

    void remove(String uuid);
}
