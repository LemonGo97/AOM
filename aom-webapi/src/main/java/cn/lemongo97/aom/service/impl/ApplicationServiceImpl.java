package cn.lemongo97.aom.service.impl;

import cn.lemongo97.aom.common.PageInfo;
import cn.lemongo97.aom.model.application.ApplicationPO;
import cn.lemongo97.aom.repository.ApplicationJpaRepository;
import cn.lemongo97.aom.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author LemonGo97
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationJpaRepository applicationJpaRepository;

    @Override
    public Page<ApplicationPO> list(String type, PageInfo pageInfo) {
        return applicationJpaRepository.queryByNameOrderByPackageNameDesc(type, pageInfo.getPageable());
    }
}
