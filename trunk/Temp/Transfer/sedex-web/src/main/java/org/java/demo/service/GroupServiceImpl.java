package org.java.demo.service;

import org.java.demo.dao.GroupDao;
import org.java.demo.model.Group;
import org.java.demo.service.base.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends AbstractService<Group, Long, GroupDao> implements GroupService {
}
