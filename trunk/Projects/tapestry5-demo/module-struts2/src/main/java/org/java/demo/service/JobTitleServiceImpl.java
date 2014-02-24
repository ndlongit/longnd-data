package org.java.demo.service;
import org.java.demo.dao.JobTitleDao;
import org.java.demo.model.JobTitle;
import org.java.demo.service.core.AbstractService;
import org.springframework.stereotype.Service;
@Service
public class JobTitleServiceImpl extends AbstractService<JobTitle, Long, JobTitleDao> implements JobTitleService {}
