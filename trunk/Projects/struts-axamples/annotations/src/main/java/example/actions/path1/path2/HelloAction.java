package example.actions.path1.path2;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Acts as a controller to handle actions related to registering a user.
 * 
 * @author bruce phillips
 * 
 */
//@Namespace("p1")
public class HelloAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(HelloAction.class.getName());

    private String message;

    @Override
    public String execute() throws Exception {
        logger.info("In execute method of class Hello");

        this.message = "Hello from Struts 2 with no XML configuration.";

        return SUCCESS;

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
