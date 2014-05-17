package org.java.demo.dao;
import org.java.demo.dao.core.AbstractDao;
import org.java.demo.model.JobTitle;
import org.springframework.stereotype.Repository;
@Repository
public class JobTitleDaoImpl extends AbstractDao<JobTitle, Long> implements JobTitleDao {}
