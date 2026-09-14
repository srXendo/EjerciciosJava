import java.util.Scanner;

public class dowhile_scan {
    public static void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        String password;
        do{
            System.out.println("Introduce la contraseña: ");
            password = teclado.nextLine();
        }while(!password.equals("java"));
        System.out.println("Acceso permitido.");
        teclado.close();

    }
}
