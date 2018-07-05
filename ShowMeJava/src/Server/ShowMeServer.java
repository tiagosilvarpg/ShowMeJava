/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
import BD.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ShowMeServer {
    static int port=8000;
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/usuario", new User());
        server.createContext("/grupo", new Group());
        server.createContext("/evento", new Event());
        server.createContext("/stop", new Stop());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
    static class User implements HttpHandler
    {
        @Override
        public void handle(HttpExchange origem) throws IOException{
            String termo=origem.getRequestURI().toString();
            termo=termo.replaceFirst("/usuario", "");
            try{
                if (termo.startsWith("/novoUsuario/")){
                    termo=termo.replaceFirst("/novoUsuario/", "");
                    if (Validar.Email(termo))
                        new Insert(origem).CreateUser(termo);
                }
                else if (termo.startsWith("/resetarSenha/")){
                    termo=termo.replaceFirst("/resetarSenha/", "");
                        new Processador (origem).RandomPassword(termo);
                }
                else if (termo.startsWith("/alterarSenha/")){
                    termo=termo.replaceFirst("/alterarSenha/", "");
                        new Processador (origem).ChangePassword(termo.split("&")[0],termo.split("&")[1]);
                }
                 else if (termo.startsWith("/entrar/")){
                    termo=termo.replaceFirst("/entrar/", "");
                        new Processador (origem).Login(termo.split("&")[0],termo.split("&")[1]);
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    static class Group implements HttpHandler
    {
        @Override
        public void handle(HttpExchange origem) throws IOException{
            String termo=origem.getRequestURI().toString();            
            System.out.println(termo);
            termo=termo.replaceFirst("/grupo", "");
            try{
                /*if (termo.startsWith("/novoGrupo/")){
                    termo=termo.replaceFirst("/novoGrupo/", "");
                        new Processador (origem).CreateGrupo(termo);
                }*/
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    static class Event implements HttpHandler
    {
        @Override
        public void handle(HttpExchange origem) throws IOException{
            String termo=origem.getRequestURI().toString();            
            System.out.println(termo);
            termo=termo.replaceFirst("/evento", "");
            try{
                if (termo.startsWith("/novoEvento/")){
                    termo=termo.replaceFirst("/novoEvento/", "");
                    new Insert (origem).CreateEvent(termo);
                }
                if (termo.startsWith("/quantidadeEventos/")){
                    termo=termo.replaceFirst("/quantidadeEventos/", "");
                    new Select (origem).QuantidadeEventosMes(termo);
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
     static class ConfirmarEmail implements HttpHandler
    {
        @Override
        public void handle(HttpExchange t) throws IOException
        {
            String termo=t.getRequestURI().toString().replaceFirst("/confirmarEmail?/","");
            String email=termo.split("&")[0].replaceFirst("email=","");
            String codigo=termo.split("&")[1].replaceFirst("senha=","");
        }
    }
    static class Stop implements HttpHandler
    {
        @Override
        public void handle(HttpExchange t) throws IOException
        {
            String response = "O Servidor esta sendo encerrado...";
            t.sendResponseHeaders(200, response.length());
            t.getResponseBody().write(response.getBytes());
            t.getResponseBody().close();
            System.exit(0);
        }
    }	
}
