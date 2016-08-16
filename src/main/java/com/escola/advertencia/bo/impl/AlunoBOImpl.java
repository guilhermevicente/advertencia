package com.escola.advertencia.bo.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.escola.advertencia.bo.AlunoBO;
import com.escola.advertencia.dao.AlunoDAO;
import com.escola.advertencia.model.Aluno;

@Stateless
public class AlunoBOImpl extends GenericBOImpl<Aluno> implements AlunoBO {

	@Inject
	private AlunoDAO alunoDAO;

	@PostConstruct
	public void init() {
		super.dao = alunoDAO;
	}
}