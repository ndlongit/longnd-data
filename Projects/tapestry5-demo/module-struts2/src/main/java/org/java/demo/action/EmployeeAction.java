package org.java.demo.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.java.demo.action.base.AbstractAction;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.Employee;
import org.java.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

@Results({ @Result(name = EmployeeAction.ACTION_LIST, location = EmployeeAction.ACTION_LIST, type = EmployeeAction.TYPE_REDIRECT_ACTION),
        @Result(name = "input", location = "/index.jsp"), @Result(name = "list-view", location = "list.jsp"),
        @Result(name = "edit", location = "/index.jsp") })
public class EmployeeAction extends AbstractAction {

    protected static final String ACTION_LIST = "list-employees";

    @Autowired
    private EmployeeService service;
    private List<Employee> employees;
    private Employee employee;
    private Long id;

    @Action(value = ACTION_LIST)
    public String list() {
        this.employees = service.findAll();
        return "list-view";
    }

    @Action(value = "create-employee")
    public String save() throws DataConstraintException, Exception {
        this.service.update(employee);
        return ACTION_LIST;
    }

    @Action("edit-employee")
    public String edit() {
        if (id != null) {
            employee = service.find(id);
        }
        return "edit";
    }

    @Action("delete-employee")
    public String delete() {
        service.delete(id);
        return ACTION_LIST;
    }

    public void validate() {
//        if (employee == null || employee.getLastName() == null || employee.getLastName().trim().length() == 0) {
//            addFieldError("employee.lastName", "lastName is required");
//        }
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

    // TODO prepare does not receive parameters
    // @Override
    // public void prepare() throws Exception {
    // if (id != null) {
    // employee = service.find(id);
    // }
    // }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
