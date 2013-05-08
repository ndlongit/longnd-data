// [LICENCE-HEADER]
//

package vn.pyco.tinycms.web.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.AccessDecisionManager;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class IfAccessable {
    @Parameter(defaultPrefix="literal")
    private String _roles;

    @Parameter
    private boolean _negate;

    @Parameter(name="else")
    private Block _elseBlock;
    
    @Inject
    @Service("tinyCMSAccessDecisionManager")
    private AccessDecisionManager _accessDecisionManager;
    
    @Inject
    private ComponentResources _resources;

    private boolean _test;

    private boolean checkPermission() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (((null == _roles) || "".equals(_roles))) {
            return (authentication != null)? true : false;
        }

        String[] attributeTokens = StringUtils.delimitedListToStringArray(_roles, ",", " \t\r\n\f");
        ConfigAttributeDefinition config = new ConfigAttributeDefinition(attributeTokens);
        boolean result = true;
        try {
            _accessDecisionManager.decide(authentication, _resources.getContainer(), config);
        } catch (AccessDeniedException ex) {
            result = false;
        }

        return result;
    }

    void setupRender() {
        _test = checkPermission();
    }

    Object beginRender() {
        return _test != _negate ? null : _elseBlock;
    }

    boolean beforeRenderBody() {
        return _test != _negate;
    }
}
