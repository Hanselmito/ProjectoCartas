package CajaDeCartas;

/**
 * crear los datos jugador, mazo, distribuicion, apuestas
 */
public class Juego {
    private Jugador jugador;
    private mazo mazo;
    private Jugador Distribuir;
    private Double Apuestas;

    public Juego(){
        jugador = new Jugador();
        mazo = new mazo();
        Distribuir = new Jugador("Dealer");
        mazo.shuffle();
        Apuestas = 0.0;
    }

    public Double getPot() {
        return Apuestas;
    }

    public void AñadeApuesta(Double amontonar) {
        this.Apuestas += amontonar;
    }

    public Jugador CojerJugador() {
        return jugador;
    }

    public Jugador CojerDistribusion() {
        return Distribuir;
    }

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

    public void DistribuirCartas(Jugador jugadorRecibeCarta){
        Cartas cartas = mazo.getCartas();
        jugadorRecibeCarta.addToHand(cartas);
    }

    public void PedirDistribucionAstaTerminar() {
        while (Distribuir.calculatePuntos() <= 17)
        {
            DistribuirCartas(Distribuir);
        }
    }

    public void RepetirApuesta() {
        if (JugadorGana()) {
            jugador.ReciveGanador(Apuestas * 2);
        }
        else {
            this.Apuestas = 0.0;
        }
    }
}
