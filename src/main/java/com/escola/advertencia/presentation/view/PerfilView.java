package com.escola.advertencia.presentation.view;

import java.util.List;

import com.escola.advertencia.model.Perfil;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

public interface PerfilView extends View, Component {
	interface PerfilViewListener {
		void salvar(Perfil objeto);
		void itemSelecionado(Integer id);
		void excluir(Perfil objeto);
		void enter();
	}	
	
	public void addListener(PerfilViewListener listener);

	void editar(Perfil objeto);

	void mostrarTabela(List<Perfil> lista);
}