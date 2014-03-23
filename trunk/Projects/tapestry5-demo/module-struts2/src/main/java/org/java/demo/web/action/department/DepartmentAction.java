package org.java.demo.web.action.department;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.Department;
import org.java.demo.service.DepartmentService;
import org.java.demo.web.action.base.AbstractAction;
import org.java.demo.web.action.employee.EmployeeAction;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Results({ @Result(name = AbstractAction.ACTION_LIST, location = AbstractAction.ACTION_LIST, type = AbstractAction.TYPE_REDIRECT_ACTION),
        @Result(name = AbstractAction.LIST, location = "listDepartments.jsp"),
        @Result(name = AbstractAction.ERROR, location = "createDepartment.jsp"),
        @Result(name = AbstractAction.INPUT, location = "createDepartment.jsp") })
public class DepartmentAction extends AbstractAction implements ModelDriven<Department> {

    @Autowired
    private DepartmentService departmentService;

    private String headerText;

    private Department department = new Department();

    private List<Department> departments;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    @SkipValidation
    @Action(value = ACTION_CREATE, results = { @Result(name = PREPARE, location = "createDepartment.jsp") })
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
            this.departmentService.save(department);
            return ACTION_LIST;
        } catch (Exception e) {
            addActionError("Create Department fail");
            initDataForCreate();
            return ERROR;
        }
    }

    @SkipValidation
    @Action(value = ACTION_COPY, results = { @Result(name = PREPARE, location = "createDepartment.jsp") })
    public String copy() throws DataConstraintException, Exception {
        try {
            initDataForCopy();
            loadDataModel(department);

            if (department == null) {
                department = new Department();
            } else {

                // Clear all un-copytable fields
                department.setValue(null);
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
            this.departmentService.save(department);

            pushModel(this.department);
            return ACTION_VIEW;
        } catch (Exception e) {
            addActionError("Copy Employee fail");
            initDataForCreate();
            return ERROR;
        }
    }

    @SkipValidation
    @Action(value = ACTION_EDIT, results = { @Result(name = PREPARE, location = "createDepartment.jsp") })
    public String edit() {
        try {
            initDataForEdit();
            loadDataModel(department);

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
            this.departmentService.update(department);
            return ACTION_LIST;
        } catch (Exception e) {
            addActionError("Edit Employee fail");
            initDataForEdit();
            return ERROR;
        }
    }

    @SkipValidation
    @Action(value = ACTION_VIEW, results = { @Result(name = SUCCESS, location = "viewDepartment.jsp") })
    public String view() {
        try {
            pageTitle = "View Employee Detail";
            headerText = pageTitle;
            loadDataModel(department);
            return SUCCESS;
        } catch (Exception e) {
            // Add errors
            return SUCCESS;
        }
    }

    @SkipValidation
    @Action(value = ACTION_LIST)
    public String list() {
        this.departments = departmentService.findAll();
        return EmployeeAction.LIST;
    }

    private void initDataForCreate() {
        action = ACTION_DO_CREATE;
        pageTitle = "Create New Department";
        headerText = pageTitle;
    }

    private void initDataForCopy() {
        action = ACTION_DO_COPY;
        pageTitle = "Copy Employee";
        headerText = pageTitle;
    }

    private void initDataForEdit() {
        action = ACTION_DO_EDIT;
        pageTitle = "Edit Employee";
        headerText = pageTitle;
    }

    private void loadDataModel(final Department model) {
        if (model != null && model.getId() != null) {
            this.department = departmentService.find(model.getId());
            if (this.department != null) {
                pushModel(this.department);
            }
        }
    }

    @Override
    public Department getModel() {
        return department;
    }
}
