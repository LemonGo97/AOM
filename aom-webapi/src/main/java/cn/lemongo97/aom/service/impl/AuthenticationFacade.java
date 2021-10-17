package cn.lemongo97.aom.service.impl;

import cn.lemongo97.aom.model.User;
import cn.lemongo97.aom.repository.UserJpaRepository;
import cn.lemongo97.aom.service.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getSessionUser() {

        return userJpaRepository.findUserByUsername((String) getAuthentication().getPrincipal());
    }
}