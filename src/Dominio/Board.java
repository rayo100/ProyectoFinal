package Dominio;

import Presentacion.BoardPanel;
/**
 * The Board class is in charge of the board's
 * funcionality
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 07, 2021)
 */
public class Board {
    private static Board board;
    private BoardPanel boardPanel;
    //Atributos tablero
    public static final int COL_COUNT = 10;
    private static final int VISIBLE_ROW_COUNT = 20;
    private static final int HIDDEN_ROW_COUNT = 2;
    public static final int ROW_COUNT = VISIBLE_ROW_COUNT + HIDDEN_ROW_COUNT;
    private TetrominoeC[][] tiles;

    /**
     * This method returns the board
     * @param boardPanel, is the board panel of the game
     * @return board, board is the board game
     */
    public static Board getBoard(BoardPanel boardPanel){
        if(board == null) board = new Board(boardPanel);
        return board;
    }

    /*
     * Create the board panel of the game
     * @param boardPanel, is the board panel of the game
     */
    private Board(BoardPanel boardPanel){
        this.boardPanel = boardPanel;
        this.tiles = new TetrominoeC[ROW_COUNT][COL_COUNT];
    }

    /**
     * This method clean the game board
     */
    public void clear() {
        for(int i = 0; i < ROW_COUNT; i++) {
            for(int j = 0; j < COL_COUNT; j++) {
                tiles[i][j] = null;
            }
        }
    }

    /**
     *This method returns if valid and empty
     * @param piece, is type of the piece
     * @param x, x is the X coordinate where the tile is located
     * @param y, y is the Y coordinate where the tile is located
     * @param rotation, rotation is the number to rotate
     * @return  True or False
     */
    public boolean isValidAndEmpty(TetrominoeC piece, int x, int y, int rotation) {

        if(x < -piece.getLeftInset(rotation) || x + piece.getDimension()
                - piece.getRightInset(rotation) >= COL_COUNT) {
            return false;
        }

        if(y < -piece.getTopInset(rotation) || y + piece.getDimension()
                - piece.getBottomInset(rotation) >= ROW_COUNT) {
            return false;
        }

        for(int col = 0; col < piece.getDimension(); col++) {
            for(int row = 0; row < piece.getDimension(); row++) {
                if(piece.isTile(col, row, rotation) && isOccupied(x + col, y + row)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method add a piece to the board
     * @param type, is type of the piece
     * @param x, x is the X coordinate where the tile is located
     * @param y, y is the Y coordinate where the tile is located
     * @param rotation, rotation is the number to rotate
     */
    public void addPiece(TetrominoeC type, int x, int y, int rotation) {
        for(int col = 0; col < type.getDimension(); col++) {
            for(int row = 0; row < type.getDimension(); row++) {
                if(type.isTile(col, row, rotation)) {
                    setTile(col + x, row + y, type);
                }
            }
        }
    }

    /*
     * This method place the part in a position
     * @param x, x is the X coordinate where the tile is located
     * @param y, y is the Y coordinate where the tile is located
     * @param type, is type of the piece
     */
    private void setTile(int  x, int y, TetrominoeC type) {
        tiles[y][x] = type;
    }

    /**
     * This method returns how many line are complete
     * @return completedLines, is number of lines completed
     */
    public int checkLines() {
        int completedLines = 0;

        for(int row = 0; row < ROW_COUNT; row++) {
            if(checkLine(row)) {
                completedLines++;
            }
        }
        return completedLines;
    }

    /*
     * This method returns if a line is complete
     * @param line, is number of line
     * @return, True or False
     */
    private boolean checkLine(int line) {
        for(int col = 0; col < COL_COUNT; col++) {
            if(!isOccupied(col, line)) {
                return false;
            }
        }

        for (int col = 0; col < COL_COUNT; col++){
            TetrominoeC tile = getTile(col,line);
            if (tile != null && !tile.isRemovable())
                return false;
        }
        for(int row = line - 1; row >= 0; row--) {
            for(int col = 0; col < COL_COUNT; col++) {
                setTile(col,row+1,getTile(col,row));
            }
        }
        return true;
    }

    /**
     * This method returns the position of a tile
     * @param x, x is the X coordinate where the tile is located
     * @param y, y is the Y coordinate where the tile is located
     * @return location of the tile
     */
    public TetrominoeC getTile(int x, int y) {
        return tiles[y][x];
    }

    /*
     * This method returns if a position is occuped
     * @param x, x is the X coordinate where the tile is located
     * @param y, y is the Y coordinate where the tile is located
     * @return True or False
     */
    private boolean isOccupied(int x, int y) {
        boolean valid = tiles[y][x] != null;
        return valid;
    }

    /**
     * This method repaint the board
     */
    public void repaint(){
        boardPanel.repaint();
    }
}
