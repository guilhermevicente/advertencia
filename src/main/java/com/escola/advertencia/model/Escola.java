package com.escola.advertencia.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema="advertencia", name="escola")
public class Escola implements Serializable {
	
	private static final long serialVersionUID = -4509417090020201742L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nome")
    private String nome;
    
	@ManyToOne
	@JoinColumn(name = "bairro")
    private Bairro bairro;
    
	@OneToMany(mappedBy="escola")
    private List<Aluno> alunos;
	
	@OneToMany(mappedBy="escola")
	private List<EscolaFuncionario> escolasFuncionarios;

    public Escola() {
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    public List<EscolaFuncionario> getEscolasFuncionarios() {
		return escolasFuncionarios;
	}

	public void setEscolasFuncionarios(List<EscolaFuncionario> escolasFuncionarios) {
		this.escolasFuncionarios = escolasFuncionarios;
	}

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nome);
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
        final Escola other = (Escola) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
}
