package cn.lemongo97.aom.mapper;

import cn.lemongo97.aom.model.SysRoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper extends BaseMapper<SysRoleDO> {
}
