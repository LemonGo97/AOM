package cn.lemongo97.aom.mapper;

import cn.lemongo97.aom.model.SysMenuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<SysMenuDO> {
    List<SysMenuDO> getById(@Param("userId") long userId);
}
