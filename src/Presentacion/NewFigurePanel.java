package Presentacion;

import Dominio.Tetrominoe;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;


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
    private String nickname;
    //Agregar atributos aca:
    private JButton save;
    private JButton color;
    private JButton controls;
    private JButton help;
    //private JLabel options;


    //Fin nuevos atributos

    private Tetris1 tetris;

    public NewFigurePanel(Tetris1 tetris) {
        this.tetris = tetris;
        nickname = tetris.getNickname();
        setPreferredSize(new Dimension(Board.PANELWIDTH, Board.PANELHEIGHT));
        setBackground(Color.WHITE);
        setLayout(null);
        prepareElementosM1();
        prepareAccionesM1();
    }
    //Metodos nuevos aca:
    private void prepareElementosM1() {
        save = new JButton("Save");
        save.setBounds(30,200,90, 20);
        controls = new JButton("Controls");
        controls.setBounds(130,200,90, 20);
        color = new JButton("Color");
        color.setBounds(30,240,90, 20);
//        help = new JButton("Help")
        add(save);
        add(controls);
        add(color);
    }

    private  void prepareAccionesM1(){
    }
    //Fin metodos nuevos
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(DRAW_COLOR);
        int offset;

        //Section next piece
        g.setFont(LARGE_FONT);
        g.drawString("Next Piece:", SMALL_INSET, 70);
        g.drawRect(SQUARECENTER_X - SQUARESIZE, SQUARECENTER_Y - SQUARESIZE, SQUARESIZE * 2, SQUARESIZE * 2);

        Tetrominoe type = tetris.getNextPieceType();
        if(!tetris.isGameOver() && type != null){
            int cols = type.getCols();
            int rows = type.getRows();
            int dimension = type.getDimension();
            int startX = (SQUARECENTER_X - (cols * TILESIZE /2));
            int startY = (SQUARECENTER_Y - (rows * TILESIZE /2));
            int top = type.getTopInset(0);
            int left = type.getLeftInset(0);
            for(int row = 0; row < dimension; row++){
                for (int col = 0; col < dimension; col++){
                    if (type.isTile(col,row,0)){
                        drawTile(type, startX + ((col - left) * TILESIZE), startY + ((row - top) * TILESIZE), g);
                    }
                }
            }
        }


        //Section Options
        g.setFont(LARGE_FONT);
        g.drawString("Options:", SMALL_INSET, offset = STATS_INSET);
        g.setFont(SMALL_FONT);
        //g.drawString("Nickname: " + tetris.getNickname(), LARGE_INSET, offset += TEXT_STRIDE);
        //g.drawString("Score: " + tetris.getScore(), LARGE_INSET, offset += TEXT_STRIDE);

        //Section Stats
        g.setFont(LARGE_FONT);
        g.drawString("Stats", SMALL_INSET, offset = CONTROLS_INSET);
        g.setFont(SMALL_FONT);
        g.drawString("Nickname: " + nickname, LARGE_INSET, offset += TEXT_STRIDE);
        //g.drawString("Level: " + tetris.getLevel(), LARGE_INSET, offset += TEXT_STRIDE);
        //g.drawString("Score: " + tetris.getScore(), LARGE_INSET, offset += TEXT_STRIDE);
        //Section controls
//        g.drawString("A - Move Left", LARGE_INSET, offset += TEXT_STRIDE);
//        g.drawString("D - Move Right", LARGE_INSET, offset += TEXT_STRIDE);
//        g.drawString("W - Rotate", LARGE_INSET, offset += TEXT_STRIDE);
//        g.drawString(". - Use a Buffo", LARGE_INSET, offset += TEXT_STRIDE);
//        g.drawString("S - Drop", LARGE_INSET, offset += TEXT_STRIDE);
//        g.drawString("P - Pause Game", LARGE_INSET, offset += TEXT_STRIDE);

    }
    private void botonControls(){

    }
    private void drawTile(Tetrominoe type, int x, int y, Graphics g) {
        g.setColor(type.getBaseColor());
        g.fillRect(x, y, TILESIZE, TILESIZE);

        g.setColor(type.getDarkColor());
        g.fillRect(x, y + TILESIZE - SHADEWIDTH, TILESIZE, SHADEWIDTH);
        g.fillRect(x + TILESIZE - SHADEWIDTH, y, SHADEWIDTH, TILESIZE);

        g.setColor(type.getLightColor());
        for(int i = 0; i < SHADEWIDTH; i++) {
            g.drawLine(x, y + i, x + TILESIZE - i - 1, y + i);
            g.drawLine(x + i, y, x + i, y + TILESIZE - i - 1);
        }
    }


}
