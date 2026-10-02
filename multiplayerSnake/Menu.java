import java.util.Scanner;

public class Menu{
    Game game = new Game();
    public Menu(){
    }
    public void startMenu() throws InterruptedException{
        int option;
        while((option = getOption()) != 9){
            switch(option){
                //Jugar
                case 1: 
                    game.startGame();
                    break; 
                default: 
                    System.out.printf("Opcion no valida: %d%n", option);
                    break;
            }
        }
    }
    public int getOption(){
        this.showMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escribe opcion: ");

        String preOption = scanner.nextLine();
        while (true) {
            try {
                return Integer.parseInt(preOption);
            } catch (NumberFormatException e) {
                System.out.print("Escribe opcion: ");
                preOption = scanner.nextLine();
            }
        }
    }
    public void showMenu(){
        System.out.println("============Menu=================");
        System.out.println("1. Jugar");
        System.out.println("9. Salir");
        System.out.println("=============Fin=================");
    }
}