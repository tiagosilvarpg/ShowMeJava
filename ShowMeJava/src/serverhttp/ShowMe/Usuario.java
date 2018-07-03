/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverhttp.ShowMe;

/**
 *
 * @author Tiago
 */
public class Usuario {
    public String email,senha,codigo;
    public boolean ativo;
    public Usuario(String email,String senha){
        this.email=email;
        this.senha=senha;
        this.ativo=true;
    }
}
