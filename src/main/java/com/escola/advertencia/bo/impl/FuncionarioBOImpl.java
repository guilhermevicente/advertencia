package com.escola.advertencia.bo.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.escola.advertencia.bo.FuncionarioBO;
import com.escola.advertencia.dao.FuncionarioDAO;
import com.escola.advertencia.model.Funcionario;

@Stateless
public class FuncionarioBOImpl extends GenericBOImpl<Funcionario> implements FuncionarioBO {

	@Inject
	private FuncionarioDAO funcionarioDAO;

	@PostConstruct
	public void init() {
		super.dao = funcionarioDAO;
	}
}