package cn.lemongo97.aom.service.impl;

import cn.lemongo97.aom.common.Application;
import cn.lemongo97.aom.mapper.ApplicationMapper;
import cn.lemongo97.aom.mapper.ApplicationVersionMapper;
import cn.lemongo97.aom.model.application.ApplicationPO;
import cn.lemongo97.aom.model.application.ApplicationVersionPO;
import cn.lemongo97.aom.service.ApplicationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LemonGo97
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, ApplicationPO> implements ApplicationService {

    @Autowired
    private ApplicationVersionMapper applicationVersionMapper;

    @Override
    public IPage<ApplicationVersionPO> listVersion(Application type, Page<ApplicationVersionPO> page) {
        return applicationVersionMapper.selectPage(page, new QueryWrapper<ApplicationVersionPO>().eq("application", type));
    }
}
