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
    public void GruposSigo(String termo){
        if (Conectar()){
            try{
                String usuario=EncontrarParametro("usuario", termo);
                query ="select g.nome_exibicao,g.codigo_grupo from sigo s,grupo g where s.codigo_usuario =? and s.codigo_grupo = s.codigo_grupo";
                statement=conexao.prepareStatement(query);
                statement.setString(1, usuario);
                result=statement.executeQuery();
                String list="";
                result.first();
                do{
                    list+="nome="+result.getString(1)+"&codigo="+result.getString(2)+"\n";
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
    public void GruposParticipo(String termo){
        if (Conectar()){
            try{
                String usuario=EncontrarParametro("usuario", termo);
                query ="select g.nome_exibicao,g.codigo_grupo from participa p,grupo g where p.codigo_usuario =? and p.codigo_grupo = g.codigo_grupo";
                statement=conexao.prepareStatement(query);
                statement.setString(1, usuario);
                result=statement.executeQuery();
                String list="";
                result.first();
                do{
                    list+="nome="+result.getString(1)+"&codigo="+result.getString(2)+"\n";
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
    public void Usuario(String termo){
        if (Conectar()){
            try{
                String facebook=EncontrarParametro("facebok", termo);
                query ="select codigo_usuario from usuario where facebook=?";
                statement=conexao.prepareStatement(query);
                statement.setString(1, facebook);
                result=statement.executeQuery();
                result.first();
                String codigo=result.getString(1);
                Responder(codigo);
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
                query = "select codigo_grupo,nome_exibicao from grupo where nome_exibicao like ?";
                statement=conexao.prepareStatement(query);
                statement.setString(1, "%"+nome+"%");
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
                String mes,ano,cidade,estado;
                mes=EncontrarParametro("mes",termo);
                ano=EncontrarParametro("ano",termo);
                cidade=EncontrarParametro("cidade", termo);
                estado=EncontrarParametro("estado", termo);
                String quantidade="";
                query = "select day(data),count(codigo_evento) from evento where month (data)=? and year (data)=? and cidade like ? and estado like ? group by data ";
                statement=conexao.prepareStatement(query);
                statement.setString(1, mes);
                statement.setString(2, ano);
                statement.setString(3, "%"+cidade+"%");
                statement.setString(4, "%"+estado+"%");
                System.out.println(statement);
                result=statement.executeQuery();
                result.first();
                do {
                    quantidade+=result.getString(1)+"="+result.getString(2)+"\n";
                }
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
                String mes,ano,cidade,estado,usuario;
                mes=EncontrarParametro("mes",termo);
                ano=EncontrarParametro("ano",termo);
                cidade=EncontrarParametro("cidade", termo);
                estado=EncontrarParametro("estado", termo);
                usuario=EncontrarParametro("usuario", termo);
                String quantidade="";
                query = "select day(data),count(codigo_evento) from evento e,segue s where month (data)=? and year (data)=? and cidade like ? and estado like ?"
                        + " and e.codigo_grupo=s.codigo_grupo and s.codigo_usuario=? group by data ";
                statement=conexao.prepareStatement(query);
                statement.setString(1, mes);
                statement.setString(2, ano);
                statement.setString(3, "%"+cidade+"%");
                statement.setString(4, "%"+estado+"%");
                statement.setString(5, usuario);
                System.out.println(statement);
                result=statement.executeQuery();
                result.first();
                do {
                    quantidade+=result.getString(1)+"="+result.getString(2)+"\n";
                }
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
    ResultSet result;
    public Select(HttpExchange origem) {
        super(origem);
    }
}
