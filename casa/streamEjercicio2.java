import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class streamEjercicio2{
    static public void main(String[] args) throws IOException{
        Scanner teclado = new Scanner(System.in);

        //Pedimos el nombre del fichero
        System.out.print("Escribe el nombre del fichero: ");
        String nombreFichero = teclado.nextLine();

        //declaramos fichero a leer y lo comprobamos
        File archivo = new File(nombreFichero);

        //Si no existe el fichero salimos
        if(!archivo.exists()){
            System.out.println("Fichero no existe");
            archivo.createNewFile();
        }
        
        //Entramos al metodo para rellenar el fichero
        rellenaFichero(archivo, teclado);

        //declaramos el lector
        BufferedReader lector = new BufferedReader(new FileReader(archivo));
        String palabraBusqueda;

        //Marcamos el inicio del fichero
        if (lector.markSupported()) {
            lector.mark((int)archivo.length() + 1); 
        }
        do{
            //Pedimos palabra para encontrar
            System.out.print("Escriba palabra a encontrar: ");
            palabraBusqueda = teclado.nextLine();
            
            //Declaramos contador, linea y encontrado
            int contador = 0;
            String linea; 
            boolean encontrado = false;

            //Recorremos fichero.
            while((linea = lector.readLine()) != null){

                if(linea.contains(palabraBusqueda)){
                    encontrado = true;
                    contador += 1;
                }
            }

            //analizamos si hemos encontrado la palabra.
            
            if("fin".equals(palabraBusqueda)){
                System.out.printf("Saliendo...");
            }else if(encontrado){
                System.out.printf("La palabra '%s' aparece: %d%n", palabraBusqueda, contador);
            }else{
                System.out.printf("La palabra '%s' no aparece%n", palabraBusqueda);
            }

            //volvemos a la posicion inicial del fichero.
            lector.reset();
        }while(!"fin".equals(palabraBusqueda));
        
        //cerramos
        lector.close();
        teclado.close();

    }
    static public void rellenaFichero(File fichero, Scanner teclado) throws IOException{
        //declaramos escritor y abrimos el fichero sin pisar el contenido
        BufferedWriter escritor = new BufferedWriter(new FileWriter(fichero, true));
        
        String nombreInsertar;
        do{
            //pide y guarda el numero en una variable temporal
            System.out.print("Escribe el siguiente nombre: ");
            nombreInsertar = teclado.nextLine();
            if("fin".equals(nombreInsertar)){
                continue;
            }

            //inserta en el fichero
            escritor.write(""+nombreInsertar+"\n");

        }while(!("fin".equals(nombreInsertar)));

        //cerramos
        escritor.close();
    }

}