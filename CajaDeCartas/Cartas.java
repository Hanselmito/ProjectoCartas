package CajaDeCartas;

public class Cartas
{


    /**
     *crear las cartas con sus simbolos e letras si son mayor de 9
     * el valor de cada carta
     * EnsimaCarta, BajoCarta, EnmedioCarta es la posision de la carta
     */
    private Sinvolos Sinvolos;
    public static String[] Sinvolo = {"♡", "♢", "♧", "♤"};
    public static String[] LetraDeLaCarta = {"A", "J", "Q", "K"};
    private Integer valor;
    private String EnsimaCarta;
    private String BajoCarta;
    private static String EnmedioCarta="|     |\n";

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
        EnsimaCarta=" ----- \n"+
                              "|"+String.format("%2s%-3s", PonerRepresentacionDeLaCara, PonerRepresentacionSimvolos)+"|\n";
        BajoCarta="|"+String.format("%4s%s", PonerRepresentacionDeLaCara, PonerRepresentacionSimvolos)+"|\n"+
                                 " ----- \n";
    }

    public Integer getValor(){
        return valor;
    }

    public Sinvolos getSinvolos(){
        return Sinvolos;
    }

}
