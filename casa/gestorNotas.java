
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class gestorNotas{
    public static final String DELIMITADOR_COLUMNA = ";";
    public static final String DELIMITADOR_NOTAS = "---";
    static public void main(String[] args) throws IOException{

        Scanner teclado = new Scanner(System.in);

        String nombreFichero = dameNomombreFichero(teclado);

        File fichero = dameFichero(nombreFichero);

        int option = -1;

        Alumnos alumnos = cargaDatos(fichero);

        do{
            option = muestraMenu(teclado);
            
            switch(option){
                //salir
                case -1: 
                    break;

                //añadir alumno    
                case 1: 
                    alumnos.addAlumno(teclado);
                    break;

                //añadir nota    
                case 2:
                    alumnos.addNota(teclado);
                    break;

                //mostrar informe    
                case 3:
                    alumnos.muestraInforme(teclado);
                    break;

                //guardar datos    
                case 4:
                    guardaDatosAlumnos(fichero, alumnos);
                    break;
                    
            }
        }while(option > 0);
        teclado.close();
    }
    //Recupera datos del fichero
    static Alumnos cargaDatos(File fichero) throws FileNotFoundException, IOException{
        
        BufferedReader lector = new BufferedReader(new FileReader(fichero));
        String fila = lector.readLine();
        Alumnos alumnos = new Alumnos();
        while(fila != null){
            String[] columnas = fila.split(DELIMITADOR_COLUMNA);
            String nombreAlumno = columnas[0];
            String[] notasAlumno = columnas[1].split(DELIMITADOR_NOTAS);
            alumnos.creaAlumnoSiNoExiste(nombreAlumno);
            for(String nota: notasAlumno ){
                
                double notaReal = Double.parseDouble(nota);
                alumnos.mapAlumnos
                    .get(nombreAlumno)
                    .add(notaReal);
                
            }
            
        }
        lector.close();
        return alumnos;
    }
    //Guarda datos en el fichero
    static void guardaDatosAlumnos(File fichero, Alumnos alumnos) throws FileNotFoundException, IOException{
        BufferedWriter escritor = new BufferedWriter(new FileWriter(fichero));
        
        for(String nombreAlumno: alumnos.mapAlumnos.keySet()){
            System.out.println("nombre alumno: " + nombreAlumno);
            ArrayList<Double> notasAlumno = alumnos.mapAlumnos.get(nombreAlumno);
            String linea = nombreAlumno + DELIMITADOR_COLUMNA;
            for(int i = 0; i < notasAlumno.size(); i++){
                if(i != 0){
                        linea = linea + DELIMITADOR_NOTAS;
                }
                linea = linea + notasAlumno.get(i);
            }
            escritor.write(linea);
            
        }
        escritor.close();

    }
    //Pide por pantalla el nombre del archivo y devuelve lo escrito.
    static public String dameNomombreFichero(Scanner teclado){
        System.out.print("Escribe nombre del fichero: ");
        String nombre = teclado.nextLine();
        return nombre;
    }

    static public File dameFichero (String nombreFichero) throws IOException{
        //instanciamos fichero y comprobamos si existe
        File fichero = new File(nombreFichero);
        if(!fichero.exists()){
            fichero.createNewFile();
        }
        return fichero;
    }

    static public int muestraMenu(Scanner s){
        System.out.println("-------Menu-------");
        System.out.println("-1. salir");
        System.out.println(" 1. Añadir alumno");
        System.out.println(" 2. Añadir nota");
        System.out.println(" 3. Muestra informe de alumno");
        System.out.println(" 4. Guardar");
        System.out.println("-------Menu-------");
        int option = s.nextInt();
        s.nextLine();
        return option;
    }
}

class Alumnos{
    Map<String, ArrayList<Double>> mapAlumnos = new HashMap<>(); 
     
    public void addAlumno(Scanner s){
        String nombre = this.pideNombreAlumno(s);
        creaAlumnoSiNoExiste(nombre);
    }

    public void addNota(Scanner s){
        String nombre = this.pideNombreAlumno(s);
        creaAlumnoSiNoExiste(nombre);

        Double nota = this.pideNotaAlumno(s);

        this.mapAlumnos.get(nombre).add(nota); 

        System.out.printf("Nota añadida correctamente: %s ---- %f%n", nombre, nota);
        System.out.println(this.mapAlumnos);
    }
    public void creaAlumnoSiNoExiste(String nombre){
        //comprueba si existe el alumno

        if(this.mapAlumnos.get(nombre) == null){
            this.mapAlumnos.put(nombre, new ArrayList<Double>());
        }
    }
    public String pideNombreAlumno(Scanner s){
        System.out.print("Escribe el nombre del alumno  : ");
        String nombre = s.nextLine();
        return nombre;
    }

    public double pideNotaAlumno(Scanner s){
        System.out.print("Escribe la nota del alumno    : ");
        double nota = s.nextDouble();
        s.nextLine();
        return nota;
    }

    public void muestraInforme(Scanner s){
        String nombreAlumno = this.pideNombreAlumno(s);

        creaAlumnoSiNoExiste(nombreAlumno);
        
        ArrayList<Double> arrayListNotas = this.mapAlumnos.get(nombreAlumno); 
        System.out.println(arrayListNotas);
        double mediaNotas = 0.0;
        int numAprovados = 0;
        int numSuspensos = 0;
        double minNota = 0;
        double maxNota = 0;
        double sumaNota = 0; 
        for(double nota: arrayListNotas){
        
            if(sumaNota == 0){
                minNota = nota;
                maxNota = nota;
            }

            sumaNota += nota;

            //comprueba la nota minima y maxima
            if(minNota > nota){
                minNota = nota;
            }
            if(maxNota < nota){
                maxNota = nota;
            }

            if(nota < 5){
                numSuspensos += 1;
            }else{
                numAprovados += 1;
            }

        }
        mediaNotas = sumaNota / (double) arrayListNotas.size();

        System.out.println("---------INFORME DE ALUMNO---------");
        //nombreAlumno
        System.out.printf("Nombre           : %s%n", nombreAlumno);
        
        //numeroNotas
        System.out.printf("numero de notas  : %d%n", arrayListNotas.size());

        //mediaNotas
        System.out.printf("media de notas   : %f%n", mediaNotas);
        
        //numeroAprovadas
        System.out.printf("Aprovados        : %d%n", numAprovados);

        //numeroSuspensas
        System.out.printf("Suspensas        : %d%n", numSuspensos);

        //maxNota
        System.out.printf("Nota maxima      : %f%n", maxNota);

        //minNota
        System.out.printf("Nota minima      : %f%n", minNota);

        System.out.println("----------FIN DEL INFORME----------");
        
    }

}