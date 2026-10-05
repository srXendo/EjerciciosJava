import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Client {
    int width = 80;
    int height = 20;
    String ip;
    int port;
    IpPort ipPort;
    int idxPlayer;
    int xPosPlayer;
    int yPosPlayer;
    Game game = new Game();

    DatagramSocket socket;
    private static final int DISCOVERY_PORT = 8080;
    public Client(){
        System.out.println("");

    }
    public ArrayList<String> search() throws Exception {
        ArrayList<String> gamesArrayList = new ArrayList<>();
        try (DatagramSocket socket = new DatagramSocket()) {

            socket.setBroadcast(true);
            socket.setSoTimeout(2000);

            byte[] data = "GAME_DISCOVER".getBytes(StandardCharsets.UTF_8);

            DatagramPacket packet = new DatagramPacket(
                    data,
                    data.length,
                    InetAddress.getByName("255.255.255.255"),
                    DISCOVERY_PORT
            );

            socket.send(packet);

            byte[] buffer = new byte[1024];
            
            while (true) {
                DatagramPacket response =
                        new DatagramPacket(buffer, buffer.length);

                try {
                    socket.receive(response);
                } catch (SocketTimeoutException e) {
                    break;
                }

                String mensaje = new String(
                        response.getData(),
                        response.getOffset(),
                        response.getLength(),
                        StandardCharsets.UTF_8
                );
                gamesArrayList.add(response.getAddress().getHostAddress() + ":" + response.getPort() + ":" + mensaje);
            }
        }
        return gamesArrayList;
    }
    public void connectGame(String ipPort) throws SocketException, UnknownHostException, IOException{
        String ip = ipPort.split(":")[0];
        int port = Integer.parseInt(ipPort.split(":")[1]);
               
        DatagramSocket socket = new DatagramSocket();

        socket.setBroadcast(true);
        socket.setSoTimeout(2000);

        byte[] data = "GAME_CONNECT".getBytes(StandardCharsets.UTF_8);

        DatagramPacket packet = new DatagramPacket(
                data,
                data.length,
                InetAddress.getByName(ip),
                port
        );

        socket.send(packet);

        byte[] buffer = new byte[1024];
        
        while (true) {
            DatagramPacket response =
                    new DatagramPacket(buffer, buffer.length);

            try {
                socket.receive(response);
            } catch (SocketTimeoutException e) {
                break;
            }

            String mensaje = new String(
                    response.getData(),
                    response.getOffset(),
                    response.getLength(),
                    StandardCharsets.UTF_8
            );
            this.game.board.createGrid();
            this.socket = socket;
            String[] arrPlayerDataRows = mensaje.split(";;");
            for(String rowPlayer : arrPlayerDataRows){
                String[] info = rowPlayer.split("//");
                this.addMultiplayerGame(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]), info[3].charAt(0));
            }
            this.ipPort = new IpPort(ip, port);
            Consumer<String> handlerMessagge = (tickMsg) -> {
                this.handleMessage(tickMsg);

            };
            new Thread(() -> listen(handlerMessagge)).start();
            return;
        }
        

    }
    private void handleMessage (String tickMsg){
        System.out.println("[handleMessage] tickMsg: " + tickMsg );
        
    }
    private void listen(Consumer<String> onMessage) {

        byte[] buffer = new byte[1024];

        while (!this.socket.isClosed()) {

            DatagramPacket packet =
                    new DatagramPacket(buffer, buffer.length);

            try {
                this.socket.receive(packet);

                String mensaje = new String(
                        packet.getData(),
                        packet.getOffset(),
                        packet.getLength(),
                        StandardCharsets.UTF_8
                );

                onMessage.accept(mensaje);

            } catch (IOException e) {
                break;
            }
        }
    }
    public void startMultiplayerGame() throws InterruptedException{
        Consumer<Character> notifyServer = (Character keyPressed) -> {
            this.game.board.inputPress(keyPressed);
            byte[] data = ("KEYPRESSED:"+keyPressed).getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet;
            try {
                packet = new DatagramPacket(
                        data,
                        data.length,
                        InetAddress.getByName(this.ipPort.ip),
                        this.ipPort.port
                );
                this.socket.send(packet);
            } catch (IOException ex) {
                System.getLogger(Client.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                System.out.println(ex);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex1) {
                    System.getLogger(Client.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex1);
                }
            }
        };
        this.game.iniciarControles(notifyServer);
        //this.game.createBoard();
        
        this.game.drawBoard();    
    }

    public void addMultiplayerGame(int idx, int xPosPlayer, int yPosPlayer, char directionPlayer){
        this.game.board.addPlayer(idx, xPosPlayer, yPosPlayer, directionPlayer);
        this.game.board.movePlayer(idx, xPosPlayer, yPosPlayer);
    }
}
class IpPort{
    String ip;
    int port;
    public IpPort(String ip, int port){
        this.ip = ip;
        this.port = port;
    }
}