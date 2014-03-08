package org.java.demo.service.spring;

import javax.annotation.Resource;

import org.java.demo.model.Account;
import org.java.demo.model.spring.UserDetailsImpl;
import org.java.demo.service.AccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AccountService accountService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.getByLoginName(username);
        UserDetails userDetails = new UserDetailsImpl(account);
        return userDetails;
    }
}
