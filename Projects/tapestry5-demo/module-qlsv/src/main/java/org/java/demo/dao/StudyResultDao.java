package org.java.demo.dao;

import java.util.List;

import org.java.demo.dao.core.BasicDao;
import org.java.demo.model.StudyResult;

public interface StudyResultDao extends BasicDao<StudyResult, Long> {

    List<StudyResult> search(Long studentId, String schoolYear, String term, Long subjectId);

}
