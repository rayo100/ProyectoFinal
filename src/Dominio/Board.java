package Dominio;

import Presentacion.BoardPanel;
import Presentacion.Tetris;

public class Board {
    private static Board board;
    private BoardPanel boardPanel;

    //Atributos tablero
    public static final int COL_COUNT = 10;
    private static final int VISIBLE_ROW_COUNT = 20;
    private static final int HIDDEN_ROW_COUNT = 2;
    public static final int ROW_COUNT = VISIBLE_ROW_COUNT + HIDDEN_ROW_COUNT;
    private Tetrominoe[][] tiles;

    public static Board getBoard(BoardPanel boardPanel){
        if(board == null) board = new Board(boardPanel);
        return board;
    }

    private Board(BoardPanel boardPanel){
        this.boardPanel = boardPanel;
        this.tiles = new Tetrominoe[ROW_COUNT][COL_COUNT];
    }


    public void clear() {
        for(int i = 0; i < ROW_COUNT; i++) {
            for(int j = 0; j < COL_COUNT; j++) {
                tiles[i][j] = null;
            }
        }
    }

    public boolean isValidAndEmpty(Tetrominoe piece, int x, int y, int rotation) {

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

    public void addPiece(Tetrominoe type, int x, int y, int rotation) {
        for(int col = 0; col < type.getDimension(); col++) {
            for(int row = 0; row < type.getDimension(); row++) {
                if(type.isTile(col, row, rotation)) {
                    setTile(col + x, row + y, type);
                }
            }
        }
    }

    private void setTile(int  x, int y, Tetrominoe type) {
        tiles[y][x] = type;
    }

    public int checkLines() {
        int completedLines = 0;

        for(int row = 0; row < ROW_COUNT; row++) {
            if(checkLine(row)) {
                completedLines++;
            }
        }
        return completedLines;
    }

    private boolean checkLine(int line) {
        for(int col = 0; col < COL_COUNT; col++) {
            if(!isOccupied(col, line)) {
                return false;
            }
        }


        for(int row = line - 1; row >= 0; row--) {
            for(int col = 0; col < COL_COUNT; col++) {
                setTile(col,row+1,getTile(col,row));
            }
        }
        return true;
    }

    private boolean checkValidPiece(int row){

        for (int col = 0; col < COL_COUNT; col++){
            Tetrominoe tile = getTile(col,row);
            if(tile != null) if (tile.getType() == "Plateado") return false;
        }
        return true;
    }

    public Tetrominoe getTile(int x, int y) {
        return tiles[y][x];
    }
    private boolean isOccupied(int x, int y) {
        boolean valid = tiles[y][x] != null;
        return valid;
    }
    public void repaint(){
        boardPanel.repaint();
    }
}
