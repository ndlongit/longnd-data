package org.java.demo.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.java.demo.action.base.AbstractAction;

@Results({ @Result(location = "login-success.jsp"), @Result(name = LoginAction.LOGIN, location = "/login.jsp") })
public class LoginAction extends AbstractAction {

    @Override
    // Login action
    public String execute() throws Exception {
        return LoginAction.LOGIN;
    }

    @Action("doLogin")
    public String doLogin() throws Exception {
        return SUCCESS;
    }
}
