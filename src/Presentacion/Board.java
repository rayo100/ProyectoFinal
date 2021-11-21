package Presentacion;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel{
    private static final int BORDERWIDTH = 5;
    private static final int VISIBLEROWCOUNT = 20;
    private static final int HIDDENROWCOUNT = 2;
    public static final int COLORMIN = 35;
    public static final int COLORMAX = 255 - COLORMIN;
    public static final int NCOLS = 10;
    public static final int NROWS = VISIBLEROWCOUNT + HIDDENROWCOUNT;
    public static final int TILESIZE = 24;
    public static final int BORDERTILEWIDTH = 4;
    public static final int MOVEMENT = 25;
    public static final int SHADEWIDTH = 4;
    private static final int CENTERX = NCOLS * TILESIZE / 2;
    private static final int CENTERY = VISIBLEROWCOUNT * TILESIZE / 2;
    public static final int PANELWIDTH = NCOLS * TILESIZE + BORDERWIDTH * 2;
    public static final int PANELHEIGHT = VISIBLEROWCOUNT * TILESIZE + BORDERWIDTH *2;
    private static final Font LARGEFONT = new Font("Arial",Font.ITALIC,18);
    private static final Font SMALLFONT = new Font("Arial",Font.ITALIC,12);
    private Tetris1 tetris;
    //private Tetrominoe[][] tiles;
    private Color[][] shape = {
            {Color.RED,Color.RED,Color.RED},
            {null,Color.RED,null}
    };
    private Timer looper;
    private int x = 4,y = 0,z = 3;

    public Board(Tetris1 tetris){
        this.tetris = tetris;
        //this.tiles = new Tetrominoe[NROWS][NCOLS];
        setPreferredSize(new Dimension(PANELWIDTH,PANELHEIGHT));

        looper = new Timer(500, new ActionListener() {
            int n = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                y++;
                //repaint();
            }
        });
        looper.start();
    }

//    public void clear(){
//        for (int i = 0; i < NROWS; i++){
//            for(int j = 0; j < NCOLS; j++){
//                tiles[i][j] = null;
//            }
//        }
//    }
//
//    public int checkLines(){
//        int completedLines = 0;
//        for(int row = 0; row < NROWS; row ++){
//            if(checkLine(row)){
//                completedLines++;
//            }
//        }
//        return completedLines;
//    }

//    private boolean checkLine(int line){
//        for(int col = 0; col < NCOLS; col++){
//            if(!isOccupied(col,line)) return false;
//        }
//        for(int row = line - 1; row >= 0; row--){
//            for(int col = 0; col < NCOLS; col ++){
//                setTile(col,row +1,getTile(col,row));
//            }
//        }
//        return true;
//    }

//    private boolean isOccupied(int x, int y){
//        return tiles[y][x] != null;
//    }
//
//    private void setTile(int x, int y, Tetrominoe type){
//        tiles[y][x] = type;
//    }
//
//    private Tetrominoe getTile(int x, int y){
//        return tiles[y][x];
//    }
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.translate(BORDERWIDTH, BORDERWIDTH);
//        if (tetris.isPaused()) {
//            loadCase1(g);
//        }
//        else if(tetris.isNewGame() || tetris.isGameOver()){
//            loadCase2(g);
//        }
//        else{
//            for(int x = 0; x < NCOLS; x++){
//                for(int y = HIDDENROWCOUNT; y < NROWS; y++){
//                    Tetrominoe tile = getTile(x,y);
//                    if(tile != null){
//                        drawTile(tile, x * TILESIZE, (y - HIDDENROWCOUNT) * TILESIZE,g);
//                    }
//                }
//            }
//            Tetrominoe type = tetris.getPieceType();
//            int pieceCol = tetris.getPieceCol();
//            int pieceRow = tetris.getPieceRow();
//            int rotation = tetris.getPieceRotation();
//
//            for (int col = 0; col < type.getDimension(); col++){
//                for (int row = 0; row < type.getDimension(); row ++){
//                    if(pieceRow + row >= 2 && type.isTile(col,row,rotation)){
//                        drawTile(type,(pieceCol+col)*TILESIZE,(pieceRow+row-HIDDENROWCOUNT)*TILESIZE,g);
//                    }
//                }
//            }
//            Color base = type.getBaseColor();
//            base = new Color(base.getRed(), base.getGreen(), base.getBlue());
//            for(int lowest = pieceRow; lowest < NROWS; lowest++){
//                if(isValidAndEmpty(type,pieceCol,lowest,rotation)) continue;
//                lowest--;
//                for (int col = 0; col < type.getDimension(); col ++){
//                    for(int row = 0; row < type.getDimension();row ++){
//                        if(lowest + row >= 2 && type.isTile(col,row,rotation)){
//                            drawTile(base,base.brighter(),base.darker(),(pieceCol + col)*TILESIZE,
//                                    (lowest+row -HIDDENROWCOUNT)*TILESIZE,g);
//                        }
//                    }
//                }
//                break;
//            }
//
//            g.setColor(Color.DARK_GRAY);
//            for(int x = 0; x < NCOLS; x++){
//                for(int y = 0; y < VISIBLEROWCOUNT; y++){
//                    g.drawLine(0,y*TILESIZE,NCOLS*TILESIZE,y*TILESIZE);
//                    g.drawLine(x*TILESIZE,0,x*TILESIZE,VISIBLEROWCOUNT*TILESIZE);
//                }
//            }
//        }
//        g.setColor(Color.BLACK);
//        g.drawRect(0,0,TILESIZE*NCOLS,TILESIZE*VISIBLEROWCOUNT);
//    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawShape(g);
        drawBoard(g);
    }
    private void drawShape(Graphics g){
        for(int row = 0; row < shape.length; row ++){
            for(int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != null) {
                    g.setColor(shape[row][col]);
                    g.fillRect((col * TILESIZE+x*TILESIZE) + MOVEMENT,
                            (row * TILESIZE+y*TILESIZE) + MOVEMENT, TILESIZE, TILESIZE);
                }
            }
        }
    }
    private void drawBoard(Graphics g){
        g.setColor(Color.DARK_GRAY);
        for(int col = 0; col < NCOLS; col++){
            for(int row = 0; row < VISIBLEROWCOUNT; row++){
                g.drawLine(MOVEMENT,row*TILESIZE+MOVEMENT,NCOLS*TILESIZE+MOVEMENT,row*TILESIZE+MOVEMENT);
                g.drawLine(col*TILESIZE+MOVEMENT,MOVEMENT,col*TILESIZE+MOVEMENT,VISIBLEROWCOUNT*TILESIZE+MOVEMENT);
            }
        }
        g.setColor(Color.BLACK);
        g.drawRect(MOVEMENT,MOVEMENT,(TILESIZE*NCOLS),(TILESIZE*VISIBLEROWCOUNT));
    }



    private void drawTile(Color base, Color light, Color dark, int x, int y, Graphics g) {
        g.setColor(base);
        g.fillRect(x,y,TILESIZE,TILESIZE);

        g.setColor(dark);
        g.fillRect(x,y+TILESIZE-SHADEWIDTH,TILESIZE,SHADEWIDTH);
        g.fillRect(x+TILESIZE-SHADEWIDTH,y,SHADEWIDTH,TILESIZE);

        g.setColor(light);
        for(int i= 0; i < SHADEWIDTH; i++){
            g.drawLine(x,y+i,x+TILESIZE-i-1,y+i);
            g.drawLine(x+i,y,x+i,y+TILESIZE-i-1);
        }
    }

    private void loadCase1(Graphics g){
        g.setFont(LARGEFONT);
        g.setColor(Color.BLACK);
        String msg = "Paused";
        g.drawString(msg, CENTERX - g.getFontMetrics().stringWidth(msg) / 2, CENTERY);
    }
    private void loadCase2(Graphics g){
        g.setFont(LARGEFONT);
        g.setColor(Color.BLACK);
        String msg = tetris.isNewGame() ? "TETRIS" : "GAME OVER";
        g.drawString(msg, CENTERX - g.getFontMetrics().stringWidth(msg)/2,150);
        g.setFont(SMALLFONT);
        msg = "Press Enter to play" + (tetris.isNewGame() ? "": " Again");
        g.drawString(msg,CENTERX -g.getFontMetrics().stringWidth(msg)/2,300);
    }
}
