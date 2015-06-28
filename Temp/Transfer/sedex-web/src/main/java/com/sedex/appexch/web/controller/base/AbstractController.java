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

    /** Suffix for Pre actions (create-pre, copy-pre...) */
    public static final String PRE_SUFFIX = "-pre";

    /** Action names - Begin */
    public static final String CREATE = "/create";
    public static final String PRE_CREATE = CREATE + PRE_SUFFIX;

    public static final String COPY = "/copy";
    public static final String PRE_COPY = COPY + PRE_SUFFIX;

    public static final String EDIT = "/edit";
    public static final String PRE_EDIT = EDIT + PRE_SUFFIX;

    public static final String SEARCH = "/search";
    public static final String PRE_SEARCH = SEARCH + PRE_SUFFIX;

    public static final String LIST = "/list";
    public static final String DELETE = "/delete";
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

    protected String getFullUrl(String nameSpace, String actionName) {
        return this.request.getContextPath() + nameSpace + actionName;
    }
}
