
import java.util.Scanner;

public class regexEjercicio5{
    static public void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        String texto = dameTexto(teclado);
        System.out.printf("Tu nombre de usuario es: %s%n", texto);
    }
    static public String dameTexto(Scanner s){
        
        System.out.print("Inserte texto: ");
        String texto = s.nextLine();
        if(!texto.matches("[A-Z]{3}[0-9]{4}")){
            System.out.println("texto incorrecto insertelo de nuevo");
            texto = dameTexto(s);
        }
        
        return texto;
        
    }
}