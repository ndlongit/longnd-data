package com.sedex.appexch.dao;

import org.springframework.stereotype.Repository;

import com.sedex.appexch.dao.base.AbstractDao;
import com.sedex.appexch.model.Group;

@Repository
public class GroupDaoImpl extends AbstractDao<Group, Long> implements GroupDao {
}
