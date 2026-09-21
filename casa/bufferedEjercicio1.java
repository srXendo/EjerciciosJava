
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class bufferedEjercicio1{
    static public void main(String[] args) throws IOException{
        Scanner teclado = new Scanner(System.in);

        //pide el nombre del archivo al usuario
        String nombreFichero = dameNomombreFichero(teclado);

        //instanciamos fichero y comprobamos si existe
        File fichero = new File(nombreFichero);
        if(!fichero.exists()){
            fichero.createNewFile();
        }

        //rellena el fichero
        rellenaFichero(fichero, teclado);

        //lee el fichero
        leerFichero(fichero);

        //cerramos
        teclado.close();
    }

    //Pide por pantalla el nombre del archivo y devuelve lo escrito.
    static public String dameNomombreFichero(Scanner teclado){
        System.out.print("Escribe nombre del fichero: ");
        String nombre = teclado.nextLine();
        return nombre;
    }

    //Abre en modo escritura el archivo y entra en bucle para rellenarlo.
    static public void rellenaFichero(File fichero, Scanner teclado) throws IOException{
        //declaramos escritor y abrimos el fichero sin pisar el contenido
        BufferedWriter escritor = new BufferedWriter(new FileWriter(fichero, true));
        
        int numeroInsertar;
        String temporal;
        do{
            //pide y guarda el numero en una variable temporal
            System.out.print("Escribe el siguiente numero: ");
            temporal = teclado.nextLine();
            if("fin".equals(temporal)){
                continue;
            }

            //transforma el texto en entero
            numeroInsertar = Integer.parseInt(temporal);

            //inserta en el fichero
            escritor.write(""+numeroInsertar+"\n");

        }while(!("fin".equals(temporal)));

        //cerramos
        escritor.close();
    }

    static public void leerFichero(File fichero) throws FileNotFoundException, IOException{
        //Inicializamos lector, numeroFilas, sumaTotal;
        BufferedReader lector = new BufferedReader(new FileReader(fichero));
        int numeroFilas = 0;
        int sumaTotal = 0;
        String fila;

        //recorre las filas que tiene el fichero
        while((fila = lector.readLine()) != null){
            numeroFilas += 1;
            sumaTotal += Integer.parseInt(fila);
        }

        //calcula media
        double media = (double)sumaTotal / (double)numeroFilas;

        //muestra el informe
        System.out.println("Contador        : " + numeroFilas);
        System.out.println("La suma total   : " + sumaTotal);
        System.out.println("La media        : " + media);

        lector.close();
        
    }
}