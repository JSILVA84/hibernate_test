package org.hibernate.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.test.entity.IEntity;

/**
 * persist -> JPA, save -> Hibernate
 * merge -> JPA, update -> Hibernate 
 * remove -> JPA, delete -> Hibernate
 * find -> JPA, load -> Hibernate
 * 
 * @author sergio.rojas
 *
 */
public abstract class AbstractGenericDAO<E extends IEntity> {
	
	private Session session;
	
	/**
	 * DAO is not responsible for handling transactions, sessions, or connections, so it receives a session object
	 * @param session
	 */
	public AbstractGenericDAO(Session session) {
		this.session = session;
	}
	
	/**
	 * Concrete subclass need to implement how to get entity class 
	 * @return
	 */
	protected abstract Class<E> getEntityClass();
	
	
	/**
	 *  
	 * @param entity
	 * @return
	 */
	public Long insert(E entity) {
		return (Long) session.save(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void delete(E entity) {
		session.delete(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void update(E entity) {
		session.update(entity);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public E findById(Long id) {
		return session.load(getEntityClass(), id);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public List<E> findAll(){
		StringBuilder hql = new StringBuilder();
		hql.append("from ").append(getEntityClass().getName());
		return (List<E>) session.createQuery(hql.toString()).list();
	}
	
	

}
