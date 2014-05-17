package org.java.demo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.ForeignKey;

@Entity
@PrimaryKeyJoinColumn(name = "employee_id")
public class Employee extends User {

    private Employee manager;
    private JobTitle jobTitle;
    private Department department;
    private Double salary;

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @ManyToOne
    @ForeignKey(name = "manager_id_fk")
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @ManyToOne
    @JoinColumn
    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    @ManyToOne
    @JoinColumn(name = "department_id")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
