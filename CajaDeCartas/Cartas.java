package CajaDeCartas;

public class Cartas
{
    /*
    *la creacion de las cartas con formas de formalas con otros metodos que e encotrado.
     */
    private Sinvolos Sinvolos;
    public static String[] Sinvolo = {"♡", "♢", "♧", "♤"};
    public static String[] SinvoloDeLaCarta = {"A", "J", "Q", "K"};
    private Integer valor;
    private String EnsimaCarta;
    private String BajoCarta;
    private static String EnmedioCarta="|     |";

    private Cartas(){
    }

    @Override
    public String toString(){
        return EnsimaCarta+EnmedioCarta+BajoCarta;
    }
    public String getTopCardRepresentation(){
        return EnsimaCarta;
    }


    public Cartas(Sinvolos ponerSinvolos, Integer ponerValor){

        this(ponerSinvolos, ponerValor, "X");
    }
    public Cartas(Sinvolos ponerSinvolos, Integer ponerValor, String PonerRepresentacionSimvolos){

        this(ponerSinvolos, ponerValor, PonerRepresentacionSimvolos, "Y");
    }
    public Cartas(Sinvolos ponerSinvolos, Integer ponerValor, String PonerRepresentacionSimvolos, String PonerRepresentacionDeLaCara){
        Sinvolos = ponerSinvolos;
        valor=ponerValor;
        EnsimaCarta=" ----- "+
                              "|"+String.format("%2s%-3s", PonerRepresentacionDeLaCara, PonerRepresentacionSimvolos)+"|";
        BajoCarta="|"+String.format("%4s%s", PonerRepresentacionDeLaCara, PonerRepresentacionSimvolos)+"|"+
                                 " ----- ";
    }

    public Integer getValor(){
        return valor;
    }

    public Sinvolos getSinvolos(){
        return Sinvolos;
    }

}
