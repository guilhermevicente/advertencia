package com.escola.advertencia.teste;

import org.junit.Assert;
import org.junit.Test;

import com.escola.advertencia.model.Escola;

public class TesteEscola {
    
    @Test
    public void teste() {
        Escola escola = new Escola();
        
        escola.setNome("Felicidade");
        
        Assert.assertEquals("Felicidade", escola.getNome());
    }
}
