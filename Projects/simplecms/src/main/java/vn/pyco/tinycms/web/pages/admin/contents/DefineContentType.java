// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.pages.admin.contents;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.internal.SelectModelImpl;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.springframework.security.annotation.Secured;

import vn.pyco.tinycms.model.ContentProperty;
import vn.pyco.tinycms.model.ContentType;
import vn.pyco.tinycms.model.PropertyType;
import vn.pyco.tinycms.services.ContentTypeService;
import vn.pyco.tinycms.services.PropertyTypeService;
import vn.pyco.tinycms.web.base.AdminPage;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Secured("ROLE_ADMIN")
@SuppressWarnings("unused")
public class DefineContentType extends AdminPage {

    @Persist
    private ContentType _contentType;
    @Inject
    private ContentTypeService _contentTypeService;
    @Inject
    private PropertyTypeService _propertyTypeService;

    @Inject
    private Request _request;
    @Persist
    private String _code;
    @Persist
    private String _name;
    @Persist
    private int _order;
    @Persist
    private String _constraints;
    @Persist
    private String _propertyTypeName;

    @Persist
    private String _contentTypeName;
    @Persist
    private String _contentTypeDescription;

    @Property
    private ContentProperty _contentProperty;

    @Property
    private ContentProperty _aProperty;

    @Property
    private PropertyType _aPropertyType;

    private SelectModel _model;
    private SelectModel _orderModel;

    public SelectModel getOrderModel() {
        return _orderModel;
    }

    public void setOrderModel(SelectModel orderModel) {
        _orderModel = orderModel;
    }

    public SelectModel getModel() {
        return _model;
    }

    public void setModel(SelectModel model) {
        _model = model;
    }

    public String getContentTypeDescription() {
        return _contentTypeDescription;
    }

    public void setContentTypeDescription(String contentTypeDescription) {
        this._contentTypeDescription = contentTypeDescription;
    }

    public String getContentTypeName() {
        return _contentTypeName;
    }

    public void setContentTypeName(String contentTypeName) {
        this._contentTypeName = contentTypeName;
    }

    public ContentType getContentType() {
        return _contentType;
    }

    public void setContentType(ContentType contentType) {
        _contentType = contentType;
    }

    public String getCode() {
        return _code;
    }

    public void setCode(String code) {
        this._code = code;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getConstraints() {
        return _constraints;
    }

    public void setConstraints(String constraints) {
        this._constraints = constraints;
    }

    public int getOrder() {
        return this._order;
    }

    @Validate("required")
    public void setOrder(int order) {
        this._order = order;
    }

    public String getPropertyTypeName() {
        return _propertyTypeName;
    }

    @Validate("required")
    public void setPropertyTypeName(String propertyTypeName) {
        _propertyTypeName = propertyTypeName;
    }

    @SetupRender
    public void beforeRender() {
        _model = createPropertyTypesNameModel();
        _orderModel = createOrderModel();
    }

    public SelectModel createPropertyTypesNameModel() {
        List<OptionModel> options = new ArrayList<OptionModel>();
        List<PropertyType> l = _propertyTypeService.findAll();
        for (PropertyType propertyType : l) {
            options.add(new OptionModelImpl(propertyType.getName()));
        }
        return new SelectModelImpl(null, options);
    }

    public SelectModel createOrderModel() {
        List<OptionModel> options = new ArrayList<OptionModel>();
        List<ContentProperty> l = _contentType.getProperties();
        if (l == null) {
            l = new ArrayList<ContentProperty>();
        }

        for (int i = 0; i <= l.size(); i++) {
            options.add(new OptionModelImpl(i + ""));
        }
        return new SelectModelImpl(null, options);
    }

    @OnEvent(value = EventConstants.SUCCESS, component = "contentTypeForm")
    Object submitContentTypeFormForm() {
        if (_contentType == null) {
            _contentType = new ContentType();
        }
        _contentType.setName(_contentTypeName);
        _contentType.setDescription(_contentTypeDescription);
        _contentTypeService.saveContentType(_contentType);
        return vn.pyco.tinycms.web.pages.admin.Index.class;
    }

    @OnEvent(value = EventConstants.SUCCESS, component = "contentPropertiesForm")
    Object submitPropertiesForm() {
        PropertyType propertyTypeObj = _propertyTypeService.getPropertyTypeByName(_propertyTypeName);
        _contentProperty = new ContentProperty();
        _contentProperty.setCode(_code);
        _contentProperty.setName(_name);
        _contentProperty.setConstraints(_constraints);
        _contentProperty.setOrder(_order);
        _contentProperty.setPropertyType(propertyTypeObj);
        if (_contentType == null) {
            _contentType = new ContentType();
        }

        List<ContentProperty> properties = _contentType.getProperties();
        if(properties == null) {
            properties = new ArrayList<ContentProperty>();
        }
        for (int i = _order; i < properties.size(); i++) {
            ContentProperty contentProperty = properties.get(i);
            contentProperty.setOrder(contentProperty.getOrder() + 1);
        }

        if (_order >= properties.size()) {
            //Add to end of list
            properties.add(_contentProperty);
        } else {
            properties.add(_order, _contentProperty);
        }

        _contentType.setProperties(properties);
        return null;
    }

    public boolean hasProperties() {
        List<ContentProperty> proList = null;
        if (_contentType != null) {
            proList = _contentType.getProperties();
        }
        boolean b = (proList != null && proList.size() > 0);
        return b;
    }

    void onActivate() {
        this.onActivate(false);
    }

    void onActivate(boolean init) {
        if (init) {
            _contentType = new ContentType();
            this._code = null;
            this._name = null;
            this._order = 0;
            this._constraints = null;
            this._propertyTypeName = null;
            this._contentTypeName = null;
            this._contentTypeDescription = null;
        }
    }
}
