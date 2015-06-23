package org.java.demo.service;

import java.util.List;

import org.java.demo.dao.UserDao;
import org.java.demo.model.User;
import org.java.demo.search.UserSearch;
import org.java.demo.service.base.AbstractService;
import org.springframework.stereotype.Service;

@Service(UserService.SERVICE_ID)
public class UserServiceImpl extends AbstractService<User, Long, UserDao> implements UserService {

    @Override
    public List<User> search(UserSearch searchModel) {
        List<User> results = dao.search(searchModel);
        return results;
    }

    @Override
    public User getByLoginName(String loginName) {
        return dao.getByLoginName(loginName);
    }
}
