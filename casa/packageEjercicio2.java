import java.io.File;

public class packageEjercicio2 {
    static public void main(String[] args){
        File carpetaBuscar = new File("./../../");
        String[] arrCarpetas = carpetaBuscar.list();
        for(String carpeta: arrCarpetas){
            System.out.println("nombre carpeta: " + carpeta);
        }

    }
}
