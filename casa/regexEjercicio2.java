
import java.util.Scanner;

public class regexEjercicio2{
    static public void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        String numeroTelefono = dameNumeroTelefono(teclado);
        System.out.printf("Tu Numero de telefono es: %s%n", numeroTelefono);
    }
    static public String dameNumeroTelefono(Scanner s){
        
        System.out.print("Inserte numero de telefono: ");
        String telfNum = s.nextLine();
        if(!telfNum.matches("[0-9]{9}")){
            System.out.println("Numero de telefono incorrecto insertelo de nuevo");
            telfNum = dameNumeroTelefono(s);
        }
        
        return telfNum;
        
    }
}