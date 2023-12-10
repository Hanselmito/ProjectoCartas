package CajaDeCartas;

import java.util.Objects;

public class cartas {
    private int value;
    private String suit;

    public cartas(){
        this(0," ");
    }
    public cartas(int value, String suit ){
        this.value = value;
        this.suit = suit;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        cartas Card = (cartas) o;
        return value == Card.value && Objects.equals(suit, Card.suit);
    }

    public static cartas añadeCartas(int value, String suit){
        cartas Card =new cartas(value,suit);
        return Card;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return " card{ " +
                " value= " + value +
                " , suit=' " + suit + '\'' +
                '}';
    }
}
