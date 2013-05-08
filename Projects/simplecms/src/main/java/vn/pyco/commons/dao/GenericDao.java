// [LICENCE-HEADER]
//

package vn.pyco.commons.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.model.Activable;
import vn.pyco.commons.model.Defaultable;
import vn.pyco.commons.model.Identifiable;
import vn.pyco.commons.model.Inheritable;
import vn.pyco.commons.model.Undeletable;

/**
 * Interface represents implementation of generic DAO.
 *
 * @param <T> object's type, it must implements at least <code>Identifiable</code>
 * @param <I> primary key's type
 *
 */

public interface GenericDao<T extends Identifiable<I>, I extends Serializable> {

    /**
     * Retrieve an persisted object using the given id as primary key.
     *
     * @param id object's primary key
     * @return object
     * @throws EntityNotFoundException -
     *             if not found
     */
    T load(I id) throws EntityNotFoundException;

    /**
     * Retrieve an persisted object using the given id as primary key.
     *
     * Returns null if not found.
     *
     * @param id object's primary key
     * @return object
     */
    T get(I id);

    /**
     * Retrieve an persisted objects using the given ids as primary keys.
     *
     * @param ids objects's ids
     * @return list of objects
     */
    List<T> get(I... ids);

    /**
     * Retrieve all persisted objects with given parents.
     *
     * If parent is null, method returns root objects (with no parent).
     *
     * @param parent objects' parent
     * @return list of objects
     */
    List<T> get(Inheritable<T> parent);

    /**
     * Retrieve all persisted objects.
     *
     * @return list of objects
     */
    List<T> getAll();
    
    /**
     * Retrieve the number of total items
     *
     * @return number of total items
     */
    long countAll();
    
    /**
     * Find objects comparing them with examplary object using all not-null properties.
     *
     * Properties <code>Identifiable.id</code>, <code>Defaultable.isDefault</code>, <code>Activable.isActive</code> and <code>Hiddenable.isHidden</code> are ignored.
     *
     * @param example examplary object
     * @return list of objects
     */
    List<T> findByExample(T example);

    /**
     * Set object as default one.
     *
     * Check if there is only one default object - it uses
     * <code>Defaultable#getExample()</code> and <code>#findByExample(Identifiable)</code> methods to
     * get objects and mark them as not-default.
     *
     * It is possible to have more
     * than one default object manipulating <code>Defaultable#getExample()</code>
     * method.
     *
     * @param object default object
     * @see Defaultable#getExample()
     * @see #findByExample(Identifiable)
     */
    void setAsDefault(Defaultable object);

    /**
     * Save all changes made to an object.
     *
     * @param entity entity
     */
//    void save(T entity);
    
    T merge(T entity);

    /**
     * Save all changes made to objects.
     *
     * @param entities entities
     */
    void save(T... entities);

    /**
     * Remove an object by given id. Check if object is not default one.
     *
     * If entity implements <code>Hiddenable</code> then it is hidden instead of deleted.
     *
     * @param id object's pk
     * @throws UnsupportedOperationException - if entity is default one
     * @see Undeletable
     */
    void delete(I id) throws UnsupportedOperationException;

    /**
     * Remove objects by given ids. Check if all objects are not mark as default one.
     *
     * If objects implement <code>Hiddenable</code> then ther are hidden instead of deleted.
     *
     * @param ids objects's pk
     * @throws UnsupportedOperationException - if one of entities is default one
     * @see Undeletable
     */
    void delete(I... ids) throws UnsupportedOperationException;

    /**
     * Remove an object. Check if object is not default one.
     *
     * If object implements <code>Hiddenable</code> then it is hidden instead of deleted.
     *
     * @param entity entity
     * @throws UnsupportedOperationException - if entity is default one
     * @see Undeletable
     */
    void delete(T entity) throws UnsupportedOperationException;

    /**
     * Remove objects. Check if all objects are not mark as default one.
     *
     * If objects implement <code>Hiddenable</code> then they are hidden instead of deleted.
     *
     * @param entities entities
     * @throws UnsupportedOperationException - if one of entities is default one
     * @see Undeletable
     */
    void delete(T... entities) throws UnsupportedOperationException;

    /**
     * Delete all objects, including default one.
     *
     * If objects implement <code>Hiddenable</code> then they are hidden instead of deleted.
     *
     * @see Undeletable
     */
    void deleteAll();

    /**
     * Refresh a persistant object that may have changed in another thread/transaction.
     *
     * @param entity transient entity
     */
    void refresh(T entity);
    
    /**
     * Create a Criteria object based-on the entity of DAO
     *
     */
    Criteria createCriteria();
    
    /**
     * Retrieve objects using criteria.
     *
     * @param criteria criteria which will be executed
     * @return list of founded objects
     * @see javax.persistence.Query#getResultList()
     */
    List findByCriteria(Criteria criteria);
    
    /**
     * Retrieve an unique object using criteria. It is equivalent to <code>criteria.uniqueResult(_entityManager)</code>.
     *
     * @param criteria criteria which will be executed
     * @return retrieved object
     * @throws NoResultException - if there is no result
     * @throws NonUniqueResultException - if more than one result
     * @see javax.persistence.Query#getSingleResult()
     */
    Object findUniqueByCriteria(Criteria criteria) throws NonUniqueResultException, NoResultException;

    /**
     * Write to database anything that is pending operation and clear it.
     */
    void flushAndClear();
    
    /**
     * Write to database anything that is pending operation.
     */
    void flush();

    /**
     * Check whether the DAO represents <code>Activable</code> implementation.
     *
     * @return true if DAO's object are activable
     * @see Activable
     */
    boolean isActivable();

    /**
     * Check whether the DAO represents <code>Defaultable</code> implementation.
     *
     * @return true if DAO's object are defaultable
     * @see Defaultable
     */
    boolean isDefaultable();

    /**
     * Check whether the DAO represents <code>Hiddenable</code> implementation.
     *
     * @return true if DAO's object are hiddenable
     * @see Undeletable
     */
    boolean isDeletable();

    /**
     * Check whether the DAO represents <code>Inheritable</code> implementation.
     *
     * @return true if DAO's object are inheritable
     * @see Inheritable
     */
    boolean isInheritable();

}
