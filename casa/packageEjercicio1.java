import java.io.File;
import java.util.Scanner;

public class packageEjercicio1 {
    static public void main(String[] args){

        Scanner teclado = new Scanner(System.in);
        System.out.print("Escribe el nombre del fichero: ");
        String nombreFichero = teclado.nextLine();
        File archivo = new File(nombreFichero);
        System.out.println("Es archivo?     : " + archivo.isFile());
        System.out.println("Existe?         : " + archivo.exists());
        System.out.println("Ruta absoluta: " + archivo.getAbsolutePath());
    }
}
