
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class bufferedEjercicio2 {
    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);

        String nombreFichero = dameNomombreFichero(teclado);

        Informe informe = leeFichero(nombreFichero);

        muestraInforme(informe);

        teclado.close();

    }
    static public void muestraInforme(Informe inform){
        System.out.println("Alumnos     : " + inform.contador);

        System.out.println("Suma        : " + inform.suma);

        System.out.println("Media       : " + inform.media);

        System.out.println("Aprovados   : " + inform.aprovados);

        System.out.println("Suspensos   : " + inform.suspensos);
        
        System.out.println("Nota maxima : " + inform.notaMax);

        System.out.println("Nota minima : " + inform.notaMin);
    }
    static public Informe leeFichero(String nombreFichero) throws FileNotFoundException, IOException{
        BufferedReader lector = new BufferedReader(new FileReader(nombreFichero));
        Informe inform = new Informe();
        String linea;


        while((linea = lector.readLine()) != null) {
        
            int nota = Integer.parseInt(linea);
            //si contador es 0 añade primera nota min y nota max
            if(inform.contador == 0){
                inform.notaMin = nota;
                inform.notaMax = nota;
            }

            if(inform.notaMin > nota){
                inform.notaMin = nota;
            }
            if(inform.notaMax < nota){
                inform.notaMax = nota;
            }

            inform.suma += nota;
            inform.contador++;

            
            if(nota < 5){
                inform.suspensos += 1;
            }else{
                inform.aprovados += 1;
            }


        }
        lector.close();

        inform.media = (double) inform.suma / inform.contador;

        return inform;
    }
    static public String dameNomombreFichero(Scanner teclado){
        System.out.print("Escribe nombre del fichero: ");
        String nombre = teclado.nextLine();
        return nombre;
    }

}
class Informe{
    int suma = 0;
    int contador = 0;
    int aprovados = 0;
    int suspensos = 0;
    int notaMin = 0;
    int notaMax = 0;
    double media = 0.0;
}