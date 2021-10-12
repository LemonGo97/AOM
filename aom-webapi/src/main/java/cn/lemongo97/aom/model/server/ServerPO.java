package cn.lemongo97.aom.model.server;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author lemongo97
 */
@Table(name = "t_server")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ServerPO extends BaseServerPO {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String uuid;

    private String name;

    private String ipAddress;

    private String systemType;

    private String platform;

    private String charset;

    private Boolean sshEnable;

    private Integer sshPort;

    private Boolean telnetEnable;

    private Integer telnetPort;

    @ManyToOne
    @JoinColumn(name="t_server_user_uuid")
    private ServerUserPO user;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Boolean getSshEnable() {
        return sshEnable;
    }

    public void setSshEnable(Boolean sshEnable) {
        this.sshEnable = sshEnable;
    }

    public Integer getSshPort() {
        return sshPort;
    }

    public void setSshPort(Integer sshPort) {
        this.sshPort = sshPort;
    }

    public Boolean getTelnetEnable() {
        return telnetEnable;
    }

    public void setTelnetEnable(Boolean telnetEnable) {
        this.telnetEnable = telnetEnable;
    }

    public Integer getTelnetPort() {
        return telnetPort;
    }

    public void setTelnetPort(Integer telnetPort) {
        this.telnetPort = telnetPort;
    }

    public ServerUserPO getUser() {
        return user;
    }

    public void setUser(ServerUserPO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ServerPO serverPO = (ServerPO) o;
        return Objects.equals(uuid, serverPO.uuid) && Objects.equals(name, serverPO.name) && Objects.equals(ipAddress, serverPO.ipAddress) && Objects.equals(systemType, serverPO.systemType) && Objects.equals(platform, serverPO.platform) && Objects.equals(charset, serverPO.charset) && Objects.equals(sshEnable, serverPO.sshEnable) && Objects.equals(sshPort, serverPO.sshPort) && Objects.equals(telnetEnable, serverPO.telnetEnable) && Objects.equals(telnetPort, serverPO.telnetPort) && Objects.equals(user, serverPO.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uuid, name, ipAddress, systemType, platform, charset, sshEnable, sshPort, telnetEnable, telnetPort, user);
    }

    @Override
    public String toString() {
        return "ServerPO{" +
                "createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", operator=" + operator +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", systemType='" + systemType + '\'' +
                ", platform='" + platform + '\'' +
                ", charset='" + charset + '\'' +
                ", sshEnable=" + sshEnable +
                ", sshPort=" + sshPort +
                ", telnetEnable=" + telnetEnable +
                ", telnetPort=" + telnetPort +
                ", user=" + user +
                '}';
    }
}
