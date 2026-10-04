public class MultiplayerGame extends Game{
    String nameMultiplayerGame;
    String nameCreatorPlayer;
    public MultiplayerGame(String nameMultiplayerGame, String nameCreatorPlayer) throws InterruptedException{

        this.nameMultiplayerGame = nameMultiplayerGame;
        this.nameCreatorPlayer = nameCreatorPlayer;
        Server server = new Server(this.nameMultiplayerGame, this.nameCreatorPlayer);
        server.listening(8080);
        //super.startGame();
        
    }
}
