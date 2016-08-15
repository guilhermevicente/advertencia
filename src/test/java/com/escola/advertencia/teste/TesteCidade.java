package com.escola.advertencia.teste;

import org.junit.Assert;
import org.junit.Test;

import com.escola.advertencia.model.Cidade;

public class TesteCidade {
    
    @Test
    public void teste() {
        Cidade cidade = new Cidade();
        
        cidade.setNome("Campo Grande");
        
        Assert.assertEquals("Campo Grande", cidade.getNome());
    }
}
