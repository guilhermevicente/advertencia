package com.escola.advertencia.presentation.view.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import com.escola.advertencia.model.Perfil;
import com.escola.advertencia.presentation.view.PerfilView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
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
public class PerfilViewImpl extends GenericViewImpl implements PerfilView {

	// Elementos para utilizacao do binding
	private BeanFieldGroup<Perfil> fieldGroup;
	private Perfil bean;

	private TextField nome, permissao;
	private Button salvar, cancelar, excluir;

	// Elementos da Tabela e Paginacao
	private Table tabela;

	private List<PerfilViewListener> listeners = new ArrayList<PerfilViewListener>();

	public PerfilViewImpl() {

		tabela = new Table();
		tabela.addContainerProperty("id", Integer.class, null);
		tabela.addContainerProperty("nome", String.class, null);
		tabela.addContainerProperty("permissao", String.class, null);
		tabela.setSizeFull();
		tabela.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Integer perfil = (Integer) tabela.getValue();

				if (perfil != null) {
					excluir.setEnabled(true);
					
					for (PerfilViewListener l : listeners)
						l.itemSelecionado(perfil);
					
				} else {
					fieldGroup.setItemDataSource(new Perfil());
					excluir.setEnabled(false);
				}
			}
		});

		nome = new TextField("Nome do perfil");
		nome.setNullRepresentation("");
		nome.setRequired(true);
		
		// CSS
		nome.setIcon(FontAwesome.USER);
		nome.addStyleName("large");
		nome.addStyleName("inline-icon");
		nome.setWidth("100%");
				
		permissao = new TextField("Permissão para perfil");
		permissao.setNullRepresentation("");
		permissao.setRequired(true);
		
		// CSS
		permissao.setIcon(FontAwesome.USER);
		permissao.addStyleName("large");
		permissao.addStyleName("inline-icon");
		permissao.setWidth("100%");
		
		salvar = new Button("Salvar");
		salvar.addStyleName("friendly");
		salvar.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					fieldGroup.commit();
					Perfil perfil = fieldGroup.getItemDataSource().getBean();
					
					for (PerfilViewListener l : listeners) {
						l.salvar(perfil);
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
				fieldGroup.setItemDataSource(new Perfil());
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

				Perfil p = fieldGroup.getItemDataSource().getBean();
				for (PerfilViewListener l : listeners)
					l.excluir(p);

				excluir.setEnabled(false);
				tabela.setValue(null);

			}
		});

		HorizontalLayout botoes = new HorizontalLayout();
		botoes.setSpacing(true);
		botoes.addComponents(salvar, cancelar, excluir);

		Label formLabel = new Label("Informações do perfil");
		formLabel.setStyleName("h2");

		VerticalLayout manutencao = new VerticalLayout();
		manutencao.setMargin(true);
		manutencao.setSpacing(true);
		manutencao.addComponents(formLabel, nome, permissao, botoes);

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

		fieldGroup = new BeanFieldGroup<Perfil>(Perfil.class);
		bean = new Perfil();
		fieldGroup.setItemDataSource(bean);
		fieldGroup.bindMemberFields(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		for (PerfilViewListener l : listeners)
			l.enter();
	}

	@Override
	public void addListener(PerfilViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void editar(Perfil e) {
		fieldGroup.setItemDataSource(e);
		excluir.setEnabled(true);
	}

	@Override
	public void mostrarTabela(List<Perfil> lista) {
		tabela.removeAllItems();

		for (Perfil perfil : lista) {
//			Object newItemId = tabela.addItem();
//
//			Item row1 = tabela.getItem(newItemId);
//			row1.getItemProperty("id").setValue(perfil.getId());
//			row1.getItemProperty("nome").setValue(perfil.getNome());
			
			tabela.addItem(new Object[] { perfil.getId(), perfil.getNome(), perfil.getPermissao()}, perfil.getId());
		}
	}
}