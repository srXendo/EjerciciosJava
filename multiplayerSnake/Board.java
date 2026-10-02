

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board{
    Map<Integer, Map<Integer, EnumSquareType>> squaresMap = new HashMap<>();
    Map<Integer, Player> playersMap = new HashMap<>();
    int width = 0;
    int height = 0;
    public Board(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void createAndAddSquare(int x, int y){
        
        if (this.squaresMap.get(x) == null) {
            this.squaresMap.put(x, new HashMap<>());
        }
        this.squaresMap.get(x).put(y, EnumSquareType.CLEAN);

    }
    public char getCharacterFromXY(int x, int y){
        EnumSquareType type = this.squaresMap.get(x).get(y);
        char response;
        switch(type){
            case EnumSquareType.CLEAN:
                response = '=';
                break;
            case EnumSquareType.PLAYER:
                response = '#';
                break;    
            case EnumSquareType.EAT:
                response = 'X'; 
                break;
            case EnumSquareType.SHADOW:
                response = '*';
                break;     
            default: 
                response = '?';
                break;
        }
        return response;
    }
    public void tick(){
        char direction = this.playersMap.get(0).direction;
        switch(direction){
            case 'd': 
                this.movePlayerRight(0);
                break;
            case 's':
                this.movePlayerBottom(0);
                break;
            case 'a':
                this.movePlayerLeft(0);
                break;
            case 'w': 
                this.movePlayerTop(0);
                break;
            default:
                break;
        }
    }
    public void movePlayerRight(int idxPlayer){
        int yPos = this.playersMap.get(idxPlayer).yPos;
        int xPos = this.playersMap.get(idxPlayer).xPos;
        int newYPos = 0; 
        if(yPos + 1 < this.width){
            newYPos = yPos + 1;
        }
        this.movePlayer(idxPlayer, xPos, newYPos);
    }
    public void movePlayerBottom(int idxPlayer){
        int yPos = this.playersMap.get(idxPlayer).yPos;
        int xPos = this.playersMap.get(idxPlayer).xPos;
        int newXPos = 0; 
        if(xPos + 1 < this.height){
            newXPos = xPos + 1;
        }
        
        this.movePlayer(idxPlayer, newXPos, yPos);
    }
    public void movePlayerLeft(int idxPlayer){
        int yPos = this.playersMap.get(idxPlayer).yPos;
        int xPos = this.playersMap.get(idxPlayer).xPos;
        int newYPos = this.width - 1; 
        if(yPos - 1 >= 0){
            newYPos = yPos - 1;
        }
        this.movePlayer(idxPlayer, xPos, newYPos);
    }
    public void movePlayerTop(int idxPlayer){
        int yPos = this.playersMap.get(idxPlayer).yPos;
        int xPos = this.playersMap.get(idxPlayer).xPos;
        int newXPos = this.height - 1; 
        if(xPos - 1 >= 0){
            newXPos = xPos - 1;
        }
        this.movePlayer(idxPlayer, newXPos, yPos);
    }    
    public void movePlayer(int idxPlayer, int newXPos, int newYPos){
        Player curentPlayer = this.playersMap.get(idxPlayer);
        if(this.squaresMap.get(newXPos).get(newYPos) == EnumSquareType.EAT){
            curentPlayer.addPoint();
            this.addEat();
        }
        int xOld = curentPlayer.getXPos();
        int yOld = curentPlayer.getYPos();
        
        curentPlayer.setNewPos(newXPos, newYPos);
        curentPlayer.updateShadow(xOld, yOld);
        this.updateShadows(curentPlayer);
        if(curentPlayer.point == 0){
            this.updateSquare(xOld, yOld, EnumSquareType.CLEAN);
        }
        
        this.updateSquare(newXPos, newYPos, EnumSquareType.PLAYER);
    }
    public void updateShadows(Player currentPlayer){
        //clean shadow
        for(Integer[] xyArr : currentPlayer.oldShadowsArr){
            this.updateSquare(xyArr[0], xyArr[1], EnumSquareType.CLEAN);
        }

        //set shadow
        for(Integer[] xyArr : currentPlayer.shadowsArr){
            this.updateSquare(xyArr[0], xyArr[1], EnumSquareType.SHADOW);
        }
    }
    public void addPlayer(int idxPlayer){
        this.playersMap.put(idxPlayer, new Player(idxPlayer, 0, 0));
    }
    public void updateSquare(int x, int y, EnumSquareType newType){
        this.squaresMap.get(x).replace(y, newType);
    }
    public void inputPress(char keyPressed){
        this.playersMap.get(0).setDirection(keyPressed);
    }
    public void addEat() {
        Random random = new Random();
        boolean centinele = false;
        int xPos = 0;
        int yPos = 0;
        while(!centinele){
            
            xPos = random.nextInt(this.height - 1); 
            yPos = random.nextInt(this.width - 1);
            try{
                if(this.squaresMap.get(xPos).get(yPos) == EnumSquareType.CLEAN){
                    centinele = true; 
                }
            }catch(Exception e){
                System.out.print("centinele");
            }
        }

        this.updateSquare(xPos, yPos, EnumSquareType.EAT);
    }
}