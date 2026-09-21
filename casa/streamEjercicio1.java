import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class streamEjercicio1 {
    static public void main(String[] args) throws IOException{
        //Pedimos nombre de fichero a escribir
        Scanner teclado = new Scanner(System.in);
        System.out.print("Escribe el nombre del fichero: ");
        String nombreFichero = teclado.nextLine();

        //Pedimos el numero de alumnos que vamos a insertar en el fichero.
        System.out.print("Escribe el numero de alumnos para añadir: ");
        int numNombres = teclado.nextInt();
        teclado.nextLine();

        //Declaramos el array de nombres y lo rellenamos
        String[] arrNombres = new String[numNombres];
        for(int i = 0; i < numNombres; i++){
            System.out.print("Escribe el nombre: ");
            arrNombres[i] = teclado.nextLine();
        }

        //Declaramos el fichero donde se va volcar el array de nombres.
        //comprobamos que no existe si no existe lo creamos.
        File archivo = new File(nombreFichero);
        if(!archivo.exists()){
            archivo.createNewFile();
        }

        //declaramos el escritor
        FileWriter escritor = new FileWriter(archivo);
        
        //añadirmos al array un salto de linea por elemento y lo escribimos en el fichero.
        escritor.write(String.join("\n", arrNombres));
        escritor.close();

        //Declaramos el fichero backup y comprobamos si no existe para crearlo.
        File archivoBackup = new File("backup_"+nombreFichero);
        if(!archivoBackup.exists()){
            archivoBackup.createNewFile();
        }

        //Declaramos el lector y el escritor del fichero backup para streams.
        FileInputStream lectorStream = new FileInputStream(archivo);
        FileOutputStream escritorStream = new FileOutputStream(archivoBackup);

        //recorremos el fichero original con streams y los escribimos en el fichero backup
        int dato;
        while((dato = lectorStream.read()) != -1){
            
            char caracter = (char) dato;
            System.out.print("[" + dato + " -> " + caracter + "] ");
            escritorStream.write(dato);
        }

        //cerramos el stream de lector y escritor.
        lectorStream.close();
        escritorStream.close();
        teclado.close();
        
    }
}
