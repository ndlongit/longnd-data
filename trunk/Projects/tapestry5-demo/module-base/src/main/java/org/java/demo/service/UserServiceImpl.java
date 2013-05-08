package org.java.demo.service;

import org.java.demo.dao.UserDao;
import org.java.demo.model.User;
import org.java.demo.service.core.BasicServiceImpl;
import org.springframework.stereotype.Service;

@Service(UserService.SERVICE_ID)
public class UserServiceImpl extends BasicServiceImpl<User, Long, UserDao> implements UserService {
}
