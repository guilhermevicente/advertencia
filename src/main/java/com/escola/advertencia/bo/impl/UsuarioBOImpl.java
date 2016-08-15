package com.escola.advertencia.bo.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.escola.advertencia.bo.UsuarioBO;
import com.escola.advertencia.dao.UsuarioDAO;
import com.escola.advertencia.model.Usuario;
import com.vaadin.ui.Notification;

@Stateless
public class UsuarioBOImpl extends GenericBOImpl<Usuario> implements UsuarioBO {
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@PostConstruct
	public void init() {
		super.dao = usuarioDAO;
	}

	@Override
	public boolean autenticar(String username, String password) {
		if (this.usuarioDAO.autenticar(username, password)) {
			return true;
		} 
		
		Notification.show("Usuário e/ou senha não conferem");
		
		return false;
	}
}