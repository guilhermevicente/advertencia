package com.escola.advertencia.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.naming.NamingException;

import com.escola.advertencia.bo.CategoriaBO;
import com.escola.advertencia.model.Teste;
import com.escola.advertencia.presentation.view.CadCategoriaView;
import com.escola.advertencia.presentation.view.CadCategoriaView.CadCategoriaViewListener;
import com.escola.advertencia.presentation.view.impl.CadCategoriaViewImpl;
import com.escola.advertencia.utils.EJBUtility;

@Stateful
public class CadCategoriaPresenter implements CadCategoriaViewListener, Presenter {

	private CategoriaBO categoriaBO;

	private CadCategoriaView view;

	public CadCategoriaPresenter() {
		view = new CadCategoriaViewImpl();
		view.addListener(this);

		try {
			this.categoriaBO = (CategoriaBO) EJBUtility.getInitialContext()
					.lookup("java:module/CategoriaBOImpl!com.escola.advertencia.bo.CategoriaBO");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
	}

	@Override
	public void enter() {
		List<Teste> testes = new ArrayList<>(this.categoriaBO.listAll());
		view.mostrarTabela(testes);
	}

	@Override
	public void salvar(Teste categoria) {
		this.categoriaBO.saveOrUpdate(categoria);
		this.enter();
	}

	@Override
	public void itemSelecionado(Integer id) {
		view.editar(categoriaBO.find(id));
	}

	@Override
	public void excluir(Teste objeto) {
		 try {
			 categoriaBO.delete(objeto);
			 this.enter();
		 } catch(Exception ex){
			 ex.getStackTrace();
		 }
	}

	public CadCategoriaView getView() {
		return view;
	}
}