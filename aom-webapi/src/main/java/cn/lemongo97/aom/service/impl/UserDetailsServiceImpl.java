package cn.lemongo97.aom.service.impl;

import cn.lemongo97.aom.common.ResultCode;
import cn.lemongo97.aom.exception.AomBaseException;
import cn.lemongo97.aom.mapper.UserMapper;
import cn.lemongo97.aom.model.LoginUser;
import cn.lemongo97.aom.model.SysUserDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserDO::getUsername, username);
        SysUserDO user = userMapper.selectOne(wrapper);
        if (Objects.isNull(user)){
            throw new AomBaseException(ResultCode.USER_LOGIN_ERROR, "用户名或密码错误！");
        }
        List<String> permissions = userMapper.getPermissionsByUserId(user.getId());
        return new LoginUser(user, permissions);
    }
}