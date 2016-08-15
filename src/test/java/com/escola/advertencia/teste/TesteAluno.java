package com.escola.advertencia.teste;

import org.junit.Assert;
import org.junit.Test;

import com.escola.advertencia.model.Aluno;

public class TesteAluno {
    
    @Test
    public void teste() {
        Aluno aluno = new Aluno();
        
        aluno.setNome("Guilherme");
        
        Assert.assertEquals("Guilherme", aluno.getNome());
    }
}
