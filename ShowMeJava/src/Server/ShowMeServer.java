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
        server.createContext("/usuario/", new User());
        server.createContext("/stop", new Stop());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
    static class User implements HttpHandler
    {
        @Override
        public void handle(HttpExchange origem) throws IOException{
            String termo=origem.getRequestURI().toString();
            termo=termo.replaceFirst("/usuario/", "");
            String[] cases=new String[]{
            "novoUsuario/",
            "entrar/",
            "seguirGrupo/",
            "deixarGrupo/",
            "criarGrupo/",
            "adicionarMembro/",
            "editarPerfil/",
            "novoEvento/",
            "listarEventosdia/",
            "listarEventosMes/",
            "listarEventosMesSigo/"
            };            
            for (int i=0;i<cases.length;i++){
                System.out.println(cases[i]);
                if (termo.startsWith(cases[i])){
                    termo=termo.replaceFirst(cases[i],"");
                    System.out.println(termo);
                    switch (i){
                        case 0: new Insert(origem).CreateEvent(termo);break;
                        case 1: new Insert(origem).Entrar(termo);break;
                        case 2: new Insert(origem).SeguirGrupo(termo);break;
                        case 3: new Delete(origem).DeixarDeSeguir(termo);break;
                        case 4: new Insert(origem).CreateGroup(termo);break;
                        case 5: new Insert(origem).ParticiparGrupo(termo);break;
                        case 6: new Update(origem).UpdateGroup(termo);break;  
                        case 7: new Insert(origem).CreateEvent(termo);break;
                        case 8: new Select(origem).EventosDia(termo);break;
                        case 9: new Select(origem).QuantidadeEventosMes(termo);break;
                        case 10: new Select(origem).QuantidadeEventosMesSigo(termo);break;
                    }
                    break;
                }
            }
            String rs="falha, comando nao reconhecido";
            byte []bs=rs.getBytes("UNICODE");
            origem.sendResponseHeaders(200, bs.length);
            origem.getResponseBody().write(bs);
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
