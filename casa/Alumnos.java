import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Alumnos {
    ArrayList<String> arrListAlumnos = new ArrayList<>();
    Boolean sortAsc = false;
    public void addAlumno(Scanner s){
        
        String nombre = this.pideNombreAlumno(s);
        this.arrListAlumnos.add(nombre);
    }
    public void muestraAlumnos(){
        System.out.println("arrListAlumnos: " + this.arrListAlumnos);
    }
    public void buscaAlumno(Scanner s){
        String nombreBuscar = this.pideNombreAlumno(s);
        System.out.println("Buscando...");
        String resultado = this.dameAlumnoSiExiste(nombreBuscar);
        if(resultado != null){
            System.out.println("Alumno encontrado");
        }else{
            System.out.println("No se ha encontrado alumno");
        }
    }
    public void eliminaAlumno(Scanner s){
        String nombreEliminar = this.pideNombreAlumno(s);
        System.out.println("Buscando...");
        String resultado = this.dameAlumnoSiExiste(nombreEliminar);
        if(resultado != null){
            this.arrListAlumnos.remove(nombreEliminar);
            System.out.println("Alumno Eliminado");
        }else{
            System.out.println("No se ha encontrado alumno");
        }
    }
    public String dameAlumnoSiExiste (String nombreAlumno){
        String alumnoEncontrado = null; 
        for(String alumno : this.arrListAlumnos){
            if(alumno.equals(nombreAlumno)){
                alumnoEncontrado = alumno;
            }
        }
        return alumnoEncontrado;
    } 
    public void muestraNumeroAlumnos(){
        System.out.println("Numero de alumnos: " + this.arrListAlumnos.size());
    }
    public void ordenaAlumnos(){
        if(this.sortAsc){
            Collections.sort(this.arrListAlumnos, Comparator.reverseOrder());
            System.out.println("Alumnos ordenados descendente: ");
            
        }else{
            Collections.sort(this.arrListAlumnos);
            System.out.println("Alumnos ordenados ascendente: ");
        }
        this.sortAsc = !this.sortAsc;
    }
    public String pideNombreAlumno(Scanner s){
        System.out.print("Escribe el nombre del alumno: ");
        String nombre = s.nextLine();
        return nombre;
    }
}
