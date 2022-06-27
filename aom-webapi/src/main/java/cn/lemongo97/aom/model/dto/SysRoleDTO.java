package cn.lemongo97.aom.model.dto;

import cn.lemongo97.aom.model.SysMenuDO;
import cn.lemongo97.aom.model.SysRoleDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SysRoleDTO extends SysRoleDO {
    private String queryKey;
    private List<SysMenuDO> permissions;
}
