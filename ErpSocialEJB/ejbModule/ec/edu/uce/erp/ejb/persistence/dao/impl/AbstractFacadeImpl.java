package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.AbstractFacade;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaListDTO;

public abstract class AbstractFacadeImpl<T> implements AbstractFacade<T> {
	
	@PersistenceContext(unitName = "seguridadesPU")
	protected EntityManager entityManager;

	private Class<T> entityClass;
	
	public AbstractFacadeImpl(){}

	@SuppressWarnings("unchecked")
	public AbstractFacadeImpl(EntityManager entityManager) {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.entityManager = entityManager;
	}
	
	public T create(T entity) {
		entityManager.persist(entity);
		return entity;
	}

	public T update(T entity) {
		entityManager.merge(entity);
		return entity;
	}

	public void remove(T entity) {
		entityManager.remove(entityManager.merge(entity));
	}

	public T find(Object id) {
		return entityManager.find(entityClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos() {
		return entityManager.createQuery("from " + this.entityClass.getName()).getResultList();
	}

	
}
