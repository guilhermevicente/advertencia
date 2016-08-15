package com.escola.advertencia.dao;

import javax.ejb.Local;

import com.escola.advertencia.model.Usuario;

@Local
public interface UsuarioDAO extends GenericDAO<Usuario>{

	boolean autenticar(String username, String password);
}