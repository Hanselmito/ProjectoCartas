package CajaDeCartas;

import java.util.Objects;

public class Card {
    private int value;
    private String Suit;

public Card(int value,String Suit){
    this.value = value;
    this.Suit = Suit;
}
public int getValue() {
    return value;
}

public void setValue(int value) {
    this.value = value;
}

public String getSuit() {
    return Suit;
}

public void setSuit(String suit) {
    Suit = suit;
}
@Override
public String toString() {
    return "Card{" + "value=" + value + ", Suit='" + Suit + '\'' + '}';
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Card card = (Card) o;
    return value == card.value && Objects.equals(Suit, card.Suit);
}
}
