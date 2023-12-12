package CajaDeCartas;

import java.util.Scanner;

public class consola {

    private static final double MIN_BET_ALLOWED=0.01;

    private static Juego juego = new Juego();
    private static Scanner scanner = new Scanner (System.in);

    public static void run(){

        Jugador userJugador = juego.getPlayer();

        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("|                                          |");
        System.out.println("|         🔥💎CASINO LA RUEDA💎🔥          |");
        System.out.println("|                                          |");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

        System.out.println(" bien benido jugador cual es tu nombre ");

        String userName = getInput();
        userJugador.setName(userName);

        System.out.println(" Hola " + userName + "!");

        System.out.println(" tienes "+forceTwoDecimalDouble(userJugador.getMoney().toString()));

        do {
            juego.start();
            playerBets(userJugador);
            displayPlayerHandAndScore(userJugador);
            displayDealerCardShowing();
            playerHitsOrStays(userJugador);
            juego.dealerHitUntilFinished();
            determineWinOrLoss(userJugador);
        } while (playerStaysForAnotherRound(userJugador));

        System.out.println("Gracias por jugar👨‍🦽! Adios!");
    }

    private static boolean playerStaysForAnotherRound(Jugador userJugador) {
        String input;
        System.out.println(" tu tienes " + forceTwoDecimalDouble(userJugador.getMoney().toString()));
        if (userJugador.getMoney() >= 0.01) {
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

    private static void determineWinOrLoss(Jugador userJugador){

        if (juego.playerWins()) {
            System.out.println(" tu ganas !");
        } else {
            System.out.println(" la maquina gana !");
        }
        System.out.println(" la maquina tiene: " + juego.getDealer().getScore());
        resetPotAndDiscardHands(userJugador);
    }

    private static void resetPotAndDiscardHands(Jugador userJugador){
        juego.returnBet();
        userJugador.getHand().clear();
        juego.getDealer().getHand().clear();
    }

    private static void playerHitsOrStays(Jugador userJugador){
        String input;
        do {
            input=forceHitOrStay();
            if ("pide".equalsIgnoreCase(input)) {
                juego.dealCard(userJugador);
            }
            displayPlayerHandAndScore(userJugador);
        } while ("pide".equalsIgnoreCase(input) && (userJugador.calculateScore() <= 21));
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
        System.out.println(" la maquina tiene: "+ juego.getDealer().getHand().get(0).toString());
    }

    private static void displayPlayerHandAndScore(Jugador userJugador){
        Cartas cartas;
        for (int i = 0; i< userJugador.getHand().size(); i++)
        {
            cartas = userJugador.getHand().get(i);
            if (i!= userJugador.getHand().size()-1)
                System.out.print(cartas.getTopCardRepresentation());
            else
                System.out.println(cartas.toString());
        }
        System.out.println(" ti tienes: " + userJugador.calculateScore());
    }

    private static void playerBets(Jugador userJugador){
        Double betAmount;
        String input;
        input=forcePlayerBet(userJugador);
        betAmount=Double.valueOf(input);
        userJugador.makeBet(betAmount);
        juego.addToPot(betAmount);
    }

    private static String forcePlayerBet(Jugador userJugador){
        String input;
        do {
            input=forceDoubleInput();
        }
        while (!userJugador.hasMoneyToMakeBet(Double.valueOf(input)));
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
        return Double.valueOf(passedString)>=MIN_BET_ALLOWED;
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
