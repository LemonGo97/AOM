package cn.lemongo97.aom.service;

import cn.lemongo97.aom.model.User;
import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {

    /**
     * 获取当前登录用户信息
     * @return
     */
    Authentication getAuthentication();

    /**
     * 获取当前登录用户名
     * @return
     */
    User getSessionUser();
}
