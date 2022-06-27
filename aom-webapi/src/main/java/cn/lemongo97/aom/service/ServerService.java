package cn.lemongo97.aom.service;

import cn.lemongo97.aom.model.application.ApplicationVersionPO;
import cn.lemongo97.aom.model.server.ServerDTO;
import cn.lemongo97.aom.model.server.ServerPO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * @author lemongo97
 */
public interface ServerService {
    IPage<ServerPO> listServers(ServerDTO server, Page<ServerPO> pageInfo);

    void save(ServerPO server);

    void remove(String uuid);

    Optional<ServerPO> findById(String uuid);

    void modify(ServerPO server);

    Collection<Map<Object, Object>> listSystemType();

}
