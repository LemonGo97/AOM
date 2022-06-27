package cn.lemongo97.aom.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import cn.lemongo97.aom.common.ResultCode;
import cn.lemongo97.aom.config.Result;
import cn.lemongo97.aom.exception.AomBaseException;
import cn.lemongo97.aom.mapper.MenuMapper;
import cn.lemongo97.aom.mapper.UserMapper;
import cn.lemongo97.aom.model.LoginUser;
import cn.lemongo97.aom.model.SysMenuDO;
import cn.lemongo97.aom.service.LoginService;
import cn.lemongo97.aom.utils.JsonUtil;
import cn.lemongo97.aom.utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Result login(String username, String password) throws JsonProcessingException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new AomBaseException(ResultCode.USER_LOGIN_ERROR, "用户名或密码错误！");
        }
        LoginUser user = (LoginUser) authenticate.getPrincipal();
        Long userId = user.getUser().getId();
        String requestToken = JwtUtil.createJWT(String.valueOf(userId));
        String refreshToken = JwtUtil.createJWT(String.valueOf(userId), JwtUtil.JWT_TTL * 2);
        redisTemplate.opsForValue().set("string:login:" + userId, JsonUtil.toJson(user));
        return Result.success(MapUtil.builder().put("requestToken", requestToken).put("refreshToken", refreshToken).build());
    }

    @Override
    public Result logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        redisTemplate.delete("string:login:" + userId);
        return Result.success("注销成功");
    }

    @Override
    public Map<String, Object> getPermissionInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        List<SysMenuDO> menus = menuMapper.getById(userId);
        List<String> permissions = userMapper.getPermissionsByUserId(userId);

        List<Tree<Long>> menuList = TreeUtil.build(menus, 0L, TreeNodeConfig.DEFAULT_CONFIG.setWeightKey("order"),
                (node, tree) -> {
                    tree.setId(node.getId());
                    tree.setParentId(node.getParent());
                    tree.setWeight(node.getOrder());
                    tree.setName(node.getMenuName());
                    tree.putExtra("path", node.getPath());
                    tree.putExtra("meta", MapUtil.of("title", node.getMenuName()));
                });

        return MapUtil.<String, Object>builder().put("user", loginUser.getUser()).put("menus", menuList).put("permissions", permissions).build();
    }

}