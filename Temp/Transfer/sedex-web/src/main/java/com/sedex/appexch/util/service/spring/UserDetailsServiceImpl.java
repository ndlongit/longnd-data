package com.sedex.appexch.util.service.spring;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sedex.appexch.model.User;
import com.sedex.appexch.service.UserService;
import com.sedex.appexch.util.model.spring.UserDetailsImpl;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByLoginName(username);
        UserDetails userDetails = new UserDetailsImpl(user);
        return userDetails;
    }
}
