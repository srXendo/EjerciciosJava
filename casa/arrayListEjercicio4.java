import java.util.ArrayList;

public class arrayListEjercicio4{
    static public void main(String[] args){
        ArrayList<String> arrListNombres = new ArrayList<>();
        arrListNombres.add("Ana");
        arrListNombres.add("Pedro");
        arrListNombres.add("Lucía");
        arrListNombres.add("Carlos");
        arrListNombres.add("Marta");
        System.out.println("La lista: " + arrListNombres);
        System.out.println("Numero de nombres: " + arrListNombres.size());
        arrListNombres.remove("Pedro");
        System.out.println("La lista pero sin Pedro: " + arrListNombres);
        System.out.println("Numero de nombres sin Pedro: " + arrListNombres.size());
    }
}