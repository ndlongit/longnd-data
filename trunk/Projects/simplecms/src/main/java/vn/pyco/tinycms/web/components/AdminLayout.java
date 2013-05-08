// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Secure;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

import vn.pyco.tinycms.model.Site;
import vn.pyco.tinycms.services.SiteService;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@SuppressWarnings("unused")
@Secure
public class AdminLayout {
    @Property
    @Parameter(required=true)
    private String _title;
    
    @Property
    @Parameter(defaultPrefix=BindingConstants.LITERAL)
    private Block _sidebar;
    
    @Inject
    private SiteService _siteService;
    
    @Parameter
    @Property
    private List<Site> _availableSites;
    
    @Property
    private Site _site;
    
    @SetupRender
    void setupRender() {
        _availableSites = _siteService.getAvailableSites();
    }
}
