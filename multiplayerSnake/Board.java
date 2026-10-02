

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
    public String getStringFromXY(int x, int y){
        EnumSquareType type = this.squaresMap.get(x).get(y);
        String response;
        switch(type){
            case EnumSquareType.CLEAN:
                response = "=";
                break;
            case EnumSquareType.PLAYER:
                response = EnumColors.VERDE+"#"+EnumColors.RESET;
                break;    
            case EnumSquareType.EAT:
                response =  EnumColors.AZUL+"X"+EnumColors.RESET; 
                break;
            case EnumSquareType.SHADOW:
                response = EnumColors.VERDE+"*"+EnumColors.RESET;
                break;     
            default: 
                response = "?";
                break;
        }
        return response;
    }
    public Boolean tick(){
        this.playersMap.get(0).inTick = false;
        char direction = this.playersMap.get(0).direction;
        Boolean isDead = false;
        switch(direction){
            case 'd': 
                if(direction != 'a'){
                    isDead = this.movePlayerRight(0);
                }
                break;
            case 's':
                if(direction != 'w'){
                    isDead = this.movePlayerBottom(0);
                }
                break;
            case 'a':
                if(direction != 'd'){
                    isDead = this.movePlayerLeft(0);
                }
                break;
            case 'w':
                if(direction != 's'){ 
                    isDead = this.movePlayerTop(0);
                }
                break;
            default:
                break;
        }
        return isDead;
    }
    public Boolean movePlayerRight(int idxPlayer){
        int yPos = this.playersMap.get(idxPlayer).yPos;
        int xPos = this.playersMap.get(idxPlayer).xPos;
        int newYPos = 0; 
        if(yPos + 1 < this.width){
            newYPos = yPos + 1;
        }
        return this.movePlayer(idxPlayer, xPos, newYPos);
    }
    public Boolean movePlayerBottom(int idxPlayer){
        int yPos = this.playersMap.get(idxPlayer).yPos;
        int xPos = this.playersMap.get(idxPlayer).xPos;
        int newXPos = 0; 
        if(xPos + 1 < this.height){
            newXPos = xPos + 1;
        }
        
        return this.movePlayer(idxPlayer, newXPos, yPos);
    }
    public Boolean movePlayerLeft(int idxPlayer){
        int yPos = this.playersMap.get(idxPlayer).yPos;
        int xPos = this.playersMap.get(idxPlayer).xPos;
        int newYPos = this.width - 1; 
        if(yPos - 1 >= 0){
            newYPos = yPos - 1;
        }
        return this.movePlayer(idxPlayer, xPos, newYPos);
    }
    public Boolean movePlayerTop(int idxPlayer){
        int yPos = this.playersMap.get(idxPlayer).yPos;
        int xPos = this.playersMap.get(idxPlayer).xPos;
        int newXPos = this.height - 1; 
        if(xPos - 1 >= 0){
            newXPos = xPos - 1;
        }
        return this.movePlayer(idxPlayer, newXPos, yPos);
    }    
    public Boolean movePlayer(int idxPlayer, int newXPos, int newYPos){
        Player curentPlayer = this.playersMap.get(idxPlayer);
        int xOld = curentPlayer.getXPos();
        int yOld = curentPlayer.getYPos();
        if(this.squaresMap.get(newXPos).get(newYPos) == EnumSquareType.EAT){
            curentPlayer.addPoint();
            this.addEat();
        }else if(this.squaresMap.get(newXPos).get(newYPos) == EnumSquareType.SHADOW){
            return true; 
        }

        
        curentPlayer.setNewPos(newXPos, newYPos);
        curentPlayer.updateShadow(xOld, yOld);
        this.updateShadows(curentPlayer);
        if(curentPlayer.point == 0){
            this.updateSquare(xOld, yOld, EnumSquareType.CLEAN);
        }
        
        this.updateSquare(newXPos, newYPos, EnumSquareType.PLAYER);
        return false;
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