// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services.init;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.pyco.tinycms.dao.NodeDao;
import vn.pyco.tinycms.dao.PageTemplateDao;
import vn.pyco.tinycms.model.ContentPageTemplate;
import vn.pyco.tinycms.model.MasterPageTemplate;
import vn.pyco.tinycms.model.Node;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class SiteDataInitializer extends AbstractDataInitializer {
    @Autowired
    private NodeDao _nodeDao;
    
    @Autowired
    private PageTemplateDao _pageTemplateDao;
    
    private Set<MasterPageTemplate> _masterTemplates;
    private Set<ContentPageTemplate> _contentTemplates;
    private Set<Node> _nodes;
    
    /**
     * @param masterTemplates the masterTemplates to set
     */
    @Required
    public void setMasterTemplates(Set<MasterPageTemplate> masterTemplates) {
        _masterTemplates = masterTemplates;
    }
    
    /**
     * @param contentTemplates the contentTemplates to set
     */
    @Required
    public void setContentTemplates(Set<ContentPageTemplate> contentTemplates) {
        _contentTemplates = contentTemplates;
    }
    
    /**
     * @param nodes the nodes to set
     */
    @Required
    public void setNodes(Set<Node> nodes) {
        _nodes = nodes;
    }
    
    /*
     * @see vn.pyco.tinycms.services.DataInitializer#initializeEntities()
     */
    @Override
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public void initialize() throws Exception {
        for (MasterPageTemplate masterTemplate : _masterTemplates) {
            _pageTemplateDao.save(masterTemplate);
        }
        
        for (ContentPageTemplate contentTemplate : _contentTemplates) {
            _pageTemplateDao.save(contentTemplate);
        }
        
        for (Node node : _nodes) {
            _nodeDao.save(node);
        }
    }
}
