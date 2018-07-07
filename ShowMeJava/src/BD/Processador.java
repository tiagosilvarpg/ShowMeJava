/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Server.Validar;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.sun.net.httpserver.HttpExchange;
import java.sql.SQLException;

/**
 *
 * @author Tiago
 */
public class Processador {
    public static String url="jdbc:mysql://",server="localhost/",database="showMe",user="tiago",password="showmeapp",ssl="?autoReconnect=true&useSSL=false";
    public Connection conexao;
    public String resposta,query;
    public PreparedStatement statement;
    public HttpExchange origem;
    public Processador(HttpExchange origem){
        this.origem=origem;
    }
    public boolean Conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexao=DriverManager.getConnection(url+server+database+ssl, user, password);
            return true;
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean Desconectar(){
        
        try{
            conexao.close();
            Responder(resposta);
            return true;
        }
        catch (SQLException e) { 
            return false; 
        }
    }
    public void Responder(String response){
        try (OutputStream os = origem.getResponseBody()) {
            byte[] bs = response.getBytes("UNICODE");
            origem.sendResponseHeaders(200, bs.length);
            os.write(bs);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public String EncontrarParametro(String parametro,String listaParametros){
        for (String part : listaParametros.split("&"))
        {
            if (part.split("=")[0].equals(parametro))
                return part.split("=")[1];
        }
        return "%";//IMPORTANTE
    }
    public void ChangePassword(String email,String password){
        if (Conectar()){
            try{
                String query = String.format("update usuario set senha = '%s' where email = '%s'",password,email);
                conexao.createStatement().executeUpdate(query);
                Responder("Sucesso, sua senha foi modificada");
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                Responder("falha");
            }
        }
        Desconectar();
    }    
}
