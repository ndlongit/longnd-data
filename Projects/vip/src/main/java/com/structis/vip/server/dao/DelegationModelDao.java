package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.DelegationMdl;
import com.structis.vip.server.bean.domain.DocumentMdl;
import com.structis.vip.server.dao.support.GenericDao;

public interface DelegationModelDao extends GenericDao<DelegationMdl, Integer> {

    List<DocumentMdl> getDocumentModels(Integer langId, String ptyId, Integer natureId, Integer collaboraterType);

    List<DocumentMdl> getDocumentModels(Integer langId, String ptyId, Integer natureId, String entId, Integer collaboraterType);

    Integer getGroup(Integer langId, String ptyId, Integer natureId, Integer collaboraterType);

    Integer getGroup(Integer langId, String ptyId, Integer natureId, String entId, Integer collaboraterType);

    List<DelegationMdl> getDelegationModels(String entId, Integer natureId);

    List<DelegationMdl> getDelegationModelsByEntite(String entId);

    List<DelegationMdl> getDelegationModelsByGroup(Integer group);

    DelegationMdl update(DelegationMdl dm);

    DelegationMdl insert(DelegationMdl dm);

    DelegationMdl insert(DelegationMdl dm, Integer group);

    Integer getLastGroup();

    Boolean deleteByGroup(Integer group);

    void updateBatch(List<DelegationMdl> dms);

    void insertBatch(List<DelegationMdl> dms);

    Boolean existOtherDelegationModel(Integer demId, String entId, Integer natureId, String ptyId, Integer delegantType, Integer langId);

    List<DelegationMdl> findByLanguageId(Integer languageId);

    List<DelegationMdl> findByNatureId(Integer natureId);

    Boolean hasMultipleDelegation(Integer dnId, String enId, String perId);

    Boolean getHasMutiDelegataire(Integer group);
}
