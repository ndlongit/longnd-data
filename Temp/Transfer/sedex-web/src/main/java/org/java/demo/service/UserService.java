package org.java.demo.service;

import java.util.List;

import org.java.demo.model.User;
import org.java.demo.search.UserSearch;
import org.java.demo.service.core.BasicService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public interface UserService extends BasicService<User, Long> {

    public static final String SERVICE_ID = "userService";

    public List<User> search(UserSearch searchModel);

    public org.java.demo.model.User getByLoginName(String loginName);
}
