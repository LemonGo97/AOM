package cn.lemongo97.aom.model.application;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

/**
 * @author lemongo97
 */
@Data
@TableName(value = "t_application")
public class ApplicationPO {

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    @TableField(jdbcType = JdbcType.VARCHAR)
    private String name;

    @TableField(jdbcType = JdbcType.LONGVARCHAR)
    private String changeLog;

    @TableField(jdbcType = JdbcType.VARCHAR)
    private String changeLogUrl;

}
