package com.structis.vip.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Control;
import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

@Repository("controlDao")
public class ControlDaoImpl extends HibernateGenericDao<Control, Integer> implements ControlDao {

    @Autowired
    PerimetreDao perimetreDao;

    public ControlDaoImpl() {
        super(Control.class);
    }

    @Override
    @Transactional
    public Control insert(Control nature) {
        this.save(nature);
        return nature;
    }

    @Override
    @Transactional
    public Control update(Control dl) {
        EntityManager em = this.getEntityManager();
        try {
            Control jpa = this.get(dl);
            if (jpa != null && jpa.getId() != null) {
                DataCopier.copyNotIdFields(dl, jpa);
                em.merge(jpa);
                return jpa;
            }
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    @Transactional
    public List<Control> getControlsByEntite(String enId, String perimetreId, List<Integer> keyList, Date startDate, Date endDate, String codeProjet,
            List<String> caracteres, String controllerName, PerimetreTreeModel perimetreTreeModel, List<UserRoleModel> userRoles) {
        List<Control> lstReturn = new ArrayList<Control>();

        List<Control> resultList = this.getControls(enId, perimetreId, keyList, startDate, endDate, codeProjet, caracteres, controllerName);

        if (resultList != null && resultList.size() > 0) {
            for (Control rs : resultList) {
                rs.setCanViewed(perimetreTreeModel.getIsLectureControl());
                rs.setCanModified(perimetreTreeModel.getIsModificationControl());
            }
            lstReturn.addAll(resultList);
        }

        List<Holder> holders = new ArrayList<ControlDaoImpl.Holder>();
        List<Perimetre> perimetres = this.perimetreDao.getTreeModelByParent(enId, perimetreId);
        for (Perimetre perimetre : perimetres) {
            Holder holder = new Holder();
            holder.setPerimetre(perimetre);
            holder.setTreeModel(perimetreTreeModel);
            holders.add(holder);
        }

        Boolean run = true;
        while (run) {
            run = false;
            List<Holder> holdersNext = new ArrayList<Holder>();
            for (Holder holder : holders) {
                PerimetreModel pm = new PerimetreModel();
                pm.setPerId(holder.getPerimetre().getPerId());
                pm.setName(holder.getPerimetre().getName());
                PerimetreTreeModel ptm = new PerimetreTreeModel(pm, userRoles);
                ptm.setPermissionByParent(holder.getTreeModel());

                List<Control> subResult = this.getControls(enId, holder.getPerimetre().getPerId(), keyList, startDate, endDate, codeProjet,
                        caracteres, controllerName);

                if (subResult != null && subResult.size() > 0) {
                    for (Control subR : subResult) {
                        subR.setCanViewed(perimetreTreeModel.getIsLectureControl());
                        subR.setCanModified(perimetreTreeModel.getIsModificationControl());
                    }
                    lstReturn.addAll(subResult);
                }
                List<Perimetre> treeModelByParent = this.perimetreDao.getTreeModelByParent(enId, holder.getPerimetre().getPerId());
                for (Perimetre pr : treeModelByParent) {
                    Holder hNext = new Holder();
                    hNext.setPerimetre(pr);
                    hNext.setTreeModel(ptm);
                    holdersNext.add(hNext);
                }
            }
            if ((holdersNext != null) && (holdersNext.size() != 0)) {
                run = true;
                holders = holdersNext;
            }
        }

        return lstReturn;
    }

    @Override
    public List<Control> getControls(String enId, String perimetreId, List<Integer> keyList, Date startDate, Date endDate, String codeProjet,
            List<String> caracteres, String controllerName) {
        StringBuffer sql = new StringBuffer("select c from Control c left join c.collaborateur cl where c.perimetre.id = :perimetreId");
        if (startDate != null) {
            sql.append(" and CONVERT(DATE,c.date) >= CONVERT(DATE,:startDate)");
        }
        if (endDate != null) {
            sql.append(" and CONVERT(DATE,c.date) <= CONVERT(DATE,:endDate)");
        }
        if (keyList != null && keyList.size() > 0) {
            sql.append(" and c.controlType.id in (:controlIds)");
        }
        if (codeProjet != null && codeProjet.length() > 0) {
            sql.append(" and c.codeProject like :codeProject");
        }
        List<Integer> controlIds = null;

        if (caracteres != null && caracteres.size() > 0) {
            if (caracteres.contains("interne")) {
                sql.append(" and c.character = 'interne' ");
                if (controllerName != null && controllerName.length() > 0) {
                    sql.append(" and (cl.lastname + ', ' + cl.firstname like :controllerName ) ");
                }
            } else if (caracteres.contains("externe")) {
                sql.append(" and c.character = 'externe' ");
                if (controllerName != null && controllerName.length() > 0) {
                    controlIds = this.getControlsHasExternName(controllerName);
                    sql.append(" and c.id in (:externControlIds) ");
                }
            }
        } else {
            if (controllerName != null && controllerName.length() > 0) {
                controlIds = this.getControlsHasExternName(controllerName);
                sql.append(" and ((c.character = 'interne' and cl.lastname + ', ' + cl.firstname like :controllerName) ");
                sql.append(" or (c.character = 'externe' and c.id in (:externControlIds))) ");
            }
        }

        sql.append(" order by c.perimetre.name");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("perimetreId", perimetreId);
        if (codeProjet != null && codeProjet.length() > 0) {
            query.setParameter("codeProject", "%" + codeProjet + "%");
        }
        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }
        if (keyList != null && keyList.size() > 0) {
            query.setParameter("controlIds", keyList);
        }

        if (caracteres != null && caracteres.size() > 0) {
            if (caracteres.contains("interne")) {
                if (controllerName != null && controllerName.length() > 0) {
                    query.setParameter("controllerName", "%" + controllerName + "%");
                }
            } else if (caracteres.contains("externe")) {
                if (controllerName != null && controllerName.length() > 0) {
                    if (controlIds != null && controlIds.size() > 0) {
                        query.setParameter("externControlIds", controlIds);
                    } else {
                        query.setParameter("externControlIds", -1);
                    }
                }
            }
        } else {
            if (controllerName != null && controllerName.length() > 0) {
                query.setParameter("controllerName", "%" + controllerName + "%");
                if (controlIds != null && controlIds.size() > 0) {
                    query.setParameter("externControlIds", controlIds);
                } else {
                    query.setParameter("externControlIds", -1);
                }
            }
        }

        @SuppressWarnings("unchecked")
        List<Control> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> getControlsHasExternName(String controllerName) {
        String sql = " select distinct ecc.control.id from ExtControllerControl ecc inner join ecc.externalController ec  where ec.name like :controllerName ";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("controllerName", "%" + controllerName + "%");

        return query.getResultList();
    }

    public class Holder {

        Perimetre perimetre;
        PerimetreTreeModel treeModel;

        public Perimetre getPerimetre() {
            return this.perimetre;
        }

        public void setPerimetre(Perimetre perimetre) {
            this.perimetre = perimetre;
        }

        public PerimetreTreeModel getTreeModel() {
            return this.treeModel;
        }

        public void setTreeModel(PerimetreTreeModel treeModel) {
            this.treeModel = treeModel;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Control> getControlsByPerimetre(String perId) {
        String sql = " from Control c where c.perimetre.perId = :perId";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("perId", perId);

        return query.getResultList();
    }

    @Override
    public List<Integer> getControlIdsByEntite(String enId, String perimetreId, List<Integer> keyList, Date startDate, Date endDate,
             String codeProject, List<String> caracteres, String controllerName, PerimetreTreeModel perimetreTreeModel, List<UserRoleModel> userRoles) {
        List<Integer> lstReturn = new ArrayList<Integer>();

        List<Integer> resultList = this.getControlIds(enId, perimetreId, keyList, startDate, endDate, codeProject, caracteres, controllerName);

        if (resultList != null && resultList.size() > 0) {
            lstReturn.addAll(resultList);
        }

//        List<Holder> holders = new ArrayList<ControlDaoImpl.Holder>();
        List<String> holders = this.perimetreDao.getPerimetreIdsByParent(enId, perimetreId);
//        lstReturn.addAll(holders);
//        for (Perimetre perimetre : perimetres) {
//            Holder holder = new Holder();
//            holder.setPerimetre(perimetre);
//            holder.setTreeModel(perimetreTreeModel);
//            holders.add(holder);
//        }

        Boolean run = true;
        while (run) {
            run = false;
            List<String> holdersNext = new ArrayList<String>();
            for (String holder : holders) {
//                PerimetreModel pm = new PerimetreModel();
//                pm.setPerId(holder.getPerimetre().getPerId());
//                pm.setName(holder.getPerimetre().getName());
//                PerimetreTreeModel ptm = new PerimetreTreeModel(pm, userRoles);
//                ptm.setPermissionByParent(holder.getTreeModel());

                List<Integer> subResult = this.getControlIds(enId, holder, keyList, startDate, endDate, codeProject,
                        caracteres, controllerName);

                if (subResult != null && subResult.size() > 0) {
                    lstReturn.addAll(subResult);
                }
                List<String> treeModelByParent = this.perimetreDao.getPerimetreIdsByParent(enId, holder);
//                for (Perimetre pr : treeModelByParent) {
//                    Holder hNext = new Holder();
//                    hNext.setPerimetre(pr);
//                    hNext.setTreeModel(ptm);
//                    holdersNext.add(hNext);
//                }
                holdersNext.addAll(treeModelByParent);
            }
            if ((holdersNext != null) && (holdersNext.size() != 0)) {
                run = true;
                holders = holdersNext;
            }
        }

        return lstReturn;
    }
    
    @SuppressWarnings("unchecked")
    private List<Integer> getControlIds(String enId, String perimetreId, List<Integer> keyList, Date startDate, Date endDate, String codeProjet,
            List<String> caracteres, String controllerName) {
        StringBuffer sql = new StringBuffer("select c.id from Control c left join c.collaborateur cl where c.perimetre.id = :perimetreId");
        if (startDate != null) {
            sql.append(" and CONVERT(DATE,c.date) >= CONVERT(DATE,:startDate)");
        }
        if (endDate != null) {
            sql.append(" and CONVERT(DATE,c.date) <= CONVERT(DATE,:endDate)");
        }
        if (keyList != null && keyList.size() > 0) {
            sql.append(" and c.controlType.id in (:controlIds)");
        }
        if (codeProjet != null && codeProjet.length() > 0) {
            sql.append(" and c.codeProject like :codeProject");
        }
        List<Integer> controlIds = null;

        if (caracteres != null && caracteres.size() > 0) {
            if (caracteres.contains("interne")) {
                sql.append(" and c.character = 'interne' ");
                if (controllerName != null && controllerName.length() > 0) {
                    sql.append(" and (cl.lastname + ', ' + cl.firstname like :controllerName ) ");
                }
            } else if (caracteres.contains("externe")) {
                sql.append(" and c.character = 'externe' ");
                if (controllerName != null && controllerName.length() > 0) {
                    controlIds = this.getControlsHasExternName(controllerName);
                    sql.append(" and c.id in (:externControlIds) ");
                }
            }
        } else {
            if (controllerName != null && controllerName.length() > 0) {
                controlIds = this.getControlsHasExternName(controllerName);
                sql.append(" and ((c.character = 'interne' and cl.lastname + ', ' + cl.firstname like :controllerName) ");
                sql.append(" or (c.character = 'externe' and c.id in (:externControlIds))) ");
            }
        }

        sql.append(" order by c.perimetre.name");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("perimetreId", perimetreId);
        if (codeProjet != null && codeProjet.length() > 0) {
            query.setParameter("codeProject", "%" + codeProjet + "%");
        }
        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }
        if (keyList != null && keyList.size() > 0) {
            query.setParameter("controlIds", keyList);
        }

        if (caracteres != null && caracteres.size() > 0) {
            if (caracteres.contains("interne")) {
                if (controllerName != null && controllerName.length() > 0) {
                    query.setParameter("controllerName", "%" + controllerName + "%");
                }
            } else if (caracteres.contains("externe")) {
                if (controllerName != null && controllerName.length() > 0) {
                    if (controlIds != null && controlIds.size() > 0) {
                        query.setParameter("externControlIds", controlIds);
                    } else {
                        query.setParameter("externControlIds", -1);
                    }
                }
            }
        } else {
            if (controllerName != null && controllerName.length() > 0) {
                query.setParameter("controllerName", "%" + controllerName + "%");
                if (controlIds != null && controlIds.size() > 0) {
                    query.setParameter("externControlIds", controlIds);
                } else {
                    query.setParameter("externControlIds", -1);
                }
            }
        }

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Control> getControlsByIds(ArrayList<Integer> ids) {
        if(ids == null || ids.size() <= 0) {
            return new ArrayList<Control>();
        }
        String sql = "from Control c where c.id in (:ids)";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("ids", ids);

        return query.getResultList();
    }
}
