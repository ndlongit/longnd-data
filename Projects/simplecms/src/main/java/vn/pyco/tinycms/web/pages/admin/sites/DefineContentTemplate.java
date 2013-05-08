// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.pages.admin.sites;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.annotation.Secured;

import vn.pyco.tinycms.model.ContentPageTemplate;
import vn.pyco.tinycms.services.ContentPageTemplateService;
import vn.pyco.tinycms.web.base.AdminPage;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Secured("ROLE_ADMIN")
public class DefineContentTemplate extends AdminPage {

    @Inject
    private ContentPageTemplateService _contentPageTemplateService;

    @Persist
    private ContentPageTemplate _contentTemplate;

    private String _templateValue;

    public String getTemplateValue() {
        return _templateValue;
    }

    public void setTemplateValue(String templateValue) {
        _templateValue = templateValue;
    }

    @OnEvent(value = EventConstants.SUCCESS, component = "contentTemplateForm")
    Object submitForm() {
        _contentTemplate = new ContentPageTemplate();
        _contentTemplate.setTemplate(_templateValue);
        _contentPageTemplateService.save(_contentTemplate);
        return vn.pyco.tinycms.web.pages.admin.Index.class;
    }
}
