package org.java.demo.dao.impl;

import org.java.demo.dao.SubjectResultDao;
import org.java.demo.dao.core.AbstractDao;
import org.java.demo.model.SubjectResult;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectResultDaoImpl extends AbstractDao<SubjectResult, Long> implements SubjectResultDao {
}
