package com.sedex.appexch.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sedex.appexch.dao.base.AbstractDao;
import com.sedex.appexch.model.Role;

@Repository
public class RoleDaoImpl extends AbstractDao<Role, Long> implements RoleDao {

    @Override
    public List<Role> getByNames(List<String> roleNames) {
        return super.findByProperty(Role.PROP_NAME, roleNames);
    }
}
