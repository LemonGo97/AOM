package cn.lemongo97.aom.mapper;

import cn.lemongo97.aom.common.BasePage;
import cn.lemongo97.aom.model.SysUserDO;
import cn.lemongo97.aom.model.dto.SysUserDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<SysUserDO> {

    List<String> getPermissionsByUserId(@Param("userId") Long userId);

    SysUserDTO getById(@Param("userId") Long userId);

    List<SysUserDTO> listUsers(@Param("page") BasePage<SysUserDO> page, @Param("user") SysUserDTO user);
}
