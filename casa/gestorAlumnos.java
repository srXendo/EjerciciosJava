
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class gestorAlumnos{
    static public void main(String[] args) throws IOException{
        ArrayList<Alumno> alumnos = cargaDatos();
        muestraMenu(alumnos);
    }
    static public ArrayList<Alumno> cargaDatos() throws IOException{
        //comprueba si existe el fichero sino lo crea
        File fichero = new File("gestorAlumnos.csv");
        if(!fichero.exists()){
            fichero.createNewFile();
        }
        
        //carga el fichero contenido del fichero en memoria
        BufferedReader lector = new BufferedReader(new FileReader(fichero));
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String fila = lector.readLine();
       
        while(fila != null){
            if(!fila.isBlank()){
                String[] arrayFilas = fila.split(";");
                Alumno al = new Alumno();
                al.nombreApellido = arrayFilas[0];
                al.edad = Integer.parseInt(arrayFilas[1]);
                al.ciudad = arrayFilas[2];
                al.ciclo = arrayFilas[3].strip().toUpperCase();
                alumnos.add(al);
            }
            fila = lector.readLine();
        }
        return alumnos;
    }
    static public void guardaDatos(ArrayList<Alumno> alumnos) throws IOException{
        BufferedWriter escritor = new BufferedWriter(new FileWriter(new File("gestorAlumnos.csv")));

        for(Alumno al: alumnos){
            String linea = "";

            linea += al.nombreApellido +  ";";
            linea += al.edad + ";";
            linea += al.ciudad + ";";
            linea += al.ciclo + ";\n";
            escritor.write(linea);
        }
        escritor.close();
    }
    static public void muestraMenu(ArrayList<Alumno> alumnos) throws IOException{
        Scanner teclado = new Scanner(System.in);
        int option = -1;

        do{
            option = pintaMenu(teclado);
            switch(option){
                case 9: 
                    break;
                case 1: 
                    Alumno al = dameAlumno(teclado);
                    alumnos.add(al);
                    guardaDatos(alumnos);
                    break;
                case 2:
                    muestraEstadistica(alumnos);
                    break;
            }
        }while(option != 9);
        teclado.close();
    }
    static public void muestraEstadistica(ArrayList<Alumno> alumnos){
        for(Alumno al: alumnos){
            System.out.println("---------INFORME DE ALUMNO---------");

            System.out.printf("Nombre           : %s%n", al.nombreApellido);

            System.out.printf("Primera letra    : %s%n", al.nombreApellido.charAt(0));
            System.out.printf("Ultima letra     : %s%n", al.nombreApellido.charAt(al.nombreApellido.length() - 1));

            System.out.printf("Edad             : %d%n", al.edad);

            System.out.printf("Ciudad           : %s%n", al.ciudad);

            System.out.printf("Ciclo            : %s%n", al.ciclo);

            if("DAM".equals(al.ciclo)){
                System.out.println("El alma del alumno pertenece a DAM");
            }


            System.out.println("----------FIN DEL INFORME----------");
        }
        
    }
    static public Alumno dameAlumno(Scanner s){
        Alumno al = new Alumno();
        
        System.out.print("Escribe el nombre y apellido del alumno   : ");
        al.nombreApellido = s.nextLine().strip().toUpperCase();

        System.out.print("Escribe edad del alumno                   : ");
        String edadText = s.nextLine();
        

        System.out.print("Escribe ciudad del alumno                 : ");
        al.ciudad = s.nextLine().strip();
        
        System.out.print("Escribe Ciclo dam o dao                   :");
        al.ciclo = s.nextLine().strip().toUpperCase();
        
        if(al.nombreApellido.isBlank() || al.ciudad.isBlank() || !edadText.matches("\\d+") || al.ciudad.isBlank() || al.ciclo.isBlank()){
            System.out.println("Alguno de los campos del alumno estan mal. Intentelo de nuevo.");
            al = dameAlumno(s);
        }else{
            al.edad = Integer.parseInt(edadText);
        }
        
        return al;
    }
    static public int pintaMenu(Scanner s){
        System.out.println("-------Menu-------");
        System.out.println(" 9. salir");
        System.out.println(" 1. Añadir alumno");
        System.out.println(" 2. Mostrar alumnos");
        System.out.println("-------Menu-------");
        int option = s.nextInt();
        s.nextLine();
        return option;
    }
}
class Alumno{
    String nombreApellido;
    int edad;
    String ciudad;
    String ciclo;
}