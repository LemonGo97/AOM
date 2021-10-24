package cn.lemongo97.aom.service;

import cn.lemongo97.aom.common.PageInfo;
import cn.lemongo97.aom.model.application.ApplicationPO;
import org.springframework.data.domain.Page;

public interface ApplicationService {
    Page<ApplicationPO> list(String type, PageInfo pageInfo);
}
