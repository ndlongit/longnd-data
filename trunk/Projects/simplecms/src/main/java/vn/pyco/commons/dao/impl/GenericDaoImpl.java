// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.impl;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.orm.jpa.JpaTemplate;

import vn.pyco.commons.dao.GenericDao;
import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;
import vn.pyco.commons.dao.criteria.projection.Projections;
import vn.pyco.commons.dao.criteria.restriction.Restrictions;
import vn.pyco.commons.model.Activable;
import vn.pyco.commons.model.Defaultable;
import vn.pyco.commons.model.Identifiable;
import vn.pyco.commons.model.Inheritable;
import vn.pyco.commons.model.Undeletable;

/**
 * Abstract implementation of generic DAO.
 *
 * @param <T> entity type, it must implements at least <code>Identifiable</code>
 * @param <I> entity's primary key, it must be serializable
 * @see Identifiable
 */
public class GenericDaoImpl<T extends Identifiable<I>, I extends Serializable> implements GenericDao<T, I> {
    private EntityManager _entityManager;
    private JpaTemplate _jpaTemplate;
    private Class<Identifiable<I>> _clazz;
    private boolean _defaultable;
    private boolean _activable;
    private boolean _deletable;
    private boolean _inheritable;

    /**
     * Default constructor. Use for extend this class.
     */
    @SuppressWarnings(value = "unchecked")
    public GenericDaoImpl() {
        _clazz = (Class<Identifiable<I>>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        checkGenericClass();
    }

    public GenericDaoImpl(Class<Identifiable<I>> clazz) {
        _clazz = clazz;
        checkGenericClass();
    }

    /**
     * Set entity manager.
     *
     * @param _entityManager entity manager
     */
    @PersistenceContext
    public final void setEntityManager(EntityManager entityManager) {
        _entityManager = entityManager;
        _jpaTemplate = new JpaTemplate(_entityManager);
    }

    public final void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        _jpaTemplate = new JpaTemplate(entityManagerFactory);        
    }
    
    @Override
    public final T load(I id) throws EntityNotFoundException {
        T entity = get(id);
        if (entity == null) {
            throw new EntityNotFoundException("entity " + _clazz + "#" + id + " was not found");
        }
        return entity;
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public final T get(I id) {
        Criteria criteria = createCriteria();
        Criterion criterion = Restrictions.eq(Identifiable.PROP_ID, id);
        criteria.add(criterion);
        if (_deletable) {
            Criterion notDeleted = Restrictions.eq(Undeletable.PROP_DELETED, new Boolean(false));
            criteria.add(notDeleted);
        } 
        return (T) _entityManager.find(_clazz, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final List<T> get(I... ids) {
        Criterion criterion = Restrictions.in(Identifiable.PROP_ID, ids);
        return findByCriteria(createCriteria().add(criterion));
    }

    @SuppressWarnings("unchecked")
    @Override
    public final List<T> get(Inheritable<T> parent) {
        if (parent == null) {
            Criterion criterion = Restrictions.isNull(Inheritable.PROP_PARENT);
            return findByCriteria(createCriteria().add(criterion));
        } else {
            Criterion criterion = Restrictions.eq(Inheritable.PROP_PARENT, parent);
            return findByCriteria(createCriteria().add(criterion));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public final List<T> getAll() {
        Criteria criteria = createCriteria();
        if (_deletable) {
            Criterion notDeleted = Restrictions.eq(Undeletable.PROP_DELETED, new Boolean(false));
            criteria.add(notDeleted);
        }
        return findByCriteria(criteria);
    }
    
    /*
     * (non-Javadoc)
     * @see vn.pyco.commons.dao.GenericDao#countAll()
     */
    @Override
    public long countAll() {
        Criteria criteria = createCriteria();
        criteria.setProjection(Projections.count(Identifiable.PROP_ID));
        if (isDeletable()) {
            Criterion notDeleted = Restrictions.eq(Undeletable.PROP_DELETED, new Boolean(false));
            criteria.add(notDeleted);
        }
        Long count = (Long) criteria.uniqueResult(_entityManager);
        
        return count;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public final List<T> findByExample(T example) {
        Criteria criteria = Criteria.forClass(_clazz);
        PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(_clazz);
        for (PropertyDescriptor descriptor : descriptors) {
            String propName = descriptor.getName();
            
            if (propName.equals(Identifiable.PROP_ID)) {
                continue;
            }
            if (propName.equals(Activable.PROP_ACTIVE)) {
                continue;
            }
            if (propName.equals(Defaultable.PROP_DEFAULT)) {
                continue;
            }
            if (propName.equals(Undeletable.PROP_DELETED)) {
                // always get item is not hidden
                criteria.add(Restrictions.eq(Undeletable.PROP_DELETED, new Boolean(false)));
                continue;
            }
            
            Method method = descriptor.getReadMethod();
            if (!method.isAnnotationPresent(Column.class) && !method.isAnnotationPresent(Basic.class)) {
                continue;
            }

            Object value = null;

            try {
                method.setAccessible(true);
                value = method.invoke(example);
            } catch (IllegalArgumentException e) {
                continue;
            } catch (IllegalAccessException e) {
                continue;
            } catch (InvocationTargetException e) {
                continue;
            }

            if (value == null) {
                continue;
            }

            criteria.add(Restrictions.eq(propName, value));
        }

        if (example instanceof Inheritable) {
            if (((Inheritable) example).getParent() == null) {
                criteria.add(Restrictions.isNull(Inheritable.PROP_PARENT));
            } else {
                criteria.add(Restrictions.eq(Inheritable.PROP_PARENT, ((Inheritable) example).getParent()));
            }
        }

        return findByCriteria(criteria);
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public final void setAsDefault(Defaultable object) {
        if (object.getExample() != null) {
            List<T> objects = findByExample((T) object.getExample());
            for (T o : objects) {
                if (object != o) {
                    ((Defaultable) o).setDefault(false);
                    _entityManager.merge(o);
                }
            }
        }
        object.setDefault(true);

        //_entityManager.merge(object);
        if (((T) object).getId() != null) {
            _entityManager.merge(object);
        } else {
            _entityManager.persist(object);
        }
    }

//    @Override
//    public void save(final T object) {
//    	//object = _entityManager.merge(object);
//        if (object.getId() != null) {
//            _entityManager.merge(object);
//        } else {
//            _entityManager.persist(object);
//        }
//    }

    @Override
    public void save(final T... objects) {
        for (T object : objects) {
        	//object = _entityManager.merge(object);
            if (object.getId() != null) {                
                _entityManager.merge(object);
            } else {
                _entityManager.persist(object);
            }
        }
    }

    @Override
    public final void delete(final I id) throws UnsupportedOperationException {
        delete(load(id));
    }

    @Override
    public final void delete(final I... ids) throws UnsupportedOperationException {
        deleteAll(get(ids), true);
    }

    @Override
    public final void delete(final T object) throws UnsupportedOperationException {
        if (_defaultable) {
            checkIfDefault(object);
        }
        if (_deletable) {
            ((Undeletable) object).setDeleted(true);
            _entityManager.merge(object);
        } else {
            _entityManager.remove(object);
        }
    }

    @Override
    public final void delete(final T... objects) throws UnsupportedOperationException {
        deleteAll(Arrays.asList(objects), true);
    }

    @Override
    public final void deleteAll() throws UnsupportedOperationException {
        deleteAll(getAll(), false);
    }

    private void deleteAll(final Collection<T> objects, boolean checkIdDefault) throws UnsupportedOperationException {
        if (checkIdDefault) {
            if (_defaultable) {
                for (T object : objects) {
                    checkIfDefault(object);
                }
            }
        }
        if (_deletable) {
            for (T object : objects) {
                ((Undeletable) object).setDeleted(true);
                _entityManager.merge(object);
            }
        } else {
            for (T object : objects) {
                _entityManager.remove(object);
            }
        }
    }

    private void checkIfDefault(T entity) {
        if (((Defaultable) entity).isDefault()) {
            throw new UnsupportedOperationException("can't delete default entity " + _clazz + "#" + entity.getId());
        }
    }

    private void checkGenericClass() {       
        _defaultable = isImplementInterface(Defaultable.class, _clazz);
        _activable = isImplementInterface(Activable.class, _clazz);    
        _deletable = isImplementInterface(Undeletable.class, _clazz);    
        _inheritable = isImplementInterface(Inheritable.class, _clazz);        
    }

    @Override
    public final void refresh(final T entity) {
        _entityManager.refresh(entity);
    }

    @Override
    public final T merge(T entity) {
        return _entityManager.merge(entity);
    }
    
    @Override
    public final void flushAndClear() {
        _entityManager.flush();
        _entityManager.clear();
    }

    @Override
    public final void flush() {
        _entityManager.flush();
    }
    
    @Override
    public Criteria createCriteria() {
        return (Criteria) Criteria.forClass(_clazz);
    }
    
    @Override
    public final List findByCriteria(Criteria criteria) {
        return criteria.list(_entityManager);
    }

    @Override
    public final Object findUniqueByCriteria(Criteria criteria) throws NonUniqueResultException, NoResultException {
        return criteria.uniqueResult(_entityManager);
    }
    
    @Override
    public final boolean isActivable() {
        return _activable;
    }

    @Override
    public final boolean isDefaultable() {
        return _defaultable;
    }

    @Override
    public final boolean isDeletable() {
        return _deletable;
    }

    @Override
    public final boolean isInheritable() {
        return _inheritable;
    }

    /**
     * Get entity manager.
     *
     * @return entity manager
     */
    protected final EntityManager getEntityManager() {
        return _entityManager;
    }

    /**
     * @return the clazz
     */
    protected Class<Identifiable<I>> getClazz() {
        return _clazz;
    }
    
    /**
     * 
    * Get a JpaTemplate
    * @return a JpaTemplate
    *
     */
    protected JpaTemplate getJpaTemplate() {
        return _jpaTemplate;
    }
    
    private boolean isImplementInterface(Class interfaceClass, Class objectClass) { 
        boolean isImplement = false;
        
        for (Class i : objectClass.getInterfaces()) {            
            if (i == interfaceClass) {
                isImplement = true;
                break;
            }
        }
        if (isImplement == false && objectClass.getSuperclass() != null) {
            isImplement = isImplementInterface(interfaceClass, objectClass.getSuperclass());
        }
        return isImplement;
    }
    
    public T getObjectByCriteria(String name, String value) {
        String[] names = new String[1];
        names[0] = name;

        String[] values = new String[1];
        values[0] = value;
        return this.getObjectByCriteria(names, values);
    }

    @SuppressWarnings(value = "unchecked")
    public T getObjectByCriteria(String[] names, String[] values) {
        T result = null;
        try {
            Criteria criteria = createCriteria();
            for (int i = 0; i < names.length; i++) {
                criteria.add(Restrictions.eq(names[0], values[0]));
            }
            result = (T) findUniqueByCriteria(criteria);

        } catch (NoResultException e) {
            return null;
        }

        return result;
    }
}
