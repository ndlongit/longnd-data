package org.java.demo.service.impl;

import javax.annotation.Resource;

import org.java.demo.model.Account;
import org.java.demo.model.impl.UserDetailsImpl;
import org.java.demo.service.AccountService;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Resource
	private AccountService accountService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountService.getByLoginName(username);
		UserDetails userDetails = new UserDetailsImpl(account);
		return userDetails;		
	}
}
