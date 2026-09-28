
import java.util.Scanner;

public class regexEjercicio4{
    static public void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        String nombreUsuario = dameNombreUsuario(teclado);
        System.out.printf("Tu nombre de usuario es: %s%n", nombreUsuario);
    }
    static public String dameNombreUsuario(Scanner s){
        
        System.out.print("Inserte nombre usuario: ");
        String nombreUsuario = s.nextLine();
        if(!nombreUsuario.matches("[0-9a-zA-Z]{4,12}")){
            System.out.println("nombre de usuario incorrecto insertelo de nuevo");
            nombreUsuario = dameNombreUsuario(s);
        }
        
        return nombreUsuario;
        
    }
}