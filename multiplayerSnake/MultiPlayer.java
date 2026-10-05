import java.net.DatagramSocket;
import java.net.InetAddress;

public class MultiPlayer extends Player {
    DatagramSocket socket;
    InetAddress ip;
    int port;
    public MultiPlayer(int idPlayer, int xPos, int yPos, char direction, DatagramSocket socket, InetAddress ip, int port){
        super(idPlayer, xPos, yPos, direction);
        this.socket = socket;
        this.ip = ip;
        this.port = port;
    }
}
