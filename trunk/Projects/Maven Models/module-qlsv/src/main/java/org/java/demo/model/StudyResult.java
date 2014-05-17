package org.java.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.java.demo.model.core.NumericIdEntity;

@Entity
public class StudyResult extends NumericIdEntity {

    private Long studentId;
    private String schoolYear;
    private String term;

    private List<SubjectResult> subjectResults;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "study_result_id")
    public List<SubjectResult> getSubjectResults() {
        return subjectResults;
    }

    public void setSubjectResults(List<SubjectResult> subjectResults) {
        this.subjectResults = subjectResults;
    }

    @Transient
    public void addSubjectResult(SubjectResult subjectResult) {
        if (subjectResults == null) {
            subjectResults = new ArrayList<SubjectResult>();
        }

        subjectResults.add(subjectResult);
    }
}
