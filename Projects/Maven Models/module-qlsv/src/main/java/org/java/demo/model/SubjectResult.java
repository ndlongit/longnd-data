package org.java.demo.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.java.demo.model.core.NumericIdEntity;

@Entity
public class SubjectResult extends NumericIdEntity {

    private Subject subject;
    private Double score;

    public SubjectResult() {
        super();
        subject = new Subject();
    }

    @Embedded
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

}
