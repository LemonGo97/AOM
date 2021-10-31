package cn.lemongo97.aom.model.application;

import cn.lemongo97.aom.common.Platform;
import cn.lemongo97.aom.common.SystemType;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
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
@Data
@TableName(value = "t_application_version")
public class ApplicationVersionPO {

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    @TableField(jdbcType = JdbcType.VARCHAR)
    private String name;

    @OrderBy
    @TableField(jdbcType = JdbcType.VARCHAR)
    private String packageName;

    @TableField(jdbcType = JdbcType.VARCHAR)
    private String version;

    @TableField
    private SystemType systemType;

    @TableField
    private Platform platform;

    @TableField
    private Date updateTime;

    @TableField
    private String downloadUrl;

    @TableField
    private String filePath;

    @TableField
    private Integer fileSize;
}
