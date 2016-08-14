package com.escola.advertencia.presentation.view.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;

import com.escola.advertencia.model.Teste;
import com.escola.advertencia.presentation.view.CadCategoriaView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WebBrowser;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Video;

@SuppressWarnings("serial")
@Stateful
public class CadCategoriaViewImpl extends GenericViewImpl implements CadCategoriaView {

	// Elementos para utilizacao do binding
	private BeanFieldGroup<Teste> fieldGroup;
	private Teste bean;

	private TextField nome;
	private Button salvar, cancelar, excluir;

	// Titulo da pagina
	private Label tituloTela = new Label("Teste da tela");

	// Elementos da Tabela e Paginacao
	private Table tabela;

	private List<CadCategoriaViewListener> listeners = new ArrayList<CadCategoriaViewListener>();

	public CadCategoriaViewImpl() {
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("nome", "Nome da Categoria");
		headers.put("ativo", "Ativo");

		tabela = new Table();
		tabela.setWidthUndefined();
		tabela.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Teste categoria = (Teste) tabela.getValue();

				if (categoria != null) {
					excluir.setEnabled(true);
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
					if (!categoria.getNome().trim().isEmpty()) {
						for (CadCategoriaViewListener l : listeners) {
							l.salvar(categoria);
						}
					} else {
						// FIXME Mensagem.alerta("O nome não foi preenchido");
					}
				} catch (CommitException e) {
					// FIXME Mensagem.erro("Não foi possível gravar!");
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
					// FIXME : Mensagem.erro("Não foi remover!");
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

		Panel panelListagem = new Panel();
		panelListagem.setContent(listagem);

		HorizontalLayout tituloTelaBotaoAjuda = new HorizontalLayout();
		tituloTelaBotaoAjuda.addComponents(tituloTela);
		tituloTelaBotaoAjuda.setSizeFull();

		this.addComponents(tituloTelaBotaoAjuda, painelManutencao, panelListagem);
		this.setSpacing(true);
		this.setMargin(true);

		fieldGroup = new BeanFieldGroup<Teste>(Teste.class);
		bean = new Teste();
		fieldGroup.setItemDataSource(bean);
		fieldGroup.bindMemberFields(this);
	}

	protected VerticalLayout getAjudaTelaCategoria() {
		WebBrowser browser = UI.getCurrent().getPage().getWebBrowser();
		if (browser.isIE()) {
			// FIXME Mensagem.alertaVideoNavegadorIE("Verificamos que você está
			// utilizando o IE. Caso o vídeo não abra nos próximos segundos,
			// contate o administrador.");
		}

		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

		final Resource mp4Resource = new FileResource(new File(basepath + "/WEB-INF/video/categoria.mp4"));

		final Resource ogvResource = new FileResource(new File(basepath + "/WEB-INF/video/ogv/categoria.ogv"));

		Video video = new Video();
		video.setSources(mp4Resource, ogvResource);
		video.setSizeFull();
		video.setHtmlContentAllowed(true);
		video.setAltText("Não foi possível reproduzir o video");
		video.setWidth("100%");
		video.setHeight("100%");
		video.setAutoplay(true);

		VerticalLayout embeddedLayout = new VerticalLayout();
		embeddedLayout.addComponent(video);
		embeddedLayout.setWidth("100%");
		embeddedLayout.setHeight("100%");

		return embeddedLayout;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		for (CadCategoriaViewListener l : listeners)
			l.enter(true);
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
		tabela.addItems(lista);
	}

	@Override
	public void sucesso(String msg) {
		fieldGroup.setItemDataSource(new Teste());
		excluir.setEnabled(false);

		Notification n = new Notification(msg);
		n.setDelayMsec(3000);
		n.setStyleName("bar success closable");
		n.setPosition(Position.BOTTOM_LEFT);
		n.show(Page.getCurrent());
	}

	@Override
	public void falha(String msg) {
		fieldGroup.setItemDataSource(new Teste());
		excluir.setEnabled(false);

		Notification n = new Notification(msg);
		n.setDelayMsec(3000);
		n.setStyleName("bar failure closable");
		n.setPosition(Position.BOTTOM_LEFT);
		n.show(Page.getCurrent());
	}
}