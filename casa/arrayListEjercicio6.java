import java.util.ArrayList;
import java.util.Scanner;

public class arrayListEjercicio6 {
    static public void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        ArrayList<Integer> arrListNumeros = new ArrayList<>();
        for(int i = 0; i <= 5; i++) {
            int numero = dameNumero(teclado);
            arrListNumeros.add(numero);
        }

        int sumaTotal = 0;
        int numeroMayor = arrListNumeros.get(0);
        int numeroMenor = arrListNumeros.get(0);

        for(int num : arrListNumeros){
            sumaTotal += num;
            if(numeroMayor < num){
                numeroMayor = num;
            }
            if(numeroMenor > num){
                numeroMenor = num;
            }
        }
        double media = (sumaTotal / arrListNumeros.size());
        System.out.println("arrListNumeros                      : " + arrListNumeros);
        System.out.println("Suma total de numeros               : " + sumaTotal);
        System.out.println("Media                               : " + media);
        System.out.println("Numero mayor                        : " + numeroMayor);
        System.out.println("Numero menor                        : " + numeroMenor);


    }
    static public Integer dameNumero (Scanner s){
        System.out.print("Escribe un numero: ");
        int numero = s.nextInt();
        s.nextLine();
        return numero;
    }
}
