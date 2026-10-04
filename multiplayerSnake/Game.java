
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;
import javax.swing.JFrame;
public class Game{
    int width = 80;
    int height = 20;
    Board board;
    private char direccion = 'd'; // Empieza moviéndose a la derecha ('d')
    public Game(){
        this.board = new Board(width, height);
        
    }

    public void startGame() throws InterruptedException{
        this.iniciarControles();
        this.board.createGrid();  
        this.board.addPlayer(0);
        this.board.addEat();
        this.drawBoard();
    }

    public void drawBoard() throws InterruptedException{
        Boolean gameEnd = false;
        while(!gameEnd){
            this.clearConsole();
            gameEnd = this.board.tick();

            StringBuilder frame = new StringBuilder();

            frame.append("------------------------------------------\n");
            for(int i = 0; i < this.height; i++){
                for(int x = 0; x < this.width; x++){
                    frame.append(this.board.getStringFromXY(i, x));
                }
                frame.append("\n");
            }
            frame.append("------------------------------------------");
            System.out.print(frame.toString());
            Thread.sleep(1000/15); // 200 milisegundos
        }
        if(gameEnd){
            System.out.println("\n\n\n ----------Fin de partida----------\n\n\n");
            System.out.println("Puntuacion: " + this.board.playersMap.get(0).point);
            System.out.println("\n\n\n ----------Fin de partida----------\n\n\n");
        }
    }
    public static void clearConsole() {
        try {

            String os = System.getProperty("os.name").toLowerCase();
 
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO()
                        .start()
                        .waitFor();
            } else {
                new ProcessBuilder("clear")
                        .inheritIO()
                        .start()
                        .waitFor();
            }
 
        } catch (Exception e) {
            // 3. Fallback: print blank lines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    public void iniciarControles() {
        // 2. Creamos una ventana minúscula solo para capturar el teclado
        JFrame ventanaTeclado = new JFrame("Controles");
        ventanaTeclado.setSize(100, 100);
        ventanaTeclado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaTeclado.setVisible(true); // Déjala visible y haz clic en ella para jugar
        Consumer<Character> tConsumer = (Character keyPressed) -> {
            this.board.inputPress(keyPressed);
        };
        ventanaTeclado.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char teclaPulsada = Character.toLowerCase(e.getKeyChar());
                
                tConsumer.accept(teclaPulsada);
            }
        });
    }
    public void iniciarControles(Consumer<Character> tConsumer) {
        // 2. Creamos una ventana minúscula solo para capturar el teclado
        JFrame ventanaTeclado = new JFrame("Controles");
        ventanaTeclado.setSize(100, 100);
        ventanaTeclado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaTeclado.setVisible(true); // Déjala visible y haz clic en ella para jugar
        ventanaTeclado.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char teclaPulsada = Character.toLowerCase(e.getKeyChar());
                
                tConsumer.accept(teclaPulsada);
            }
        });
    }
}