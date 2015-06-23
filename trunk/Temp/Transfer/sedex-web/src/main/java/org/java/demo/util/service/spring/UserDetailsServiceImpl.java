package org.java.demo.util.service.spring;

import javax.annotation.Resource;

import org.java.demo.model.User;
import org.java.demo.service.UserService;
import org.java.demo.util.model.spring.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
