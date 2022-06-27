package cn.lemongo97.aom.model.dto;

import cn.hutool.core.map.MapUtil;
import cn.lemongo97.aom.model.SysMenuDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SysMenuDTO extends SysMenuDO {
    private String name;
    private Set<SysMenuDTO> children;

    public String getName(){
        return this.getMenuName();
    }

    public Map<String,String> getMeta(){
        return MapUtil.of("title", this.getMenuName());
    }

}
