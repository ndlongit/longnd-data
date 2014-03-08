package org.java.demo.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.java.demo.action.base.AbstractAction;

@Results({ @Result(location = "login-success.jsp"), @Result(name = LogoutAction.LOGIN, location = "/login.jsp") })
public class LogoutAction extends AbstractAction {

    //FIXME Not working
    @Action(value = "logout", results = @Result(name = "springLogout", location = "j_spring_security_logout"))
    public String logout() throws Exception {
        return "springLogout";
    }

    @Action("logout-success")
    public String logoutSuccess() throws Exception {
        addActionMessage(getText("logout.success.message"));
        return LogoutAction.LOGIN;
    }
}
