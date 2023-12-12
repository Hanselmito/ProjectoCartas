package CajaDeCartas;

import java.util.ArrayList;
import java.util.Collections;

public class mazo {

    private ArrayList<Cartas> cartas;

    public mazo(){
        populate();
    }

    public Cartas getCard(){
        if(this.cartas.size() == 0) {
            populate();
            shuffle();
        }
        Cartas cartas = this.cartas.get(0);
        this.cartas.remove(0);
        return cartas;
    }

    public ArrayList<Cartas> getAllCards(){
        return cartas;
    }


    private void populate() {
        cartas = new ArrayList<Cartas>();
        for (regla regla : regla.values()) {
            for (int value = 1; value <= 13; value++)
            {
                if (value < 11)
                {
                    if (value==1)
                        cartas.add(new Cartas(regla, value, Cartas.suitSymbols[regla.ordinal()], Cartas.faceSymbols[0]));
                    else
                        cartas.add(new Cartas(regla, value, Cartas.suitSymbols[regla.ordinal()], ""+value));
                }
                else
                    cartas.add(new Cartas(regla, 10, Cartas.suitSymbols[regla.ordinal()], Cartas.faceSymbols[value - 10]));

            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cartas);
    }

}
