// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.components;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Retain;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.ui.AbstractProcessingFilter;

import vn.pyco.tinycms.utils.SecurityConstants;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@SuppressWarnings("unused")
public class SignInForm {
    @Property
    private String _username;

    @Retain
    @Property
    private String _password;

    private boolean _failed;

    @Component(id="loginForm")
    private Form _form;
    
    @Inject
    private Messages _messages;
    
    @Inject
    private HttpServletRequest _request;

    @Inject
    private HttpServletResponse _response;
    
    @SetupRender
    void setupRender() {
        _failed = "true".equalsIgnoreCase(_request.getParameter("failed"));
        if(_failed) {
            String lastError = AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY;
            Object error = _request.getSession(true).getAttribute(lastError);
            if(error == null) {
                _failed = false;
            }
        }
    }

    @BeginRender
    void beginRender() {
        _form.clearErrors();
        if (_failed) {
            _form.recordError(_messages.get("message.authentication-failed"));
        }
    }

    @OnEvent(value=EventConstants.SUCCESS, component="loginForm")
    public void onSuccess() throws ServletException, IOException {
        _request.getRequestDispatcher(SecurityConstants.J_LOGIN).forward(_request, _response);
    }
}
