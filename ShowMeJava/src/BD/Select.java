/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import com.sun.net.httpserver.HttpExchange;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Tiago
 */
public class Select extends Processador{
    
    public Select(HttpExchange origem) {
        super(origem);
    }
    public void GruposParticipo(String codigoUsuario){
        if (Conectar()){
            try{
                query = String.format("select codigo_grupo from participa where codigo_usuario = '%s'",codigoUsuario);
                ResultSet result=conexao.createStatement().executeQuery(query);
                String list="";
                do{
                    list+=result.getString("codigo_grupo")+"\n";
                }
                while (result.next());
                Responder(list);
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                Responder("falha");
            }
        }
        Desconectar();
    }
    public void GruposSigo(String codigoUsuario){
        if (Conectar()){
            try{
                query = String.format("select codigo_grupo from segue where codigo_usuario = '%s'",codigoUsuario);
                ResultSet result=conexao.createStatement().executeQuery(query);
                String list="";
                do{
                    list+=result.getString("codigo_grupo")+"\n";
                }
                while (result.next());
                Responder(list);
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                Responder("falha");
            }
        }
        Desconectar();
    }
    public void GruposPorNome(String nome){
        if (Conectar()){
            try{
                query = "select codigo_grupo from segue where nome_exibicao like '%"+nome+"%'";
                ResultSet result=conexao.createStatement().executeQuery(query);
                String list="";
                do{
                    list+=result.getString("codigo_grupo")+"\n";
                }
                while (result.next());
                Responder(list);
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                Responder("falha");
            }
        }
        Desconectar();
    }
    public void EventosDia(String termo){
        if (Conectar()){
            try{
                String ano,mes,dia;
                ano=EncontrarParametro("ano",termo);
                mes=EncontrarParametro("mes",termo);
                dia=EncontrarParametro("dia",termo);
                query = "select codigo_evento from evento where data like '"+ano+mes+dia+"'";
                ResultSet result=conexao.createStatement().executeQuery(query);
                String list="";
                do{
                    list+=result.getString("codigo_evento")+"\n";
                }
                while (result.next());
                Responder(list);
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                Responder("falha");
            }
        }
        Desconectar();
    }
    public void QuantidadeEventosMes(String termo){
        if (Conectar()){
            try{
                String ano,mes;
                ano=EncontrarParametro("ano",termo);
                mes=EncontrarParametro("mes",termo);
                String quantidade="";
                for (int i=0;i<31;i++){
                    query = "select count(codigo_evento) from evento e,sigo s,grupo g where g.codigo_grupo=s.codigo_grupo and data like '"+ano+mes+"__,";
                    ResultSet result=conexao.createStatement().executeQuery(query);
                    result.first();
                    quantidade+=result.getString(1)+"&";
                }
                Responder(quantidade);
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                Responder("falha");
            }
        }
        Desconectar();
    }
    public void QuantidadeEventosMesSigo(String termo){
        if (Conectar()){
            try{
                String ano,mes;
                ano=EncontrarParametro("ano",termo);
                mes=EncontrarParametro("mes",termo);
                String quantidade="";
                for (int i=0;i<31;i++){
                    query = "select count(codigo_evento) from evento e,sigo s,grupo g where data like '"+ano+mes+"__,";
                    ResultSet result=conexao.createStatement().executeQuery(query);
                    result.first();
                    quantidade+=result.getString(1)+"&";
                }
                Responder(quantidade);
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                Responder("falha");
            }
        }
        Desconectar();
    }
}
