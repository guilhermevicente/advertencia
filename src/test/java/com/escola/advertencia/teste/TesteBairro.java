package com.escola.advertencia.teste;

import org.junit.Assert;
import org.junit.Test;

import com.escola.advertencia.model.Bairro;

public class TesteBairro {
    
    @Test
    public void teste() {
        Bairro bairro = new Bairro();
        
        bairro.setNome("Monte Castelo");
        
        Assert.assertEquals("Monte Castelo", bairro.getNome());
    }
}
