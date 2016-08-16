package com.escola.advertencia.bo.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.escola.advertencia.bo.AdvertenciaBO;
import com.escola.advertencia.dao.AdvertenciaDAO;
import com.escola.advertencia.model.Advertencia;

@Stateless
public class AdvertenciaBOImpl extends GenericBOImpl<Advertencia> implements AdvertenciaBO {
	
	@Inject
	private AdvertenciaDAO advertenciaDAO;
	
	@PostConstruct
	public void init() {
		super.dao = advertenciaDAO;
	}
}