package com.sedex.appexch.web.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public abstract class AbstractController {

    /** Action names - Begin */
    public static final String PRE_CREATE = "preCreate";
    public static final String CREATE = "create";
    public static final String PRE_COPY = "preCopy";
    public static final String COPY = "copy";
    public static final String PRE_EDIT = "preEdit";
    public static final String EDIT = "edit";
    public static final String VIEW = "view";
    public static final String LIST = "list";
    public static final String PRE_SEARCH = "preSearch";
    public static final String SEARCH = "search";
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
        return this.request.getContextPath() + "/" + actionName;
    }
}
