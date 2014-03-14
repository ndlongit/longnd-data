package org.java.demo.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.java.demo.action.base.AbstractAction;

public class LogoutAction extends AbstractAction {

    @Action("logout")
    public String logout() throws Exception {
        return "j_spring_security_logout";
    }

    @Action(value = "logout-success", results = { @Result(name = "logout-succes", location = "/index.jsp") })
    public String logoutSuccess() throws Exception {
        return "logout-succes";
    }
}
