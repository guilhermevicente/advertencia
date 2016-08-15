package com.escola.advertencia.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.naming.NamingException;

import com.escola.advertencia.bo.PerfilBO;
import com.escola.advertencia.model.Perfil;
import com.escola.advertencia.presentation.view.PerfilView;
import com.escola.advertencia.presentation.view.PerfilView.PerfilViewListener;
import com.escola.advertencia.presentation.view.impl.PerfilViewImpl;
import com.escola.advertencia.utils.EJBUtility;

@Stateful
public class PerfilPresenter implements PerfilViewListener, Presenter {

	private PerfilBO perfilBO;

	private PerfilView view;

	public PerfilPresenter() {
		view = new PerfilViewImpl();
		view.addListener(this);

		try {
			this.perfilBO = (PerfilBO) EJBUtility.getInitialContext()
					.lookup("java:module/PerfilBOImpl!com.escola.advertencia.bo.PerfilBO");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
	}

	@Override
	public void enter() {
		List<Perfil> perfis = new ArrayList<>(this.perfilBO.listAll());
		view.mostrarTabela(perfis);
	}

	@Override
	public void salvar(Perfil perfil) {
		this.perfilBO.saveOrUpdate(perfil);
		this.enter();
	}

	@Override
	public void itemSelecionado(Integer id) {
		view.editar(perfilBO.find(id));
	}

	@Override
	public void excluir(Perfil objeto) {
		 try {
			 perfilBO.delete(objeto);
			 this.enter();
		 } catch(Exception ex){
			 ex.getStackTrace();
		 }
	}

	public PerfilView getView() {
		return view;
	}
}