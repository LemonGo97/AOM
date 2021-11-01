package cn.lemongo97.aom.model.application;

import cn.lemongo97.aom.common.Application;
import cn.lemongo97.aom.common.Platform;
import cn.lemongo97.aom.common.SystemType;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import java.util.Date;

/**
 * @author lemongo97
 */
@Data
@TableName(value = "t_application_version")
public class ApplicationVersionPO {

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    @TableField
    private Application application;

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
