
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
public class stringEjercicio3{
    static public void main(String[] args) throws NoSuchAlgorithmException{
        Scanner teclado = new Scanner(System.in);
        String nombreUsuario = null;
        String passwordUsuario = null;
        do{
            System.out.print("Escribe nombre de usuario: ");
            String nombreUsuarioTemporal = teclado.nextLine();
            if(
                !nombreUsuarioTemporal.isEmpty() &&
                nombreUsuarioTemporal.indexOf(' ') == -1 &&
                nombreUsuarioTemporal.length() >= 5 &&
                nombreUsuarioTemporal.length() <= 16 &&
                Character.isLetter(nombreUsuarioTemporal.charAt(0)) &&
                !nombreUsuarioTemporal.contains("@")
            ){
                nombreUsuario = nombreUsuarioTemporal;
            }else{
                System.out.println("Nombre de usuario incorrecto"); 
            }

        }while(nombreUsuario == null);

        do{
            System.out.print("Escribe contraseña del usuario: ");
            String passwordUsuarioTemporal = teclado.nextLine();
            if(
                !passwordUsuarioTemporal.isEmpty() &&
                passwordUsuarioTemporal.indexOf(' ') == -1 &&
                passwordUsuarioTemporal.length() >= 5 &&
                !nombreUsuario.contains(passwordUsuarioTemporal)
                
            ){
                passwordUsuario = passwordUsuarioTemporal;
            }else{
                System.out.println("Password de usuario no valido"); 
            }

        }while(passwordUsuario == null);
        System.out.printf("Nombre de usuario: %s%n", nombreUsuario);
        System.out.printf("Contraseña del usuario: %s%n", passwordUsuario);


        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(passwordUsuario.getBytes());
        byte[] digest = md.digest();
        BigInteger no = new BigInteger(1, digest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        System.out.printf("Contraseña en md5: %s%n", hashtext);
        teclado.close();
    }
}