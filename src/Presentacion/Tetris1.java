package Presentacion;

//import Dominio.Tetrominoe;

import Ayudas.BoardPanel;
import Ayudas.TileType;
import Dominio.*;


import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Tetris1 extends JFrame {
    //Padre
    TetrisMain main;
    //Panel NewFigure
    NewFigurePanel newFigure;
    //Panel Board
    Board board;
    //Tamano ventana
    private final int ANCHO = 566;
    private final int ALTO = 568;
    //Atributos motor juego
    //Estados juego
    private boolean isPaused;
    private boolean isNewGame;
    private boolean isGameOver;
    //Stats del juego
    private int level;
    private int score;
    //Reloj del juego
    private static final long FRAME_TIME = 1000L / 50L;
    private Clock logicTimer;

    //Fichas juego
    private Tetrominoe nextPiece;
    private Tetrominoe currPiece;
    private Random random;
    private static final int TYPE_COUNT = TileType.values().length;

    //Fila, columna y rotacion de la pieza
    private int currRow;
    private int currCol;
    private int currRotation;

    //otros
    private int dropCooldown;
    private float gameSpeed;


    public Tetris1(TetrisMain main, String title) {
        super(title);
        this.main = main;
        //System.out.println(ANCHO);
        //System.out.println(ALTO);
        prepareElementos();
        //startGame();
    }


//    public static void main(String[] args){
//        Tetris1 gui = new Tetris1("Tetris Game");
//        gui.setVisible(true);
//    }

    public void prepareElementos(){
        setPreferredSize(new Dimension(ANCHO,ALTO));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        cargueElementos();
        configureElementos();
        agregueElementos();
//        prepareAcciones();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void cargueElementos(){
        newFigure = new NewFigurePanel(this);
        board = new Board(this);
    }
    private void configureElementos(){
        board.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),
                new TitledBorder("Board")));
        newFigure.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),
                new TitledBorder("Game Info")));
        Color color = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
        newFigure.setBackground(color);
        board.setBackground(color);
    }
    private void agregueElementos(){
        add(board,BorderLayout.CENTER);
        add(newFigure,BorderLayout.WEST);
    }
//    private void prepareAcciones() {
//        addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                switch (e.getKeyCode()){
//                    case KeyEvent.VK_S:
//                        if(!isPaused && dropCooldown == 0){
//                            logicTimer.setCyclesPerSecond(25.0f);
//                        }
//                        break;
//
//                    case KeyEvent.VK_A:
//                        if(!isPaused && board.isValidAndEmpty(currPiece,currCol-1,currRow,currRotation)){
//                            currCol--;
//                        }
//                        break;
//
//                    case KeyEvent.VK_D:
//                        if(!isPaused && board.isValidAndEmpty(currPiece,currCol-1,currRow,currRotation)){
//                            currCol++;
//                        }
//                    case KeyEvent.VK_Q:
//                        if(!isPaused){
//                            rotatePiece((currRotation == 0) ? 3 :currRotation-1);
//                        }
//                        break;
//
//                    case KeyEvent.VK_P:
//                        if(!isGameOver && !isNewGame){
//                            isPaused = !isPaused;
//                            logicTimer.setPaused(isPaused);
//                        }
//                        break;
//                    case KeyEvent.VK_ENTER:
//                        if(isGameOver || isNewGame){
//                            resetGame();
//                        }
//                        break;
//                }
//            }
//            @Override
//            public void keyReleased(KeyEvent e) {
//                switch (e.getKeyCode()) {
//                    case KeyEvent.VK_S:
//                        logicTimer.setCyclesPerSecond(gameSpeed);
//                        logicTimer.reset();
//                        break;
//                }
//            }
//        });
//    }
//    private void actionsFromKeyBoard(KeyEvent e){
//
//    }

    public Tetrominoe getNextPieceType() {
        return nextPiece;
    }

    public void startGame(){
        isNewGame = true;
        gameSpeed = 1.0f;
        random = new Random();
        logicTimer = new Clock(gameSpeed);
        logicTimer.setPaused(true);

        while (true){
            long start = System.nanoTime();

            logicTimer.update();
            if(logicTimer.hasElapsedCycle()){
                updateGame();
            }

            if(dropCooldown > 0){
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
    private void updateGame(){
        if(board.isValidAndEmpty(currPiece,currCol,currRow+1,currRotation)){
            currRow++;
        }
        else{
            board.addPiece(currPiece,currCol,currRow,currRotation);
            int cleared = board.checkLines();
            if(cleared > 0) {
                score += 50 << cleared;
            }
            gameSpeed += 0.035f;
            logicTimer.setCyclesPerSecond(gameSpeed);
            logicTimer.reset();

            dropCooldown = 25;
            level = (int)(gameSpeed *1.70f);
            spawnPiece();
        }
    }



    private void renderGame(){
        newFigure.repaint();
        board.repaint();
    }
    private void resetGame(){
        level = 1;
        score = 0;
        gameSpeed = 1.0f;
        nextPiece = Tetrominoe.values()[random.nextInt(TYPE_COUNT)];
        isNewGame = false;
        isGameOver = false;
        board.clear();
        logicTimer.reset();
        logicTimer.setCyclesPerSecond(gameSpeed);
        spawnPiece();
    }

    private void spawnPiece() {
        currPiece = nextPiece;
        currCol = currPiece.getSpawnColumn();
        currRow = currPiece.getSpawnRow();
        currRotation = 0;
        nextPiece = Tetrominoe.values()[random.nextInt(TYPE_COUNT)];

        if(!board.isValidAndEmpty(currPiece,currCol,currRow,currRotation)){
            isGameOver = true;
            logicTimer.setPaused(true);
        }
    }
    private void rotatePiece(int newRotation){
        int newColumn = currCol;
        int newRow = currRow;

        int left = currPiece.getLeftInset(newRotation);
        int top = currPiece.getTopInset(newRotation);
        int right = currPiece.getRightInset(newRotation);
        int bottom = currPiece.getBottomInset(newRotation);

        if(currCol < -left) {
            newColumn -= currCol - left;
        } else if(currCol + currPiece.getDimension() - right >= BoardPanel.COL_COUNT) {
            newColumn -= (currCol + currPiece.getDimension() - right) - BoardPanel.COL_COUNT + 1;
        }

        if(currRow < -top) {
            newRow -= currRow - top;
        } else if(currRow + currPiece.getDimension() - bottom >= BoardPanel.ROW_COUNT) {
            newRow -= (currRow + currPiece.getDimension() - bottom) - BoardPanel.ROW_COUNT + 1;
        }
        if(board.isValidAndEmpty(currPiece, newColumn, newRow, newRotation)) {
            currRotation = newRotation;
            currRow = newRow;
            currCol = newColumn;
        }

    }

    public String getNickname(){
        return main.player();
    }

    public boolean isPaused(){
        return isPaused;
    }

    public boolean isNewGame() {
        return isNewGame;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public Tetrominoe getPieceType(){
        return currPiece;
    }

    public int getPieceCol() {
        return currCol;
    }

    public int getPieceRow() {
        return currRow;
    }

    public int getPieceRotation() {
        return currRotation;
    }
}
