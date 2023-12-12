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
    public Jugador(Double passedMoney){
        this();
        Dinero=passedMoney;
    }
    public Jugador(String Nombre, Double pasasMoney){
        this();
        Dinero=pasasMoney;
        this.Nombre=Nombre;
    }
    public Jugador(String Nombre) {
        this();
        this.Nombre = Nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }

    public Double getDinero() {
        return Dinero;
    }

    public Integer getpuntos() {
        return puntos;
    }

    public ArrayList<Cartas> getmanos() {
        return Mano;
    }

    // blackjack hit
    public void addToHand(Cartas cartas) {
        Mano.add(cartas);
    }

    public boolean hasMoneyToMakeBet(Double amount) {
        return (amount <= Dinero);
    }

    public void makeBet(Double amontonar) {
        Dinero -= amontonar;
    }

    public boolean isAceInHand() {
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

        if(isAceInHand() && sum <= 11) {
            sum += 10;
        }
        puntos = sum;

        return sum;
    }

    public void receiveWinnings(Double amount) {
        Dinero += amount;
    }
}
