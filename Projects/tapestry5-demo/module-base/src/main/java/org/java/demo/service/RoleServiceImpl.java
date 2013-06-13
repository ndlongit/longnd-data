package org.java.demo.service;

import java.util.List;

import org.java.demo.dao.RoleDao;
import org.java.demo.model.Role;
import org.java.demo.service.core.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends AbstractService<Role, Long, RoleDao> implements RoleService {

    @Override
    public List<Role> getByNames(List<String> roleNames) {
        return dao.getByNames(roleNames);
    }
}
