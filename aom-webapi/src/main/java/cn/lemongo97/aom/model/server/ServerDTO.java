package cn.lemongo97.aom.model.server;

import cn.lemongo97.aom.common.PageInfo;

/**
 * @author lemongo97
 */
public class ServerDTO extends ServerPO {
    private PageInfo pageInfo;

    public PageInfo getPageInfo() {
        return pageInfo == null ? PageInfo.create() : pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
