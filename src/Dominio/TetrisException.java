package Dominio;

/**
 * La clase TetrisException es donde manejamos
 * las excepcione spropias del juego
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 09, 2021)
 */
public class TetrisException extends Exception{

    public static final String INVALID_MODE = "First choose the game mode!";
    public static final String INVALID_VELOCITY = "Choose the game velocity!";
    public static final String NO_GAME_FINISHED = "Game has not finished yet";

    /**
     * Crea la excepcion
     * @param message, es el mensaje lanzado en la excepcion
     */
    public TetrisException(String message){
        super(message);
    }
}
