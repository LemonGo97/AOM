package cn.lemongo97.aom.model.dto;

import cn.lemongo97.aom.model.SysRoleDO;
import cn.lemongo97.aom.model.SysUserDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SysUserDTO extends SysUserDO {
    private String queryKey;
    private SysRoleDO role;
}
