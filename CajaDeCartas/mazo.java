package CajaDeCartas;

import java.util.ArrayList;
import java.util.Collections;

public class mazo {

    private ArrayList<Cartas> cartas;

    public mazo(){
        populate();
    }

    public Cartas getCartas(){
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
        for (Sinvolos Sinvolos : Sinvolos.values()) {
            for (int valor = 1; valor <= 13; valor++)
            {
                if (valor < 11)
                {
                    if (valor==1)
                        cartas.add(new Cartas(Sinvolos, valor, Cartas.Sinvolo[Sinvolos.ordinal()], Cartas.SinvoloDeLaCarta[0]));
                    else
                        cartas.add(new Cartas(Sinvolos, valor, Cartas.Sinvolo[Sinvolos.ordinal()], ""+valor));
                }
                else
                    cartas.add(new Cartas(Sinvolos, 10, Cartas.Sinvolo[Sinvolos.ordinal()], Cartas.SinvoloDeLaCarta[valor - 10]));

            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cartas);
    }

}
