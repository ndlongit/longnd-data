package org.java.demo.action.base;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.java.demo.util.AppUtil;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public abstract class AbstractAction extends ActionSupport implements Preparable {

    private static final long serialVersionUID = 1L;
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    protected String action;
    protected String pageTitle;

    public static final String PREPARE = "prepare";
    public static final String CREATE = "create";
    public static final String EDIT = "edit";
    public static final String LIST = "list";
    public static final String VIEW = "view";
    
    public static final String VALIDATION_REQUIRED = "validation.required";
    public static final String VALIDATION_INVALID = "validation.invalid";

    public static final String TYPE_REDIRECT_ACTION = "redirectAction";

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    protected void checkRequired(String[] fieldValues, String[] fieldLabels, String[] fieldNames) {
        if (isNullOrEmpty(fieldValues) || isNullOrEmpty(fieldLabels)) {
            return;
        }

        String fieldName = null;
        for (int i = 0; i < fieldValues.length; i++) {
            if (isNullOrEmpty(fieldValues[i])) {
                if (!isNullOrEmpty(fieldNames) && fieldNames.length > i) {

                    // Also add Field Error
                    fieldName = fieldNames[i];
                }
                
                addError(getText(VALIDATION_REQUIRED, Arrays.asList(fieldLabels[i])), fieldName);
            }
        }
    }

    /**
     * Add error for Form only, not for Field
     * 
     * @param message
     */
    protected void addError(String message) {
        this.addError(message, null);
    }

    /**
     * Add error for Form and also Field
     * 
     * @param message
     * @param fieldName
     */
    protected void addError(String message, String fieldName) {
        addActionError(message);
        if (!isNullOrEmpty(fieldName)) {
            addFieldError(fieldName, message);
        }
    }

    @Override
    public void prepare() throws Exception {

        // Sub-classes should implement this method as needed
    }

    @Override
    public void validate() {

        // Sub-classes should implement this method as needed
    }
}
