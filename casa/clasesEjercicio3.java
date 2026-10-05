
import java.util.ArrayList;

public class clasesEjercicio3 {
    static public void main(String args[]){
        
        ArrayList <Vehiculo> vehiculos = new ArrayList<>();
        
        vehiculos.add(new Vehiculo("1234ABC", "Toyota", "Corolla", "Blanco", 2020));
        vehiculos.add(new Vehiculo("5678DEF", "BMW", "Serie 3", "Negro", 2022));
        vehiculos.add(new Vehiculo("9012GHI", "Seat", "León", "Rojo", 2019));
        vehiculos.add(new Vehiculo("3456JKL", "Volkswagen", "Golf", "Azul", 2021));
        vehiculos.add(new Vehiculo("7890MNP", "Ford", "Focus", "Gris", 2018));

        for(Vehiculo car : vehiculos){
            car.muestraVehiculo();
        }
    }
}
class Vehiculo{
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private int year;
    public Vehiculo(
        String matricula,
        String marca,
        String modelo,
        String color,
        int year
    ){
        this.setMatricula(matricula);
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setColor(color);
        this.setYear(year);
    }
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setYear(int year){
        this.year = year;
    }
    public String getMatricula(){
        return this.matricula;
    }
    public String getMarca(){
        return this.marca;
    }
    public String getModelo(){
        return this.modelo;
    }
    public String getColor(){
        return this.color;
    }
    public int getyear(){
        return this.year;
    }
    public void muestraVehiculo(){
        System.out.println("========Vehiculo Informacion========");
        System.out.println("Matrícula: " + this.getMatricula());
        System.out.println("Marca: " + this.getMarca());
        System.out.println("Modelo: " + this.getModelo());
        System.out.println("Color: " + this.getColor());
        System.out.println("Año: " + this.getyear());
    }
}
