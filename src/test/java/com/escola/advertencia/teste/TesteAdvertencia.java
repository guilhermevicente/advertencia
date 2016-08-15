package com.escola.advertencia.teste;

import org.junit.Assert;
import org.junit.Test;

import com.escola.advertencia.model.Advertencia;
import com.escola.advertencia.model.TipoAdvertencia;

public class TesteAdvertencia {
    
    @Test
    public void texto() {
        Advertencia advertencia = new Advertencia();
        
        advertencia.setTexto("Aluno pulou o muro");
        
        Assert.assertEquals("Aluno pulou o muro", advertencia.getTexto());
    }
    
    @Test
    public void tipo() {
        TipoAdvertencia tipoAdvertencia = new TipoAdvertencia();
        
        tipoAdvertencia.setNivel(1);
        
        Integer nivel = 1;
        
        Assert.assertEquals(nivel, tipoAdvertencia.getNivel());
        
        Advertencia advertencia = new Advertencia();
        
        advertencia.setTipoAdvertencia(tipoAdvertencia);
        
        Assert.assertEquals(tipoAdvertencia.getNivel(), 
                advertencia.getTipoAdvertencia().getNivel());
    }
}
