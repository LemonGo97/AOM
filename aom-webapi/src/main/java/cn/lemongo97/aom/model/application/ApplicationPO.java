package cn.lemongo97.aom.model.application;

import cn.lemongo97.aom.common.Application;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

/**
 * @author lemongo97
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_application")
public class ApplicationPO {

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    @TableField
    private Application name;

    @TableField
    private String icon;

    @TableField
    private String description;

    @TableField
    private String downloadUrl;

    @TableField(jdbcType = JdbcType.LONGVARCHAR)
    private String changeLog;

    @TableField(jdbcType = JdbcType.VARCHAR)
    private String changeLogUrl;

}
