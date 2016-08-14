package com.escola.advertencia.bo.impl;

import java.util.Set;

import com.escola.advertencia.bo.GenericBO;
import com.escola.advertencia.dao.GenericDAO;

@SuppressWarnings( {"rawtypes", "unchecked"})
public abstract class GenericBOImpl<T> implements GenericBO<T> {
	
	protected GenericDAO dao;

	@Override
	public T find(Integer id) {
		return (T) this.dao.find(id);
	}

	@Override
	public Set<T> listAll() {
		return this.dao.list();
	}

	@Override
	public void saveOrUpdate(T p) {
		this.dao.merge(p);
	}

	@Override
	public boolean delete(T p) {
		return this.dao.remove(p);
	}
}