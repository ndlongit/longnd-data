package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientEntiteJuridiqueService;
import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.bean.domain.EntiteJuridique;
import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDelegationService;
import com.structis.vip.server.service.domain.DomEntiteJuridiqueService;
import com.structis.vip.server.service.domain.DomPerimetreService;
import com.structis.vip.shared.exception.EntiteJuridiqueException;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.model.EntiteJuridiqueModel;

@Service("clientEntiteJuridiqueService")
public class ClientEntiteJuridiqueServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientEntiteJuridiqueService {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientEntiteJuridiqueServiceImpl.class);

    @Autowired
    private DomEntiteJuridiqueService domEntiteJuriService;

    @Autowired
    private DomPerimetreService domPerimetreService;

    @Autowired
    private DomDelegationService domDelegationService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @Override
    public EntiteJuridiqueModel findById(Integer id) {
        EntiteJuridique entite = this.domEntiteJuriService.findById(id);
        return (EntiteJuridiqueModel) this.modelBeanMapper.map(entite);
    }

    @Override
    public EntiteJuridiqueModel insert(EntiteJuridiqueModel model) {
        EntiteJuridique doc = (EntiteJuridique) this.modelBeanMapper.map(model);
        doc = this.domEntiteJuriService.insert(doc);
        return (EntiteJuridiqueModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public EntiteJuridiqueModel update(EntiteJuridiqueModel model) {
        EntiteJuridique doc = (EntiteJuridique) this.modelBeanMapper.map(model);
        doc = this.domEntiteJuriService.update(doc);
        return (EntiteJuridiqueModel) this.modelBeanMapper.map(doc);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EntiteJuridiqueModel> findByEntiteId(final String entId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientEntiteJuridiqueServiceImpl.this.domEntiteJuriService.findByEntiteId(entId);
            }
        };
        return (List<EntiteJuridiqueModel>) this.callManager(callBack);
    }

    @Override
    public boolean delete(EntiteJuridiqueModel model) {
        List<Perimetre> perimetres = this.domPerimetreService.findPerimetreByEntiteJuri(model.getId());
        if (perimetres.isEmpty() == false) {
            throw new EntiteJuridiqueException(ExceptionType.ENTITE_JUR_DELETE_EXIST_IN_PERIMETRE);
        }

        EntiteJuridique defEntiteJuri = this.domEntiteJuriService.getDefaultByEntiteId(model.getEntite().getEntId());
        if (defEntiteJuri != null && defEntiteJuri.getId().intValue() == model.getId().intValue()) {
            throw new EntiteJuridiqueException(ExceptionType.ENTITE_JUR_DELETE_DEFAULT);
        }

        List<Delegation> delegations = this.domDelegationService.findByEntiteJuriId(model.getId());
        if (delegations.isEmpty() == false) {
            throw new EntiteJuridiqueException(ExceptionType.ENTITE_JUR_DELETE_EXIST_IN_DELEGATION);
        }

        EntiteJuridique dm = (EntiteJuridique) this.modelBeanMapper.map(model);
        this.domEntiteJuriService.delete(dm);
        return true;
    }

    @Override
    public EntiteJuridiqueModel getDefaultByEntiteId(String entId) {
        EntiteJuridique entite = this.domEntiteJuriService.getDefaultByEntiteId(entId);
        return (EntiteJuridiqueModel) this.modelBeanMapper.map(entite);
    }
}
