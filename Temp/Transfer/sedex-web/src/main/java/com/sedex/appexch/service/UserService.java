package com.sedex.appexch.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sedex.appexch.model.User;
import com.sedex.appexch.search.UserSearch;
import com.sedex.appexch.service.base.BasicService;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public interface UserService extends BasicService<User, Long> {

	public static final String SERVICE_ID = "userService";

	public List<User> search(UserSearch searchModel);

	public com.sedex.appexch.model.User getByLoginName(String loginName);
}
