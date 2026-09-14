import java.util.ArrayList;

public class arrayListEjercicio5 {
    static public void main(String[] args){
        ArrayList<Integer> arrListNumeros = new ArrayList<>();
        arrListNumeros.add(10);
        arrListNumeros.add(25);
        arrListNumeros.add(8);
        arrListNumeros.add(14);
        arrListNumeros.add(30);
        arrListNumeros.add(7);


        int sumaTotal = 0;
        for(int i = 0; i < arrListNumeros.size();  i++){
            sumaTotal += arrListNumeros.get(i);
        }
        System.out.println("Array list de numeros               : " + arrListNumeros);
        System.out.println("Tamaño del array list de numeros    : " + arrListNumeros.size());
        System.out.println("Primer elemento                     : " + arrListNumeros.get(0));
        System.out.println("Ultimo elemento                     : " + arrListNumeros.get(arrListNumeros.size() - 1));
        System.out.println("Suma total de numeros               : " + sumaTotal);
        System.out.println("Media                               : " + sumaTotal / arrListNumeros.size());

    }
}
