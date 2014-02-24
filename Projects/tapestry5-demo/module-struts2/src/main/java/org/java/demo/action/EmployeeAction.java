package org.java.demo.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.Employee;
import org.java.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Preparable;

@Results({ @Result(name = "list", location = "list.jsp"), @Result(name = "edit", location = "/index.jsp") })
public class EmployeeAction implements Preparable {

    @Autowired
    private EmployeeService service;
    private List<Employee> employees;
    private Employee employee;
    private Long id;

    public String execute() {
        this.employees = service.findAll();
        return "list";
    }

    @Action("list")
    public String list() {
        return execute();
    }

    @Action("save")
    public String save() throws DataConstraintException, Exception {
        this.service.update(employee);
        return execute();
    }

    @Action("edit")
    public String edit() {
        if (id != null) {
            employee = service.find(id);
        }
        return "edit";
    }

    @Action("remove")
    public String remove() {
        service.delete(id);
        return execute();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void prepare() throws Exception {
        if (id != null) {
            employee = service.find(id);
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
