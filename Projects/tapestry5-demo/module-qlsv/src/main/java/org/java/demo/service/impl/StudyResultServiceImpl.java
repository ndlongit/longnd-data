package org.java.demo.service.impl;

import java.util.List;

import org.java.demo.dao.StudyResultDao;
import org.java.demo.model.StudyResult;
import org.java.demo.service.StudyResultService;
import org.java.demo.service.core.BasicServiceImpl;
import org.springframework.stereotype.Service;

@Service(StudyResultService.SERVICE_ID)
public class StudyResultServiceImpl extends BasicServiceImpl<StudyResult, Long, StudyResultDao> implements StudyResultService {

    @Override
    public List<StudyResult> search(Long studentId, String schoolYear, String term, Long subjectId) {
        return dao.search(studentId, schoolYear, term, subjectId);
    }
}
