package Dominio;

import Presentacion.BoardPanel;
import Presentacion.SidePanel;
import Presentacion.Tetris;

import java.util.Random;

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

    public static Game getGame(Tetris tetris){
        if(GAME == null) GAME = new Game(tetris);
        return GAME;
    }
    private Game(Tetris tetris){
        this.tetris = tetris;
    }
    public void startGame(){
        board = tetris.getBoard().getBoard();
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

    private void acelerada(){
        gameSpeed += 0.035f;
        logicTimer.setCyclesPerSecond(gameSpeed);
        logicTimer.reset();
        dropCooldown = 25;
        level = (int) (gameSpeed * 1.70f);
        spawnPiece();
    }
    private void uniforme(){
        //gameSpeed += 0.035f;
        logicTimer.setCyclesPerSecond(gameSpeed);
        logicTimer.reset();
        dropCooldown = 25;
        level ++;//level = (int) (gameSpeed * 1.70f);
        spawnPiece();
    }

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

    private void renderGame(){
        board.repaint();
        side.repaint();
    }
    private void spawnPiece() {
        this.currentType = nextType;
        this.currentCol = currentType.getSpawnColumn();
        this.currentRow = currentType.getSpawnRow();
        this.currentRotation = 0;
        this.nextType = new TetrominoeC();
        if(!board.isValidAndEmpty(currentType, currentCol, currentRow, currentRotation)) {
            this.isGameOver = true;
            logicTimer.setPaused(true);
        }
    }
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

    public void caseD(){
        if(!isPaused &&
                board.isValidAndEmpty(currentType,currentCol+1,
                        currentRow,currentRotation)) currentCol++;
    }
    public void caseS(){
        if(!isPaused && dropCooldown == 0) {
            logicTimer.setCyclesPerSecond(25.0f);
        }
    }
    public void caseSPressed(){
        logicTimer.setCyclesPerSecond(gameSpeed);
        logicTimer.reset();
    }

    public void caseW(){
        if(!isPaused) {
            rotatePiece((currentRotation == 0) ? 3 : currentRotation - 1);
        }
    }
    public void caseA(){
        if(!isPaused && board.isValidAndEmpty(currentType, currentCol - 1, currentRow, currentRotation)) {
            currentCol--;
        }
    }
    public void caseP(){
        if(!isGameOver && !isNewGame) {
            isPaused = !isPaused;
            logicTimer.setPaused(isPaused);
        }
    }
    public void caseE(){
        if(isGameOver || isNewGame) {
            resetGame();
        }
    }
    public void caseO(){
        if(!isGameOver && isPaused) {
            //saveGame();
        }
    }
    public  void caseR(){
        if (!isGameOver && !isPaused){
            resetGame();
        }
        else{
            //Lanzar excepcion
        }
    }
    public void caseI(){
        this.isGameOver = true;
        logicTimer.setPaused(true);
    }
//    public void casePeriod(){
//        if (!isGameOver && !isPaused){
//            //buffo.useBuffo();
//        }
//    }

    public boolean isPaused() {
        return isPaused;
    }
    public boolean isGameOver() {
        return isGameOver;
    }
    public boolean isNewGame() {
        return isNewGame;
    }
    public int getScore() {
        return score;
    }
    public int getLevel() {
        return level;
    }
    public TetrominoeC getPieceType() {
        return currentType;
    }
    public TetrominoeC getNextPieceType() {
        return nextType;
    }
    public int getPieceCol() {
        return currentCol;
    }
    public int getPieceRow() {
        return currentRow;
    }
    public int getPieceRotation() {
        return currentRotation;
    }
    public float getGameSpeed(){
        return gameSpeed;
    }
}
