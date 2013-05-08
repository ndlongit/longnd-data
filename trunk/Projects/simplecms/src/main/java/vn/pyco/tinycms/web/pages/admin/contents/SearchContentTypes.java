// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.pages.admin.contents;

import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.annotation.Secured;

import vn.pyco.tinycms.model.ContentType;
import vn.pyco.tinycms.services.ContentTypeService;
import vn.pyco.tinycms.web.base.AdminPage;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Secured("ROLE_WRITER")
public class SearchContentTypes extends AdminPage {
    @Inject
    private ContentTypeService _contentTypeService;
    private ContentType _contentType;
    private String _contentTypeName;

    public String getContentTypeName() {
        return _contentTypeName;
    }

    public void setContentTypeName(String contentTypeName) {
        _contentTypeName = contentTypeName;
    }

    private String _contentTypeDescription;
    @Persist
    private List<ContentType> _contentTypeList;

    public ContentType getContentType() {
        return _contentType;
    }

    public void setContentType(ContentType contentType) {
        _contentType = contentType;
    }

    public String getContentTypeDescription() {
        return _contentTypeDescription;
    }

    public void setContentTypeDescription(String contentTypeDescription) {
        _contentTypeDescription = contentTypeDescription;
    }

    public List<ContentType> getContentTypeList() {
        return _contentTypeList;
    }

    public void setContentTypeList(List<ContentType> contentTypeList) {
        _contentTypeList = contentTypeList;
    }

    @SetupRender
    public void beforeRender() {
        _contentTypeList = _contentTypeService.findAll();
    }

    public boolean hasDataList() {
        boolean b = _contentTypeList != null && _contentTypeList.size() > 0;
        return b;
    }

//    Object onActivate() {
//        return vn.pyco.tinycms.web.pages.admin.contents.InputContent.class;
//    }

    Object onPassivate() {
        return "a value";
    }
}
