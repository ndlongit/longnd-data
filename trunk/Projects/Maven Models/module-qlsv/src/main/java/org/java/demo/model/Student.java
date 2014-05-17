package org.java.demo.model;

import javax.persistence.Entity;

@Entity
public class Student extends User {

    private Long studentId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
