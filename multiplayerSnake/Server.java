import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Random;

public class Server {
    String nameServer;
    String namePlayerMain;

    int width = 80;
    int height = 20;
    HashMap<String, HashMap<Integer, Player>> playersMap = new HashMap<>();
    Board board = new Board(width, height);
    public Server(String nameServer, String namePlayerMain){
        this.nameServer = nameServer;
        this.namePlayerMain = namePlayerMain;
        this.board.createGrid();
        
    }
    public void listening(int port) {
        try (DatagramSocket socket = new DatagramSocket(port)) {

            byte[] buffer = new byte[1024];

            System.out.println("Servidor UDP escuchando en puerto " + port);

            while (true) {
                DatagramPacket packet =
                        new DatagramPacket(buffer, buffer.length);

                socket.receive(packet); // Bloquea hasta recibir un paquete

                String mensaje = new String(
                        packet.getData(),
                        packet.getOffset(),
                        packet.getLength(),
                        StandardCharsets.UTF_8
                );

                System.out.println(
                        "Recibido de " +
                        packet.getAddress().getHostAddress() +
                        ":" + packet.getPort() +
                        " -> " + mensaje
                );
                String strResponse = this.handleMessage(mensaje, packet.getAddress().getHostAddress(), packet.getPort());
                byte[] response = strResponse.getBytes(StandardCharsets.UTF_8);
                DatagramPacket dataGramResponse = new DatagramPacket(
                        response,
                        response.length,
                        packet.getAddress(), // IP del cliente
                        packet.getPort()     // puerto del cliente
                );
                socket.send(dataGramResponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String handleMessage(String msg, String ip, int port){
        String response = "";
        String[] arrParamsMsg = msg.split(":");
        switch(arrParamsMsg[0]){
            case "GAME_DISCOVER" -> {
                response = this.nameServer + ":" + this.namePlayerMain ;
            }
            case "GAME_CONNECT" -> {
                int idxPlayer;
                if(this.playersMap.get(ip) == null){
                    this.playersMap.put(ip, new HashMap<>());
                }
                int xPos = 0;
                int yPos = 0;
                if (this.playersMap.get(ip).get(port) == null) {
                    idxPlayer = this.playersMap.keySet().size() - 1;

                    Boolean centinele = false;
                    Random random = new Random();
                    while(!centinele){
                        
                        xPos = random.nextInt(this.height - 1); 
                        yPos = random.nextInt(this.width - 1);
                        try{
                            if(this.board.squaresMap.get(xPos).get(yPos) == EnumSquareType.CLEAN){
                                centinele = true; 
                            }
                        }catch(Exception e){
                            System.out.print("centinele");
                        }
                    }
                    this.playersMap.get(ip).put(port, new Player(idxPlayer, xPos, yPos));
                }

                response = this.playersMap.get(ip).get(port).idPlayer + "//" + xPos + "//" + yPos;
            }
            case "KEYPRESSED" -> {

            }
            default -> {
                System.out.println("[handleMessage] Paquete no reconocido: " + msg);
            }
        }
        return response;
    }
}