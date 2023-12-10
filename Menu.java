import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);

        boolean salir = false;
        int opcion;  // Declarar la variable 'opcion' aquí

        while (!salir) {  // Agregar un bucle while para repetir el menú hasta que 'salir' sea verdadero
            System.out.println("");
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            System.out.println("🔞💎💎1. INICIAR PARTIDA💎💎🔞");
            System.out.println("↘↘ 2. Salir ↙↙");
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            opcion = sn.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Este es el programa que realiza Funciones");
                    break;
                case 2:
                    salir = true;
                    break;
                default:
                    System.out.println(" tu lees o comes bordillos a bocado ");
            }
        }
    }
}
