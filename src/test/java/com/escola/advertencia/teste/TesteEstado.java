package com.escola.advertencia.teste;

import org.junit.Assert;
import org.junit.Test;

import com.escola.advertencia.model.Estado;

public class TesteEstado {
    
    @Test
    public void teste() {
        Estado estado = new Estado();
        
        estado.setNome("Mato Grosso do Sul");
        
        Assert.assertEquals("Mato Grosso do Sul", estado.getNome());
    }
}
