package org.java.demo.dao;

import java.util.List;

import org.java.demo.dao.core.BasicDaoImpl;
import org.java.demo.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends BasicDaoImpl<Role, Long> implements RoleDao {

    @Override
    public List<Role> getByNames(List<String> roleNames) {
        return super.findByProperty(Role.PROP_NAME, roleNames);
    }
}
