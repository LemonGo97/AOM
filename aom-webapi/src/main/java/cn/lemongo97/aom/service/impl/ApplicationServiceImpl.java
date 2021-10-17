package cn.lemongo97.aom.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.lemongo97.aom.common.PageInfo;
import cn.lemongo97.aom.common.Platform;
import cn.lemongo97.aom.model.application.ApplicationPO;
import cn.lemongo97.aom.service.ApplicationService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author LemonGo97
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Override
    public Object list(String type, PageInfo pageInfo) {
        ArrayList<ApplicationPO> content = Lists.newArrayList(
//                ApplicationPO.builder().name("MySQL").packageName("mysql-server_5.7-1ubuntu21.04_amd64.deb-bundle.tar").version("5.7").systemType("Ubuntu Linux 21.04").platform(Platform.LINUX_X64).updateTime("2021-10-16 10:00:00").changeLog("这里是更新日志").filePath("/data/mysql/1.tgz").build(),
//                ApplicationPO.builder().name("MySQL").packageName("mysql-server_8.0.25-1ubuntu21.04_amd64.deb-bundle.tar").version("8.0.25").systemType("Ubuntu Linux 21.04").platform(Platform.LINUX_X64).updateTime("2021-10-16 10:00:00").changeLog("这里是更新日志").filePath("/data/mysql/1.tgz").build(),
//                ApplicationPO.builder().name("MySQL").packageName("mysql-server_8.0.26-1ubuntu21.04_amd64.deb-bundle.tar").version("8.0.26").systemType("Ubuntu Linux 21.04").platform(Platform.LINUX_X64).updateTime("2021-10-16 10:00:00").changeLog("这里是更新日志").filePath("/data/mysql/1.tgz").build()
        );
        return MapUtil.builder()
                .put("totalElements", content.size())
                .put("content", content)
                .build();
    }
}
