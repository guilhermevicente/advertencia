package com.escola.advertencia.presentation.view.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import com.escola.advertencia.model.TipoAdvertencia;
import com.escola.advertencia.presentation.view.TipoAdvertenciaView;
import com.escola.advertencia.utils.IntegerField;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Stateful
public class TipoAdvertenciaViewImpl extends GenericViewImpl implements TipoAdvertenciaView {

	// Elementos para utilizacao do binding
	private BeanFieldGroup<TipoAdvertencia> fieldGroup;
	private TipoAdvertencia bean;

	private TextField nome;
	private IntegerField nivel;
	private Button salvar, cancelar, excluir;

	// Elementos da Tabela e Paginacao
	private Table tabela;

	private List<TipoAdvertenciaViewListener> listeners = new ArrayList<TipoAdvertenciaViewListener>();

	public TipoAdvertenciaViewImpl() {

		tabela = new Table();
		tabela.addContainerProperty("id", Integer.class, null);
		tabela.addContainerProperty("nome", String.class, null);
		tabela.addContainerProperty("nivel", Integer.class, null);
		tabela.setSizeFull();
		tabela.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Integer tipoAdvertencia = (Integer) tabela.getValue();

				if (tipoAdvertencia != null) {
					excluir.setEnabled(true);

					for (TipoAdvertenciaViewListener l : listeners)
						l.itemSelecionado(tipoAdvertencia);

				} else {
					fieldGroup.setItemDataSource(new TipoAdvertencia());
					excluir.setEnabled(false);
				}
			}
		});

		nome = new TextField("Nome do Tipo de Advertência");
		nome.setNullRepresentation("");
		nome.setRequired(true);

		nivel = new IntegerField("Nível");
		nivel.setNullRepresentation("");
		nivel.setRequired(true);

		salvar = new Button("Salvar");
		salvar.addStyleName("friendly");
		salvar.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					fieldGroup.commit();
					TipoAdvertencia tipoAdvertencia = fieldGroup.getItemDataSource().getBean();

					for (TipoAdvertenciaViewListener l : listeners) {
						l.salvar(tipoAdvertencia);
					}
				} catch (CommitException e) {
					e.getStackTrace();
				}
			}
		});

		cancelar = new Button("Limpar");
		cancelar.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				fieldGroup.setItemDataSource(new TipoAdvertencia());
				excluir.setEnabled(false);
				tabela.setValue(null);
			}
		});

		excluir = new Button("Excluir");
		excluir.addStyleName("danger");
		excluir.setEnabled(false);
		excluir.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {

				try {
					fieldGroup.commit();
				} catch (CommitException e) {
					e.getStackTrace();
				}

				TipoAdvertencia tipoAdvertencia = fieldGroup.getItemDataSource().getBean();
				for (TipoAdvertenciaViewListener l : listeners)
					l.excluir(tipoAdvertencia);

				excluir.setEnabled(false);
				tabela.setValue(null);

			}
		});

		HorizontalLayout botoes = new HorizontalLayout();
		botoes.setSpacing(true);
		botoes.addComponents(salvar, cancelar, excluir);

		Label formLabel = new Label("Informações do Tipo de Distribuição");
		formLabel.setStyleName("h2");

		VerticalLayout manutencao = new VerticalLayout();
		manutencao.setMargin(true);
		manutencao.setSpacing(true);
		manutencao.addComponents(formLabel, nome, nivel, botoes);

		Panel painelManutencao = new Panel();
		painelManutencao.setContent(manutencao);

		Label tableLabel = new Label("Perfis já cadastrados");
		tableLabel.setStyleName("h2");

		VerticalLayout listagem = new VerticalLayout();
		listagem.setMargin(true);
		listagem.setSpacing(true);
		listagem.addComponents(tableLabel, tabela);

		listagem.setWidth("100%");

		Panel panelListagem = new Panel();
		panelListagem.setContent(listagem);

		Label tituloTela = new Label("Cadastro de Perfil");
		tituloTela.setStyleName("h1");

		this.addComponents(tituloTela, painelManutencao, panelListagem);
		this.setSpacing(true);
		this.setMargin(true);

		fieldGroup = new BeanFieldGroup<TipoAdvertencia>(TipoAdvertencia.class);
		bean = new TipoAdvertencia();
		fieldGroup.setItemDataSource(bean);
		fieldGroup.bindMemberFields(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		for (TipoAdvertenciaViewListener l : listeners)
			l.enter();
	}

	@Override
	public void addListener(TipoAdvertenciaViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void editar(TipoAdvertencia e) {
		fieldGroup.setItemDataSource(e);
		excluir.setEnabled(true);
	}

	@Override
	public void mostrarTabela(List<TipoAdvertencia> lista) {
		tabela.removeAllItems();

		for (TipoAdvertencia tipoAdvertencia : lista) {
			tabela.addItem(
					new Object[] { tipoAdvertencia.getId(), tipoAdvertencia.getNome(), tipoAdvertencia.getNivel() },
					tipoAdvertencia.getId());
		}
	}
}