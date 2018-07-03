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
public class Update extends Processador{
    
    public Update(HttpExchange origem) {
        super(origem);
    }
    public void UpdateGroup(String termo){
        if (Conectar()){
            String grupo=EncontrarParametro("grupo", termo);
            String[] campo={"telefone","email","site","poster","descricao"};
            for (String campo1 : campo) {
                String valor = EncontrarParametro(campo1, termo);
                if (valor!=null) {
                    try {
                        query = String.format("update into grupo (%s) values ('%s') where nomeGrupo='%s'", campo1, valor, grupo);                
                        conexao.createStatement().executeUpdate(query);
                        resposta="Sucesso, todos os campo alterados";
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                        resposta="Falha, alguns campos nao puderam ser alterados";
                    }
                }
            }
            Desconectar();
        }
    }    
}
