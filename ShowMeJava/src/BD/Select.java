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
    public void GruposParticipo(String codigoUsuario){
        if (Conectar()){
            try{
                query = String.format("select codigo_grupo from participa where codigo_usuario = '%s'",codigoUsuario);
                result=conexao.createStatement().executeQuery(query);
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
    public void GruposPorNome(String termo){
        if (Conectar()){
            try{
                String nome=EncontrarParametro("nome", termo);
                query = "select codigo_grupo,nome_exibicao from segue where nome_exibicao like ?";
                statement=conexao.prepareStatement(query);
                statement.setString(1, nome);
                result=statement.executeQuery(query);
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
    public void Evento(String termo){
        if (Conectar()){
            try{
                String codigo;
                codigo=EncontrarParametro("codigo",termo);
                query = "select e.data,e.local,g.nome_unico from evento e,grupo g WHERE codigo_evento=? and e.codigo_grupo=g.codigo_grupo";
                statement=conexao.prepareStatement(query);
                statement.setString(1, codigo);
                result=statement.executeQuery();
                String saida="";
                result.first();
                for (String s : new String[]{"codigo_grupo",""}){
                    saida+=s+"="+result.getString(s)+"\n";
                }
                Responder(saida);
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
                String codigo;
                codigo=EncontrarParametro("codigo",termo);
                query = "select e.data,e.local,g.nome_unico from evento e,grupo g WHERE codigo_evento=? and e.codigo_grupo=g.codigo_grupo";
                statement=conexao.prepareStatement(query);
                statement.setString(1, codigo);
                result=statement.executeQuery();
                String saida="";
                result.first();
                for (String s : new String[]{"codigo_grupo",""}){
                    saida+=s+"="+result.getString(s)+"\n";
                }
                Responder(saida);
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
                String mes,ano,cidade,estado,tag;
                mes=EncontrarParametro("mes",termo);
                ano=EncontrarParametro("ano",termo);
                tag=EncontrarParametro("tag", termo);
                cidade=EncontrarParametro("cidade", termo);
                estado=EncontrarParametro("estado", termo);
                String quantidade="";
                query = "select count(codigo_evento) from evento where month (data)=? and year (data)=? and cidade=? and estado=? and tag like ?";
                statement=conexao.prepareStatement(query);
                statement.setString(1, mes);
                statement.setString(1, ano);
                statement.setString(2, cidade);
                statement.setString(3, estado);
                statement.setString(4, tag);
                result=statement.executeQuery(query);
                result.first();
                do {quantidade+=result.getString(1)+"&";}
                while (result.next());
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
    ResultSet result;
    public Select(HttpExchange origem) {
        super(origem);
    }
}
