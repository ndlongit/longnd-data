// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.pages;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.internal.services.LinkImpl;
import org.apache.tapestry5.internal.services.RequestPathOptimizer;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;

import vn.pyco.tinycms.utils.SecurityConstants;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class SignOut {
    @Inject
    private RequestPathOptimizer _optimizer;

    @Inject
    private Response _response;
    

    Link onActivate() throws ServletException, IOException {
        return new LinkImpl(SecurityConstants.J_LOGOUT, true, false, _response, _optimizer);
    }
}
