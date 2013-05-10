package org.java.demo.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;

public class AppAuthenticationProcessingFilter extends AuthenticationProcessingFilter {

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        super.onSuccessfulAuthentication(request, response, authResult);

        // Do something here
        // request.getSession().setAttribute("myValue", "My value is set");
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException {
        super.onUnsuccessfulAuthentication(request, response, failed);

        // request.getSession().getAttribute(SPRING_SECURITY_LAST_EXCEPTION_KEY);
    }

}
