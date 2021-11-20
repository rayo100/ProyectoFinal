package Dominio;

public class TetrisException extends Exception{

    public static final String INVALID_MODE = "First choose the game mode!";

    public TetrisException(String message){
        super(message);
    }
}
