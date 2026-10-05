
import java.util.ArrayList;

public class clasesEjercicio4 {

    static public void main(String[] args){
        Padre dad = new Padre("Alfredo pereira", 54);
        Hijo hijo = new Hijo("Ignacio perreira", 5);
        Perro perro = new Perro("Rodolfo ", 3);
        dad.addHijo(hijo);
        dad.addHijo(perro); 
        dad.comunicar();
        hijo.comunicar();
        perro.comunicar();
    }
}

class Padre extends SerVivo{
    ArrayList<SerVivo> hijos = new ArrayList<>();
    String trabajo;
    public Padre(String nombre, int edad){
        super(nombre, edad);
    }
    public void addHijo(SerVivo hijo){
        this.hijos.add(hijo);
    }
    @Override
    public void comunicar(){
        System.out.println("Soy un padre que tiene " + this.hijos.size() + " Hijos");
        System.out.println("Mis hijos se llaman: ");
        for(SerVivo hijo: this.hijos){
            System.out.println(hijo.getNombre());
        }
    }
}
class Hijo extends SerVivo{
    String curso;
    public Hijo(String nombre, int edad){
        super(nombre, edad);
        this.curso = "Parbulitos";
    }
    @Override
    public void comunicar(){
        System.out.println("agugutata!!");
    }
}
class Perro extends SerVivo{
    public Perro(String nombre, int edad){
        super(nombre, edad);
    }
    @Override
    public void comunicar(){
        System.out.println("guaw!!");
    }
}
abstract class SerVivo {
    String nombre;
    int edad;
    SerVivo(String nombre, int edad){
        this.setEdad(edad);
        this.setNombre(nombre);
    }
    public void comunicar(){
        System.out.println("<--->");
    }
    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}


