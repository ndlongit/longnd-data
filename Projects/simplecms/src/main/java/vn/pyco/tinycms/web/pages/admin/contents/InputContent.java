// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.pages.admin.contents;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.annotation.Secured;

import vn.pyco.tinycms.model.Content;
import vn.pyco.tinycms.model.ContentType;
import vn.pyco.tinycms.services.ContentTypeService;
import vn.pyco.tinycms.web.base.AdminPage;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Secured("ROLE_WRITER")
public class InputContent extends AdminPage {
    @Persist
    private Content _content;

    public Content getContent() {
        return _content;
    }

    public void setContent(Content content) {
        _content = content;
    }

    @Inject
    private ContentTypeService _contentTypeService;

    private String _contentTypeName;

    private ContentType _contentType;

    public ContentType getContentType() {
        return _contentType;
    }

    public void setContentType(ContentType contentType) {
        _contentType = contentType;
    }

    private BeanModel _model;

    public BeanModel getModel() {
        return _model;
    }

    public void setModel(BeanModel model) {
        _model = model;
    }

    public String getContentName() {
        return _contentTypeName;
    }

    public void setContentName(String contentTypeName) {
        _contentTypeName = contentTypeName;
    }

    void onActivate(String contentTypeName) {
        this._contentTypeName = contentTypeName;
    }

    @SetupRender
    public void beforeRender() {
        _contentType = _contentTypeService.getObjectByCriteria("name", _contentTypeName);
        // List<ContentProperty> contentProperties = contentType.getProperties();
        // PropertyConduitSource propertyConduitSource = createPropertyConduitSource();
        // TypeCoercer typeCoercer = null;
        // Messages messages = null;
        // ObjectLocator locator = null;
        // _model = new BeanModelImpl<Content>(Content.class,
        // propertyConduitSource, typeCoercer,
        // messages, locator);
        // for (ContentProperty contentProperty : contentProperties) {
        // _model.add(contentProperty.getCode());
        // }
        // }
        //
        // private PropertyConduitSource createPropertyConduitSource() {
        // PropertyAccessImpl access = new PropertyAccessImpl();
        // ClassFactoryImpl classFactory = new ClassFactoryImpl();
        // Collection<CoercionTuple> tuples = new ArrayList<CoercionTuple>();
        // CoercionTuple tuple = null;
        // TypeCoercerImpl typeCoercer = new TypeCoercerImpl(tuples);
        // StringInternerImpl interner = new StringInternerImpl();
        // PropertyConduitSource propertyConduitSource = new PropertyConduitSourceImpl(
        // access, classFactory, typeCoercer,
        // interner);
        // return propertyConduitSource;
    }

    @OnEvent(value = EventConstants.SUCCESS)
    Object submitForm() {
        // ContentType contentType = _content.getContentType();
        // List<ContentProperty> contentProperties = contentType.getProperties();
        // for (ContentProperty contentProperty : contentProperties) {
        // // Debug
        // System.out.println(contentProperty.getName());
        // }
        return vn.pyco.tinycms.web.pages.admin.Index.class;
    }
    void onActivate() {
        System.out.println("No arg");
    }
    void onActivate(Object[] contexts) {
        for (int i = 0; i < contexts.length; i++) {
            System.out.println(contexts[i]);
        }
    }
}
