package com.structis.vip.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.CollaborateurException;
import com.structis.vip.shared.model.CollaborateurFormationModel;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.KeyValueModel;

@RemoteServiceRelativePath("springGwtServices/clientCollaborateurService")
public interface ClientCollaborateurService extends RemoteService {

    List<CollaborateurModel> getAllCollaborateurs();

    List<CollaborateurModel> getAllCollaborateursByEntiteId(String entiteId, Boolean sortie);

    List<CollaborateurModel> getAllCollaborateursByEntiteId(String entiteId, Boolean sortie, String sortedField, Integer sortDir, Integer start,
            Integer pageSize);

    List<CollaborateurModel> getAllDelegantsByEntiteId(String entiteId);

    List<CollaborateurModel> getAllDelegantsByPerimeter(String perId, String entiteId, Boolean recusion);

    List<CollaborateurModel> getAllDelegatairesByPerimeter(String perId, String entiteId, Boolean recusion);

    List<CollaborateurModel> getAllDelegatairesByEntiteId(String entiteId);

    CollaborateurModel insert(CollaborateurModel model);

    CollaborateurModel update(CollaborateurModel model);

    CollaborateurModel updateAndFormation(CollaborateurModel model);

    List<CollaborateurModel> findByName(String name, String entityId, Boolean sortie);

    List<CollaborateurModel> findByName(String name, String entityId, Boolean sortie, String sortedField, Integer sortDir, Integer start,
            Integer pageSize);

    Boolean delete(CollaborateurModel model) throws CollaborateurException;

    List<CollaborateurFormationModel> findByCollaborateurId(Integer colId);

    List<CollaborateurFormationModel> findByFormationId(Integer forId);

    CollaborateurModel updatePerimetreDelegant(Integer id, String perId);

    CollaborateurModel updatePerimetreDelegataire(Integer id, String perId);

    PagingLoadResult<CollaborateurModel> getAllCollaborateursByEntiteIdPaging(PagingLoadConfig loadConfig, String entId);

    List<CollaborateurModel> getDelegantsByNatureAndPerimetre(String perId, String ptyId, String entId, Integer natureId);

    List<KeyValueModel> getAllControleursByEntiteId(String entiteIdIsByefe);

    CollaborateurModel findById(Integer colId);

    CollaborateurModel getAndUpdate(CollaborateurModel model);

    List<CollaborateurModel> findByPerimetre(String perId);

}
