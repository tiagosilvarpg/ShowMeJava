/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import com.sun.net.httpserver.HttpExchange;
import java.sql.SQLException;

/**
 *
 * @author Tiago
 */
public class Delete extends Processador{
    
    public void DeixarDeSeguir(String termo){
        if (Conectar()){
            String grupo=EncontrarParametro("grupo", termo);
            String usuario=EncontrarParametro("usuario", termo);            
            query="delete from SEGUIR where codigo_grupo=? and codigo_usuario= ?";
            try{
                statement=conexao.prepareStatement(query);
                statement.setString(1, grupo);
                statement.setString(2, usuario);
                statement.executeUpdate();
            }catch (SQLException e){
                System.out.println(e.getMessage());
                resposta="Falha, alguns campos nao puderam ser removidos";
            }
        }
        Desconectar();
    }
    public void DeixarDeParticipar(String termo){
        if (Conectar()){
            String grupo=EncontrarParametro("grupo", termo);
            String usuario=EncontrarParametro("usuario", termo);            
            query="delete from PARTICIPA where codigo_grupo= ? and codigo_usuario= ?";
            try{
                statement=conexao.prepareStatement(query);
                statement.setString(1, grupo);
                statement.setString(2, usuario);
                statement.executeUpdate();
            }catch (SQLException e){
                System.out.println(e.getMessage());
                resposta="Falha, alguns campos nao puderam ser removidos";
            }            
        }
        Desconectar();
    }
    public Delete(HttpExchange origem) {
        super(origem);
    }
}
