public class stringEjercicio1{
    static public void main(String[] args){
        String frase = "Hola esto es un texto de prueba para ver si la documentación esta bien puesta es";
        int posicion = frase.indexOf("es");
        int posicion_actual = 0;
        int contador = 0;
        while(posicion != -1){
            posicion_actual = posicion;
            contador = contador + 1;
            System.out.println("Estamos en la posicion  : " + (posicion_actual - 1));
            
            posicion = frase.indexOf("es", posicion_actual + "es".length());

        }
    }
}