package CajaDeCartas;

import java.util.Scanner;

public class consola {

    private static final double Apuesta_Minima=0.20;

    private static Juego juego = new Juego();
    private static Scanner scanner = new Scanner (System.in);

    public static void run(){

        Jugador usuarioJugador = juego.getPlayer();

        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("|                                          |");
        System.out.println("|         🔥💎CASINO LA RUEDA💎🔥          |");
        System.out.println("|                                          |");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

        System.out.println(" vien venido jugador cual es tu nombre ");

        String userName = getInput();
        usuarioJugador.setNombre(userName);

        System.out.println(" Hola " + userName + "!");

        System.out.println(" tienes "+forceTwoDecimalDouble(usuarioJugador.getDinero().toString()));

        do {
            juego.start();
            playerBets(usuarioJugador);
            displayPlayerHandAndScore(usuarioJugador);
            displayDealerCardShowing();
            playerHitsOrStays(usuarioJugador);
            juego.dealerHitUntilFinished();
            DeterminaGana0Pierde(usuarioJugador);
        } while (playerStaysForAnotherRound(usuarioJugador));

        System.out.println("Gracias por jugar👨‍🦽! Adios!");
    }

    private static boolean playerStaysForAnotherRound(Jugador usuarioJugador) {
        String input;
        System.out.println(" tu tienes " + forceTwoDecimalDouble(usuarioJugador.getDinero().toString()));
        if (usuarioJugador.getDinero() >= 0.20) {
            do {
                System.out.print("quieres probar de nuevo? [si/No]  ");
                input = getInput();
                if (isInputYesOrNo(input))
                {
                    return ("si".equalsIgnoreCase(input));
                }
            } while (!isInputYesOrNo(input));

        }
        else {
            System.out.println(" no tienes más dinero.");
        }
        return false;
    }

    private static void DeterminaGana0Pierde(Jugador usuarioJugador){

        if (juego.playerWins()) {
            System.out.println(" tu ganas !");
        } else {
            System.out.println(" la maquina gana !");
        }
        System.out.println(" la maquina tiene: " + juego.getDealer().getpuntos());
        resetPotAndDiscardHands(usuarioJugador);
    }

    private static void resetPotAndDiscardHands(Jugador usuarioJugador){
        juego.returnBet();
        usuarioJugador.getmanos().clear();
        juego.getDealer().getmanos().clear();
    }

    private static void playerHitsOrStays(Jugador usuarioJugador){
        String input;
        do {
            input=forceHitOrStay();
            if ("pide".equalsIgnoreCase(input)) {
                juego.dealCard(usuarioJugador);
            }
            displayPlayerHandAndScore(usuarioJugador);
        } while ("pide".equalsIgnoreCase(input) && (usuarioJugador.calculatePuntos() <= 21));
    }

    private static String forceHitOrStay(){
        String input;
        do {
            System.out.print(" pides o pasas? ");
            input = getInput();
        } while (!isInputStayOrHit(input));
        return input;
    }

    private static void displayDealerCardShowing(){
        System.out.println(" la maquina tiene: "+ juego.getDealer().getmanos().get(0).toString());
    }

    private static void displayPlayerHandAndScore(Jugador usuarioJugador){
        Cartas cartas;
        for (int i = 0; i< usuarioJugador.getmanos().size(); i++)
        {
            cartas = usuarioJugador.getmanos().get(i);
            if (i!= usuarioJugador.getmanos().size()-1)
                System.out.print(cartas.getTopCardRepresentation());
            else
                System.out.println(cartas.toString());
        }
        System.out.println(" ti tienes: " + usuarioJugador.calculatePuntos());
    }

    private static void playerBets(Jugador usuarioJugador){
        Double betAmount;
        String input;
        input=forcePlayerBet(usuarioJugador);
        betAmount=Double.valueOf(input);
        usuarioJugador.makeBet(betAmount);
        juego.addToPot(betAmount);
    }

    private static String forcePlayerBet(Jugador usuarioJugador){
        String input;
        do {
            input=forceDoubleInput();
        }
        while (!usuarioJugador.hasMoneyToMakeBet(Double.valueOf(input)));
        return (input);
    }

    private static String forceDoubleInput() {
        String input;
        do {
            System.out.print("cuanto quieres apostar?  ");
            input = getInput();
        } while (!isInputDouble(input) || !isInputPositive(input));
        return forceTwoDecimalDouble(input);
    }

    private static String getInput()
    {
        return scanner.nextLine();
    }

    public static boolean isInputPositive(String passedString){
        return Double.valueOf(passedString)>=Apuesta_Minima;
    }

    public static boolean isInputDouble(String passedString)
    {
        try {
            Double output = Double.valueOf(passedString);
        } catch (NumberFormatException e) {
            return false;
        }
        return (true);
    }

    public static boolean isInputStayOrHit(String passedString)
    {
        return ("pide".equalsIgnoreCase(passedString) ||
                "paso".equalsIgnoreCase(passedString));
    }

    public static boolean isInputYesOrNo(String passedString)
    {
        return ("si".equalsIgnoreCase(passedString) ||
                "no".equalsIgnoreCase(passedString));
    }

    public static String forceTwoDecimalDouble(String input){
        if ( !(input.contains(".")) ||
               input.substring(input.indexOf("."), input.length()).length()==3)
        {
            return input;
        }
        else
        {
            if (input.substring(input.indexOf("."), input.length()).length()<3) {
                do {
                    input = input + "0";
                } while (input.substring(input.indexOf("."), input.length()).length() < 3);
                return input;
            }

            input = input.substring(0,input.indexOf("."))+
                    input.substring(input.indexOf("."), input.indexOf(".")+3);
            return input;
        }


    }
}
