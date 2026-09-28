
import java.util.Scanner;

public class stringEjercicio5{
    static public void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        String Palabra = null; 
        do{
            System.out.print("Escribe una palabra: ");
            String palabraAux = teclado.nextLine();
            if(!palabraAux.isBlank() && !palabraAux.contains(" ")){
                Palabra = palabraAux.toLowerCase();
            }
             
        }while(Palabra == null);

        String reversPalabra = "";
        for(int i = Palabra.length() - 1; i >= 0; i--){
            reversPalabra += Palabra.charAt(i);
        }
        System.out.printf("Palabra    : %s%n", Palabra);
        System.out.printf("Reverse  : %s%n", reversPalabra);
        teclado.close();

        if(Palabra.equals(reversPalabra)){
            System.out.println("Es palindromo");
        }else{
            System.out.println("No es palindromo");
        }
        
    }
}