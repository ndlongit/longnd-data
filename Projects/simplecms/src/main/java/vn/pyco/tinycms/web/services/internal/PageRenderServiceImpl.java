// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.chenillekit.template.services.TemplateService;

import vn.pyco.tinycms.model.Content;
import vn.pyco.tinycms.model.Node;
import vn.pyco.tinycms.model.Page;
import vn.pyco.tinycms.model.PageModel;
import vn.pyco.tinycms.model.Site;
import vn.pyco.tinycms.services.ContentService;
import vn.pyco.tinycms.web.services.PageRenderService;
import edu.emory.mathcs.backport.java.util.Collections;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class PageRenderServiceImpl implements PageRenderService {
    private final String _contextPath;
    private final TemplateService _templateService;
    private final ContentService _contentService;
    
    public PageRenderServiceImpl(String contextPath, TemplateService templateService, ContentService contentService) {
        _contextPath = contextPath;
        _templateService = templateService;
        _contentService = contentService;
    }
    
    /*
     * @see vn.pyco.tinycms.web.services.PageRenderService#renderPage(vn.pyco.tinycms.model.Page)
     */
    @Override
    public String renderPage(Site site, Page page) {
        // get content from FreeMarker template engine 
        Map<String, Object> paramMap = buildParamMap(site, page);
        ByteArrayInputStream input = buildTemplateStream(site, page);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        _templateService.mergeDataWithStream(input, output, paramMap);
        String text = "";
        try {
            text = output.toString("utf-8");
        } catch (UnsupportedEncodingException e) {
            // no-op
        }
                
        return text;
    }
    
    private Map<String, Object> buildParamMap(Site site, Page page) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<PageModel> models = page.getModels();
        for (PageModel model : models) {
            paramMap.put(model.getName(), getPageModelValue(model));
        }
        paramMap.put("SITE", site);
        paramMap.put("PAGE", page);
        
        return paramMap;
    }
    
    private Object getPageModelValue(PageModel model) {
        if (Content.class.getName().equals(model.getType())) {
            int contentId = Integer.parseInt(model.getValue());
            Map<String, Object> map = _contentService.buildContentDataMap(contentId);
            
            return map;
        }
        
        return model.getValue();
    }
    
    private ByteArrayInputStream buildTemplateStream(Site site, Page page) {
        String masterText = page.getMasterTemplate().getTemplate();
        String body = page.getContentTemplate().getTemplate();
        String search = buildSearchForm();
        String rss = buildRssButton();
        StringBuilder navbar = new StringBuilder();
        buildNavbar(site, page.getPath(), navbar);
        
        String result = masterText;
        result = result.replace("#{tinycms:body}", body);
        result = result.replace("#{tinycms:navbar}", navbar.toString());
        result = result.replace("#{tinycms:search}", search);
        result = result.replace("#{tinycms:rss}", rss);
        
        ByteArrayInputStream input = null;
        try {
            input = new ByteArrayInputStream(result.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            // do nothing
        }
        
        return input;
    }
    
    private void buildNavbar(Node node, String activePath, StringBuilder builder) {
        if (node == null || node.getChildNodes() == null) {
            return;
        }
        
        List<Node> childNodes = node.getChildNodes();
        Collections.sort(childNodes);
        builder.append("<ul>");
        for (Node childNode : childNodes) {
            builder.append("<li>");
            String path = childNode.getPath();
            String title = childNode.getTitle();
            if (path.equals(activePath)) {
                builder.append(String.format("<span><strong>%s</strong></span>", title));
            } else {
                builder.append(String.format("<a href='%s%s'>%s</a>", _contextPath, path, title));
            }
            if (!childNode.getChildNodes().isEmpty()) {
                buildNavbar(childNode, activePath, builder);
            }
            builder.append("</li>");
        }
        builder.append("</ul>");
    }
    
    private String buildSearchForm() {
        StringBuilder builder = new StringBuilder();
        builder.append("<input type='text' name='search' />");
        builder.append("<input type='submit' value='search' />");
        
        return builder.toString();
    }
    
    private String buildRssButton() {
        StringBuilder builder = new StringBuilder();
        builder.append("<a href='#'>");
        builder.append("<img src='/tinycms/assets/ctx/1.0-SNAPSHOT/images/rss.png'/>");
        builder.append("</a>");
        
        return builder.toString();
    }
}
