package com.escola.advertencia.presentation.view;

import java.util.List;

import com.escola.advertencia.model.Advertencia;
import com.escola.advertencia.model.Aluno;
import com.escola.advertencia.model.Funcionario;
import com.escola.advertencia.model.TipoAdvertencia;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

public interface AdvertenciaView extends View, Component {
	interface AdvertenciaViewListener {
		void salvar(Advertencia objeto);
		void itemSelecionado(Integer id);
		void excluir(Advertencia objeto);
		void enter();
	}	
	
	public void addListener(AdvertenciaViewListener listener);

	void editar(Advertencia objeto);

	void mostrarTabela(List<Advertencia> lista);
	
	void montarCombos(List<Funcionario> funcionarios, List<TipoAdvertencia> tiposAdvertencias, List<Aluno> alunos);
}