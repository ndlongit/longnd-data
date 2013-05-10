package org.java.demo.service;

import org.java.demo.model.SubjectResult;
import org.java.demo.service.core.BasicService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public interface SubjectResultService extends BasicService<SubjectResult, Long> {

    public static final String SERVICE_ID = "subjectResultService";

}
