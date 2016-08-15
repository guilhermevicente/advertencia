package com.escola.advertencia.bo.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.escola.advertencia.bo.PerfilBO;
import com.escola.advertencia.dao.PerfilDAO;
import com.escola.advertencia.model.Perfil;

@Stateless
public class PerfilBOImpl extends GenericBOImpl<Perfil> implements PerfilBO {
	
	@Inject
	private PerfilDAO perfilDAO;
	
	@PostConstruct
	public void init() {
		super.dao = perfilDAO;
	}
}