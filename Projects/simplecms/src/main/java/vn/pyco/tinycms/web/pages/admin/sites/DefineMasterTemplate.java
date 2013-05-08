// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.pages.admin.sites;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.annotation.Secured;

import vn.pyco.tinycms.model.MasterPageTemplate;
import vn.pyco.tinycms.services.MasterPageTemplateService;
import vn.pyco.tinycms.web.base.AdminPage;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Secured("ROLE_ADMIN")
public class DefineMasterTemplate extends AdminPage {

    @Inject
    private MasterPageTemplateService _masterPageTemplateService;

    @Persist
    private MasterPageTemplate _masterTemplate;

    private String _templateValue;
    private String _cssCode;
    private String _jsCode;

    public String getTemplateValue() {
        return _templateValue;
    }

    public void setTemplateValue(String templateValue) {
        _templateValue = templateValue;
    }

    public String getCssCode() {
        return _cssCode;
    }

    public void setCssCode(String cssCode) {
        _cssCode = cssCode;
    }

    public String getJsCode() {
        return _jsCode;
    }

    public void setJsCode(String jsCode) {
        _jsCode = jsCode;
    }

    @OnEvent(value = EventConstants.SUCCESS, component = "masterTemplateForm")
    Object submitForm() {
        _masterTemplate = new MasterPageTemplate();
        _masterTemplate.setTemplate(_templateValue);
        _masterTemplate.setCssCode(_cssCode);
        _masterTemplate.setJsCode(_jsCode);
        _masterPageTemplateService.save(_masterTemplate);
        return vn.pyco.tinycms.web.pages.admin.Index.class;
    }
}
