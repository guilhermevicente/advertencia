package com.escola.advertencia.dao;

import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

public interface GenericDAO<T> {

	public boolean persist(T t)
			throws EntityExistsException, IllegalArgumentException, TransactionRequiredException, PersistenceException;

	public boolean merge(T t) throws IllegalArgumentException, TransactionRequiredException;

	public boolean remove(T t) throws IllegalArgumentException, TransactionRequiredException;

	public T find(int pk) throws IllegalArgumentException;
	
	public Set<T> list() throws IllegalArgumentException;
}