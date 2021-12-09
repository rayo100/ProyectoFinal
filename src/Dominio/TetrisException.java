package Dominio;

/**
 * The TetrisException class is where game exception
 * handling is done
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 07, 2021)
 */
public class TetrisException extends Exception{

    public static final String INVALID_MODE = "First choose the game mode!";
    public static final String INVALID_VELOCITY = "Choose the game velocity!";
    public static final String NO_GAME_FINISHED = "Game has not finished yet";

    /**
     * Create exception
     * @param message, is the message when throwing exception
     */
    public TetrisException(String message){
        super(message);
    }
}
