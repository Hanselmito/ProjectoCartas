package CajaDeCartas;

import java.util.ArrayList;
/*
*hand lo e utilizado para poder ver las cartas que tengo en el juego.
* name el nombre que diga de poner
* money lo busque por paginas y videos para averiguar poder jugar apostando
* score para ver la puntuacion
 */
public class Player {
    private ArrayList<Card> hand;
    private String name;
    private Double money;
    private Integer score;

    /*
    *ponemos un array de cartas de 2 por que empesamos en el blackjack con 2 cartas
    * el nombre del jugador
    * y con el dinero que nos dan y la que queremos apostar
     */
    public Player(){
        hand = new ArrayList<Card>(2);
        name="jugador";
        money = 500.0;
        score = 0;
    }
    public Player(Double passedMoney){
        this();
        money=passedMoney;
    }
    public Player(String name, Double passedMoney){
        this();
        money=passedMoney;
        this.name=name;
    }
    public Player(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public Integer getScore() {
        return score;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    // blackjack hit
    public void addToHand(Card card) {
        hand.add(card);
    }

    public boolean hasMoneyToMakeBet(Double amount) {
        return (amount <= money);
    }

    public void makeBet(Double amount) {
        money -= amount;
    }

    public boolean isAceInHand() {
        for(Card card: hand) {
            if(card.getValue() == 1) {
                return true;
            }
        }
        return false;
    }

    /*
    *y para calcular la puntuacion de cada partida que hagamos
     */
    public Integer calculateScore() {
        int sum = 0;
        for(Card card: hand) {
            sum += card.getValue();
        }

        if(isAceInHand() && sum <= 11) {
            sum += 10;
        }
        score = sum;

        return sum;
    }

    public void receiveWinnings(Double amount) {
        money += amount;
    }
}
