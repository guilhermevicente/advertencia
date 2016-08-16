package com.escola.advertencia.bo.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.escola.advertencia.bo.TipoAdvertenciaBO;
import com.escola.advertencia.dao.TipoAdvertenciaDAO;
import com.escola.advertencia.model.TipoAdvertencia;

@Stateless
public class TipoAdvertenciaBOImpl extends GenericBOImpl<TipoAdvertencia> implements TipoAdvertenciaBO {
	
	@Inject
	private TipoAdvertenciaDAO tipoAdvertenciaDAO;
	
	@PostConstruct
	public void init() {
		super.dao = tipoAdvertenciaDAO;
	}
}