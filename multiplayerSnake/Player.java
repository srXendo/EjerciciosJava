
import java.util.ArrayList;

public class Player{
    int idPlayer = 0;  
    int xPos = 0;
    int yPos = 0;
    char direction = 'd';
    int point = 0;
    ArrayList<Integer[]> shadowsArr = new ArrayList<>();
    ArrayList<Integer[]> oldShadowsArr = new ArrayList<>();
    
    public Player(int idPlayer, int xPos, int yPos){
        this.idPlayer = 0;
        this.xPos = xPos;
        this.yPos = yPos;
        this.direction = 'd';
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
        System.out.println("\n: " + this.shadowsArr.size());

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
        this.direction = direction;
    }
    public void addPoint(){
        this.point = this.point + 1;
    }
    
}