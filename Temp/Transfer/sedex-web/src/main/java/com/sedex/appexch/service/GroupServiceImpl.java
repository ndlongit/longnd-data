package com.sedex.appexch.service;

import org.springframework.stereotype.Service;

import com.sedex.appexch.dao.GroupDao;
import com.sedex.appexch.model.Group;
import com.sedex.appexch.service.base.AbstractService;

@Service
public class GroupServiceImpl extends AbstractService<Group, Long, GroupDao>
		implements GroupService {
}
