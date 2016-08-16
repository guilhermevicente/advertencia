package com.escola.advertencia.dao.impl;

import javax.ejb.Stateless;

import com.escola.advertencia.dao.AlunoDAO;
import com.escola.advertencia.model.Aluno;

@Stateless
public class AlunoDAOImpl extends GenericDAOImpl<Aluno> implements AlunoDAO {
}