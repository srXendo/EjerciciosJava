
import java.util.Scanner;

public class regexEjercicio1{
    static public void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        String codigoPostal = dameCodigoPostal(teclado);
        System.out.printf("Tu codigo postal es: %s%n", codigoPostal);
    }
    static public String dameCodigoPostal(Scanner s){
        
        System.out.print("Inserte codigo postal: ");
        String cp = s.nextLine();
        if(!cp.matches("[0-9]{5}")){
            System.out.println("Codigo postal incorrecto insertelo de nuevo");
            cp = dameCodigoPostal(s);
        }
        
        return cp;
        
    }
}