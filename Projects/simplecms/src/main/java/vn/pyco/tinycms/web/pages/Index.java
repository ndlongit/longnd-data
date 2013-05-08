// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.pages;

import static vn.pyco.tinycms.web.services.NoBodyResponse.NOT_FOUND;
import static vn.pyco.tinycms.web.services.NoBodyResponse.NOT_MODIFIED;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;
import org.springframework.security.AccessDecisionManager;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.context.SecurityContextHolder;

import vn.pyco.tinycms.model.Authority;
import vn.pyco.tinycms.model.Page;
import vn.pyco.tinycms.model.Site;
import vn.pyco.tinycms.services.SiteService;
import vn.pyco.tinycms.utils.SecurityConstants;
import vn.pyco.tinycms.web.services.PageRenderService;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@SuppressWarnings("unused")
public class Index {
    @Property
    private PageData _data;
    
    @Inject
    private SiteService _siteService;
    
    @Inject
    private PageRenderService _pageRenderService;
    
    @Inject
    @Service("tinyCMSAccessDecisionManager")
    private AccessDecisionManager _accessDecisionManager;
    
    @Inject
    private Request _request;
    
    @Inject
    private Response _response;
    
    Object onActivate(Object[] context) {
        // Start - for testing purpose only
        if(context.length == 0) {
            return vn.pyco.tinycms.web.pages.admin.Index.class;
        }
        // End - for testing purpose only
        
        String path = convertToPath(context);
        Page page = _siteService.getPageByPath(path);
        if (page == null) {
            return NOT_FOUND;
        }
         
        String siteAlias = context[0].toString();
        Site site = _siteService.getSite(siteAlias);
        if (!authenticate(page)) {
            String targetUrl = _request.getPath();
            _request.getSession(true).setAttribute(SecurityConstants.FO_TARGET_URL, targetUrl);
            return Forbidden.class;
        }
        
        if (isNotModified(page)) {
            return NOT_MODIFIED;
        }
        
        String content = _pageRenderService.renderPage(site, page);
        // set response header-fields
        setCacheHeaders(_response, page);
        
        _data = new PageData(site, page, content);
                
        return null;
    }
    
    private String convertToPath(Object[] args) {
        StringBuilder builder = new StringBuilder();
        for (Object arg : args) {
            builder.append("/" + arg);
        }
        
        return builder.toString();
    }
    
    private boolean authenticate(Page page) {
        if (!page.isSecured()) {
            return true;
        }
        
        boolean result = true;
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ConfigAttributeDefinition config = new ConfigAttributeDefinition(new String[] {Authority.ROLE_FOUSER});
        try {
            _accessDecisionManager.decide(authentication, page, config);
        } catch (AccessDeniedException ex) {
            result = false;
        }
        
        return result;
    }
    
    private boolean isNotModified(Page page) {
        //long modifiedSince = _request.getDateHeader("If-Modified-Since");
        //return modifiedSince >= page.getUpdatedDate().getTime();
        return false;
    }
    
    private void setCacheHeaders(Response response, Page page) {
        //response.setDateHeader(HttpHeaderConstants.LAST_MODIFIED, page.getUpdatedDate().getTime());
        //response.setHeader(HttpHeaderConstants.ETAG, page.getETag());
    }
    
    public static class PageData {
        private final Site _site;
        private final Page _page;
        private final String _pageContent;
        
        public PageData(Site site, Page page, String pageContent) {
            _site = site;
            _page = page;
            _pageContent = pageContent;
        }
        
        /**
         * @return the site
         */
        public Site getSite() {
            return _site;
        }
        
        /**
         * @return the page
         */
        public Page getPage() {
            return _page;
        }        
        
        public String getTitle() {
            return _page.getTitle();
        }
        
        public String getCssCode() {
            StringBuilder builder = new StringBuilder();
            if (_site.getCssCode() != null) {
                builder.append(_site.getCssCode());
            }
            if (_page.getMasterTemplate().getCssCode() != null) {
                builder.append(_page.getMasterTemplate().getCssCode());
            }
            
            return builder.toString();
        }
        
        public String getJsCode() {
            StringBuilder builder = new StringBuilder();
            if (_site.getJsCode() != null) {
                builder.append(_site.getJsCode());
            }
            if (_page.getMasterTemplate().getJsCode() != null) {
                builder.append(_page.getMasterTemplate().getJsCode());
            }
            
            return builder.toString();
        }
        
        public String getContent() {
            return _pageContent;
        }
    }
}
