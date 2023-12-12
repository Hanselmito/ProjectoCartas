package CajaDeCartas;



import java.util.ArrayList;

public class Game {
    private Player player;
    private Deck deck;
    private Player dealer;
    private Double pot;

    public Game(){
        player = new Player();
        deck = new Deck();
        dealer = new Player("Dealer");
        deck.shuffle();
        pot = 0.0;
    }

    public Double getPot() {
        return pot;
    }

    public void addToPot(Double amount) {
        this.pot += amount;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getDealer() {
        return dealer;
    }

    public void start() {

        //Deal two cards to the player and the dealer
        for (int i = 0; i < 2; i++)
        {
            dealCard(player);
            dealCard(dealer);
        }

    }

    public boolean playerWins() {
        // si las puntuaciones del jugador y de la casa son = hasta 21 victorias de la casa
        // si la puntuación tanto del jugador como de la casa es superior a 21, entonces la casa gana
        // si la puntuación del jugador es <= 21 y la puntuación de los jugadores es mayor que la puntuación de la casa, entonces el jugador gana.
        //si la puntuación de la casa es > 21 y la puntuación del jugador es <= 21, entonces el jugador gana.
        player.calculateScore();
        dealer.calculateScore();

        if(     (player.getScore().equals(21) && !dealer.getScore().equals(21)) ||
                (player.getScore()<21 && dealer.getScore() < player.getScore()) ||
                (player.getScore() < 21 && dealer.getScore() > 21)) {
            //jugador gana
            return true;
        }
        //la casa gana
        return false;
    }

    public void dealCard(Player playerToReceiveCard){
        Card card = deck.getCard();
        playerToReceiveCard.addToHand(card);
    }

    public void dealerHitUntilFinished() {
        while (dealer.calculateScore() <= 17)
        {
            dealCard(dealer);
        }
    }

    public void returnBet() {
        if (playerWins()) {
            player.receiveWinnings(pot * 2);
        }
        else {
            this.pot = 0.0;
        }
    }
}
