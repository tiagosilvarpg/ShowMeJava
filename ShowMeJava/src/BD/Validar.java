/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tiago
 */
public abstract class Validar {
    public static boolean Email(String entrada){
        if (entrada != null && entrada.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(entrada);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }    
    public static boolean Grupo(String entrada){
        return false;
    }
    public static boolean Imagem(String entrada){
        return false;
    }
    public static String SenhaAleatoria(int size){
        String saida="";
        String ValidChar="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random=new Random();
        while(saida.length()<size){
            int val=random.nextInt(ValidChar.length());
            saida=saida+ValidChar.substring(val,val+1);
        }
        return saida;
    }
    static boolean ChecarSenha(String pass, String senha) {
        return senha.equals(pass);
    }
    static String AAAAMMDD(){
        String year=ZonedDateTime.now().getYear()+"";
        String month=ZonedDateTime.now().getMonthValue()+"";
        String day=ZonedDateTime.now().getDayOfMonth()+"";
        if (month.length()==1)month="0"+month;
        if (day.length()==1)day="0"+day;
        return year+month+day;
    }
    static String HHMM(){        
        String hora=ZonedDateTime.now().getHour()+"";
        String minuto=ZonedDateTime.now().getMinute()+"";        
        if (hora.length()==1)hora="0"+hora;
        if (minuto.length()==1)minuto="0"+minuto;
        return hora+minuto;
    }
}
