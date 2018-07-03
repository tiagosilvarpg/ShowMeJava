/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import ShowMeServerhttp.EnviarEmail;
import com.sun.net.httpserver.HttpExchange;
import java.sql.SQLException;

/**
 *
 * @author Tiago
 */
public class Insert extends Processador{
    
    public void CreateUserOLD(String email){
        if (Conectar()){        
            try {
                String senha = Validar.SenhaAleatoria(8);   
                query = String.format("insert into usuario (email,senha) values ('%s','%s')",email,senha);
                conexao.createStatement().executeUpdate(query);
                EnviarEmail.NovoEmail(email,"Bem vindo ao ShowMe","sua senha é:\n"+senha+"\n\nVoce pode alterar a senha apos realizar o login.");
                Responder("sucesso, senha enviada para seu email");
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                Responder("falha");
            }
        }
        Desconectar();
    }
    public void CreateUser(String termo){
        if (Conectar()){        
            try {
                String email=EncontrarParametro("email", termo);
                String facebook=EncontrarParametro("facebook", termo);
                query = String.format("insert into usuario (email,facebook) values ('%s','%s')",email,facebook);
                conexao.createStatement().executeUpdate(query);
                Responder("sucesso, conta vinculada ao facebook");
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                Responder("falha");
            }
        }
        Desconectar();
    }
    public void CreateEvent(String termo){
        if (Conectar()){        
            try {
                String local=EncontrarParametro("local", termo);
                String hora=EncontrarParametro("hora", termo);
                String dia=EncontrarParametro("dia", termo);
                String grupo=EncontrarParametro("grupo", termo);
                query = String.format("insert into evento (local,hora,dia,grupo) values ('%s','%s','%s','%s')",local,hora,dia,grupo);
                conexao.createStatement().executeUpdate(query);
                Responder("sucesso, conta vinculada ao facebook");
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                Responder("Falha");
            }
        }
        Desconectar();
    }
    public void ParticiparGrupo(String termo){
        if (Conectar()){
            try{
                String grupo=EncontrarParametro("grupo", termo);
                String usuario=EncontrarParametro("usuario", termo);
                query="insert into PARTICIPA (codigo_grupo,codigo_usuario) values ?,?";
                statement.setString(1, grupo);
                statement.setString(2, usuario);
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                resposta="Falha, alguns campos nao puderam ser removidos";
            }     
        }
    }
    public void SeguirGrupo(String termo){
        if (Conectar()){
            try{
                String grupo=EncontrarParametro("grupo", termo);
                String usuario=EncontrarParametro("usuario", termo);
                query="insert into SEGUE (codigo_grupo,codigo_usuario) values ?,?";
                statement.setString(1, grupo);
                statement.setString(2, usuario);
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                resposta="Falha, alguns campos nao puderam ser removidos";
            }     
        }
    }
    public void CreateGroup(String termo){
        if (Conectar()){        
            try {
                String nomeUnico=EncontrarParametro("nomeUnico", termo);
                String nomeBusca=EncontrarParametro("nomeBusca", termo);
                String codigoUsuario=EncontrarParametro("codigoUsuario", termo);
                query = String.format("insert into grupo (nome_exibicao,nome_unico,codigo_usuario) values ('%s','%s','%s')",nomeBusca,nomeUnico,codigoUsuario);                
                conexao.createStatement().executeUpdate(query);
                Responder("sucesso, grupo criado");
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                Responder("falha");
            }
        }
        Desconectar();
    }
    public Insert(HttpExchange origem) {
        super(origem);
    }
}
