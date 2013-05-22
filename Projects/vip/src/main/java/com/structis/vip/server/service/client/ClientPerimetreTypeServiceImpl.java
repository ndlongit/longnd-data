package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientPerimetreTypeService;
import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.bean.domain.PerimetreType;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomPerimetreService;
import com.structis.vip.server.service.domain.DomPerimetreTypeService;
import com.structis.vip.shared.exception.ChantierTypeException;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.model.PerimetreTypeModel;

@Service("clientPerimetreTypeService")
public class ClientPerimetreTypeServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientPerimetreTypeService {

    /**
	 * 
	 */
    private static final long serialVersionUID = 8827164663509859578L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientPerimetreTypeServiceImpl.class);

    @Autowired
    private DomPerimetreTypeService domPerimetreTypeService;

    @Autowired
    private DomPerimetreService domPerimetreService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @Override
    public List<PerimetreTypeModel> getPerimetreTypes(final String entiteId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientPerimetreTypeServiceImpl.this.domPerimetreTypeService.getPerimetreTypes(entiteId);
            }
        };
        return (List<PerimetreTypeModel>) this.callManager(callBack);
    }

    @Override
    public Boolean delete(PerimetreTypeModel model) {
        List<Perimetre> perimetres = this.domPerimetreService.findByPerimetreTypeId(model.getPtyId());
        if (perimetres.isEmpty() == false) {
            throw new ChantierTypeException(ExceptionType.PERIMETRE_TYPE_DELETE_EIXST);
        }

        PerimetreType dm = (PerimetreType) this.modelBeanMapper.map(model);
        this.domPerimetreTypeService.delete(dm);

        return true;
    }

    @Override
    public PerimetreTypeModel insert(PerimetreTypeModel model) {
        PerimetreType doc = (PerimetreType) this.modelBeanMapper.map(model);
        doc = this.domPerimetreTypeService.insert(doc);
        return (PerimetreTypeModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public PerimetreTypeModel update(PerimetreTypeModel model) {
        PerimetreType doc = (PerimetreType) this.modelBeanMapper.map(model);
        doc = this.domPerimetreTypeService.update(doc);
        return (PerimetreTypeModel) this.modelBeanMapper.map(doc);
    }

}
