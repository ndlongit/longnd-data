package com.sedex.appexch.service;

import java.util.List;

import com.sedex.appexch.model.Role;
import com.sedex.appexch.service.base.BasicService;

public interface RoleService extends BasicService<Role, Long> {

    public List<Role> getByNames(List<String> roleNames);
}
