package org.java.demo.service;

import org.java.demo.dao.UserDao;
import org.java.demo.model.User;
import org.java.demo.service.core.AbstractService;
import org.springframework.stereotype.Service;

@Service(UserService.SERVICE_ID)
public class UserServiceImpl extends AbstractService<User, Long, UserDao> implements UserService {
    
//    @Override
//    public void delete(Long id) {
////        User user = dao.find(id);
////        user.setRoles(null);
//        super.delete(id);
//    }
}
