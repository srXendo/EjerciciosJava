
import java.net.DatagramSocket;
import java.util.ArrayList;

public class Player{
    int idPlayer = 0;  
    int xPos = 0;
    int yPos = 0;
    char direction = 'd';
    char oldDirection = 'd';
    int point = 0;
    ArrayList<Integer[]> shadowsArr = new ArrayList<>();
    ArrayList<Integer[]> oldShadowsArr = new ArrayList<>();
    Boolean inTick = false;
    DatagramSocket socket;
    String ip;
    int port;
    public Player(int idPlayer, int xPos, int yPos){
        this.idPlayer = 0;
        this.xPos = xPos;
        this.yPos = yPos;
        this.direction = 'd';
    }
    public Player(int idPlayer, int xPos, int yPos, char direction){
        this.idPlayer = 0;
        this.xPos = xPos;
        this.yPos = yPos;
        this.direction = direction;
    }

    public void setNewPos(int newXPos, int newYPos){
        this.xPos = newXPos;
        this.yPos = newYPos;
    }
    public void updateShadow(int xPosLast, int yPosLast ){
        Integer[] xyArr= new Integer[2];
        xyArr[0] = xPosLast;
        xyArr[1] = yPosLast;
        this.oldShadowsArr = (ArrayList<Integer[]>) this.shadowsArr.clone();
        if(this.point > 0){
            this.shadowsArr.add(xyArr);
        }

        if(this.shadowsArr.size() > this.point){
            this.shadowsArr.remove(0);
        }
        System.out.println("Puntuacion: " + this.point);
    }
    public int[] getPos(){
        int[] response = new int[2];
        response[0] = this.xPos;
        response[1] = this.yPos;
        return response;
    }
    public int getXPos(){
        return this.xPos;
    }
    public int getYPos(){
        return this.yPos;
    }
    public void setDirection(char direction){

        switch(direction){
            case 'd': 
                if(!this.inTick && this.direction != 'a'){
                    this.inTick = true;
                    this.oldDirection = this.direction;
                    this.direction = direction;
                }
                break;
            case 's':
                if(!this.inTick && this.direction != 'w'){
                    this.inTick = true;
                    this.oldDirection = this.direction;
                    this.direction = direction;
                }
                break;
            case 'a':
                if(!this.inTick && this.direction != 'd'){
                    this.inTick = true;
                    this.oldDirection = this.direction;
                    this.direction = direction;
                }
                break;
            case 'w':
                if(!this.inTick && this.direction != 's'){ 
                    this.inTick = true;
                    this.oldDirection = this.direction;
                    this.direction = direction;
                }
                break;
            default:
                break;
        }

    }
    public void addPoint(){
        this.point = this.point + 1;
    }
    
}