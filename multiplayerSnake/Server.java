import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Server {
    String nameServer;
    String namePlayerMain;

    int width = 80;
    int height = 20;
    HashMap<String, HashMap<Integer, MultiPlayer>> playersMap = new HashMap<>();
    Board board = new Board(width, height);
    public Server(String nameServer, String namePlayerMain){
        this.nameServer = nameServer;
        this.namePlayerMain = namePlayerMain;
        this.board.createGrid();
        
        
    }
    public void listening(int port) {
        this.startTickThread();

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
                String strResponse = this.handleMessage(mensaje, packet.getAddress(), packet.getPort(), socket);
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
        
    public void startTickThread() {
        Thread tickThread = new Thread(() -> {

            while (true) {
                try {
                    this.tick();

                    Thread.sleep(1000/15); // 20 ticks por segundo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }

        });

        tickThread.start();
    }
    private void tick() throws IOException {

        List <MultiPlayer> players = this.getArrPlayers();

        players.stream().forEach(player->{
            String row = "";
            row +=player.idPlayer;
            row +="//";
            row +=player.xPos;
            row +="//";
            row +=player.yPos;
            row +="//";
            row +=player.direction;
            byte[] tickMsg = row.getBytes(StandardCharsets.UTF_8);

            players.stream().forEach(playerSend->{
                try {
                    DatagramPacket dataGramResponse = new DatagramPacket(
                        tickMsg,
                        tickMsg.length,
                        playerSend.ip,
                        playerSend.port
                    );
                    playerSend.socket.send(dataGramResponse);
                } catch (IOException ex) {
                    System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            });

        });
    }
    public String handleMessage(String msg, InetAddress ipInetAddress, int port, DatagramSocket socket){
        String response = "";
        String ip = ipInetAddress.toString();
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
                char direction;
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
                    this.playersMap.get(ip).put(port, new MultiPlayer(idxPlayer, xPos, yPos, 'd', socket, ipInetAddress, port));
                }
                direction = this.playersMap.get(ip).get(port).direction;
                response = this.playersMap.get(ip).get(port).idPlayer + "//" + xPos + "//" + yPos + "//" + direction;
                
                for(String ipPlayer: this.playersMap.keySet()){
   
                    for(int portPlayer: this.playersMap.get(ipPlayer).keySet()){
                        if(ipPlayer.equals(ip) && portPlayer == port ){
                            continue;
                        }
                        MultiPlayer player = this.playersMap.get(ipPlayer).get(portPlayer);
                        response += ";;" + player.idPlayer + "//" + player.xPos + "//" + player.yPos + "//" + direction;
                    }
                     

                }
            }
            case "KEYPRESSED" -> {
                this.playersMap.get(ip).get(port).direction = arrParamsMsg[1].charAt(0);
            }
            default -> {
                System.out.println("[ServerHandleMessage] Paquete no reconocido: " + msg);
            }
        }
        return response;
    }
    public ArrayList<MultiPlayer> getArrPlayers(){
        ArrayList<MultiPlayer> players = new ArrayList<>(); 
        for(String ipPlayer: this.playersMap.keySet()){

            for(int portPlayer: this.playersMap.get(ipPlayer).keySet()){
                MultiPlayer player = this.playersMap.get(ipPlayer).get(portPlayer);
                players.add(player);
            }
                

        }
        return players;
    }
}