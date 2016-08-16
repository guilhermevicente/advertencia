package com.escola.advertencia.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.naming.NamingException;

import com.escola.advertencia.bo.AdvertenciaBO;
import com.escola.advertencia.bo.AlunoBO;
import com.escola.advertencia.bo.FuncionarioBO;
import com.escola.advertencia.bo.TipoAdvertenciaBO;
import com.escola.advertencia.model.Advertencia;
import com.escola.advertencia.model.Aluno;
import com.escola.advertencia.model.Funcionario;
import com.escola.advertencia.model.TipoAdvertencia;
import com.escola.advertencia.presentation.view.AdvertenciaView;
import com.escola.advertencia.presentation.view.AdvertenciaView.AdvertenciaViewListener;
import com.escola.advertencia.presentation.view.impl.AdvertenciaViewImpl;
import com.escola.advertencia.utils.EJBUtility;

@Stateful
public class AdvertenciaPresenter implements AdvertenciaViewListener, Presenter {

	private AdvertenciaBO advertenciaBO;
	
	private FuncionarioBO funcionarioBO;
	
	private TipoAdvertenciaBO tipoAdvertenciaBO;
	
	private AlunoBO alunoBO;

	private AdvertenciaView view;

	public AdvertenciaPresenter() {
		view = new AdvertenciaViewImpl();
		view.addListener(this);

		try {
			this.advertenciaBO = (AdvertenciaBO) EJBUtility.getInitialContext()
					.lookup("java:module/AdvertenciaBOImpl!com.escola.advertencia.bo.AdvertenciaBO");
			
			this.funcionarioBO = (FuncionarioBO) EJBUtility.getInitialContext()
					.lookup("java:module/FuncionarioBOImpl!com.escola.advertencia.bo.FuncionarioBO");
			
			this.tipoAdvertenciaBO = (TipoAdvertenciaBO) EJBUtility.getInitialContext()
					.lookup("java:module/TipoAdvertenciaBOImpl!com.escola.advertencia.bo.TipoAdvertenciaBO");
			
			this.alunoBO = (AlunoBO) EJBUtility.getInitialContext()
					.lookup("java:module/AlunoBOImpl!com.escola.advertencia.bo.AlunoBO");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
	}

	@Override
	public void enter() {
		List<Advertencia> advertencias = new ArrayList<>(this.advertenciaBO.listAll());
		
		for (Advertencia advertencia : advertencias) {
			Aluno alunoT = this.alunoBO.find(advertencia.getAluno());
			advertencia.setAlunoT(alunoT);
		}
		
		view.mostrarTabela(advertencias);
		
		List<Funcionario> funcionarios = new ArrayList<>(this.funcionarioBO.listAll());
		
		List<TipoAdvertencia> tiposAdvertencias = new ArrayList<>(this.tipoAdvertenciaBO.listAll());
		
		List<Aluno> alunos  = new ArrayList<>(this.alunoBO.listAll());
		
		view.montarCombos(funcionarios, tiposAdvertencias, alunos);
	}

	@Override
	public void salvar(Advertencia advertencia) {
		this.advertenciaBO.saveOrUpdate(advertencia);
		this.enter();
	}

	@Override
	public void itemSelecionado(Integer id) {
		Advertencia advertencia = advertenciaBO.find(id);
		
		Aluno alunoT = this.alunoBO.find(advertencia.getAluno());
		
		advertencia.setAlunoT(alunoT);
		
		view.editar(advertencia);
	}

	@Override
	public void excluir(Advertencia objeto) {
		 try {
			 advertenciaBO.delete(objeto);
			 this.enter();
		 } catch(Exception ex){
			 ex.getStackTrace();
		 }
	}

	public AdvertenciaView getView() {
		return view;
	}
}