package CajaDeCartas;

import java.util.Scanner;

public class consola {

    private static final double Apuesta_Minima=0.20;

    private static Juego juego = new Juego();
    private static Scanner scanner = new Scanner (System.in);

    /**
     * el menu para jugar
     */
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
            ApuestasDelJugador(usuarioJugador);
            ConfigurarJugadorManoyPuntos(usuarioJugador);
            displayDealerCardShowing();
            JugadorPedir0Parar(usuarioJugador);
            juego.PedirDistribucionAstaTerminar();
            DeterminaGana0Pierde(usuarioJugador);
        } while (JugadorEsperaRonda(usuarioJugador));

        System.out.println("Gracias por jugar👨‍🦽! Adios!");
    }

    /**
     * cuando empiesas la partida te muestra la cantidad de dineros que te dan
     * y con el else te lo dice cuando no tienes mas que apostar
     *
     * y tambien te muestra si quieres segir jugando o no
     * @param usuarioJugador
     * @return
     */
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

    /**
     * aqui te mostraria despues de parar de apostar en la partida si has ganado o has perdido.
     *
     * y tambien te mostraria cuanto tenia la maquina.
     * @param usuarioJugador
     */
    private static void DeterminaGana0Pierde(Jugador usuarioJugador){

        if (juego.JugadorGana()) {
            System.out.println(" tu ganas !");
        } else {
            System.out.println(" la maquina gana !");
        }
        System.out.println(" la maquina tiene: " + juego.CojerDistribusion().getpuntos());
        VolveraEmpesaryQuitarcartas(usuarioJugador);
    }

    /**
     * cuando hayas ganado o perdido mientras tengas dinero que apostar te preguntaran si quieres jugar otra ves
     * y actuaria el programa de volver a empesar y quitar y dar cartas nuevas
     * @param usuarioJugador
     */
    private static void VolveraEmpesaryQuitarcartas(Jugador usuarioJugador){
        juego.RepetirApuesta();
        usuarioJugador.getmanos().clear();
        juego.CojerDistribusion().getmanos().clear();
    }

    /**
     * si el jugador pide carta para continuar o para, para terminar
     * @param usuarioJugador
     */
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

    /**
     * para pedir o parar tu mismo
     * @return
     */
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

    /**
     * configurar las cartas de la mano del jugador
     * @param usuarioJugador
     */
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

    /**
     * para que pueda apostar el jugador
     * @param usuarioJugador
     */
    private static void ApuestasDelJugador(Jugador usuarioJugador){
        Double betAmount;
        String input;
        input=ForsarApuestaDelJugador(usuarioJugador);
        betAmount=Double.valueOf(input);
        usuarioJugador.makeBet(betAmount);
        juego.AñadeApuesta(betAmount);
    }

    /**
     * para que puedas decir tu cuanto quieres apostar
     * @param usuarioJugador
     * @return
     */
    private static String ForsarApuestaDelJugador(Jugador usuarioJugador){
        String input;
        do {
            input=ForsarDoubleInput();
        }
        while (!usuarioJugador.CuantoDineroAcumulamos(Double.valueOf(input)));
        return (input);
    }

    private static String ForsarDoubleInput() {
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

    /**
     * decir si pedir carta o parar
     * @param passedString
     * @return
     */
    public static boolean DecirSipedir0Parar(String passedString)
    {
        return ("pide".equalsIgnoreCase(passedString) ||
                "paso".equalsIgnoreCase(passedString));
    }

    /**
     * decir si segir o parar de jugar
     * @param passedString
     * @return
     */
    public static boolean DecirSi0No(String passedString)
    {
        return ("si".equalsIgnoreCase(passedString) ||
                "no".equalsIgnoreCase(passedString));
    }

    /**
     * para poder utilizar decimales que son centimos
     * @param input
     * @return
     */
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
