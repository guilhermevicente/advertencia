package com.escola.advertencia.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.naming.NamingException;

import com.escola.advertencia.bo.TipoAdvertenciaBO;
import com.escola.advertencia.model.TipoAdvertencia;
import com.escola.advertencia.presentation.view.TipoAdvertenciaView;
import com.escola.advertencia.presentation.view.TipoAdvertenciaView.TipoAdvertenciaViewListener;
import com.escola.advertencia.presentation.view.impl.TipoAdvertenciaViewImpl;
import com.escola.advertencia.utils.EJBUtility;

@Stateful
public class TipoAdvertenciaPresenter implements TipoAdvertenciaViewListener, Presenter {

	private TipoAdvertenciaBO tipoAdvertenciaBO;

	private TipoAdvertenciaView view;

	public TipoAdvertenciaPresenter() {
		view = new TipoAdvertenciaViewImpl();
		view.addListener(this);

		try {
			this.tipoAdvertenciaBO = (TipoAdvertenciaBO) EJBUtility.getInitialContext()
					.lookup("java:module/TipoAdvertenciaBOImpl!com.escola.advertencia.bo.TipoAdvertenciaBO");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
	}

	@Override
	public void enter() {
		List<TipoAdvertencia> tiposAdvertencia = new ArrayList<>(this.tipoAdvertenciaBO.listAll());
		view.mostrarTabela(tiposAdvertencia);
	}

	@Override
	public void salvar(TipoAdvertencia tipoAdvertencia) {
		this.tipoAdvertenciaBO.saveOrUpdate(tipoAdvertencia);
		this.enter();
	}

	@Override
	public void itemSelecionado(Integer id) {
		view.editar(tipoAdvertenciaBO.find(id));
	}

	@Override
	public void excluir(TipoAdvertencia objeto) {
		 try {
			 tipoAdvertenciaBO.delete(objeto);
			 this.enter();
		 } catch(Exception ex){
			 ex.getStackTrace();
		 }
	}

	public TipoAdvertenciaView getView() {
		return view;
	}
}