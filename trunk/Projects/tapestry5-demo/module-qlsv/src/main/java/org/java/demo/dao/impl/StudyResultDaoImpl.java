package org.java.demo.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.java.demo.dao.StudyResultDao;
import org.java.demo.dao.core.BasicDaoImpl;
import org.java.demo.model.StudyResult;
import org.java.demo.util.AppUtil;
import org.springframework.stereotype.Repository;

@Repository
public class StudyResultDaoImpl extends BasicDaoImpl<StudyResult, Long> implements StudyResultDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<StudyResult> search(Long studentId, String schoolYear, String term, Long subjectId) {
        String queryString = "from " + getClazz().getName() + " where schoolYear = :schoolYear and term = :term";
        if (!AppUtil.isNullOrEmpty(studentId)) {
            queryString += " and studentId = :studentId";
        }

        if (!AppUtil.isNullOrEmpty(subjectId)) {
            queryString += " and subjectId = :subjectId";
        }

        Query queryObject = this.entityManager.createQuery(queryString);

        queryObject.setParameter("schoolYear", schoolYear);
        queryObject.setParameter("term", term);

        if (!AppUtil.isNullOrEmpty(studentId)) {
            queryObject.setParameter("studentId", studentId);
        }

        if (!AppUtil.isNullOrEmpty(subjectId)) {
            queryObject.setParameter("subjectId", subjectId);
        }

        List<StudyResult> list = queryObject.getResultList();
        return list;
    }
}
