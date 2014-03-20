package org.java.demo.action.admin;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.java.demo.action.base.AbstractAction;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.User;
import org.java.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@Results({ @Result(name = UserAction.ACTION_LIST, location = UserAction.ACTION_LIST, type = AbstractAction.TYPE_REDIRECT_ACTION),
        @Result(name = UserAction.LIST, location = "listUsers.jsp"), @Result(name = AbstractAction.ERROR, location = "createUser.jsp"),
        @Result(name = AbstractAction.INPUT, location = "createUser.jsp") })
public class UserAction extends AbstractAction {

    @Autowired
    private UserService userService;
    private List<User> users;
    private User user;
    private Long id;
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
            if (id != null) {
                user = userService.find(id);
            }
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
            id = user.getId(); // Used for [View User Detail] page
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
            if (id != null) {
                user = userService.find(id);
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
            if (id != null) {
                user = userService.find(id);
            }
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
            if (!isNullOrEmpty(id)) {
                userService.delete(id);
            }

            return ACTION_LIST;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public List<User> getUsers() {
        return users;
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
    // user = userService.find(id);
    // }
    // }

    public User getUser() {
        return user;
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
        // if (ACTION_CREATE.equalsIgnoreCase(action) || ACTION_EDIT.equalsIgnoreCase(action)) {
        //
        // String[] fieldValues = { user.getLoginName(), user.getPassword(), password2, user.getEmail(), user.getFirstName(), user.getLastName() };
        // String[] fieldNames = { "user.loginName", "user.password", "password2", "user.firstName", "user.lastName", "user.email" };
        //
        // String[] fieldLabels = { getText("user.loginName"), getText("user.password"), getText("user.password2"), getText("user.firstName"),
        // getText("user.lastName"), getText("user.email") };
        //
        // checkRequired(fieldValues, fieldLabels, fieldNames);
        //
        // if (hasErrors()) {
        // if (ACTION_CREATE.equalsIgnoreCase(action)) {
        // initDataForCreate();
        // } else {
        // initDataForEdit();
        // }
        // }
        // }
    }
}
