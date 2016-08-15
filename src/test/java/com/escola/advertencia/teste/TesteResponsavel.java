package com.escola.advertencia.teste;

import org.junit.Assert;
import org.junit.Test;

import com.escola.advertencia.model.Responsavel;

public class TesteResponsavel {
    
    @Test
    public void teste() {
        Responsavel responsavel = new Responsavel();
        
        responsavel.setNome("Guilherme");
        
        Assert.assertEquals("Guilherme", responsavel.getNome());
    }
}
