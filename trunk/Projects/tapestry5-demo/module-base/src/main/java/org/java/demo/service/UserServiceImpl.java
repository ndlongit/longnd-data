package org.java.demo.service;

import java.util.List;

import org.java.demo.dao.UserDao;
import org.java.demo.model.Group;
import org.java.demo.model.User;
import org.java.demo.search.UserSearch;
import org.java.demo.service.core.AbstractService;
import org.springframework.stereotype.Service;

@Service(UserService.SERVICE_ID)
public class UserServiceImpl extends AbstractService<User, Long, UserDao> implements UserService {

    @Override
    public List<User> search(UserSearch searchModel) {

        // FIXME Implement here
        return null;
    }

    @Override
    public User find(Long id) {

        // FIXME add OpenSessionInViewMode capacity
        User user = dao.find(id);
        List<Group> groups = user.getGroups();
        for (Group group : groups) {

        }
        user.setGroups(groups);
        return user;
        // return super.find(id);
    }
}
