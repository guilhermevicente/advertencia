package com.escola.advertencia.teste;

import org.junit.Assert;
import org.junit.Test;

import com.escola.advertencia.model.Funcionario;

public class TesteFuncionario {
    
    @Test
    public void teste() {
        Funcionario funcionario = new Funcionario();
        
        funcionario.setNome("Guilherme");
        
        Assert.assertEquals("Guilherme", funcionario.getNome());
    }
}
