package org.java.demo.dao;

import java.util.List;

import org.java.demo.dao.base.BasicDao;
import org.java.demo.model.Role;

public interface RoleDao extends BasicDao<Role, Long> {

    List<Role> getByNames(List<String> roleNames);
}
