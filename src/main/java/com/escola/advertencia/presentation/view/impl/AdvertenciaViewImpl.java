package com.escola.advertencia.presentation.view.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;

import com.escola.advertencia.model.Advertencia;
import com.escola.advertencia.model.Aluno;
import com.escola.advertencia.model.Funcionario;
import com.escola.advertencia.model.TipoAdvertencia;
import com.escola.advertencia.presentation.view.AdvertenciaView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Stateful
public class AdvertenciaViewImpl extends GenericViewImpl implements AdvertenciaView {

	// Elementos para utilizacao do binding
	private BeanFieldGroup<Advertencia> fieldGroup;
	private Advertencia bean;

	private TextField texto;
	
	private DateField dataEmissao;
	
	private ComboBox funcionario, tipoAdvertencia, alunoCombo;
	
	private Button salvar, cancelar, excluir;

	// Elementos da Tabela e Paginacao
	private Table tabela;

	private List<AdvertenciaViewListener> listeners = new ArrayList<AdvertenciaViewListener>();

	public AdvertenciaViewImpl() {

		tabela = new Table();
		tabela.addContainerProperty("id", Integer.class, null);
		tabela.addContainerProperty("texto", String.class, null);
		tabela.addContainerProperty("dataEmissao", Date.class, null);
		tabela.addContainerProperty("funcionario", Funcionario.class, null);
		tabela.addContainerProperty("tipoAdvertencia", TipoAdvertencia.class, null);
		tabela.addContainerProperty("alunoT", Aluno.class, null);
		
		tabela.setColumnHeader("id", "Registro");
		tabela.setColumnHeader("texto", "Advertência");
		tabela.setColumnHeader("dataEmissao", "Data");
		tabela.setColumnHeader("funcionario", "Funcionário");
		tabela.setColumnHeader("tipoAdvertencia", "Tipo");
		tabela.setColumnHeader("alunoT", "Aluno");
		
		tabela.setSizeFull();
		tabela.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Integer advertencia = (Integer) tabela.getValue();

				if (advertencia != null) {
					excluir.setEnabled(true);

					for (AdvertenciaViewListener l : listeners)
						l.itemSelecionado(advertencia);

				} else {
					fieldGroup.setItemDataSource(new Advertencia());
					excluir.setEnabled(false);
				}
			}
		});

		texto = new TextField("Texto");
		texto.setNullRepresentation("");
		texto.setRequired(true);
		texto.setWidth("100%");

		dataEmissao = new DateField();
		dataEmissao.setWidth("100%");
		
		funcionario = new ComboBox();
		funcionario.setWidth("100%");
		
		tipoAdvertencia = new ComboBox();
		tipoAdvertencia.setWidth("100%");
		
		alunoCombo = new ComboBox();
		alunoCombo.setWidth("100%");
		
		salvar = new Button("Salvar");
		salvar.addStyleName("friendly");
		salvar.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					fieldGroup.commit();
					Advertencia advertencia = fieldGroup.getItemDataSource().getBean();

					Aluno aluno = (Aluno) alunoCombo.getValue();
					
					advertencia.setAluno(aluno.getId());
					
					for (AdvertenciaViewListener l : listeners) {
						l.salvar(advertencia);
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
				fieldGroup.setItemDataSource(new Advertencia());
				alunoCombo.select(null);
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

				Advertencia advertencia = fieldGroup.getItemDataSource().getBean();
				for (AdvertenciaViewListener l : listeners)
					l.excluir(advertencia);

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
		manutencao.addComponents(formLabel, texto, dataEmissao, funcionario, 
				tipoAdvertencia, alunoCombo, botoes);

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

		fieldGroup = new BeanFieldGroup<Advertencia>(Advertencia.class);
		bean = new Advertencia();
		fieldGroup.setItemDataSource(bean);
		fieldGroup.bindMemberFields(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		for (AdvertenciaViewListener l : listeners)
			l.enter();
	}

	@Override
	public void addListener(AdvertenciaViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void editar(Advertencia e) {
		fieldGroup.setItemDataSource(e);
		alunoCombo.select(e.getAlunoT());
		excluir.setEnabled(true);
	}

	@Override
	public void mostrarTabela(List<Advertencia> lista) {
		tabela.removeAllItems();

		tabela.addContainerProperty("id", Integer.class, null);
		tabela.addContainerProperty("texto", String.class, null);
		tabela.addContainerProperty("dataEmissao", Date.class, null);
		tabela.addContainerProperty("funcionario", Funcionario.class, null);
		tabela.addContainerProperty("tipoAdvertencia", TipoAdvertencia.class, null);
		tabela.addContainerProperty("alunoT", Aluno.class, null);

		for (Advertencia advertencia : lista) {
			tabela.addItem(
					new Object[] { advertencia.getId(), advertencia.getTexto(), advertencia.getDataEmissao(),
							advertencia.getFuncionario(), advertencia.getTipoAdvertencia(), advertencia.getAlunoT() },
					advertencia.getId());
		}
	}
	
	@Override
	public void montarCombos(List<Funcionario> funcionarios, List<TipoAdvertencia> tiposAdvertencias, List<Aluno> alunos) {
		funcionario.addItems(funcionarios);
		tipoAdvertencia.addItems(tiposAdvertencias);
		alunoCombo.addItems(alunos);
	}
}