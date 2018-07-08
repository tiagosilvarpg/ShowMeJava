/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Server.Validar;
import com.sun.net.httpserver.HttpExchange;
import java.sql.SQLException;

/**
 *
 * @author Tiago
 */
public class Insert extends Processador{
    
    public void CreateUser(String termo){
        if (Conectar()){        
            try {
                String email=EncontrarParametro("email", termo);
                String facebook=EncontrarParametro("facebook", termo);
                query = "insert into usuario (email,facebook) values (?,?)";
                statement=conexao.prepareStatement(query);
                statement.setString(1, email);
                statement.setString(2, facebook);
                statement.executeUpdate();
                Responder("sucesso, conta vinculada ao facebook");
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                Responder("falha");
            }
        }
        Desconectar();
    }
    public void Entrar(String termo){
        if (Conectar()){
            try{
                String usuario=EncontrarParametro("usuario", termo);
                String data=Validar.Now();
                query="insert into login (codigo_usuario,time) values (?,?)";
                statement=conexao.prepareStatement(query);
                statement.setString(1, usuario);
                statement.setString(2, data);
                statement.executeUpdate();
                resposta="sucesso, login efetuado";
            }
            catch (SQLException e ){
                System.out.println(e.getMessage());
                resposta="falha";
            }
        }
        Responder(resposta);
    }
    public void CreateEvent(String termo){
        if (Conectar()){        
            try {
                String local=EncontrarParametro("local", termo);
                String data=EncontrarParametro("data", termo);
                String cidade=EncontrarParametro("cidade", termo);
                String estado=EncontrarParametro("estado", termo);
                String descricao=EncontrarParametro("descricao", termo);
                String grupo=EncontrarParametro("grupo", termo);
                query = "insert into evento (local,descricao,codigo_grupo,data,cidade,estado) values (?,?,?,?,?,?)";
                statement=conexao.prepareStatement(query);
                statement.setString(1, local);
                statement.setString(2, descricao);
                statement.setString(3, grupo);
                statement.setString(4, data);
                statement.setString(5, cidade);
                statement.setString(6, estado);
                statement.executeUpdate();
                Responder("sucesso, evento criado");
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
                String convidou=EncontrarParametro("usuario", termo);
                query="insert into PARTICIPA (codigo_grupo,codigo_usuario,codigo_convidou) select codigo_grupo,?,? from participa where codigo_grupo=? and codigo_usuario=? ";
                statement.setString(1, usuario);
                statement.setString(2, convidou);
                statement.setString(3, grupo);
                statement.setString(4, convidou);
                statement.executeUpdate();
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
                query="insert into SEGUE (codigo_grupo,codigo_usuario) values (?,?)";
                statement.setString(1, grupo);
                statement.setString(2, usuario);
                statement.executeUpdate();
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
                query ="insert into grupo (nome_exibicao,nome_unico,codigo_usuario) values (?,?,?)";
                statement=conexao.prepareStatement(query);
                statement.setString(1, nomeBusca);
                statement.setString(2, nomeUnico);
                statement.setString(3, codigoUsuario);
                statement.executeUpdate();
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
