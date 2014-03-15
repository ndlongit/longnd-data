package example.actions.base;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractAction extends ActionSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected Logger logger = Logger.getLogger(this.getClass().getName());

}
