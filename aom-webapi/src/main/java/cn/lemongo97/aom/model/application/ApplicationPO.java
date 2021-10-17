package cn.lemongo97.aom.model.application;

import cn.lemongo97.aom.common.Platform;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author lemongo97
 */
@Table(name = "t_application")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "org.hibernate.id.UUIDGenerator")
public class ApplicationPO {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String uuid;
    private String name;
    private String packageName;
    private String version;
    private String systemType;
    private Platform platform;
    private String updateTime;
    private String changeLog;
    private String filePath;

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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationPO that = (ApplicationPO) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(name, that.name) && Objects.equals(packageName, that.packageName) && Objects.equals(version, that.version) && Objects.equals(systemType, that.systemType) && platform == that.platform && Objects.equals(updateTime, that.updateTime) && Objects.equals(changeLog, that.changeLog) && Objects.equals(filePath, that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, packageName, version, systemType, platform, updateTime, changeLog, filePath);
    }

    @Override
    public String toString() {
        return "ApplicationPO{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", version='" + version + '\'' +
                ", systemType='" + systemType + '\'' +
                ", platform=" + platform +
                ", updateTime='" + updateTime + '\'' +
                ", changeLog='" + changeLog + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
