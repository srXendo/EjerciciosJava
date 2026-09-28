
import java.util.Scanner;

public class stringEjercicio2{
    static public void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        System.out.print("Escribe una frase            : ");
        String frase = teclado.nextLine();
        String[] palabras = frase.split(" ");

        int vocales = 0;
        int digitos = 0;

        for(String palabra: palabras){
            for(int i = 0; i < palabra.length(); i++){
                char letra = Character.toLowerCase(palabra.charAt(i));
                if('a' == letra || 'e' == letra || 'i' == letra || 'o' == letra || 'u' == letra){
                    vocales++;
                }else if(Character.isDigit(letra)){
                    digitos++;
                } 
            }
        }

        System.out.printf("Longitud                     : %d%n", frase.length());
        System.out.printf("Mayusculas                   : %s%n", frase.toUpperCase());
        System.out.printf("Minusculas                   : %s%n", frase.toLowerCase());
        System.out.printf("Primera Ocurrencia java      : %d%n", frase.indexOf("java"));
        System.out.printf("Ultima Ocurrencia java       : %d%n", frase.indexOf("java"));
        System.out.printf("Numero palabras              : %d%n", palabras.length);
        System.out.printf("Numero vocales               : %d%n", vocales);
        System.out.printf("Numero digitos               : %d%n", digitos);
        

        

    }
}