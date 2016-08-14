package com.escola.advertencia.bo;

import java.util.Set;

public interface GenericBO<T> {

	public abstract T find(Integer id);

	public abstract Set<T> listAll();

	public abstract void saveOrUpdate(T p);

	public abstract boolean delete(T p);
}