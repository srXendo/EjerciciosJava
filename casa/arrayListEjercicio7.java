import java.util.Scanner;

public class arrayListEjercicio7 {
    static public void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        int option = -1;
        Alumnos alumnosClass = new Alumnos();
        do{
            option = muestraMenu(teclado);
            switch(option){
                case -1: 
                    break;
                case 1: 
                    alumnosClass.addAlumno(teclado);
                    break;
                case 2:
                    alumnosClass.muestraAlumnos();
                    break;
                case 3: 
                    alumnosClass.buscaAlumno(teclado);
                    break;
                case 4: 
                    alumnosClass.eliminaAlumno(teclado);
                    break;
                case 5: 
                    alumnosClass.muestraNumeroAlumnos();
                    break;
                case 6: 
                    alumnosClass.ordenaAlumnos();
                    break;
            }
        }while(option > 0);
        teclado.close();
    }
    static public int muestraMenu(Scanner s){
        System.out.println("-------Menu-------");
        System.out.println("-1. salir");
        System.out.println(" 1. Añadir alumno");
        System.out.println(" 2. Mostrar alumnos");
        System.out.println(" 3. Buscar alumno");
        System.out.println(" 4. Eliminar alumno");
        System.out.println(" 5. Mostrar numero de alumnos");
        System.out.println(" 6. Ordenar alumnos");
        System.out.println("-------Menu-------");
        int option = s.nextInt();
        s.nextLine();
        return option;
    }
}
