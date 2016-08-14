package com.escola.advertencia.presentation.view.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import com.escola.advertencia.model.Teste;
import com.escola.advertencia.presentation.view.CadCategoriaView;
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
public class CadCategoriaViewImpl extends GenericViewImpl implements CadCategoriaView {

	// Elementos para utilizacao do binding
	private BeanFieldGroup<Teste> fieldGroup;
	private Teste bean;

	private TextField nome;
	private Button salvar, cancelar, excluir;

	// Elementos da Tabela e Paginacao
	private Table tabela;

	private List<CadCategoriaViewListener> listeners = new ArrayList<CadCategoriaViewListener>();

	public CadCategoriaViewImpl() {

		tabela = new Table();
		tabela.addContainerProperty("id", Integer.class, null);
		tabela.addContainerProperty("nome", String.class, null);
		tabela.setSizeFull();
		tabela.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Integer categoria = (Integer) tabela.getValue();

				if (categoria != null) {
					excluir.setEnabled(true);
					
					for (CadCategoriaViewListener l : listeners)
						l.itemSelecionado(categoria);
					
				} else {
					fieldGroup.setItemDataSource(new Teste());
					excluir.setEnabled(false);
				}
			}
		});

		nome = new TextField("Nome da Categoria");
		nome.setNullRepresentation("");
		nome.setRequired(true);

		salvar = new Button("Salvar");
		salvar.addStyleName("friendly");
		salvar.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					fieldGroup.commit();
					Teste categoria = fieldGroup.getItemDataSource().getBean();
					
					for (CadCategoriaViewListener l : listeners) {
						l.salvar(categoria);
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
				fieldGroup.setItemDataSource(new Teste());
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

				Teste p = fieldGroup.getItemDataSource().getBean();
				for (CadCategoriaViewListener l : listeners)
					l.excluir(p);

				excluir.setEnabled(false);
				tabela.setValue(null);

			}
		});

		HorizontalLayout botoes = new HorizontalLayout();
		botoes.setSpacing(true);
		botoes.addComponents(salvar, cancelar, excluir);

		Label formLabel = new Label("Informações da Categoria");
		formLabel.setStyleName("h2");

		VerticalLayout manutencao = new VerticalLayout();
		manutencao.setMargin(true);
		manutencao.setSpacing(true);
		manutencao.addComponents(formLabel, nome, botoes);

		Panel painelManutencao = new Panel();
		painelManutencao.setContent(manutencao);

		Label tableLabel = new Label("Categorias já cadastradas");
		tableLabel.setStyleName("h2");

		VerticalLayout listagem = new VerticalLayout();
		listagem.setMargin(true);
		listagem.setSpacing(true);
		listagem.addComponents(tableLabel, tabela);

		listagem.setWidth("100%");

		Panel panelListagem = new Panel();
		panelListagem.setContent(listagem);

		Label tituloTela = new Label("Teste da tela");
		tituloTela.setStyleName("h1");
		
		this.addComponents(tituloTela, painelManutencao, panelListagem);
		this.setSpacing(true);
		this.setMargin(true);

		fieldGroup = new BeanFieldGroup<Teste>(Teste.class);
		bean = new Teste();
		fieldGroup.setItemDataSource(bean);
		fieldGroup.bindMemberFields(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		for (CadCategoriaViewListener l : listeners)
			l.enter();
	}

	@Override
	public void addListener(CadCategoriaViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void editar(Teste e) {
		fieldGroup.setItemDataSource(e);
		excluir.setEnabled(true);
	}

	@Override
	public void mostrarTabela(List<Teste> lista) {
		tabela.removeAllItems();

		for (Teste teste : lista) {
//			Object newItemId = tabela.addItem();
//
//			Item row1 = tabela.getItem(newItemId);
//			row1.getItemProperty("id").setValue(teste.getId());
//			row1.getItemProperty("nome").setValue(teste.getNome());
			
			tabela.addItem(new Object[] { teste.getId(), teste.getNome()}, teste.getId());
		}
	}
}