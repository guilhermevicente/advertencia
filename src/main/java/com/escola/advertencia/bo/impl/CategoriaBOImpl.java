package com.escola.advertencia.bo.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.escola.advertencia.bo.CategoriaBO;
import com.escola.advertencia.dao.CategoriaDAO;
import com.escola.advertencia.model.Teste;

@Stateless
public class CategoriaBOImpl extends GenericBOImpl<Teste> implements CategoriaBO {
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	@PostConstruct
	public void init() {
		super.dao = categoriaDAO;
	}
}