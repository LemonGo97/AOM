package cn.lemongo97.aom.controller;

import cn.lemongo97.aom.common.BasePage;
import cn.lemongo97.aom.common.PageResult;
import cn.lemongo97.aom.config.ResponseResult;
import cn.lemongo97.aom.model.SysUserDO;
import cn.lemongo97.aom.model.dto.SysUserDTO;
import cn.lemongo97.aom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseResult
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('manage:sys:user:list')")
    @GetMapping
    public PageResult<SysUserDTO> list(SysUserDTO user, BasePage<SysUserDO> page) {
        return userService.listUsers(user, page);
    }

    @PreAuthorize("hasAuthority('manage:sys:user:detail')")
    @GetMapping("/{userId}")
    public SysUserDTO getUserById(@PathVariable(name = "userId") Long userId){
        return userService.getUserById(userId);
    }

}
