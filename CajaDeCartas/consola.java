package CajaDeCartas;

import java.util.Scanner;

public class consola {

    private static final double Apuesta_Minima=0.20;

    private static Juego juego = new Juego();
    private static Scanner scanner = new Scanner (System.in);

    public static void run(){

        Jugador usuarioJugador = juego.CojerJugador();

        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("|      ///////////////////////////////      |");
        System.out.println("|     🔥💎CASINO EL PUNTO CALIENTE💎🔥      |");
        System.out.println("|                  SI/NO                    |");
        System.out.println("|      //////////////////////////////       |");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

        System.out.println(" bienvenido jugador cual es tu nombre ");

        String userName = getInput();
        usuarioJugador.setNombre(userName);

        System.out.println(" Hola " + userName + "!");

        System.out.println(" tienes "+ForsarDecimalesDouble(usuarioJugador.getDinero().toString()));

        do {
            juego.Empezar();
            playerBets(usuarioJugador);
            ConfigurarJugadorManoyPuntos(usuarioJugador);
            displayDealerCardShowing();
            JugadorPedir0Parar(usuarioJugador);
            juego.PedirDistribucionAstaTerminar();
            DeterminaGana0Pierde(usuarioJugador);
        } while (JugadorEsperaRonda(usuarioJugador));

        System.out.println("Gracias por jugar👨‍🦽! Adios!");
    }

    private static boolean JugadorEsperaRonda(Jugador usuarioJugador) {
        String input;
        System.out.println(" tu tienes " + ForsarDecimalesDouble(usuarioJugador.getDinero().toString()));
        if (usuarioJugador.getDinero() >= 0.20) {
            do {
                System.out.print("quieres probar de nuevo? [si/No]  ");
                input = getInput();
                if (DecirSi0No(input))
                {
                    return ("si".equalsIgnoreCase(input));
                }
            } while (!DecirSi0No(input));

        }
        else {
            System.out.println(" no tienes más dinero.");
        }
        return false;
    }

    private static void DeterminaGana0Pierde(Jugador usuarioJugador){

        if (juego.JugadorGana()) {
            System.out.println(" tu ganas !");
        } else {
            System.out.println(" la maquina gana !");
        }
        System.out.println(" la maquina tiene: " + juego.CojerDistribusion().getpuntos());
        VolveraEmpesaryQuitarcartas(usuarioJugador);
    }

    private static void VolveraEmpesaryQuitarcartas(Jugador usuarioJugador){
        juego.RepetirApuesta();
        usuarioJugador.getmanos().clear();
        juego.CojerDistribusion().getmanos().clear();
    }

    private static void JugadorPedir0Parar(Jugador usuarioJugador){
        String input;
        do {
            input=ForsarSiPedir0Parar();
            if ("pide".equalsIgnoreCase(input)) {
                juego.DistribuirCartas(usuarioJugador);
            }
            ConfigurarJugadorManoyPuntos(usuarioJugador);
        } while ("pide".equalsIgnoreCase(input) && (usuarioJugador.calculatePuntos() <= 21));
    }

    private static String ForsarSiPedir0Parar(){
        String input;
        do {
            System.out.print(" pides o pasas? ");
            input = getInput();
        } while (!DecirSipedir0Parar(input));
        return input;
    }

    private static void displayDealerCardShowing(){
        System.out.println(" la maquina tiene: "+ juego.CojerDistribusion().getmanos().get(0).toString());
    }

    private static void ConfigurarJugadorManoyPuntos(Jugador usuarioJugador){
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
        juego.AñadeApuesta(betAmount);
    }

    private static String forcePlayerBet(Jugador usuarioJugador){
        String input;
        do {
            input=forceDoubleInput();
        }
        while (!usuarioJugador.CuantoDineroAcumulamos(Double.valueOf(input)));
        return (input);
    }

    private static String forceDoubleInput() {
        String input;
        do {
            System.out.print("cuanto quieres apostar?  ");
            input = getInput();
        } while (!isInputDouble(input) || !isInputPositive(input));
        return ForsarDecimalesDouble(input);
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

    public static boolean DecirSipedir0Parar(String passedString)
    {
        return ("pide".equalsIgnoreCase(passedString) ||
                "paso".equalsIgnoreCase(passedString));
    }

    public static boolean DecirSi0No(String passedString)
    {
        return ("si".equalsIgnoreCase(passedString) ||
                "no".equalsIgnoreCase(passedString));
    }

    public static String ForsarDecimalesDouble(String input){
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
