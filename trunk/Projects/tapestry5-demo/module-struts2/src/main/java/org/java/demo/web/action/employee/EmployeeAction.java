package org.java.demo.web.action.employee;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.Department;
import org.java.demo.model.Employee;
import org.java.demo.model.JobTitle;
import org.java.demo.service.DepartmentService;
import org.java.demo.service.EmployeeService;
import org.java.demo.service.JobTitleService;
import org.java.demo.web.action.base.AbstractAction;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Results({ @Result(name = AbstractAction.ACTION_LIST, location = AbstractAction.ACTION_LIST, type = AbstractAction.TYPE_REDIRECT_ACTION),
        @Result(name = AbstractAction.LIST, location = "listEmployees.jsp"), @Result(name = AbstractAction.ERROR, location = "createEmployee.jsp"),
        @Result(name = AbstractAction.INPUT, location = "createEmployee.jsp") })
public class EmployeeAction extends AbstractAction implements ModelDriven<Employee> {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private JobTitleService jobTitleService;

    // Main Data Model
    private Employee employee = new Employee();

    private List<Employee> employees;
    private List<Department> departments;
    private List<JobTitle> jobTitles;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<JobTitle> getJobTitles() {
        return jobTitles;
    }

    public void setJobTitles(List<JobTitle> jobTitles) {
        this.jobTitles = jobTitles;
    }

    private String password2;
    private String headerText;

    @SkipValidation
    @Action(value = ACTION_LIST)
    public String list() {
        this.employees = employeeService.findAll();
        return EmployeeAction.LIST;
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
            loadDataModel(employee);

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

            pushModel(this.employee);
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
            loadDataModel(employee);

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
            loadDataModel(employee);
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
        loadDataLists();
    }

    private void initDataForCopy() {
        action = ACTION_DO_COPY;
        pageTitle = "Copy Employee";
        headerText = pageTitle;
        loadDataLists();
    }

    private void initDataForEdit() {
        action = ACTION_DO_EDIT;
        pageTitle = "Edit Employee";
        headerText = pageTitle;
        loadDataLists();
    }

    private void loadDataLists() {
        departments = departmentService.findAll();
        jobTitles = jobTitleService.findAll();
    }

    @SkipValidation
    @Action(ACTION_DELETE)
    public String delete() {
        try {
            if (employee != null && !isNullOrEmpty(employee.getId())) {
                employeeService.delete(employee.getId());
            }

            return ACTION_LIST;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public void prepare() throws Exception {
    }

    public Employee getEmployee() {
        return this.employee;
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
        // if (ACTION_DO_CREATE.equalsIgnoreCase(action) || ACTION_DO_EDIT.equalsIgnoreCase(action)) {
        // final Employee model = getModel();
        // String[] fieldValues = { model.getLoginName(), model.getPassword(), password2, model.getFirstName(), model.getLastName() };
        // String[] fieldNames = { "loginName", "password", "password2", "firstName", "lastName" };
        // String[] fieldLabels = { getText("loginName"), getText("password"), getText("password2"), getText("firstName"), getText("lastName") };
        // validateRequired(fieldValues, fieldLabels, fieldNames);
        // }

        if (ACTION_DO_CREATE.equalsIgnoreCase(action)) {
            initDataForCreate();
        } else if (ACTION_DO_EDIT.equalsIgnoreCase(action)) {
            initDataForEdit();
        } else {
            initDataForCopy();
        }
    }

    private void loadDataModel(final Employee model) {
        if (model != null && model.getId() != null) {
            this.employee = employeeService.find(model.getId());
            if (this.employee != null) {
                pushModel(this.employee);
            }
        }
    }

    @Override
    public Employee getModel() {
        return this.employee;
    }
}
