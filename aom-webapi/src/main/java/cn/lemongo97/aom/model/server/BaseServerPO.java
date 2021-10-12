package cn.lemongo97.aom.model.server;

import cn.lemongo97.aom.model.User;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.Objects;

/**
 * @author lemongo97
 */
@MappedSuperclass
public abstract class BaseServerPO {
    protected Date createTime;
    protected Date updateTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="operator_id")
    protected User operator;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseServerPO that = (BaseServerPO) o;
        return Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(operator, that.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createTime, updateTime, operator);
    }

    @Override
    public String toString() {
        return "BaseServerPO{" +
                "createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", operator=" + operator +
                '}';
    }
}
