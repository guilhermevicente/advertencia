package com.escola.advertencia.teste;

import org.junit.Assert;
import org.junit.Test;

import com.escola.advertencia.model.Aluno;
import com.escola.advertencia.model.Perfil;
import com.escola.advertencia.model.Pessoa;
import com.escola.advertencia.model.Usuario;

public class TestePessoaUsuario {
    
    @Test
    public void teste() {
        Perfil perfil = new Perfil();
        
        perfil.setPermissao("1010");
        
        Assert.assertEquals("1010", perfil.getPermissao());
        
        Usuario usuario = new Usuario();
        
        usuario.setPerfil(perfil);
        usuario.setLogin("admin");
        
        Assert.assertEquals("1010", usuario.getPerfil().getPermissao());
        Assert.assertEquals("admin", usuario.getLogin());
        
        Pessoa pessoa = new Aluno();
        
        pessoa.setUsuario(usuario);
        
        Assert.assertEquals("admin", pessoa.getUsuario().getLogin());
    }
}
