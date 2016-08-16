package com.escola.advertencia.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema="advertencia", name="aluno")
public class Aluno extends Pessoa {
    
	private static final long serialVersionUID = 6410859083682537114L;

	@Column(name="registro_aluno")
	private Integer registroAluno;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "escola")
    private Escola escola;
	
	@OneToMany(mappedBy="aluno", fetch=FetchType.LAZY)
    private List<ResponsavelAluno> responsaveisAluno;
	
    public Aluno() {
    }

    public Integer getRegistroAluno() {
        return registroAluno;
    }

    public void setRegistroAluno(Integer registroAluno) {
        this.registroAluno = registroAluno;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.registroAluno);
        return hash;
    }
	    
    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }
    
    public List<ResponsavelAluno> getResponsaveisAluno() {
		return responsaveisAluno;
	}

	public void setResponsaveisAluno(List<ResponsavelAluno> responsaveisAluno) {
		this.responsaveisAluno = responsaveisAluno;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluno other = (Aluno) obj;
        if (!Objects.equals(this.registroAluno, other.registroAluno)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return this.getNome();
	}
}
