package cn.lemongo97.aom.service;

import cn.lemongo97.aom.common.BasePage;
import cn.lemongo97.aom.common.PageResult;
import cn.lemongo97.aom.model.SysUserDO;
import cn.lemongo97.aom.model.dto.SysUserDTO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<SysUserDO> {
    SysUserDTO getUserById(Long userId);

    PageResult<SysUserDTO> listUsers(SysUserDTO user, BasePage<SysUserDO> page);
}