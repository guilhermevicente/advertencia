package com.escola.advertencia.presentation.presenter;

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
	public void enter(boolean todos) {
		// view.mostrarTabela(lista);
	}

	@Override
	public void salvar(Teste categoria) {
		this.categoriaBO.saveOrUpdate(categoria);
	}

	@Override
	public void itemSelecionado(Integer id) {
		// view.editar(categoriaBO.find(id));
	}

	@Override
	public void excluir(Teste objeto) {
		// try {
		// categoriaBO.delete(objeto);
		// view.sucesso("Excluí­do com sucesso!");
		// //view.mostrarTabela(bo.listAll());
		// enter(false);
		// } catch(Exception ex){
		// view.falha("Não foi possível excluir a categoria, verifique se ela
		// possui algum tipo de distribuição vinculado.");
		// }
	}

	public CadCategoriaView getView() {
		return view;
	}
}