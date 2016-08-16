package com.escola.advertencia.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema="advertencia", name="tb_advertencia")
public class Advertencia implements Serializable {

	private static final long serialVersionUID = -1083377181139251841L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="texto")
	private String texto;
	
	@Column(name="data_emissao")
    private Date dataEmissao;
	
	@ManyToOne
	@JoinColumn(name = "funcionario")
    private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name = "tipo_advertencia")
    private TipoAdvertencia tipoAdvertencia;
	
	private Integer aluno;
	
	@Transient
	private Aluno alunoT;
    
    public Advertencia() {
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public TipoAdvertencia getTipoAdvertencia() {
        return tipoAdvertencia;
    }

    public void setTipoAdvertencia(TipoAdvertencia tipoAdvertencia) {
        this.tipoAdvertencia = tipoAdvertencia;
    }
    
    public Integer getAluno() {
		return aluno;
	}

	public void setAluno(Integer aluno) {
		this.aluno = aluno;
	}
	
	public Aluno getAlunoT() {
		return alunoT;
	}

	public void setAlunoT(Aluno alunoT) {
		this.alunoT = alunoT;
	}

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.texto);
        hash = 89 * hash + Objects.hashCode(this.dataEmissao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Advertencia other = (Advertencia) obj;
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        if (!Objects.equals(this.dataEmissao, other.dataEmissao)) {
            return false;
        }
        return true;
    }
}
