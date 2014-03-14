package org.java.demo.action.admin;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.java.demo.action.base.AbstractAction;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.User;
import org.java.demo.service.UserService;
import org.java.demo.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;

@Results({ @Result(name = UserAction.ACTION_LIST, location = UserAction.ACTION_LIST, type = UserAction.TYPE_REDIRECT_ACTION),
        @Result(name = "list-view", location = "listUsers.jsp"), @Result(name = UserAction.VIEW_ERROR, location = "createUser.jsp") })
public class UserAction extends AbstractAction {

    static final String VIEW_ERROR = "error";

    static final String VIEW_PREPARE = "prepare";

    static final String ACTION_CREATE = "create-user";

    static final String ACTION_EDIT = "edit-user";

    static final String ACTION_LIST = "list-users";

    @Autowired
    private UserService userService;
    private List<User> users;
    private User user;
    private Long id;
    private String password2;

    // Actions: {do-create-user | do-edit-user | delete-user}
    private String action;

    @Action(value = ACTION_LIST)
    public String list() {
        this.users = userService.findAll();
        return "list-view";
    }

    @Action(value = ACTION_CREATE, results = { @Result(name = VIEW_PREPARE, location = "createUser.jsp") })
    public String create() throws DataConstraintException, Exception {
        if (AppUtil.isNullOrEmpty(action)) {
            action = ACTION_CREATE;
            return VIEW_PREPARE;
        }

        try {
            this.userService.save(user);
            return ACTION_LIST;
        } catch (Exception e) {
            action = ACTION_CREATE;
            addActionError("Create User fail");
            return VIEW_ERROR;
        }
    }

    @Action(value = ACTION_EDIT, results = { @Result(name = VIEW_PREPARE, location = "createUser.jsp") })
    public String edit() {

        if (AppUtil.isNullOrEmpty(action)) {

            action = ACTION_EDIT;
            if (id != null) {
                user = userService.find(id);
            }
            return VIEW_PREPARE;
        }

        try {
            this.userService.update(user);
            return ACTION_LIST;
        } catch (Exception e) {
            action = ACTION_EDIT;
            addActionError("Edit User fail");
            return VIEW_ERROR;
        }
    }

    @Action("delete-user")
    public String delete() {
        try {
            userService.delete(id);
            return ACTION_LIST;
        } catch (Exception e) {
            return VIEW_ERROR;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
