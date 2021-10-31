package cn.lemongo97.aom.model.application;

import cn.lemongo97.aom.common.Platform;
import cn.lemongo97.aom.common.SystemType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * @author lemongo97
 */
@Table(name = "t_application_version")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "org.hibernate.id.UUIDGenerator")
public class ApplicationVersionPO {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String uuid;
    private String name;
    private String packageName;
    private String version;
    private SystemType systemType;
    private Platform platform;
    private Date updateTime;
    private String changeLog;
    private String changeLogUrl;
    private String downloadUrl;
    private String filePath;
    private Integer fileSize;

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

    public SystemType getSystemType() {
        return systemType;
    }

    public void setSystemType(SystemType systemType) {
        this.systemType = systemType;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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

    public String getChangeLogUrl() {
        return changeLogUrl;
    }

    public void setChangeLogUrl(String changeLogUrl) {
        this.changeLogUrl = changeLogUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }


    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationVersionPO that = (ApplicationVersionPO) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(name, that.name) && Objects.equals(packageName, that.packageName) && Objects.equals(version, that.version) && systemType == that.systemType && platform == that.platform && Objects.equals(updateTime, that.updateTime) && Objects.equals(changeLog, that.changeLog) && Objects.equals(changeLogUrl, that.changeLogUrl) && Objects.equals(downloadUrl, that.downloadUrl) && Objects.equals(filePath, that.filePath) && Objects.equals(fileSize, that.fileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, packageName, version, systemType, platform, updateTime, changeLog, changeLogUrl, downloadUrl, filePath, fileSize);
    }

    @Override
    public String toString() {
        return "ApplicationPO{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", version='" + version + '\'' +
                ", systemType=" + systemType +
                ", platform=" + platform +
                ", updateTime='" + updateTime + '\'' +
                ", changeLog='" + changeLog + '\'' +
                ", changeLogUrl='" + changeLogUrl + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSize='" + fileSize + '\'' +
                '}';
    }
}
