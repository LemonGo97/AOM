package cn.lemongo97.aom.model;

import cn.lemongo97.aom.enums.UserStatus;
import cn.lemongo97.aom.enums.UserType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_sys_user")
public class SysUserDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("username")
    private String username;
    /**
     * 真实姓名
     */
    @TableField("actual_name")
    private String actualName;
    @TableField("password")
    private String password;
    @TableField("email")
    private String email;
    @TableField("telephone")
    private String telephone;
    @TableField("type")
    private UserType type;
    @TableField("status")
    private UserStatus status;
    @TableField("last_login_ip")
    private String lastLoginIp;
    @TableField("last_login_time")
    private String lastLoginTime;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("locked")
    private Boolean locked;
    @TableField("disabled")
    private Boolean disabled;
}
