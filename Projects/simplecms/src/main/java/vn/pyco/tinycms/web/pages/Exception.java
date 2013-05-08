// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.pages;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.services.ExceptionReporter;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@SuppressWarnings("unused")
public class Exception implements ExceptionReporter {
    @Property
    private String _exceptionMessage;
    
    @Property
    private String _exceptionTrace;
    
    /*
     * @see org.apache.tapestry5.services.ExceptionReporter#reportException(java.lang.Throwable)
     */
    @Override
    public void reportException(Throwable exception) {
        _exceptionMessage = exception.getLocalizedMessage();
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(output);
        exception.printStackTrace(writer);
        writer.flush();
        try {
            _exceptionTrace = output.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // no-op
        }
    }   
}
