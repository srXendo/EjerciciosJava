
import java.util.Scanner;

public class stringEjercicio4{
    static public void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String frase = null; 
        do{
            System.out.print("Escribe una frase: ");
            String fraseAux = teclado.nextLine();
            if(!fraseAux.isBlank()){
                frase = fraseAux;
            }
             
        }while(frase == null);
        String reversFrase = "";
        for(int i = frase.length() - 1; i >= 0; i--){
            reversFrase += frase.charAt(i);
        }
        System.out.printf("Frase    : %s%n", frase);
        System.out.printf("Reverse  : %s%n", reversFrase);
        teclado.close();
    }
}