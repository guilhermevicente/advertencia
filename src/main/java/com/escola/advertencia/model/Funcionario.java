package com.escola.advertencia.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema="advertencia", name="funcionario")
public class Funcionario extends Pessoa {
	
	private static final long serialVersionUID = -4225791988736925127L;

	@Column(name="registro_funcionario")
    private Integer registroFuncionario;

	@OneToMany(mappedBy="funcionario")
    private List<Advertencia> advertencias;
    
    public Funcionario() {
    }
    
	public Integer getRegistroFuncionario() {
        return registroFuncionario;
    }

    public void setRegistroFuncionario(Integer registroFuncionario) {
        this.registroFuncionario = registroFuncionario;
    }

    public List<Advertencia> getAdvertencias() {
        return advertencias;
    }

    public void setAdvertencias(List<Advertencia> advertencias) {
        this.advertencias = advertencias;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.registroFuncionario);
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.registroFuncionario, other.registroFuncionario)) {
            return false;
        }
        return true;
    }
}
