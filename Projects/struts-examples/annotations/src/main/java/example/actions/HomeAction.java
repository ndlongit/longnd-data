package example.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;

import example.actions.base.AbstractAction;

@Actions(value = { @Action("trang-chu"), @Action("home") })
@Result(location = "/index.jsp")
public class HomeAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

}
