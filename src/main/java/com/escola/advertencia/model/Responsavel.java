package com.escola.advertencia.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema="advertencia", name="responsavel")
public class Responsavel extends Pessoa {
	
	private static final long serialVersionUID = 8764108078657247466L;

	@Column(name="profissao")
    private String profissao;
	
	@Column(name="contato")
    private String contato;
	
	@Column(name="registro_responsavel")
    private Integer registroResponsavel;
	
	@OneToMany(mappedBy="responsavel")
    private List<ResponsavelAluno> responsaveisAlunos;

    public Responsavel() {
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Integer getRegistroResponsavel() {
        return registroResponsavel;
    }

    public void setRegistroResponsavel(Integer registroResponsavel) {
        this.registroResponsavel = registroResponsavel;
    }

    public List<ResponsavelAluno> getResponsaveisAlunos() {
        return responsaveisAlunos;
    }

    public void setResponsaveisAlunos(List<ResponsavelAluno> alunos) {
        this.responsaveisAlunos = alunos;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.profissao);
        hash = 47 * hash + Objects.hashCode(this.contato);
        hash = 47 * hash + Objects.hashCode(this.registroResponsavel);
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
        final Responsavel other = (Responsavel) obj;
        if (!Objects.equals(this.profissao, other.profissao)) {
            return false;
        }
        if (!Objects.equals(this.contato, other.contato)) {
            return false;
        }
        if (!Objects.equals(this.registroResponsavel, other.registroResponsavel)) {
            return false;
        }
        return true;
    }
}
