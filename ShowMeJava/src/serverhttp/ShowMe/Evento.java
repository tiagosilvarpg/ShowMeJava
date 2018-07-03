package serverhttp.ShowMe;

public class Evento {
    public String local,cidade,estado,codigoArtista,descricao;
    public int precoCentavos,data,horario;
    public Evento(int data,int horario,int precoCentavos,String local,String cidade,String estado,String codigoArtista,String descricao){
        this.precoCentavos=precoCentavos;
        this.local=local;
        this.cidade=cidade;
        this.estado=estado;
        this.data=data;
        this.codigoArtista=codigoArtista;
        this.descricao=descricao;
        this.horario=horario;
    }
    public String Preco(){
        int real,centavo;
        real=precoCentavos/100;
        centavo=precoCentavos%100;
        return "R$"+real+","+centavo;
    }
    @Override
    public String toString(){
        return codigoArtista+" local:"+local+" data:"+data;
    }
}
