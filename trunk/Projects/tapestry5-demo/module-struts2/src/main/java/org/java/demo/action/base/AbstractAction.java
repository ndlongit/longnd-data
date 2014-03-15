package org.java.demo.action.base;

import org.apache.log4j.Logger;
import org.java.demo.util.AppUtil;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public abstract class AbstractAction extends ActionSupport implements Preparable {

    private static final long serialVersionUID = 1L;
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    protected String action;
    protected String pageTitle;
    protected String submitButtonLabel;

    public static final String PREPARE = "prepare";

    public static final String TYPE_REDIRECT_ACTION = "redirectAction";

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSubmitButtonLabel() {
        return submitButtonLabel;
    }

    public void setSubmitButtonLabel(String submitButtonLabel) {
        this.submitButtonLabel = submitButtonLabel;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    protected static boolean isNullOrEmpty(Object value) {
        return AppUtil.isNullOrEmpty(value);
    }

    @Override
    public void prepare() throws Exception {

        // Sub-classes should implement this method as needed
    }
}
