package org.java.demo.web.action.user;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.User;
import org.java.demo.search.UserSearch;
import org.java.demo.service.UserService;
import org.java.demo.web.action.base.AbstractAction;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Results({ @Result(name = AbstractAction.SEARCH, location = "searchUser.jsp"), @Result(name = AbstractAction.ERROR, location = "searchUser.jsp"),
        @Result(name = AbstractAction.INPUT, location = "searchUser.jsp") })
public class UserSearchAction extends AbstractAction implements ModelDriven<User> {

    @Autowired
    private UserService userService;
    private List<User> users;

    private User user = new User();

    private String headerText;
    private UserSearch search;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @SkipValidation
    @Action(value = ACTION_SEARCH, results = { @Result(name = PREPARE, location = "searchUser.jsp") })
    public String search() throws DataConstraintException, Exception {
        try {
            initDataForSearch();
            return PREPARE;
        } catch (Exception e) {
            addActionError("Prepare data fail");
            initDataForSearch();
            return ERROR;
        }
    }

    @Action(value = ACTION_DO_SEARCH)
    public String doSearch() throws DataConstraintException, Exception {
        try {
            search.setModel(user);
            this.users = this.userService.search(search);
            return ACTION_LIST;
        } catch (Exception e) {
            addActionError("Create User fail");
            initDataForSearch();
            return ERROR;
        }
    }

    private void initDataForSearch() {
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    @Override
    public void prepare() throws Exception {
    }

    @Override
    public void validate() {
    }

    @Override
    public User getModel() {
        return this.user;
    }
}
