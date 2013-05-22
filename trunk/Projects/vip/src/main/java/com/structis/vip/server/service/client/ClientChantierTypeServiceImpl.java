package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientChantierTypeService;
import com.structis.vip.server.bean.domain.ChantierType;
import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomChantierTypeService;
import com.structis.vip.server.service.domain.DomPerimetreService;
import com.structis.vip.shared.exception.ChantierTypeException;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.model.ChantierTypeModel;

@Service("clientChantierTypeService")
public class ClientChantierTypeServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientChantierTypeService {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientChantierTypeServiceImpl.class);

    @Autowired
    private DomChantierTypeService domChantierTypeService;

    @Autowired
    private DomPerimetreService domPerimetreService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<ChantierTypeModel> findChantierByEntite(final String entiteId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientChantierTypeServiceImpl.this.domChantierTypeService.findChantierByEntite(entiteId);
            }
        };
        return (List<ChantierTypeModel>) this.callManager(callBack);
    }

    @Override
    public Boolean delete(ChantierTypeModel model) {
        List<Perimetre> perimetres = this.domPerimetreService.findByChantierTypeId(model.getId());
        if (perimetres.isEmpty() == false) {
            throw new ChantierTypeException(ExceptionType.CHANTIER_TYPE_DELETE_EIXST);
        }

        ChantierType dm = (ChantierType) this.modelBeanMapper.map(model);
        this.domChantierTypeService.delete(dm);

        return true;
    }

    @Override
    public ChantierTypeModel insert(ChantierTypeModel model) {
        ChantierType doc = (ChantierType) this.modelBeanMapper.map(model);
        doc = this.domChantierTypeService.insert(doc);
        return (ChantierTypeModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public ChantierTypeModel update(ChantierTypeModel model) {
        ChantierType doc = (ChantierType) this.modelBeanMapper.map(model);
        doc = this.domChantierTypeService.update(doc);
        return (ChantierTypeModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public List<ChantierTypeModel> findAll() {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientChantierTypeServiceImpl.this.domChantierTypeService.findAll();
            }
        };
        return (List<ChantierTypeModel>) this.callManager(callBack);
    }
}
