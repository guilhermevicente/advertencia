package com.escola.advertencia.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.escola.advertencia.dao.UsuarioDAO;
import com.escola.advertencia.model.Usuario;

@Stateless
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario> implements UsuarioDAO {

	@Override
	public boolean autenticar(String username, String password) {
		
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Usuario u ");
		hql.append("WHERE u.login = :username ");
		hql.append("AND u.senha = :password ");
		
		Query q = em.createQuery(hql.toString());
		q.setParameter("username", username);
		q.setParameter("password", password);
		
		try {
			Usuario usuario = (Usuario) q.getSingleResult();
			return usuario != null;
		} catch (Exception e) {
			return false;
		}
	}
}