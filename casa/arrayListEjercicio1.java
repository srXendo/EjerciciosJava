public class arrayListEjercicio1{
    static public void main(String[] args){
        Double[] arrNotas = new Double[5];
        arrNotas[0] = 7.5;
        arrNotas[1] = 6.0;
        arrNotas[2] = 8.5;
        arrNotas[3] = 4.5;
        arrNotas[4] = 9.0;
        Double sumaTotal = 0.0;
        double mayorNota = arrNotas[0];
        double menorNota = arrNotas[0];
        for(int i = 0; i < arrNotas.length; i++){
            Double nota = arrNotas[i];
            sumaTotal += nota;
            System.out.print("Tu nota es: " + nota + " ");
            
            if(nota < 5){
                System.out.println("Suspenso");
            }else{
                System.out.println("Aprobado");
            }

            if(mayorNota < nota){
                mayorNota = nota;
            }
            if(menorNota > nota){
                menorNota = nota;
            }
        }
        System.out.println("La suma total: " + sumaTotal);
        System.out.println("La media: " + sumaTotal / arrNotas.length);
        System.out.println("La nota mas alta: " + mayorNota);
        System.out.println("La nota mas baja: " + menorNota);
        
        
    }
}