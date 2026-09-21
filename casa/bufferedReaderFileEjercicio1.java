import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class bufferedReaderFileEjercicio1 {
    static public void main(String[] args) throws IOException{
        //Crea el lector a nivel de bytes y el lector buffered
        FileReader lectorBytes = new FileReader("Prueba.txt");
        BufferedReader lector = new BufferedReader(lectorBytes);

        //Crea el escritor a nivel de bytes y el escritor buffered
        FileWriter escritorBytes = new FileWriter("backup_prueba.txt");
        BufferedWriter escritor = new BufferedWriter(escritorBytes);

        //Declaramos y inicializamos linea y contador
        String linea = lector.readLine();
        int contadorLineas = 0;
        int contadorCaracteres = 0;

        //Recorre todas las lineas hasta que sea null y las pinta por pantalla.
        while((linea = lector.readLine()) != null){
            contadorLineas += 1;
            contadorCaracteres += linea.length();
            System.out.printf("Fila nº: %d Numero Caracteres: %d Fila: %s%n", contadorLineas, contadorCaracteres ,linea);
            escritor.write(linea);
            escritor.newLine(); //salto de linea
            
        };
        
        System.out.printf("---Backup del fichero completado---");


        //cerramos lector y escritor
        escritor.close();
        lector.close();
    }
    
}
