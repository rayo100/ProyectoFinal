package Dominio;

import Presentacion.Tetris;

import java.util.Random;

public class Game {
    private static Game GAME;
    private Tetris tetris;

    // Atributos del juego
    private boolean isPaused;
    private boolean isNewGame;
    private boolean isGameOver;
    private int level;
    private int score;
    private Random random;
    private Clock logicTimer;
    private Tetrominoe currentType;
    private Tetrominoe nextType;
    private int currentCol;
    private int currentRow;
    private int currentRotation;
    private int dropCooldown;
    private float gameSpeed;

    public static Game getGame(){
        if(GAME == null) GAME = new Game();
        return GAME;
    }
    private Game(){

    }
}
