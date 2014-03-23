package org.java.demo.web.action.user;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.User;
import org.java.demo.service.UserService;
import org.java.demo.web.action.base.AbstractAction;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Results({ @Result(name = AbstractAction.ACTION_LIST, location = AbstractAction.ACTION_LIST, type = AbstractAction.TYPE_REDIRECT_ACTION),
        @Result(name = AbstractAction.LIST, location = "listUsers.jsp"), @Result(name = AbstractAction.ERROR, location = "createUser.jsp"),
        @Result(name = AbstractAction.INPUT, location = "createUser.jsp") })
public class UserAction extends AbstractAction implements ModelDriven<User> {

    @Autowired
    private UserService userService;
    private List<User> users;
    private User user = new User();
    private String password2;
    private String headerText;

    @SkipValidation
    @Action(value = ACTION_LIST)
    public String list() {
        this.users = userService.findAll();
        return UserAction.LIST;
    }

    @SkipValidation
    @Action(value = ACTION_CREATE, results = { @Result(name = PREPARE, location = "createUser.jsp") })
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
            this.userService.save(user);
            return ACTION_LIST;
        } catch (Exception e) {
            addActionError("Create User fail");
            initDataForCreate();
            return ERROR;
        }
    }

    @SkipValidation
    @Action(value = ACTION_COPY, results = { @Result(name = PREPARE, location = "createUser.jsp") })
    public String copy() throws DataConstraintException, Exception {
        try {
            initDataForCopy();
            loadDataModel(user);

            if (user == null) {
                user = new User();
            } else {

                // Clear all un-copytable fields
                user.setLoginName(null);
                user.setPassword(null);
                password2 = null;
            }

            return PREPARE;
        } catch (Exception e) {
            addActionError("Prepare data fail");
            initDataForCreate();
            return ERROR;
        }
    }

    @Action(value = ACTION_DO_COPY, results = { @Result(name = UserAction.ACTION_VIEW, location = UserAction.ACTION_VIEW, type = AbstractAction.TYPE_REDIRECT_ACTION, params = {
            "id", "%{id}" }) })
    public String doCopy() throws DataConstraintException, Exception {
        try {
            this.userService.save(user);

            pushModel(this.user);
            return ACTION_VIEW;
        } catch (Exception e) {
            addActionError("Copy User fail");
            initDataForCreate();
            return ERROR;
        }
    }

    @SkipValidation
    @Action(value = ACTION_EDIT, results = { @Result(name = PREPARE, location = "createUser.jsp") })
    public String edit() {
        try {
            initDataForEdit();
            loadDataModel(user);

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
            this.userService.update(user);
            return ACTION_LIST;
        } catch (Exception e) {
            addActionError("Edit User fail");
            initDataForEdit();
            return ERROR;
        }
    }

    @SkipValidation
    @Action(value = ACTION_VIEW, results = { @Result(name = SUCCESS, location = "viewUser.jsp") })
    public String view() {
        try {
            pageTitle = "View User Detail";
            headerText = pageTitle;
            loadDataModel(user);
            return SUCCESS;
        } catch (Exception e) {
            // Add errors
            return SUCCESS;
        }
    }

    private void initDataForCreate() {
        action = ACTION_DO_CREATE;
        pageTitle = "Create New User";
        headerText = pageTitle;
    }

    private void initDataForCopy() {
        action = ACTION_DO_COPY;
        pageTitle = "Copy User";
        headerText = pageTitle;
    }

    private void initDataForEdit() {
        action = ACTION_DO_EDIT;
        pageTitle = "Edit User";
        headerText = pageTitle;
    }

    @SkipValidation
    @Action(ACTION_DELETE)
    public String delete() {
        try {
            if (user != null && !isNullOrEmpty(user.getId())) {
                userService.delete(user.getId());
            }

            return ACTION_LIST;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public void prepare() throws Exception {
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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
//         if (ACTION_DO_CREATE.equalsIgnoreCase(action) || ACTION_DO_EDIT.equalsIgnoreCase(action)) {
//         final User model = getModel();
//         String[] fieldValues = { model.getLoginName(), model.getPassword(), password2, model.getFirstName(), model.getLastName() };
//         String[] fieldNames = { "loginName", "password", "password2", "firstName", "lastName" };
//         String[] fieldLabels = { getText("loginName"), getText("password"), getText("password2"), getText("firstName"), getText("lastName") };
//         validateRequired(fieldValues, fieldLabels, fieldNames);
//         }

        if (ACTION_DO_CREATE.equalsIgnoreCase(action)) {
            initDataForCreate();
        } else if (ACTION_DO_EDIT.equalsIgnoreCase(action)) {
            initDataForEdit();
        } else {
            initDataForCopy();
        }
    }

    private void loadDataModel(final User model) {
        if (model != null && model.getId() != null) {
            this.user = userService.find(model.getId());
            if (this.user != null) {
                pushModel(this.user);
            }
        }
    }

    @Override
    public User getModel() {
        return this.user;
    }
}
