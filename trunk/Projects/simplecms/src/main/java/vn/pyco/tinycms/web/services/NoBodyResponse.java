// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services;

import static javax.servlet.http.HttpServletResponse.*;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
public class NoBodyResponse {
    public static final NoBodyResponse FORBIDDEN = new NoBodyResponse(SC_FORBIDDEN, "Forbidden");
    public static final NoBodyResponse NOT_FOUND = new NoBodyResponse(SC_NOT_FOUND, "Not Found");
    public static final NoBodyResponse NOT_MODIFIED = new NoBodyResponse(SC_NOT_MODIFIED, "Not Modified");
        
    private final int _statusCode;
    private final String _message;

    public NoBodyResponse(int statusCode, String message) {
        _statusCode = statusCode;
        _message = message;
    }

    public int getStatusCode() {
        return _statusCode;
    }

    public String getMessage() {
        return _message;
    }
}
