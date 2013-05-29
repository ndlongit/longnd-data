package com.structis.vip.server.dao;

import java.io.File;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.DomDel;
import com.structis.vip.server.core.ServerConstant;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;

@Repository("domDelDao")
public class DomDelDaoImpl extends HibernateGenericDao<DomDel, Integer> implements DomDelDao {

    private static final Logger LOGGER = Logger.getLogger(DomDelDaoImpl.class);

    public DomDelDaoImpl() {
        super(DomDel.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DomDel> getByDelId(Integer delId) {
        String sql = " from DomDel d where d.delegation.id = :delId ";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("delId", delId);
        List<DomDel> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public DomDel insert(DomDel demDom) {
        this.save(demDom);
        return demDom;
    }

    @Override
    @Transactional
    public Boolean deleteByDelId(Integer delId, String path) {
        List<DomDel> list = this.getByDelId(delId);
        if (list.isEmpty() == false) {
            File file = null;
            for (DomDel domDel : list) {
                file = new File(path + ServerConstant.SIGNED_DOCUMENT_FILE_PATH + File.separator + domDel.getSignedFilename());
                LOGGER.info("DELETE SIGNED DOCUMENT FILE PATH: " + file.getAbsolutePath());
                if (file.exists()) {
                    LOGGER.info("SIGNED DOCUMENT EXISTS");
                    file.delete();
                }
                this.delete(domDel);
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DomDel> findByDocumentModel(Integer dId) {
        String sql = " from DomDel d where d.documentMdl.id = :dId ";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("dId", dId);
        List<DomDel> resultList = query.getResultList();
        return resultList;
    }
}
