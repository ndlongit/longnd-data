package org.java.demo.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.java.demo.action.base.AbstractAction;

@Actions(value = { @Action("home"), @Action("index"), @Action("trang-chu") })
@Result(location = "/index.jsp")
public class HomeAction extends AbstractAction {
}
