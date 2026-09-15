import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class hashSetEjercicio2 {
    static public void main(String[] args){ 

        System.out.print("Escribe el numero de alumnos que quieres añadir: ");
        
        Scanner teclado = new Scanner(System.in);
        
        int numeroAlumnosNuevos = teclado.nextInt();
        teclado.nextLine();

        Map<String, Double> alumnos = new HashMap<>(); 
        for(int i = 0; i < numeroAlumnosNuevos; i++){
            String nuevoNombre = dameNombre(teclado);
            Double nuevaNota = dameNota(teclado);
            alumnos.put(nuevoNombre, nuevaNota);
        }

        for(String alumno: alumnos.keySet()){
            System.out.println("Alumno  : " + alumno);
            System.out.println("Nota    : " + alumnos.get(alumno));
            System.out.println("---------------------------------");
        }
        teclado.close();
        
    }
    static public String dameNombre(Scanner s){
        System.out.print("Escribe el nombre del alumno: ");
        String nombre = s.nextLine();  
        return nombre;
    }
    static public Double dameNota(Scanner s){
        System.out.print("Escribe la nota del alumno: ");
        Double nota = s.nextDouble();
        s.nextLine();
        return nota;
    }
}
