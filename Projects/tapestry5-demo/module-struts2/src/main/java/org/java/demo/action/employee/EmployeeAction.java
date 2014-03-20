package org.java.demo.action.employee;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.java.demo.action.base.AbstractAction;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.Employee;
import org.java.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

@Results({ @Result(name = EmployeeAction.ACTION_LIST, location = EmployeeAction.ACTION_LIST, type = AbstractAction.TYPE_REDIRECT_ACTION),
        @Result(name = EmployeeAction.LIST, location = "listEmployees.jsp"), @Result(name = AbstractAction.ERROR, location = "createEmployee.jsp"),
        @Result(name = AbstractAction.INPUT, location = "createEmployee.jsp") })
public class EmployeeAction extends AbstractAction {

    @Autowired
    private EmployeeService employeeService;
    private List<Employee> employees;
    private Employee employee;
    private Long id;
    private String password2;
    private String headerText;

    @SkipValidation
    @Action(value = ACTION_LIST)
    public String list() {
        this.employees = employeeService.findAll();
        return LIST;
    }

    @SkipValidation
    @Action(value = ACTION_CREATE, results = { @Result(name = PREPARE, location = "createEmployee.jsp") })
    public String create() throws DataConstraintException, Exception {
        try {
            initDataForCreate();
            return PREPARE;
        } catch (Exception e) {
            addActionError("Prepare data fail");
            initDataForCreate();
            return ERROR;
        }
    }

    @Action(value = ACTION_DO_CREATE)
    public String doCreate() throws DataConstraintException, Exception {
        try {
            this.employeeService.save(employee);
            return ACTION_LIST;
        } catch (Exception e) {
            addActionError("Create Employee fail");
            initDataForCreate();
            return ERROR;
        }
    }

    @SkipValidation
    @Action(value = ACTION_COPY, results = { @Result(name = PREPARE, location = "createEmployee.jsp") })
    public String copy() throws DataConstraintException, Exception {
        try {
            initDataForCopy();
            if (id != null) {
                employee = employeeService.find(id);
            }
            if (employee == null) {
                employee = new Employee();
            } else {

                // Clear all un-copytable fields
                employee.setLoginName(null);
                employee.setPassword(null);
                password2 = null;
            }

            return PREPARE;
        } catch (Exception e) {
            addActionError("Prepare data fail");
            initDataForCreate();
            return ERROR;
        }
    }

    @Action(value = ACTION_DO_COPY, results = { @Result(name = EmployeeAction.ACTION_VIEW, location = EmployeeAction.ACTION_VIEW, type = AbstractAction.TYPE_REDIRECT_ACTION, params = {
            "id", "%{id}" }) })
    public String doCopy() throws DataConstraintException, Exception {
        try {
            this.employeeService.save(employee);
            id = employee.getId(); // Used for [View Employee Detail] page
            return ACTION_VIEW;
        } catch (Exception e) {
            addActionError("Copy Employee fail");
            initDataForCreate();
            return ERROR;
        }
    }

    @SkipValidation
    @Action(value = ACTION_EDIT, results = { @Result(name = PREPARE, location = "createEmployee.jsp") })
    public String edit() {
        try {
            initDataForEdit();
            if (id != null) {
                employee = employeeService.find(id);
            }
            return PREPARE;
        } catch (Exception e) {
            addActionError("Prepare data fail");
            initDataForEdit();
            return ERROR;
        }
    }

    @Action(value = ACTION_DO_EDIT)
    public String doEdit() {
        try {
            this.employeeService.update(employee);
            return ACTION_LIST;
        } catch (Exception e) {
            addActionError("Edit Employee fail");
            initDataForEdit();
            return ERROR;
        }
    }

    @SkipValidation
    @Action(value = ACTION_VIEW, results = { @Result(name = SUCCESS, location = "viewEmployee.jsp") })
    public String view() {
        try {
            pageTitle = "View Employee Detail";
            headerText = pageTitle;
            if (id != null) {
                employee = employeeService.find(id);
            }
            return SUCCESS;
        } catch (Exception e) {
            // Add errors
            return SUCCESS;
        }
    }

    private void initDataForCreate() {
        action = ACTION_DO_CREATE;
        pageTitle = "Create New Employee";
        headerText = pageTitle;
    }

    private void initDataForCopy() {
        action = ACTION_DO_COPY;
        pageTitle = "Copy a Employee";
        headerText = pageTitle;
    }

    private void initDataForEdit() {
        action = ACTION_DO_EDIT;
        pageTitle = "Edit Employee";
        headerText = pageTitle;
    }

    @SkipValidation
    @Action(ACTION_DELETE)
    public String delete() {
        try {
            if (!isNullOrEmpty(id)) {
                employeeService.delete(id);
            }

            return ACTION_LIST;
        } catch (Exception e) {
            return ERROR;
        }
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    @Override
    public void validate() {
    }
}
