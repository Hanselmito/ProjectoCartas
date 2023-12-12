package CajaDeCartas;

import java.util.ArrayList;
/*
*hand lo e utilizado para poder ver las cartas que tengo en el juego.
* name el nombre que diga de poner
* money lo busque por paginas y videos para averiguar poder jugar apostando
* score para ver la puntuacion
 */
public class Jugador {
    private ArrayList<Cartas> hand;
    private String name;
    private Double money;
    private Integer score;

    /*
    *ponemos un array de cartas de 2 por que empesamos en el blackjack con 2 cartas
    * el nombre del jugador
    * y con el dinero que nos dan y la que queremos apostar
     */
    public Jugador(){
        hand = new ArrayList<Cartas>(2);
        name="jugador";
        money = 500.0;
        score = 0;
    }
    public Jugador(Double passedMoney){
        this();
        money=passedMoney;
    }
    public Jugador(String name, Double passedMoney){
        this();
        money=passedMoney;
        this.name=name;
    }
    public Jugador(String name) {
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

    public ArrayList<Cartas> getHand() {
        return hand;
    }

    // blackjack hit
    public void addToHand(Cartas cartas) {
        hand.add(cartas);
    }

    public boolean hasMoneyToMakeBet(Double amount) {
        return (amount <= money);
    }

    public void makeBet(Double amount) {
        money -= amount;
    }

    public boolean isAceInHand() {
        for(Cartas cartas : hand) {
            if(cartas.getValue() == 1) {
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
        for(Cartas cartas : hand) {
            sum += cartas.getValue();
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
