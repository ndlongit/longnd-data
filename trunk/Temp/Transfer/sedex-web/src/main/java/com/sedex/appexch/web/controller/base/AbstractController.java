package com.sedex.appexch.web.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public abstract class AbstractController {

    protected static final String ADMIN_NAME_SPACE = "/admin";

    /** Prefix for Pre action (pre-create, pre-copy...) */
    public static final String PRE_PREFIX = "pre-";

    /** Action names - Begin */
    public static final String CREATE = "create";
    public static final String PRE_CREATE = PRE_PREFIX + CREATE;

    public static final String COPY = "copy";
    public static final String PRE_COPY = PRE_PREFIX + COPY;

    public static final String EDIT = "edit";
    public static final String PRE_EDIT = PRE_PREFIX + EDIT;

    public static final String SEARCH = "search";
    public static final String PRE_SEARCH = PRE_PREFIX + SEARCH;

    public static final String LIST = "list";
    public static final String DELETE = "delete";
    /** Action names - End */

    protected Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpSession session;

    protected ModelAndView initModelAndView(String viewName) {
        ModelAndView mnv = new ModelAndView(viewName);
        return mnv;
    }

    protected String getFullUrl(String actionName) {
        return this.request.getContextPath() + actionName;
    }
}
