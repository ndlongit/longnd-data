package org.java.demo.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.java.demo.web.action.base.AbstractAction;

@Results({ @Result(location = "login-success.jsp"), @Result(name = LoginAction.LOGIN, location = "/login.jsp") })
public class LoginAction extends AbstractAction {

    @Override
    public String execute() throws Exception {
        return LoginAction.LOGIN;
    }

    @Action("login-success")
    public String loginSuccess() throws Exception {
        return SUCCESS;
    }

    @Action("login-fail")
    public String loginFail() throws Exception {
        addActionError(getText("login.fail"));
        return LoginAction.LOGIN;
    }

    @Action("access-denied")
    public String accessDenied() throws Exception {
        addActionError(getText("login.accessDenied"));
        return LoginAction.LOGIN;
    }
}
