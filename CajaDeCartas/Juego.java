package CajaDeCartas;


public class Juego {
    private Jugador jugador;
    private mazo mazo;
    private Jugador dealer;
    private Double pot;

    public Juego(){
        jugador = new Jugador();
        mazo = new mazo();
        dealer = new Jugador("Dealer");
        mazo.shuffle();
        pot = 0.0;
    }

    public Double getPot() {
        return pot;
    }

    public void addToPot(Double amount) {
        this.pot += amount;
    }

    public Jugador getPlayer() {
        return jugador;
    }

    public Jugador getDealer() {
        return dealer;
    }

    public void start() {

        //Deal two cards to the player and the dealer
        for (int i = 0; i < 2; i++)
        {
            dealCard(jugador);
            dealCard(dealer);
        }

    }

    public boolean playerWins() {
        // si las puntuaciones del jugador y de la casa son = hasta 21 victorias de la casa
        // si la puntuación tanto del jugador como de la casa es superior a 21, entonces la casa gana
        // si la puntuación del jugador es <= 21 y la puntuación de los jugadores es mayor que la puntuación de la casa, entonces el jugador gana.
        //si la puntuación de la casa es > 21 y la puntuación del jugador es <= 21, entonces el jugador gana.
        jugador.calculatePuntos();
        dealer.calculatePuntos();

        if(     (jugador.getpuntos().equals(21) && !dealer.getpuntos().equals(21)) ||
                (jugador.getpuntos()<21 && dealer.getpuntos() < jugador.getpuntos()) ||
                (jugador.getpuntos() < 21 && dealer.getpuntos() > 21)) {
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
        while (dealer.calculatePuntos() <= 17)
        {
            dealCard(dealer);
        }
    }

    public void returnBet() {
        if (playerWins()) {
            jugador.receiveWinnings(pot * 2);
        }
        else {
            this.pot = 0.0;
        }
    }
}
