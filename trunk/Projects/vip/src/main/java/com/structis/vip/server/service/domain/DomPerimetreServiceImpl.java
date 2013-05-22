package com.structis.vip.server.service.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.core.Constants;
import com.structis.vip.server.dao.PerimetreDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;
import com.structis.vip.shared.model.PerimetreModel;

@Service("domPerimetreService")
public class DomPerimetreServiceImpl extends GenericEntityServiceImpl<Perimetre, String> implements DomPerimetreService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomPerimetreServiceImpl.class);

    @Autowired
    @Qualifier("perimetreDao")
    private PerimetreDao perimetreDao;

    @Override
    public GenericDao<Perimetre, String> getDao() {
        return this.perimetreDao;
    }

    @Override
    public Perimetre getNew() {
        return new Perimetre();
    }

    @Override
    public Perimetre getNewWithDefaults() {
        return this.getNew();
    }

    /**
     * Get all perimetre for an entity
     */
    @Override
    public List<Perimetre> findPerimetreByEntite(String entiteId) {
        return this.perimetreDao.findPerimetreByEntite(entiteId);
    }

    /**
     * Get list of 1st level perimetre for 1 entity
     */
    @Override
    public List<Perimetre> findFirstLevelPerimetreByEntite(String entiteId) {
        return this.perimetreDao.findFirstLevelPerimetreByEntite(entiteId);
    }

    @Override
    public List<PerimetreModel> getTreeModel(String entiteId, String perimetreId) {
        return this.perimetreDao.getTreeModel(entiteId, perimetreId);
    }

    @Override
    public Boolean insert(Perimetre perimetre) {
        try {
            this.save(perimetre);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(Perimetre perimetre) {
        if (perimetre.getEntite().getEntId().equals(Constants.ENTITE_ID_ETDE)) {
            return this.perimetreDao.update(perimetre);
        } else {
            boolean isBreak = false;
            if (perimetre.getChantierType() != null && perimetre.getChantierType().getLabel().equalsIgnoreCase("Parfait achevement")) {
                this.findById(perimetre.getPerId());
            }
            if (!isBreak) {
                return this.perimetreDao.update(perimetre);
            } else {
                return false;
            }
        }
    }

    @Override
    public Perimetre findById(String id) {
        return this.getByPrimaryKey(id);
    }

    @Override
    public List<Perimetre> findFirstLevelPerimetre() {
        return this.perimetreDao.findFirstLevelPerimetre();
    }

    @Override
    public Integer getNewIndex() {
        return this.perimetreDao.getNewIndex();
    }

    @Override
    public List<Perimetre> getTreeModelByParent(String entiteId, String perimetreId) {
        return this.perimetreDao.getTreeModelByParent(entiteId, perimetreId);
    }

    @Override
    public List<Perimetre> findParents(String perimetreId) {
        List<Perimetre> parents = new ArrayList<Perimetre>();
        Perimetre perimetre = this.findById(perimetreId);
        while (perimetre.getParent() != null) {
            parents.add(perimetre.getParent());
            perimetre = this.findById(perimetre.getParent().getPerId());
        }
        return parents;
    }

    @Override
    public List<Perimetre> findPerimetreByEntiteJuri(Integer entiteJuriId) {
        return this.perimetreDao.findPerimetreByEntiteJuri(entiteJuriId);
    }

    @Override
    public List<Perimetre> findPerimetreByLanguage(Integer languageId) {
        return this.perimetreDao.findPerimetreByLanguage(languageId);
    }

    @Override
    public List<Perimetre> findByChantierTypeId(Integer chantierTypeId) {
        return this.perimetreDao.findByChantierTypeId(chantierTypeId);
    }

    @Override
    public boolean isPerimetreChild(String compareId, String parentPer) {
        return this.perimetreDao.isPerimetreChildOf(compareId, parentPer);
    }

    @Override
    public List<Perimetre> findByPerimetreTypeId(String ptyId) {
        return this.perimetreDao.findByPerimetreTypeId(ptyId);
    }

    @Override
    public Integer hasReferenceInDelegationOrControlOrPerimetre(String perId) {
        return this.perimetreDao.hasReferenceInDelegationOrControlOrPerimetre(perId);
    }

    @Override
    public Integer hasReferenceInUserCollaborateur(String perId) {
        return this.perimetreDao.hasReferenceInUserCollaborateur(perId);
    }

    @Override
    public void clearReferenceToPerimetreInUserCollaborateur(String perId) {
        this.perimetreDao.clearReferenceToPerimetreInUserCollaborateur(perId);
    }

    @Override
    public List<Perimetre> findAndDeleteOrphanPerimetresNotInArgosByEntite(String entiteId, String parentId, List<String> idsInArgos) {
        return this.perimetreDao.findAndDeleteOrphanPerimetresNotInArgosByEntite(entiteId, parentId, idsInArgos);
    }

    @Override
    public List<Perimetre> findByPerimetreParent(String perId) {
        return this.perimetreDao.findByPerimetreParent(perId);
    }
}
