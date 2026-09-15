import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class hashsetEjercicio1 {
    static public void main(String[] args){
        Set<String> nombres = new HashSet<>();
        Scanner teclado = new Scanner(System.in);

        for(int i = 0; i < 10; i++){
            System.out.println("Escribe el nombre nº " + (i+1) + ": ");
            String nuevoNombre = teclado.nextLine();
            nombres.add(nuevoNombre);
            System.out.println(nombres); 
        }
        System.out.println("El numero de nombres unicos que has creado: " + nombres.size());
        teclado.close();
    }
}
