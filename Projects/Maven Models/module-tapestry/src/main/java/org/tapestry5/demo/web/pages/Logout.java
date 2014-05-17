package org.tapestry5.demo.web.pages;

import org.apache.tapestry5.ioc.annotations.Inject;

public class Logout {

    @Inject
    private javax.servlet.http.HttpServletRequest request;

    public Object onActivate() {
        request.getSession().invalidate();
        return Index.class;
    }
}
