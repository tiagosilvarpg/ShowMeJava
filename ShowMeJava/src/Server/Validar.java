/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
    public static boolean ChecarSenha(String pass, String senha) {
        return senha.equals(pass);
    }
    public static String Now(){
        String date=ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE);
        String time=ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
        return date+" "+time;
    }
    public static String Today(){
        String date=ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE);
        return date;
    }
}
