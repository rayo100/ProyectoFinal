package Presentacion;

import Dominio.Tetrominoe;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.Font;

public class Tetris1 extends JFrame {
    //Panel NewFigure
    TetrisMain main;
    NewFigurePanel newFigure;
    //Panel Board
    Board board;
    //Tamano ventana
    private final int ANCHO = 566;
    private final int ALTO = 568;

    public Tetris1(TetrisMain main, String title) {
        super(title);
        this.main = main;
        System.out.println(ANCHO);
        System.out.println(ALTO);
        prepareElementos();
    }
//    public static void main(String[] args){
//        Tetris1 gui = new Tetris1("Tetris Game");
//        gui.setVisible(true);
//    }

    public void prepareElementos(){
        setPreferredSize(new Dimension(ANCHO,ALTO));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLayout(new BorderLayout());
        cargueElementos();
        configureElementos();
        agregueElementos();
        setLocationRelativeTo(null);
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
    }
    private void agregueElementos(){
        add(board,BorderLayout.CENTER);
        add(newFigure,BorderLayout.WEST);
    }
    public String getNickname(){
        return main.player();
    }

    public boolean isPaused(){
        return false;
    }



    public boolean isNewGame() {
        return false;
    }

    public boolean isGameOver() {
        return false;
    }

    public Tetrominoe getPieceType() {
        return null;
    }

    public int getPieceCol() {
        return 0;
    }

    public int getPieceRow() {
        return 0;
    }

    public int getPieceRotation() {
        return 0;
    }
}
