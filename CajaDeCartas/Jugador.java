package CajaDeCartas;

import java.util.ArrayList;
/*
*Mano lo e utilizado para poder ver las cartas que tengo en el juego.
* Nombre el nombre que diga de poner
* Dinero lo busque por paginas y videos para averiguar poder jugar apostando
* score para ver la puntuacion
 */
public class Jugador {
    private ArrayList<Cartas> Mano;
    private String Nombre;
    private Double Dinero;
    private Integer puntos;

    /*
    *ponemos un array de cartas de 2 por que empesamos en el blackjack con 2 cartas
    * el nombre del jugador
    * y con el dinero que nos dan y la que queremos apostar
     */
    public Jugador(){
        Mano = new ArrayList<Cartas>(2);
        Nombre="jugador";
        Dinero = 500.0;
        puntos = 0;
    }

    /**
     * para pasar el dinero ganado
     * @param PasarDinero
     */
    public Jugador(Double PasarDinero){
        this();
        Dinero=PasarDinero;
    }

    /**
     * pasar el dinero ganado al nombre del jugador
     * @param Nombre
     * @param pasarDinero
     */
    public Jugador(String Nombre, Double pasarDinero){
        this();
        Dinero=pasarDinero;
        this.Nombre=Nombre;
    }
    public Jugador(String Nombre) {
        this();
        this.Nombre = Nombre;
    }

    /**
     * cojer el nombre y poner el nombre
     * @return
     */
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }

    /**
     * cojer el dinero
     * @return
     */
    public Double getDinero() {
        return Dinero;
    }

    /**
     * cojer los puntos
     * @return
     */
    public Integer getpuntos() {
        return puntos;
    }

    /**
     * cojer las cartas de la mano
     * @return
     */
    public ArrayList<Cartas> getmanos() {
        return Mano;
    }

    // blackjack pedir
    public void AñadeALaMano(Cartas cartas) {
        Mano.add(cartas);
    }

    public boolean CuantoDineroAcumulamos(Double amount) {
        return (amount <= Dinero);
    }

    public void makeBet(Double amontonar) {
        Dinero -= amontonar;
    }

    public boolean ElAzDeLaCarta() {
        for(Cartas cartas : Mano) {
            if(cartas.getValor() == 1) {
                return true;
            }
        }
        return false;
    }

    /*
    *y para calcular la puntuacion de cada partida que hagamos
     */
    public Integer calculatePuntos() {
        int sum = 0;
        for(Cartas cartas : Mano) {
            sum += cartas.getValor();
        }

        if(ElAzDeLaCarta() && sum <= 11) {
            sum += 10;
        }
        puntos = sum;

        return sum;
    }

    public void ReciveGanador(Double amount) {
        Dinero += amount;
    }
}
