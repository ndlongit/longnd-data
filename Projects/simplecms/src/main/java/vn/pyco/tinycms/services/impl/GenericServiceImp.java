package vn.pyco.tinycms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.pyco.commons.dao.GenericDao;

@SuppressWarnings("unchecked")
//class GenericDaoImpl<T extends Identifiable<I>, I extends Serializable> implements GenericDao<T, I> {
//public class GenericServiceImp<D extends GenericDaoImpl<Identifiable<I>, Serializable>, E> {

public class GenericServiceImp<D extends GenericDao, E> {

    @Autowired
    protected D _dao;
    protected E _entity;

//    @Transactional
//    public List<E> findAll() {
//        return _dao.getAll();
//    }
}
