package com.sedex.appexch.util.handler.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;

public class AccessDeniedHandler extends org.springframework.security.web.access.AccessDeniedHandlerImpl {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException,
            ServletException {

        // TODO re-implement this method to forward to Struts2 actions
         super.handle(request, response, accessDeniedException);
    }

    @Override
    public void setErrorPage(String errorPage) {
        if (errorPage == null || errorPage.trim().length() == 0) {
            errorPage = "/access-denied.jsp";
        }
        super.setErrorPage(errorPage);
    }

}
