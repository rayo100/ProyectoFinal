package Dominio;

import Presentacion.BoardPanel;
/**
 * La clase Board es la encargada de la
 * funcionalidad de los tableros
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 09, 2021)
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

//    public static Board getBoard(BoardPanel boardPanel){
//        if(board == null) board = new Board(boardPanel);
//        return board;
//    }

    /**
     * Este metodo crea el panel del tablero del juego
     * @param boardPanel, es el panel del talero
     */
    public Board(BoardPanel boardPanel){
        this.boardPanel = boardPanel;
        this.tiles = new TetrominoeC[ROW_COUNT][COL_COUNT];
    }

    /**
     * Este metodo limpia el tablero de juego
     */
    public void clear() {
        for(int i = 0; i < ROW_COUNT; i++) {
            for(int j = 0; j < COL_COUNT; j++) {
                tiles[i][j] = null;
            }
        }
    }

    /**
     * Este metodo retorna si es valido y está vacio el tablero
     * @param piece, es el tipo de pieza
     * @param x, es la coordenada X donde esta localizada la pieza
     * @param y, es la coordenada Y donde esta localizada la pieza
     * @param rotation, es el numero de rotacion
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

    public boolean isValidAndEmpty() {
        for(int col = 0; col < 10; ++col) {
            TetrominoeC tile = this.getTile(col, 2);
            if (tile != null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Este metodo agrega una pieza en el tablero
     * @param type, es el tipo de pieza
     * @param x, es la coordenada X donde esta localizada la pieza
     * @param y, es la coordenada Y donde esta localizada la pieza
     * @param rotation, es el numero de rotacion
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
     * Este metodo coloca la pieza en una posicion
     * @param x, es la coordenada X donde esta localizada la pieza
     * @param y, es la coordenada Y donde esta localizada la pieza
     * @param type, es el numero de rotacion
     */
    private void setTile(int  x, int y, TetrominoeC type) {
        tiles[y][x] = type;
    }

    /**
     * Este metodo retorna el numero de todas las lineas que estan completas
     * @return completedLines, es el numero de lineas completas
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
     * Este metodo retorna si una linea esta completa
     * @param line, es el numero de linea
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
     * Este metodo retorna la posicion de una pieza
     * @param x, es la coordenada X donde esta localizada la pieza
     * @param y, es la coordenada Y donde esta localizada la pieza
     * @return posicion de la pieza
     */
    public TetrominoeC getTile(int x, int y) {
        return tiles[y][x];
    }

    /*
     * Este metodo retorna si una posicion esta ocupada
     * @param x, es la coordenada X
     * @param y, es la coordenada Y
     * @return True or False
     */
    private boolean isOccupied(int x, int y) {
        boolean valid = tiles[y][x] != null;
        return valid;
    }

    /**
     * Este metodo repinta el tablero
     */
    public void repaint(){
        boardPanel.repaint();
    }
}
