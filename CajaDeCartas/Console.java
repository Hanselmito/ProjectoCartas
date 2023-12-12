package CajaDeCartas;

import java.util.Scanner;

public class Console {

    private static final double MIN_BET_ALLOWED=0.01;

    private static Game game = new Game();
    private static Scanner scanner = new Scanner (System.in);

    public static void run(){

        Player userPlayer = game.getPlayer();

        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("|                                          |");
        System.out.println("|         🔥💎CASINO LA RUEDA💎🔥          |");
        System.out.println("|                                          |");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

        String userName = getInput();
        userPlayer.setName(userName);

        System.out.println(" Hola " + userName + "!");

        System.out.println(" tienes "+forceTwoDecimalDouble(userPlayer.getMoney().toString()));

        do {
            game.start();
            playerBets(userPlayer);
            displayPlayerHandAndScore(userPlayer);
            displayDealerCardShowing();
            playerHitsOrStays(userPlayer);
            game.dealerHitUntilFinished();
            determineWinOrLoss(userPlayer);
        } while (playerStaysForAnotherRound(userPlayer));

        System.out.println("Gracias por jugar👨‍🦽! Adios!");
    }

    private static boolean playerStaysForAnotherRound(Player userPlayer) {
        String input;
        System.out.println(" tu tienes " + forceTwoDecimalDouble(userPlayer.getMoney().toString()));
        if (userPlayer.getMoney() >= 0.01) {
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

    private static void determineWinOrLoss(Player userPlayer){

        if (game.playerWins()) {
            System.out.println(" tu ganas !");
        } else {
            System.out.println(" la maquina gana !");
        }
        System.out.println(" la maquina tiene: " + game.getDealer().getScore());
        resetPotAndDiscardHands(userPlayer);
    }

    private static void resetPotAndDiscardHands(Player userPlayer){
        game.returnBet();
        userPlayer.getHand().clear();
        game.getDealer().getHand().clear();
    }

    private static void playerHitsOrStays(Player userPlayer){
        String input;
        do {
            input=forceHitOrStay();
            if ("pide".equalsIgnoreCase(input)) {
                game.dealCard(userPlayer);
            }
            displayPlayerHandAndScore(userPlayer);
        } while ("pide".equalsIgnoreCase(input) && (userPlayer.calculateScore() <= 21));
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
        System.out.println(" Dealer is showing: "+game.getDealer().getHand().get(0).toString());
    }

    private static void displayPlayerHandAndScore(Player userPlayer){
        Card card;
        for (int i=0; i<userPlayer.getHand().size(); i++)
        {
            card=userPlayer.getHand().get(i);
            if (i!=userPlayer.getHand().size()-1)
                System.out.print(card.getTopCardRepresentation());
            else
                System.out.println(card.toString());
        }
        System.out.println(" Your current score is: " + userPlayer.calculateScore());
    }

    private static void playerBets(Player userPlayer){
        Double betAmount;
        String input;
        input=forcePlayerBet(userPlayer);
        betAmount=Double.valueOf(input);
        userPlayer.makeBet(betAmount);
        game.addToPot(betAmount);
    }

    private static String forcePlayerBet(Player userPlayer){
        String input;
        do {
            input=forceDoubleInput();
        }
        while (!userPlayer.hasMoneyToMakeBet(Double.valueOf(input)));
        return (input);
    }

    private static String forceDoubleInput() {
        String input;
        do {
            System.out.print("\ncuanto quieres apostar?  ");
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
                "pasas".equalsIgnoreCase(passedString));
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
