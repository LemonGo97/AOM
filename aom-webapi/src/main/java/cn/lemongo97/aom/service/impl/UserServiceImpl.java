package cn.lemongo97.aom.service.impl;

import cn.lemongo97.aom.common.BasePage;
import cn.lemongo97.aom.common.PageResult;
import cn.lemongo97.aom.mapper.UserMapper;
import cn.lemongo97.aom.model.SysUserDO;
import cn.lemongo97.aom.model.dto.SysUserDTO;
import cn.lemongo97.aom.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lemongo97
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUserDO> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public SysUserDTO getUserById(Long userId) {
        return userMapper.getById(userId);
    }

    @Override
    public PageResult<SysUserDTO> listUsers(SysUserDTO user, BasePage<SysUserDO> page) {
        List<SysUserDTO> records = userMapper.listUsers(page, user);
        return PageResult.<SysUserDTO>builder().list(records).total(page.getTotal()).pageSize(page.getPageSize()).pageNum(page.getPageNum()).build();
    }
}
