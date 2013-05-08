// [LICENCE-HEADER]
//

package vn.pyco.tinycms.web.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Parameter;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class IfAuthenticated {
    @Parameter
    private boolean _negate;

    @Parameter(name = "else")
    private Block _elseBlock;
    
    Object beginRender() {
        if (test() != _negate) {
            return null;
        } else {
            return _elseBlock;
        }
    }

    boolean beforeRenderBody() {
        return test() != _negate;
    }

    private boolean test() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        return authentication != null;
    }
}
