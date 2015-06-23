package org.java.demo.dao;

import org.java.demo.dao.base.AbstractDao;
import org.java.demo.model.Group;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDaoImpl extends AbstractDao<Group, Long> implements GroupDao {
}
