package com.escola.advertencia.presentation.view;

import java.util.List;

import com.escola.advertencia.model.TipoAdvertencia;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

public interface TipoAdvertenciaView extends View, Component {
	interface TipoAdvertenciaViewListener {
		void salvar(TipoAdvertencia objeto);
		void itemSelecionado(Integer id);
		void excluir(TipoAdvertencia objeto);
		void enter();
	}	
	
	public void addListener(TipoAdvertenciaViewListener listener);

	void editar(TipoAdvertencia objeto);

	void mostrarTabela(List<TipoAdvertencia> lista);
}