package com.escola.advertencia.dao.impl;

import javax.ejb.Stateless;

import com.escola.advertencia.dao.FuncionarioDAO;
import com.escola.advertencia.model.Funcionario;

@Stateless
public class FuncionarioDAOImpl extends GenericDAOImpl<Funcionario> implements FuncionarioDAO {
}