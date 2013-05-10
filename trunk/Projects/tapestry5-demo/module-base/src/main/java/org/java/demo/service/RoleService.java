package org.java.demo.service;

import java.util.List;

import org.java.demo.model.Role;
import org.java.demo.service.core.BasicService;

public interface RoleService extends BasicService<Role, Long> {

    public List<Role> getByNames(List<String> roleNames);
}
