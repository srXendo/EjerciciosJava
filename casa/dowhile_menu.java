import java.util.Scanner;
public class dowhile_menu {
    public static void main(String[] argv){
        int option = 0;
        Scanner teclado = new Scanner(System.in);
        do{

            System.out.println("======= MENU =======");
            System.out.println("1. Alta");
            System.out.println("2. Baja");
            System.out.println("3. Salir");
            System.out.println("Seleccione una opción");
            option = teclado.nextInt();
        }while(option != 3);
        System.out.println("Programa finalizado.");
        teclado.close();
    }
}
