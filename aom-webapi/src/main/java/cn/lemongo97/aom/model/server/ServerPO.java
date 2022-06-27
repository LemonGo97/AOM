package cn.lemongo97.aom.model.server;

import cn.lemongo97.aom.common.SystemType;
import cn.lemongo97.aom.model.SysUserDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author lemongo97
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_application")
public class ServerPO {

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    @TableField("name")
    private String name;

    @TableField("ip_address")
    private String ipAddress;

    @TableField("system_type")
    private SystemType systemType;

    @TableField("platform")
    private String platform;

    @TableField("charset")
    private String charset;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("ssh_enable")
    private Boolean sshEnable;

    @TableField("ssh_port")
    private Integer sshPort;

    @TableField("telnet_enable")
    private Boolean telnetEnable;

    @TableField("telnet_port")
    private Integer telnetPort;

    @TableField("operator_id")
    private Long operatorId;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

}
