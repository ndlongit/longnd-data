// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.If;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@SuppressWarnings("unused")
public class Layout {
    @Property
    @Parameter(required=true)
    private String _title;
    
    @Property
    @Parameter(defaultPrefix=BindingConstants.LITERAL)
    private Block _jsCode;
    
    @Property
    @Parameter(defaultPrefix=BindingConstants.LITERAL)
    private Block _cssCode;
    
    @Property
    @Parameter(defaultPrefix=BindingConstants.LITERAL)
    private Block _header;
}
