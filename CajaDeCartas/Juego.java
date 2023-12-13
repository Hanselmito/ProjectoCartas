package CajaDeCartas;

/**
 * crear los datos jugador, mazo, distribuicion, apuestas
 */
public class Juego {
    private Jugador jugador;
    private mazo mazo;
    private Jugador Distribuir;
    private Double Apuestas;

    /**
     * crea una nueva instancia de la clase Jugador y la asigna a la variable jugador.
     * Esto representa al jugador humano en el juego.
     *
     * crea una nueva instancia de la clase mazo y la asigna a la variable mazo.
     * Esto representa el mazo de cartas que se utilizará en el juego.
     *
     * crea una nueva instancia de la clase Jugador con el nombre “Dealer” y la asigna a la variable Distribuir.
     * Esto representa al repartidor en el juego.
     *
     * llama al método shuffle() en la instancia mazo. Esto baraja el mazo de cartas.
     *
     * inicializa la variable Apuestas a 0.0. Esto representa la cantidad total de dinero apostado en el juego.
     * Al inicio del juego, la apuesta es 0.0.
     */
    public Juego(){
        jugador = new Jugador();
        mazo = new mazo();
        Distribuir = new Jugador("");
        mazo.shuffle();
        Apuestas = 0.0;
    }

    /**
     * recoje apuesta y debielve apuesta
     * @return
     */
    public Double getPot() {
        return Apuestas;
    }

    /**
     * acumula las apuestas que hemos ganado
     * @param amontonar
     */
    public void AñadeApuesta(Double amontonar) {
        this.Apuestas += amontonar;
    }

    /**
     * llamamos al jugador
     * @return
     */
    public Jugador CojerJugador() {
        return jugador;
    }

    /**
     * llamar distribusion
     * @return
     */
    public Jugador CojerDistribusion() {
        return Distribuir;
    }

    /**
     * empesamos la partida con 2 cartas con este metodo
     */
    public void Empezar() {

        //Reparte dos cartas al jugador y a la maquina
        for (int i = 0; i < 2; i++)
        {
            DistribuirCartas(jugador);
            DistribuirCartas(Distribuir);
        }

    }

    public boolean JugadorGana() {
        // si las puntuaciones del jugador y de la casa son = hasta 21 victorias de la casa
        // si la puntuación tanto del jugador como de la casa es superior a 21, entonces la casa gana
        // si la puntuación del jugador es <= 21 y la puntuación de los jugadores es mayor que la puntuación de la casa, entonces el jugador gana.
        //si la puntuación de la casa es > 21 y la puntuación del jugador es <= 21, entonces el jugador gana.
        jugador.calculatePuntos();
        Distribuir.calculatePuntos();

        if(     (jugador.getpuntos().equals(21) && !Distribuir.getpuntos().equals(21)) ||
                (jugador.getpuntos()<21 && Distribuir.getpuntos() < jugador.getpuntos()) ||
                (jugador.getpuntos() < 21 && Distribuir.getpuntos() > 21)) {
            //jugador gana
            return true;
        }
        //la casa gana
        return false;
    }

    /**
     * destribuir la reparticion de las cartas
     * @param jugadorRecibeCarta
     */
    public void DistribuirCartas(Jugador jugadorRecibeCarta){
        Cartas cartas = mazo.getCartas();
        jugadorRecibeCarta.AñadeALaMano(cartas);
    }

    /**
     * pedir carta hasta perder o sacar 21 que es blackjack
     */
    public void PedirDistribucionAstaTerminar() {
        while (Distribuir.calculatePuntos() <= 17)
        {
            DistribuirCartas(Distribuir);
        }
    }

    /**
     * volver a repetir la apuesta
     */
    public void RepetirApuesta() {
        if (JugadorGana()) {
            jugador.ReciveGanador(Apuestas * 2);
        }
        else {
            this.Apuestas = 0.0;
        }
    }
}
