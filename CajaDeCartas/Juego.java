package CajaDeCartas;


public class Juego {
    private Jugador jugador;
    private mazo mazo;
    private Jugador Distribuir;
    private Double Pot;

    public Juego(){
        jugador = new Jugador();
        mazo = new mazo();
        Distribuir = new Jugador("Dealer");
        mazo.shuffle();
        Pot = 0.0;
    }

    public Double getPot() {
        return Pot;
    }

    public void addToPot(Double amount) {
        this.Pot += amount;
    }

    public Jugador getPlayer() {
        return jugador;
    }

    public Jugador getDealer() {
        return Distribuir;
    }

    public void start() {

        //Deal two cards to the player and the Distribuir
        for (int i = 0; i < 2; i++)
        {
            dealCard(jugador);
            dealCard(Distribuir);
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

    public void dealCard(Jugador jugadorToReceiveCard){
        Cartas cartas = mazo.getCartas();
        jugadorToReceiveCard.addToHand(cartas);
    }

    public void dealerHitUntilFinished() {
        while (Distribuir.calculatePuntos() <= 17)
        {
            dealCard(Distribuir);
        }
    }

    public void returnBet() {
        if (JugadorGana()) {
            jugador.ReciveGanador(Pot * 2);
        }
        else {
            this.Pot = 0.0;
        }
    }
}
