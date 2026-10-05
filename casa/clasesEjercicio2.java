public class clasesEjercicio2 {
    static public void main(String[] args){
        Coche coche = new Coche("Nissan", "r32rb", "m-9999-bc");
        coche.muestraCoche();
        
    }
}
class Coche{
    private String marca;
    private String modelo;
    private String matricula;
    public Coche(String marca, String modelo, String matricula){
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setMatricula(matricula);
    }
    public void muestraCoche(){
        System.out.println("marca: "+ this.getMarca());
        System.out.println("modelo: "+ this.getModelo());
        System.out.println("matricula: "+ this.getMatricula());
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    public String getMarca(){
        return this.marca;
    }
    public String getModelo(){
        return this.modelo;
    }
    public String getMatricula(){
        return this.matricula;
    }
}