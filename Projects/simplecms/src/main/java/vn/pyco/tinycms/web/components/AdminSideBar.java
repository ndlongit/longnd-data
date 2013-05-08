// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class AdminSideBar {
    @Parameter(defaultPrefix=BindingConstants.LITERAL)
    private String _activeItem;

    public String getItemCssClass(String item) {
        return (item.equals(_activeItem))? "Active" : "";
    }

    private boolean _init = true;

    public boolean isInit() {
        return _init;
    }

    public void setInit(boolean init) {
        _init = init;
    }

}
