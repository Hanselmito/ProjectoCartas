package CajaDeCartas;

import java.util.ArrayList;
import java.util.Collections;

public class mazo {
    /**
     * Esta línea declara una variable privada llamada cartas que es un ArrayList de objetos Cartas.
     * Este ArrayList representa el mazo de cartas.
     */
    private ArrayList<Cartas> cartas;

    /**
     *  Este es el constructor de la clase mazo.
     *  Cuando se crea un nuevo objeto mazo, este constructor llama al método populate()
     *  para llenar el mazo con cartas.
     */
    public mazo(){
        populate();
    }

    /**
     * Este método devuelve una carta del mazo.
     * Si el mazo está vacío, primero lo llena y lo baraja.
     * Luego, toma la primera carta del mazo, la elimina del mazo y la devuelve.
     * @return
     */
    public Cartas getCartas(){
        if(this.cartas.size() == 0) {
            populate();
            shuffle();
        }
        Cartas cartas = this.cartas.get(0);
        this.cartas.remove(0);
        return cartas;
    }

    /**
     * Este método devuelve todas las cartas en el mazo.
     * @return
     */
    public ArrayList<Cartas> getAllCards(){
        return cartas;
    }

    /**
     * Este método llena el mazo con cartas.
     * Para cada símbolo en la enumeración Sinvolos, añade 13 cartas (una para cada valor de 1 a 13) al mazo.
     */
    private void populate() {
        cartas = new ArrayList<Cartas>();
        for (Sinvolos Sinvolos : Sinvolos.values()) {
            for (int valor = 1; valor <= 13; valor++)
            {
                if (valor < 11)
                {
                    if (valor==1)
                        cartas.add(new Cartas(Sinvolos, valor, Cartas.Sinvolo[Sinvolos.ordinal()], Cartas.LetraDeLaCarta[0]));
                    else
                        cartas.add(new Cartas(Sinvolos, valor, Cartas.Sinvolo[Sinvolos.ordinal()], ""+valor));
                }
                else
                    cartas.add(new Cartas(Sinvolos, 10, Cartas.Sinvolo[Sinvolos.ordinal()], Cartas.LetraDeLaCarta[valor - 10]));

            }
        }
    }

    /**
     * Este método baraja las cartas en el mazo.
     */
    public void shuffle(){
        Collections.shuffle(cartas);
    }

}
