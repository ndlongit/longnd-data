package example.actions.path1.path2;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;

import example.model.Person;

/**
 * Acts as a controller to handle actions related to registering a user.
 * 
 * @author bruce phillips
 * 
 */
public class RegisterAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(RegisterAction.class.getName());

    private Person personBean;

    @Override
    @Action("register-input2")
    public String input() throws Exception {
        return INPUT;

    }

    @Override
    public String execute() throws Exception {

        logger.info("In execute method of class RegisterAction");

        // call Service class to store personBean's state in database

        return SUCCESS;

    }

    public Person getPersonBean() {

        return this.personBean;

    }

    public void setPersonBean(Person person) {

        this.personBean = person;

    }

}
