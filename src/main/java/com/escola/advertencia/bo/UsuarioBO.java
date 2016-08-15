package com.escola.advertencia.bo;

import javax.ejb.Local;

import com.escola.advertencia.model.Usuario;

@Local
public interface UsuarioBO extends GenericBO<Usuario> {

	boolean autenticar(String username, String password);
}