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

    private String username;

    private String password;

    private Boolean sshEnable;

    private Integer sshPort;

    private Boolean telnetEnable;

    private Integer telnetPort;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ServerPO serverPO = (ServerPO) o;
        return Objects.equals(uuid, serverPO.uuid) && Objects.equals(name, serverPO.name) && Objects.equals(ipAddress, serverPO.ipAddress) && Objects.equals(systemType, serverPO.systemType) && Objects.equals(platform, serverPO.platform) && Objects.equals(charset, serverPO.charset) && Objects.equals(username, serverPO.username) && Objects.equals(password, serverPO.password) && Objects.equals(sshEnable, serverPO.sshEnable) && Objects.equals(sshPort, serverPO.sshPort) && Objects.equals(telnetEnable, serverPO.telnetEnable) && Objects.equals(telnetPort, serverPO.telnetPort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uuid, name, ipAddress, systemType, platform, charset, username, password, sshEnable, sshPort, telnetEnable, telnetPort);
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
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sshEnable=" + sshEnable +
                ", sshPort=" + sshPort +
                ", telnetEnable=" + telnetEnable +
                ", telnetPort=" + telnetPort +
                '}';
    }
}
