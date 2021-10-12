package cn.lemongo97.aom.model.server;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author lemongo97
 */
@Table(name = "t_server_user")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ServerUserPO extends BaseServerPO {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String uuid;

    private String username;

    private String password;

    private Boolean isAutoGenerated;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAutoGenerated() {
        return isAutoGenerated;
    }

    public void setAutoGenerated(Boolean autoGenerated) {
        isAutoGenerated = autoGenerated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ServerUserPO that = (ServerUserPO) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(isAutoGenerated, that.isAutoGenerated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uuid, username, password, isAutoGenerated);
    }

    @Override
    public String toString() {
        return "ServerUserPO{" +
                "createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", operator=" + operator +
                ", uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAutoGenerated=" + isAutoGenerated +
                '}';
    }
}
