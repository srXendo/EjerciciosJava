import java.util.ArrayList;
import java.util.Scanner;

public class Menu{
    Game game = new Game();
    public Menu(){
    }
    public void startMenu() throws InterruptedException, Exception{
        Scanner scanner = new Scanner(System.in);
        int option;
        this.showMenu();
        while((option = getOption(scanner)) != 9){
            
            switch(option){
                //Jugar
                case 1: 
                    game.startGame();
                    break;
                case 2: 
                    String namePlayer = this.getNamePlayer(scanner);

                    this.startMultiplayerMenu(scanner, namePlayer);
                    break;    
                default: 
                    System.out.printf("Opcion no valida: %d%n", option);
                    break;
            }
            this.showMenu();
        }
        scanner.close();
    }
    public String getNamePlayer(Scanner scanner){
        
        System.out.print("Escribe tu nombre: ");

        String preOption = scanner.nextLine();
        return preOption;


    }
    
    public String getNameMultiplayerGame(Scanner scanner){
        
        System.out.print("Escribe nombre de partida: ");

        String preOption = scanner.nextLine();
        return preOption;


    }
    public int getOption(Scanner scanner){
        
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
        System.out.println("1. SingelPlayer");
        System.out.println("2. Multiplayer(Lan)");
        System.out.println("9. Salir");
        System.out.println("=============Fin=================");
    }
    public void showMultiplayerMenu(){
        System.out.println("============Menu=================");
        System.out.println("1. Buscar salas");
        System.out.println("2. Crear sala");
        System.out.println("9. Salir");
        System.out.println("=============Fin=================");
    }
    public void startMultiplayerMenu(Scanner scanner, String namePlayer) throws InterruptedException, Exception{
        
        int option;
        showMultiplayerMenu();
        while((option = getOption(scanner)) != 9){
            
            switch(option){
                
                //Buscar salas
                case 1 -> {
                    
                    Client client = new Client();
                    ArrayList<String> list = client.search();
                    int idxConnect = this.showListGameMenu(scanner, list);
                    client.connectGame(list.get(idxConnect));
                    client.startMultiplayerGame();
                    
                }

                //Crear sala
                case 2 -> {
                    String nameMultiplayerGame = this.getNameMultiplayerGame(scanner);
                    MultiplayerGame mpGame = new MultiplayerGame(nameMultiplayerGame, namePlayer);
                    
                }

                default -> System.out.printf("Opcion multiplayer no valida: %d%n", option);
            }
            showMultiplayerMenu();
        }
    }
    public int showListGameMenu(Scanner scanner, ArrayList<String> list){
        for(int i = 0; i < list.size(); i++){
            System.out.printf("%s. %s%n", i + 1, list.get(i));
        }
        while(true){
            System.out.print("Escribe numero de servidor: ");
            try{
                int idxGame = scanner.nextInt() - 1;
                scanner.nextLine();
                if(list.get(idxGame) != null){
                    return idxGame;
                }
                
            }catch(Exception e){
                scanner.nextLine();
            }
        }
    }

}