package org.java.demo.service;

import java.util.List;

import org.java.demo.model.StudyResult;
import org.java.demo.service.core.BasicService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public interface StudyResultService extends BasicService<StudyResult, Long> {

	public static final String SERVICE_ID = "studyResultService";

	public List<StudyResult> search(Long studentId, String schoolYear, String term, Long subjectId);

}
