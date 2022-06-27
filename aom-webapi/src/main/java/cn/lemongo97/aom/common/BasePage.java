package cn.lemongo97.aom.common;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * @author lipu
 */
public class BasePage<T> extends Page<T> {

    public Long getPageSize() {
        return this.size;
    }

    public void setPageSize(Long pageSize) {
        this.size = pageSize;
    }

    public Long getPageNum() {
        return this.current;
    }

    public void setPageNum(Integer pageNum) {
        this.current = pageNum;
    }

    public List<T> getList() {
        return super.getRecords();
    }

    @Override
    public Page<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @JsonIgnore
    @Override
    public long getSize() {
        return this.size;
    }

    @JsonIgnore
    @Override
    public long getCurrent() {
        return this.current;
    }


    @JsonIgnore
    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @JsonIgnore
    @Override
    public List<OrderItem> getOrders(){
        return super.getOrders();
    }

    @JsonIgnore
    @Override
    public long getPages() {
        return super.getPages();
    }
}
