package cn.lemongo97.aom.service;

import cn.lemongo97.aom.common.PageInfo;

public interface ApplicationService {
    Object list(String type, PageInfo pageInfo);
}
