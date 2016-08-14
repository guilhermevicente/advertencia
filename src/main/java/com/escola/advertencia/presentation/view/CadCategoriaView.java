package com.escola.advertencia.presentation.view;

import java.util.List;

import com.escola.advertencia.model.Teste;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

public interface CadCategoriaView extends View, Component {
	interface CadCategoriaViewListener {
		void salvar(Teste objeto);
		void itemSelecionado(Integer id);
		void excluir(Teste objeto);
		void enter();
	}	
	
	public void addListener(CadCategoriaViewListener listener);

	void editar(Teste objeto);

	void mostrarTabela(List<Teste> lista);
}