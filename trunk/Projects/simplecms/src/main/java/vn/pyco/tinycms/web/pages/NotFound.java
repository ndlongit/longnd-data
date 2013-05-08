// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

import vn.pyco.tinycms.model.Site;
import vn.pyco.tinycms.services.SiteService;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@SuppressWarnings("unused")
public class NotFound {
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
