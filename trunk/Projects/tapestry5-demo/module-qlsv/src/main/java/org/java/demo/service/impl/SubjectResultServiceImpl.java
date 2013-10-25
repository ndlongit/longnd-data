package org.java.demo.service.impl;

import org.java.demo.dao.StudyResultDao;
import org.java.demo.model.SubjectResult;
import org.java.demo.service.SubjectResultService;
import org.java.demo.service.core.AbstractService;
import org.springframework.stereotype.Service;

@Service(SubjectResultService.SERVICE_ID)
public class SubjectResultServiceImpl extends AbstractService<SubjectResult, Long, StudyResultDao> implements SubjectResultService {
}