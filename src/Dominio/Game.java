package Dominio;

import Presentacion.BoardPanel;
import Presentacion.SidePanel;
import Presentacion.Tetris;

/**
 * La clase Game es el motor de juego
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 09, 2021)
*/
public class Game {
    private static Game GAME;
    private Tetris tetris;

    // Atributos del juego
    private static final long FRAME_TIME = 1000L / 50L;
    private boolean isPaused;
    private boolean isNewGame;
    private boolean isGameOver;
    private int level;
    private int score;
    private Clock logicTimer;
    private TetrominoeC currentType;
    private TetrominoeC nextType;
    private int currentCol;
    private int currentRow;
    private int currentRotation;
    private int dropCooldown;
    private float gameSpeed;
    private Board board;
    private SidePanel side;
    private boolean twoPlayers;
    private boolean isWinner;

//    public static Game getGame(Tetris tetris){
//        if(GAME == null) GAME = new Game(tetris);
//        return GAME;
//    }

    /**
     * Este metodo crea el JDialog del juego
     * @param tetris, es el JDialog donde se vizualiza el juego
     * @param twoPlayers, dice si el juego es de para dos
     */
    public Game(Tetris tetris, boolean twoPlayers){
        this.twoPlayers = twoPlayers;
        this.tetris = tetris;
    }

    /**
     * Este metodo inicia el juego
     */
    public void startGame(){
        if (twoPlayers) board = tetris.getBoard2().getBoard();
        else board = tetris.getBoard1().getBoard();
        side = tetris.getSide();
        this.isNewGame = true;
        this.gameSpeed = 1.0f;
        this.logicTimer = new Clock(gameSpeed);
        logicTimer.setPaused(true);
        while(true) {
            long start = System.nanoTime();
            logicTimer.update();
            if(logicTimer.hasElapsedCycle()) {
                updateGame();
            }
            if(dropCooldown > 0) {
                dropCooldown--;
            }
            renderGame();
            long delta = (System.nanoTime() - start) / 1000000L;
            if(delta < FRAME_TIME) {
                try {
                    Thread.sleep(FRAME_TIME - delta);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
     * Este metodo actualiza el juego
     */
    private void updateGame() {
        if(board.isValidAndEmpty(currentType, currentCol, currentRow + 1, currentRotation)) {
            currentRow++;
        } else {
            board.addPiece(currentType, currentCol, currentRow, currentRotation);
            int cleared = board.checkLines();
            if(cleared > 0) {
                score += 50 << cleared;
            }
            acelerada();
//            if (isAcelerada) {
//                acelerada();
//            }
//            else if (isUniforme){
//               uniforme();
//            }
        }
    }

    /*
     * Este metodo permite iniciar el juego con velociada acelerada
     */
    private void acelerada(){
        gameSpeed += 0.035f;
        logicTimer.setCyclesPerSecond(gameSpeed);
        logicTimer.reset();
        dropCooldown = 25;
        level = (int) (gameSpeed * 1.70f);
        spawnPiece();
    }
    /*
     * Este metodo permite iniciar el juego con velociada uniforme
     */
    private void uniforme(){
        //gameSpeed += 0.035f;
        logicTimer.setCyclesPerSecond(gameSpeed);
        logicTimer.reset();
        dropCooldown = 25;
        level ++;//level = (int) (gameSpeed * 1.70f);
        spawnPiece();
    }

    /**
     * Este metodo resetea el juego
     */
    public void resetGame() {
        this.level = 1;
        this.score = 0;
        this.gameSpeed = 1.0f;
        if (!twoPlayers) {
            this.nextType = new TetrominoeC();
        } else {
            this.nextType = tetris.getMaingame().getNextPieceType();
        }
        this.isNewGame = false;
        this.isGameOver = false;
        board.clear();
        logicTimer.reset();
        logicTimer.setCyclesPerSecond(gameSpeed);
        spawnPiece();
    }

    /*
     * Este metodo repinta el juego
     */
    private void renderGame(){
        board.repaint();
        side.repaint();
    }

    /*
     * Este metodo
     */
    private void spawnPiece() {
        this.currentType = nextType;
        this.currentCol = currentType.getSpawnColumn();
        this.currentRow = currentType.getSpawnRow();
        this.currentRotation = 0;
        if(twoPlayers)this.nextType = tetris.getMaingame().getNextPieceType();
        else this.nextType = new TetrominoeC();
        if(!board.isValidAndEmpty(currentType, currentCol, currentRow, currentRotation) ||
                !board.isValidAndEmpty()) {
            finishGame();
        }

    }

    /*
     * Este metodo finaliza el juego
     */
    private void finishGame(){
        isGameOver = true;
        logicTimer.setPaused(true);
        records();
    }

    /*
     * Este metodo envia el puntaje al finalizar el juego
     */
    private void records(){
        Records records = Records.getRecord(tetris);
        records.getScores();
    }

    /*
     * Este metodo rota una pieza
     * @param newRotation, numero de rotacion
     */
    private void rotatePiece(int newRotation) {
        int newColumn = currentCol;
        int newRow = currentRow;

        int left = currentType.getLeftInset(newRotation);
        int right = currentType.getRightInset(newRotation);
        int top = currentType.getTopInset(newRotation);
        int bottom = currentType.getBottomInset(newRotation);

        if(currentCol < -left) {
            newColumn -= currentCol - left;
        } else if(currentCol + currentType.getDimension() - right >= BoardPanel.COL_COUNT) {
            newColumn -= (currentCol + currentType.getDimension() - right) - BoardPanel.COL_COUNT + 1;
        }

        if(currentRow < -top) {
            newRow -= currentRow - top;
        } else if(currentRow + currentType.getDimension() - bottom >= BoardPanel.ROW_COUNT) {
            newRow -= (currentRow + currentType.getDimension() - bottom) - BoardPanel.ROW_COUNT + 1;
        }

        if(board.isValidAndEmpty(currentType, newColumn, newRow, newRotation)) {
            currentRotation = newRotation;
            currentRow = newRow;
            currentCol = newColumn;
        }
    }

    /**
     * Este metodo da la funcion al pulsar la tecla D
     */
    public void caseD(){
        if(!isPaused && board.isValidAndEmpty(currentType,currentCol+1,
                    currentRow,currentRotation)) currentCol++;
    }
    /**
     * Este metodo da la funcion al pulsar la tecla S
     */
    public void caseS(){
        if(!isPaused && dropCooldown == 0) {
            logicTimer.setCyclesPerSecond(25.0f);
        }
    }
    /**
     * Este metodo da la funcion al mantener pulsada la tecla D
     */
    public void caseSPressed(){
        logicTimer.setCyclesPerSecond(gameSpeed);
        logicTimer.reset();
    }
    /**
     * Este metodo da la funcion al pulsar la tecla W
     */
    public void caseW(){
        if(!isPaused) {
            rotatePiece((currentRotation == 0) ? 3 : currentRotation - 1);
        }
    }
    /**
     * Este metodo da la funcion al pulsar la tecla A
     */
    public void caseA(){
        if(!isPaused && board.isValidAndEmpty(currentType, currentCol - 1, currentRow, currentRotation)) {
            currentCol--;
        }
    }
    /**
     * Este metodo da la funcion al pulsar la tecla P
     */
    public void caseP(){
        if(!isGameOver && !isNewGame) {
            pauseGame();
        }
    }
    /**
     * Este metodo da la funcion al pulsar la tecla E
     */
    public void caseE(){
        if(isGameOver || isNewGame) {
            resetGame();
        }
    }
    /**
     * Este metodo da la funcion al pulsar la tecla O
     */
    public void caseO(){
        if(!isGameOver) {
            pauseGame();
            saveGame();
        }
    }
    /**
     * Este metodo da la funcion al pulsar la tecla R
     */
    public  void caseR(){
        if (!isGameOver && !isPaused){
            resetGame();
        }
        else{
            //Lanzar excepcion
        }
    }
    /*
     * Este metodo pausa el juego
     */
    private void pauseGame(){
        isPaused = !isPaused;
        logicTimer.setPaused(isPaused);
    }
    /*
     * Este metodo guarda el juego
     */
    private void saveGame(){
        //Llamado persistencia
    }
    /**
     * Este metodo da la funcion al pulsar la tecla I
     */
    public void caseI(){
        finishGame();
    }
    /**
     * Este metodo da la funcion al pulsar la tecla "."
     */
//    public void casePeriod(){
//        if (!isGameOver && !isPaused){
//            //buffo.useBuffo();
//        }
//    }

    /**
     * Este metodo retorna si el juego está pausado
     * @return True or False
     */
    public boolean isPaused() {return isPaused;}

    /**
     * Este metodo retorna si se terminó el juego
     * @return True or False
     */
    public boolean isGameOver() {return isGameOver;}

    /**
     * Este metodo retorna si el juego empezo
     * @return True or False
     */
    public boolean isNewGame() {return isNewGame;}

    /**
     * Este metodo retorna el puntaje del juego
     * @return score,es el puntaje del juego
     */
    public int getScore() {return score;}
    /**
     * Este metodo retorna el nivel del juego
     * @return level, es el nivel del juego
     */
    public int getLevel() {return level;}

    /**
     * Este metodo retorna el tipo actual de la pieza
     * @return currentType, es el tipo actual de la pieza
     */
    public TetrominoeC getPieceType() {return currentType;}

    /**
     * Este metodo retorna el siguiente tipo de la pieza
     * @return nextType, es el siguiente tipo de la pieza
     */
    public TetrominoeC getNextPieceType() {return nextType;}

    /**
     * Este metodo retorna el numero actual de columnas
     * @return currentCol, es el numero actual de columnas
     */
    public int getPieceCol() {return currentCol;}
    /**
     * Este metodo retorna el numero actual de filas
     * @return currentRow, es el numero actual de filas
     */
    public int getPieceRow() {return currentRow;}

    /**
     * Este numero retorna el numero actual de rotacion
     * @return currentRotation, es el numero actual de rotacion
     */
    public int getPieceRotation() {return currentRotation;}

    /**
     * Este metodo retorna la velocidad del juego
     * @return gameSpeed, es la velocidad del juego
     */
    public float getGameSpeed(){return gameSpeed;}
}
