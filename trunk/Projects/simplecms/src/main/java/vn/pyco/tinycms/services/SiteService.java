// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services;

import java.util.List;

import vn.pyco.tinycms.model.Page;
import vn.pyco.tinycms.model.Section;
import vn.pyco.tinycms.model.Site;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public interface SiteService {
    String SERVICE_ID = "siteService";
    
    List<Site> getAvailableSites();
    
    Site getSite(String alias);
    
    Page getPageByPath(String path);
    
    void createSite(Site site);
    
    void createSection(Section section);
    
    void createPage(Page page);
}
