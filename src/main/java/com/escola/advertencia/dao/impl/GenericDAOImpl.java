package com.escola.advertencia.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import com.escola.advertencia.dao.GenericDAO;

@SuppressWarnings("unchecked")
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {
	private Class<T> persistentClass;

	@PersistenceContext(name = "advertencia")
	protected EntityManager em;

	public GenericDAOImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public boolean persist(T t)
			throws EntityExistsException, IllegalArgumentException, TransactionRequiredException, PersistenceException {

		this.em.persist(t);

		return true;
	}

	@Override
	public boolean merge(T t) throws IllegalArgumentException, TransactionRequiredException {

		this.em.merge(t);

		return true;
	}

	@Override
	public boolean remove(T t) throws IllegalArgumentException, TransactionRequiredException {

		this.em.remove(this.em.contains(t) ? t : this.em.merge(t));

		return true;

	}

	@Override
	public T find(int pk) throws IllegalArgumentException {
		return this.em.find(this.persistentClass, pk);
	}

	@Override
	public Set<T> list() throws IllegalArgumentException {
		return new HashSet<T>(this.em.createQuery("from " + this.persistentClass.getSimpleName()).getResultList());
	}
}
