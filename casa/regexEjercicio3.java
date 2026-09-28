
import java.util.Scanner;

public class regexEjercicio3{
    static public void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        String dni = dameDni(teclado);
        System.out.printf("Tu dni es: %s%n", dni);
    }
    static public String dameDni(Scanner s){
        
        System.out.print("Inserte dni: ");
        String dni = s.nextLine();
        if(!dni.matches("[0-9]{8}[a-z, A-Z]")){
            System.out.println("dni incorrecto insertelo de nuevo");
            dni = dameDni(s);
        }
        
        return dni;
        
    }
}