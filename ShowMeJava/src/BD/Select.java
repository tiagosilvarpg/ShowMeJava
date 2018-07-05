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
                query = "select codigo_evento from evento where dia=? and mes=? and ano=?";
                statement=conexao.prepareStatement(query);
                statement.setString(1, dia);
                statement.setString(2, mes);
                statement.setString(3, ano);
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
                for (int dia=0;dia<31;dia++){
                    query = "select count(codigo_evento) from evento e,sigo s,grupo g where e.dia=? and e.mes=? and e.ano=?";
                    statement=conexao.prepareStatement(query);
                    statement.setString(1, dia+"");
                    statement.setString(2, mes);
                    statement.setString(3, ano);
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
                String ano,mes,usuario;
                ano=EncontrarParametro("ano",termo);
                mes=EncontrarParametro("mes",termo);
                usuario=EncontrarParametro("usuario", termo);
                String quantidade="";
                for (int dia=0;dia<31;dia++){
                    query = "select count(codigo_evento) from evento e,sigo s,grupo g where e.dia=? and e.mes=? and e.ano=?"
                            + " and e.codigo_grupo=g.codigo_grupo and g.codigo_grupo=s.codigo_grupo and s.codigo_usuario=?";
                    statement=conexao.prepareStatement(query);
                    statement.setString(1, dia+"");
                    statement.setString(2, mes);
                    statement.setString(3, ano);
                    statement.setString(4, usuario);
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
