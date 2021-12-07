package Dominio;

import Presentacion.BoardPanel;
import Presentacion.SidePanel;
import Presentacion.Tetris;


/**
 * The Machines class is the engine
 * of the tetris game
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 07, 2021)
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
    private int nRenders;

    /**
     * This method return the new game
     * @param tetris, tetris is the panel of the game
     * @return
     */
    public static Game getGame(Tetris tetris){
        if(GAME == null) GAME = new Game(tetris);
        return GAME;
    }

    /*
     * Create the panel of the tetris game
     * @param tetris, tetris is the panel of the game
     */
    private Game(Tetris tetris){
        this.tetris = tetris;
    }

    /**
     * This method start the tetris game
     */
    public void startGame(){
        nRenders = 0;
        board = tetris.getBoard1().getBoard();
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
     * This method update the tetris game
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
     * This method initialize accelerated speed
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
     * This method initialize uniform speed
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
     * This method reset the tetris game
     */
    public void resetGame() {
        this.level = 1;
        this.score = 0;
        this.gameSpeed = 1.0f;
        this.nextType = new TetrominoeC();
        this.isNewGame = false;
        this.isGameOver = false;
        board.clear();
        logicTimer.reset();
        logicTimer.setCyclesPerSecond(gameSpeed);
        spawnPiece();
    }

    /*
     * This method render the tetris game
     */
    private void renderGame(){
        nRenders += 1;
        board.repaint();
        side.repaint();
    }

    /*
     * This method
     */
    private void spawnPiece() {
        this.currentType = nextType;
        this.currentCol = currentType.getSpawnColumn();
        this.currentRow = currentType.getSpawnRow();
        this.currentRotation = 0;
        this.nextType = new TetrominoeC();
        if(!board.isValidAndEmpty(currentType, currentCol, currentRow, currentRotation)) {
            finishGame();
        }
    }

    /*
     * This method finish the tetris game
     */
    private void finishGame(){
        System.out.println(nRenders);
        this.isGameOver = true;
        logicTimer.setPaused(true);
    }

    /*
     * This method rotate a piece
     * @param newRotation, newRotation is the number to rotate
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
     * This method returns what the D key does
     */
    public void caseD(){
        if(!isPaused && board.isValidAndEmpty(currentType,currentCol+1,
                    currentRow,currentRotation)) currentCol++;
    }
    /**
     * This method returns what the S key does
     */
    public void caseS(){
        if(!isPaused && dropCooldown == 0) {
            logicTimer.setCyclesPerSecond(25.0f);
        }
    }
    /**
     * This method returns what the S key does
     * pressed for a long time
     */
    public void caseSPressed(){
        logicTimer.setCyclesPerSecond(gameSpeed);
        logicTimer.reset();
    }
    /**
     * This method returns what the W key does
     */
    public void caseW(){
        if(!isPaused) {
            rotatePiece((currentRotation == 0) ? 3 : currentRotation - 1);
        }
    }
    /**
     * This method returns what the A key does
     */
    public void caseA(){
        if(!isPaused && board.isValidAndEmpty(currentType, currentCol - 1, currentRow, currentRotation)) {
            currentCol--;
        }
    }
    /**
     * This method returns what the P key does
     */
    public void caseP(){
        if(!isGameOver && !isNewGame) {
            pauseGame();
        }
    }
    /**
     * This method returns what the E key does
     */
    public void caseE(){
        if(isGameOver || isNewGame) {
            resetGame();
        }
    }
    /**
     * This method returns what the O key does
     */
    public void caseO(){
        if(!isGameOver) {
            pauseGame();
            saveGame();
        }
    }
    /**
     * This method returns what the R key does
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
     * This method paused the tetris game
     */
    private void pauseGame(){
        isPaused = !isPaused;
        logicTimer.setPaused(isPaused);
    }
    /*
     * This method save a tetris game
     */
    private void saveGame(){
        //Llamado persistencia
    }
    /**
     * This method returns what the I key does
     */
    public void caseI(){
        finishGame();
    }
    /**
     * This method returns what the . key does
     */
//    public void casePeriod(){
//        if (!isGameOver && !isPaused){
//            //buffo.useBuffo();
//        }
//    }

    /**
     * This method returns if the game is paused
     * @return True or False
     */
    public boolean isPaused() {return isPaused;}

    /**
     * his method returns if game over
     * @return True or False
     */
    public boolean isGameOver() {return isGameOver;}

    /**
     * This method return if is new game
     * @return True or False
     */
    public boolean isNewGame() {return isNewGame;}

    /**
     * This method returns the score of the game
     * @return score, is the score of the game
     */
    public int getScore() {return score;}
    /**
     * This method returns the level of the game
     * @return level, is the level of the game
     */
    public int getLevel() {return level;}

    /**
     * This method returns the current type of the piece
     * @return currentType, is the current type of the tile
     */
    public TetrominoeC getPieceType() {return currentType;}

    /**
     * This method returns the next type of the piece
     * @return nextType, is the next type of the tile
     */
    public TetrominoeC getNextPieceType() {return nextType;}

    /**
     * This method returns of the number of the current columns
     * @return currentCol, is the number of the current columns
     */
    public int getPieceCol() {return currentCol;}
    /**
     * This method returns of the number of the current rows
     * @return currentRow, is the number of the current rows
     */
    public int getPieceRow() {return currentRow;}

    /**
     * This method returns of the number of the current rotation
     * @return currentRotation, is the number of the current rotation
     */
    public int getPieceRotation() {return currentRotation;}

    /**
     * This method the game speed
     * @return gameSpeed, is the game speed
     */
    public float getGameSpeed(){return gameSpeed;}
}
