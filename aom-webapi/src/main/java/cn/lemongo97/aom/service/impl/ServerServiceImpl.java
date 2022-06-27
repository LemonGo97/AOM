package cn.lemongo97.aom.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.lemongo97.aom.common.SystemType;
import cn.lemongo97.aom.mapper.ServerMapper;
import cn.lemongo97.aom.model.LoginUser;
import cn.lemongo97.aom.model.server.ServerDTO;
import cn.lemongo97.aom.model.server.ServerPO;
import cn.lemongo97.aom.service.ServerService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * @author lemongo97
 */
@Service
public class ServerServiceImpl implements ServerService {

    @Autowired
    private ServerMapper serverMapper;


    @Override
    public IPage<ServerPO> listServers(ServerDTO server, Page<ServerPO> pageInfo) {
        return serverMapper.selectPage(pageInfo, null);
    }

    @Override
    public void save(ServerPO server) {
        takeOperateMessage(server,true);
        serverMapper.insert(server);
    }

    @Override
    public void remove(String uuid) {
        serverMapper.deleteById(uuid);
    }

    @Override
    public void modify(ServerPO server) {
        takeOperateMessage(server,false);
        serverMapper.updateById(server);
    }

    @Override
    public Collection<Map<Object, Object>> listSystemType() {
        return SystemType.getAllSystemType();
    }

    @Override
    public Optional<ServerPO> findById(String uuid) {
        return Optional.of(serverMapper.selectById(uuid));
    }

    private void takeOperateMessage(ServerPO server, boolean isCreate){
        Date now = DateUtil.dateSecond().toJdkDate();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        if (isCreate){
            server.setCreateTime(now);
        }
        server.setUpdateTime(now);
        server.setOperatorId(loginUser.getUser().getId());
    }
}
