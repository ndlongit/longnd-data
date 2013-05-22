package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.Entite;
import com.structis.vip.server.bean.domain.User;
import com.structis.vip.server.dao.EntiteDao;
import com.structis.vip.server.dao.UserDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;
import com.structis.vip.shared.model.UserModel;

@Service("domEntiteService")
public class DomEntiteServiceImpl extends GenericEntityServiceImpl<Entite, String> implements DomEntiteService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomEntiteServiceImpl.class);

    @Autowired
    @Qualifier("entiteDao")
    private EntiteDao entiteDao;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public GenericDao<Entite, String> getDao() {
        return this.entiteDao;
    }

    @Override
    public Entite getNew() {
        return new Entite();
    }

    @Override
    public Entite getNewWithDefaults() {
        return this.getNew();
    }

    /**
     * Get all entites
     */
    @Override
    public List<Entite> getAllEntites() {
        return this.find();
    }

    /**
     * Get entity for user
     */
    @Override
    public Entite getEntityByUser(UserModel user) {
        User u = new User();
        u.setId(user.getId());
        u = this.userDao.get(u);
        Entite en = u.getEntite();
        return this.entiteDao.get(en);
    }

    @Override
    public Entite getEntityByUser(Integer userId) {
        UserModel user = new UserModel();
        user.setId(userId);
        return this.getEntityByUser(user);
    }

    @Override
    public Boolean insert(Entite entite) {
        this.save(entite);
        return true;
    }

    @Override
    public Boolean update(Entite entite) {
        return this.entiteDao.update(entite);
    }

    @Override
    public Entite findById(String id) {
        return this.getByPrimaryKey(id);
    }

    @Override
    public List<Entite> findByLanguageId(Integer languageId) {
        return this.entiteDao.findByLanguageId(languageId);
    }
}
