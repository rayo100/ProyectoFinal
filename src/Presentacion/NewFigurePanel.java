package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class NewFigurePanel extends JPanel {

    private static final int TILESIZE = Board.TILESIZE / 2;
    private static final int SHADEWIDTH = Board.SHADEWIDTH >> 1;
    private static final int TILECOUNT = 5;
    private static final int SQUARECENTER_X = 130;
    private static final int SQUARECENTER_Y = 65;
    private static final int SQUARESIZE = (TILESIZE * TILECOUNT >> 1);
    private static final int SMALL_INSET = 20;
    private static final int LARGE_INSET = 40;
    private static final int STATS_INSET = 175;
    private static final int CONTROLS_INSET = 300;
    private static final int TEXT_STRIDE = 25;
    private static final Font SMALL_FONT = new Font("Arial", Font.BOLD, 11);
    private static final Font LARGE_FONT = new Font("Arial Bold", Font.BOLD, 13);
    private static final Color DRAW_COLOR = Color.BLACK;
    //Agregar atributos aca:

    //Fin nuevos atributos

    private Tetris1 tetris;

    public NewFigurePanel(Tetris1 tetris) {
        this.tetris = tetris;
        setPreferredSize(new Dimension(Board.PANELWIDTH, Board.PANELHEIGHT));
        setBackground(Color.WHITE);
    }
    //Metodos nuevos aca:


    //Fin metodos nuevos
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(DRAW_COLOR);
        int offset;

        //Section stats
        g.setFont(LARGE_FONT);
        g.drawString("Stats", SMALL_INSET, offset = STATS_INSET);
        g.setFont(SMALL_FONT);
        //g.drawString("Level: " + tetris.getLevel(), LARGE_INSET, offset += TEXT_STRIDE);
        //g.drawString("Score: " + tetris.getScore(), LARGE_INSET, offset += TEXT_STRIDE);

        //Section controls
        g.setFont(LARGE_FONT);
        g.drawString("Controls Player one", SMALL_INSET, offset = CONTROLS_INSET);
        g.setFont(SMALL_FONT);
        g.drawString("A - Move Left", LARGE_INSET, offset += TEXT_STRIDE);
        g.drawString("D - Move Right", LARGE_INSET, offset += TEXT_STRIDE);
        g.drawString("Q - Rotate Anticlockwise", LARGE_INSET, offset += TEXT_STRIDE);
        g.drawString("E - Rotate Clockwise", LARGE_INSET, offset += TEXT_STRIDE);
        g.drawString("S - Drop", LARGE_INSET, offset += TEXT_STRIDE);
        g.drawString("P - Pause Game", LARGE_INSET, offset += TEXT_STRIDE);

        //Section next piece
        g.setFont(LARGE_FONT);
        g.drawString("Next Piece:", SMALL_INSET, 70);
        g.drawRect(SQUARECENTER_X - SQUARESIZE, SQUARECENTER_Y - SQUARESIZE, SQUARESIZE * 2, SQUARESIZE * 2);
    }
}
