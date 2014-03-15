package org.java.demo.action.admin;

import java.util.Arrays;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.java.demo.action.base.AbstractAction;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.User;
import org.java.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@Results({ @Result(name = UserAction.ACTION_LIST, location = UserAction.ACTION_LIST, type = AbstractAction.TYPE_REDIRECT_ACTION),
        @Result(name = "list-view", location = "listUsers.jsp"), @Result(name = AbstractAction.ERROR, location = "createUser.jsp") })
public class UserAction extends AbstractAction {

    public static final String ACTION_CREATE = "create-user";

    public static final String ACTION_EDIT = "edit-user";

    public static final String ACTION_LIST = "list-users";

    @Autowired
    private UserService userService;
    private List<User> users;
    private User user;
    private Long id;
    private String password2;
    private String headerText;

    @Action(value = ACTION_LIST)
    public String list() {
        this.users = userService.findAll();
        return "list-view";
    }

    @Action(value = ACTION_CREATE, results = { @Result(name = PREPARE, location = "createUser.jsp"),
            @Result(name = INPUT, location = "createUser.jsp") })
    public String create() throws DataConstraintException, Exception {
        if (isNullOrEmpty(action)) {
            initDataForCreate();
            return PREPARE;
        }

        try {
            this.userService.save(user);
            return ACTION_LIST;
        } catch (Exception e) {
            initDataForCreate();
            addActionError("Create User fail");
            return ERROR;
        }
    }

    @Action(value = ACTION_EDIT, results = { @Result(name = PREPARE, location = "createUser.jsp"), @Result(name = INPUT, location = "createUser.jsp") })
    public String edit() {

        if (isNullOrEmpty(action)) {

            initDataForEdit();
            if (id != null) {
                user = userService.find(id);
            }
            return PREPARE;
        }

        try {
            this.userService.update(user);
            return ACTION_LIST;
        } catch (Exception e) {
            initDataForEdit();
            addActionError("Edit User fail");
            return ERROR;
        }
    }

    private void initDataForCreate() {
        action = ACTION_CREATE;
        submitButtonLabel = "Create";
        pageTitle = "Create New User";
        headerText = pageTitle;
    }

    private void initDataForEdit() {
        action = ACTION_EDIT;
        submitButtonLabel = "Edit";
        pageTitle = "Edit User";
        headerText = pageTitle;
    }

    @Action("delete-user")
    public String delete() {
        try {
            userService.delete(id);
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
        if (ACTION_CREATE.equalsIgnoreCase(action) || ACTION_EDIT.equalsIgnoreCase(action)) {
            String loginName = user.getLoginName();
            if (isNullOrEmpty(loginName)) {
                List<String> args = Arrays.asList(getText("account.loginName"));
                addFieldError("loginName", getText("validation.required", args));
                addActionError(getText("validation.required", args));
            }

            if (hasErrors()) {
                if (ACTION_CREATE.equalsIgnoreCase(action)) {
                    initDataForCreate();
                } else {
                    initDataForEdit();
                }
            }
        }
    }
}
