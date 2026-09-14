import java.util.Scanner;

public class arrayEjercicio2 {
    static public void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        String[] arrNombres = new String[6];
        arrNombres[0] = "Ana";
        arrNombres[1] = "Pedro";
        arrNombres[2] = "Lucia";
        arrNombres[3] = "Carlos";
        arrNombres[4] = "Marta";
        arrNombres[5] = "Javier";

        System.out.print("Escribe el nombre a buscar: ");
        String nombreBusqueda = teclado.nextLine();
        Boolean resultado = false;
        for(int i = 0; i < arrNombres.length; i++){
            if(arrNombres[i].equals(nombreBusqueda)){
                resultado = true;
            }
        }
        if(resultado){
            System.out.println("Nombre encontrado");
        }else{
            System.out.println("Nombre no existe");
        }
    }
}
