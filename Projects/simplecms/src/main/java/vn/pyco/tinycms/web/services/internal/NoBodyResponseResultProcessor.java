// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services.internal;

import java.io.IOException;

import org.apache.tapestry5.services.ComponentEventResultProcessor;
import org.apache.tapestry5.services.Response;

import vn.pyco.tinycms.web.services.NoBodyResponse;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class NoBodyResponseResultProcessor implements ComponentEventResultProcessor<NoBodyResponse> {
    private final Response _response;
    
    public NoBodyResponseResultProcessor(Response response) {
        _response = response;
    }
    
    /*
     * @see org.apache.tapestry5.services.ComponentEventResultProcessor#processResultValue(java.lang.Object)
     */
    @Override
    public void processResultValue(NoBodyResponse error) throws IOException {
        _response.sendError(error.getStatusCode(), error.getMessage());
    }
}
