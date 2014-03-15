package org.java.demo.action.base;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public abstract class AbstractAction extends ActionSupport implements Preparable {

    private static final long serialVersionUID = 1L;
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    public static final String PREPARE = "prepare";

    public static final String TYPE_REDIRECT_ACTION = "redirectAction";

    @Override
    public void prepare() throws Exception {

        // Sub-classes should implement this method as needed
    }
}
