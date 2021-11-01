package cn.lemongo97.aom.service;

import cn.lemongo97.aom.common.Application;
import cn.lemongo97.aom.model.application.ApplicationPO;
import cn.lemongo97.aom.model.application.ApplicationVersionPO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ApplicationService extends IService<ApplicationPO> {
    IPage<ApplicationVersionPO> listVersion(Application type, Page<ApplicationVersionPO> pageInfo);
}
