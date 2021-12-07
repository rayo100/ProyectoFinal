package Dominio;

public class TetrisException extends Exception{

    public static final String INVALID_MODE = "First choose the game mode!";
    public static final String INVALID_VELOCITY = "Choose the game velocity!";
    public static final String NO_GAME_FINISHED = "Game has not finished yet";

    public TetrisException(String message){
        super(message);
    }
}
