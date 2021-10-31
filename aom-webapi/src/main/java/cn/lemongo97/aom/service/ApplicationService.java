package cn.lemongo97.aom.service;

import cn.lemongo97.aom.common.PageInfo;
import cn.lemongo97.aom.model.application.ApplicationVersionPO;
import org.springframework.data.domain.Page;

public interface ApplicationService {
    Page<ApplicationVersionPO> list(String type, PageInfo pageInfo);
}
