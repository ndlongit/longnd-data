package org.java.demo.action.admin;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.java.demo.action.base.AbstractAction;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.User;
import org.java.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@Results({ @Result(name = UserAction.ACTION_LIST, location = UserAction.ACTION_LIST, type = UserAction.TYPE_REDIRECT_ACTION),
        @Result(name = "list-view", location = "listUsers.jsp") })
public class UserAction extends AbstractAction {

    public static final String ACTION_LIST = "list-users";

    @Autowired
    private UserService userService;
    private List<User> users;
    private User user;
    private Long id;
    private String password2;

    @Action(value = ACTION_LIST)
    public String list() {
        this.users = userService.findAll();
        return "list-view";
    }

    @Action(value = "create-user", results = { @Result(name = "prepare", location = "createUser.jsp") })
    public String create() throws DataConstraintException, Exception {
        return "prepare";
    }

    @Action(value = "do-create-user")
    public String doCreate() throws DataConstraintException, Exception {
        this.userService.update(user);
        return ACTION_LIST;
    }

    @Action(value = "edit-user", results = { @Result(name = "edit", location = "createUser.jsp") })
    public String edit() {
        if (id != null) {
            user = userService.find(id);
        }
        return "edit";
    }

    @Action("delete-user")
    public String delete() {
        userService.delete(id);
        return ACTION_LIST;
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

    public void setUser(User User) {
        this.user = User;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
