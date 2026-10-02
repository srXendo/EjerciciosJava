public class Square{
    int x;
    int y;
    EnumSquareType type;
    public Square(int x, int y, EnumSquareType type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
    public char getBoxText() throws InterruptedException{
        char response;
        switch(this.type){
            
            case EnumSquareType.CLEAN:
                response = '=';
                break;
            case EnumSquareType.PLAYER:
                response = '*';
                break;
            case EnumSquareType.EAT:
                response = '+';
                break;    
            case EnumSquareType.SHADOW:
                response = '-';
                break;
            default:
                System.out.print(this.type);
            
                response = '?';
                break;
        }
        return response;
    }
}
