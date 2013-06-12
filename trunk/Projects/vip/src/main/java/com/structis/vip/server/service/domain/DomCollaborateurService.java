package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.CollaborateurFormation;
import com.structis.vip.server.bean.domain.DelegantPerimetre;
import com.structis.vip.server.bean.domain.DelegatairePerimetre;
import com.structis.vip.server.bean.domain.core.business.KeyValueVM;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomCollaborateurService extends GenericEntityService<Collaborateur, Integer> {

    public List<Collaborateur> getAllCollaborateurs();

    public List<Collaborateur> getAllCollaborateursByEntiteId(String entiteId, Boolean sortie);

    public List<Collaborateur> getAllCollaborateursByEntiteId(String entiteId, Boolean sortie, String sortedField, Integer sortDir, Integer start,
            Integer pageSize);

    public List<Collaborateur> getAllDelegantsByEntiteId(String entiteId);

    public List<Collaborateur> getAllDelegatairesByEntiteId(String entiteId);

    public Collaborateur findByIdBycn(String idBycn);

    public Collaborateur update(Collaborateur collaborateur);

    public Collaborateur updateAndFormation(Collaborateur collaborateur);

    public Collaborateur insert(Collaborateur collaborateur);

    public List<Collaborateur> findByName(String name, String entityId, Boolean sortie);

    public List<Collaborateur> findByName(String name, String entityId, Boolean sortie, String sortedField, Integer sortDir, Integer start,
            Integer pageSize);

    public List<Collaborateur> findByPerimetre(String perimetreId);

    public List<CollaborateurFormation> findByCollaborateurId(Integer colId);

    public List<CollaborateurFormation> findByFormationId(Integer forId);

    public Collaborateur updatePerimetreDelegant(Integer colId, String perId);

    public Collaborateur updatePerimetreDelegataire(Integer colId, String perId);

    public List<Collaborateur> getAllDelegantsByPerimeter(String perId, String entiteId);

    public List<Collaborateur> getAllDelegatairesByPerimeter(String perId, String entiteId,Boolean level);

    public Boolean deleteFormation(Integer id);

    public List<Collaborateur> getDelegantsByNatureAndPerimetre(String perId, String ptyId, String entId, Integer natureId);

    public List<KeyValueVM> getDelegatairesKeyValueByEntiteId(String entiteId);

    public void deleteDelegantPerimetres(Integer delegantId);

    public void insertDelegantPerimetre(DelegantPerimetre item);

    public List<DelegantPerimetre> findPerimetresByDelegant(Integer colId);

    public void deleteDelegatairePerimetres(Integer id);

    public void insertDelegatairePerimetre(DelegatairePerimetre doc);

    public List<DelegatairePerimetre> findPerimetresByDelegataire(Integer id);

    public Collaborateur findById(Integer colId);

    public Long countByName(String name, String entId, Boolean sortie);

    public Long countAllCollaborateursByEntiteId(String entId, Boolean sortie);

}
