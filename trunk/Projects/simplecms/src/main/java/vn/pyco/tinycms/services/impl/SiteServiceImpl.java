// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.pyco.tinycms.dao.NodeDao;
import vn.pyco.tinycms.model.Node;
import vn.pyco.tinycms.model.Page;
import vn.pyco.tinycms.model.Section;
import vn.pyco.tinycms.model.Site;
import vn.pyco.tinycms.services.SiteService;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Service(SiteService.SERVICE_ID)
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class SiteServiceImpl implements SiteService {
    @Autowired
    private NodeDao _nodeDao;
    
    /*
     * @see vn.pyco.tinycms.services.SiteService#getAvailableSites()
     */
    @Override
    public List<Site> getAvailableSites() {
        return _nodeDao.getAllChilds(null);
    }
    
    /*
     * @see vn.pyco.tinycms.services.SiteService#getSite(java.lang.String)
     */
    @Override
    public Site getSite(String alias) {
        return _nodeDao.getByAlias(null, alias, Site.class);
    }
    
    /*
     * @see vn.pyco.tinycms.services.SiteService#getPageByPath(java.lang.String)
     */
    @Override
    public Page getPageByPath(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        
        // get directly page by path
        Node node = _nodeDao.getByPath(path, Node.class);
        if (!(node instanceof Page)) {
            // the path is site or section, get default page of it
            node = _nodeDao.getDefaultPage(path);
            if (node == null) {
                // no default page, get the first page
                node = _nodeDao.getFirstPage(path);
            }
        }
        
        return (Page) node;
    }
    
    /*
     * @see vn.pyco.tinycms.services.SiteService#createSite(vn.pyco.tinycms.model.Site)
     */
    @Override
    public void createSite(Site site) {
        _nodeDao.save(site);
    }
    
    /*
     * @see vn.pyco.tinycms.services.SiteService#createSection(vn.pyco.tinycms.model.Section)
     */
    @Override
    public void createSection(Section section) {
        _nodeDao.save(section);
    }
    
    /*
     * @see vn.pyco.tinycms.services.SiteService#createPage(vn.pyco.tinycms.model.Page)
     */
    @Override
    public void createPage(Page page) {
        _nodeDao.save(page);
    }
}
