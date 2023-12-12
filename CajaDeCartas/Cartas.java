package CajaDeCartas;

public class Cartas
{
    /*
    *la creacion de las cartas con formas de formalas con otros metodos que e encotrado.
     */
    private regla regla;
    public static String[] suitSymbols = {"♡", "♢", "♧", "♤"};
    public static String[] faceSymbols = {"A", "J", "Q", "K"};
    private Integer value;
    private String topCardRepresentation;
    private String bottomCardRepresentation;
    private static String middleCardRepresentation="|     |";

    private Cartas(){
    }

    @Override
    public String toString(){
        return topCardRepresentation+middleCardRepresentation+bottomCardRepresentation;
    }
    public String getTopCardRepresentation(){
        return topCardRepresentation;
    }


    public Cartas(regla passedRegla, Integer passedValue){

        this(passedRegla, passedValue, "X");
    }
    public Cartas(regla passedRegla, Integer passedValue, String passedSuitRepresentation){

        this(passedRegla, passedValue, passedSuitRepresentation, "Y");
    }
    public Cartas(regla passedRegla, Integer passedValue, String passedSuitRepresentation, String passedFaceRepresentation){
        regla = passedRegla;
        value=passedValue;
        topCardRepresentation=" ----- "+
                              "|"+String.format("%2s%-3s", passedFaceRepresentation, passedSuitRepresentation)+"|";
        bottomCardRepresentation="|"+String.format("%4s%s", passedFaceRepresentation, passedSuitRepresentation)+"|"+
                                 " ----- ";
    }

    public Integer getValue(){
        return value;
    }

    public regla getSuit(){
        return regla;
    }

}
