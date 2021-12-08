package Dominio;

import Presentacion.BoardPanel;
import java.awt.*;
import java.util.Random;
/**
 * The TetrominoeC class it is where we create the
 * game pieces and their embroidery
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 07, 2021)
 */

public class TetrominoeC {
    private final boolean[][] TYPEO = {
        {
                true, true,
                true, true,
        },
        {
                true, true,
                true, true,
        },
        {
                true, true,
                true, true,
        },
        {
                true, true,
                true, true,
        }
    };
    private final boolean[][] TYPES = {
            {
                    false, true, true,
                    true, true, false,
                    false, false, false,
            },
            {
                    false, true, false,
                    false, true, true,
                    false, false, true,
            },
            {
                    false, false, false,
                    false, true, true,
                    true, true, false,
            },
            {
                    true, false, false,
                    true, true, false,
                    false, true, false,
            }
    };
    private final boolean[][] TYPET = {
            {
                    false, true, false,
                    true, true, true,
                    false, false, false,
            },
            {
                    false, true, false,
                    false, true, true,
                    false, true, false,
            },
            {
                    false, false, false,
                    true, true, true,
                    false, true, false,
            },
            {
                    false, true, false,
                    true, true, false,
                    false, true, false,
            }
    };
    private final boolean[][] TYPEL = {
            {
                    false, false, true,
                    true, true, true,
                    false, false, false,
            },
            {
                    false, true, false,
                    false, true, false,
                    false, true, true,
            },
            {
                    false, false, false,
                    true, true, true,
                    true, false, false,
            },
            {
                    true, true, false,
                    false, true, false,
                    false, true, false,
            }
    };
    private final boolean[][] TYPEI = {
            {
                    false, false, false, false,
                    true, true, true, true,
                    false, false, false, false,
                    false, false, false, false,
            },
            {
                    false, false, true, false,
                    false, false, true, false,
                    false, false, true, false,
                    false, false, true, false,
            },
            {
                    false, false, false, false,
                    false, false, false, false,
                    true, true, true, true,
                    false, false, false, false,
            },
            {
                    false, true, false, false,
                    false, true, false, false,
                    false, true, false, false,
                    false, true, false, false,
            }
    };
    private final boolean[][][] types = {TYPEO,TYPEI,TYPET,TYPES,TYPEL};
    private final Color[] COLORS = {
            new Color(BoardPanel.COLOR_MAX, BoardPanel.COLOR_MAX,BoardPanel.COLOR_MIN),
            new Color(BoardPanel.COLOR_MIN,BoardPanel.COLOR_MAX,BoardPanel.COLOR_MAX),
            new Color(128,BoardPanel.COLOR_MIN,128),
            new Color(BoardPanel.COLOR_MIN,BoardPanel.COLOR_MAX,BoardPanel.COLOR_MIN),
            new Color(BoardPanel.COLOR_MAX,127,BoardPanel.COLOR_MIN)
    };
    private final int[] DIMENSIONS = {2,4,3,3,3};

    private final int[] COLUMNS = {2,4,3,3,3};

    private final int[] ROWS = {2,1,2,2,2};

    private final String[] BORDERTYPES = {"Negro","Plateado","Rojo","Dorado"};

    private String borderType;

    private Color baseColor;
    private Color lightColor;
    private Color darkColor;
    private int spawnCol;
    private int spawnRow;
    private int dimension;
    private int rows;
    private int cols;
    private boolean[][] tiles;

    /**
     * Create ramdomly the possible pieces and they embroidery
     */
    public TetrominoeC(){
        Random r = new Random();
        int typePiece = r.nextInt(5);
        this.borderType = BORDERTYPES[r.nextInt(1)];
        this.baseColor = COLORS[typePiece];
        assignColors();
        this.dimension = DIMENSIONS[typePiece];
        this.tiles = types[typePiece];
        this.cols = COLUMNS[typePiece];
        this.rows = ROWS[typePiece];
        this.spawnCol = 5 - (dimension >> 1);
        this.spawnRow = getTopInset(0);
    }

    /*
     * This method assigns the color of the edges of a piece
     */
    private void assignColors(){
        switch (borderType){
            case("Plateado"):
                lightColor = Color.lightGray;
                darkColor = Color.lightGray;
                break;
            case("Rojo"):
                lightColor = Color.RED;
                darkColor = Color.RED;
                break;
            case("Dorado"):
                lightColor = Color.ORANGE;
                darkColor = Color.ORANGE;
                break;
            case("Negro"):
                lightColor = Color.BLACK;
                darkColor = Color.BLACK;
                break;
        }
    }

    /**
     * This method returns the color of the base of the piece
     * @return baseColor, baseColor is the color of the base
     */
    public Color getBaseColor() {
        return baseColor;
    }

    /**
     * This method returns the light color of the piece
     * @return lightColor, lightColor is the light color of the piece
     */
    public Color getLightColor() {return lightColor;}
    /**
     * This method returns the dark color of the piece
     * @return darkColor, darkColor is the dark color of the piece
     */
    public Color getDarkColor() {return darkColor;}

    /**
     * This method returns the dimension of the piece
     * @return dimension, dimension is the dimension of the piece
     */
    public int getDimension() {return dimension;}

    /**
     * This method returns the spawn column of the piece
     * @return spawnCol, spawnCol is the spawn column
     */
    public int getSpawnColumn() {return spawnCol;}

    /**
     * This method returns the number of the rows
     * @return rows, rows is the number of the rows
     */
    public int getRows() {return rows;}

    /**
     * This method returns if there is room left to rotate
     * @param rotation, rotation is the number to rotate
     * @return, the number of spaces to the left
     */
    public int getLeftInset(int rotation) {
        for(int x = 0; x < dimension; x++) {
            for(int y = 0; y < dimension; y++) {
                if(isTile(x, y, rotation)) {
                    return x;
                }
            }
        }
        return -1;
    }

    /**
     * This method returns if there is room right to rotate
     * @param rotation, rotation is the number to rotate
     * @return, the number of spaces to the right
     */
    public int getRightInset(int rotation) {
        for(int x = dimension - 1; x >= 0; x--) {
            for(int y = 0; y < dimension; y++) {
                if(isTile(x, y, rotation)) {
                    return dimension - x;
                }
            }
        }
        return -1;
    }

    /**
     * This method returns if there is room to rotate up
     * @param rotation, rotation is the number to rotate
     * @return, the number of spaces above
     */
    public int getTopInset(int rotation) {
        for(int y = 0; y < dimension; y++) {
            for(int x = 0; x < dimension; x++) {
                if(isTile(x, y, rotation)) {
                    return y;
                }
            }
        }
        return -1;
    }

    /**
     * This method returns if there is room to rotate down
     * @param rotation, rotation is the number to rotate
     * @return, the number of spaces below
     */
    public int getBottomInset(int rotation) {
        for(int y = dimension - 1; y >= 0; y--) {
            for(int x = 0; x < dimension; x++) {
                if(isTile(x, y, rotation)) {
                    return dimension - y;
                }
            }
        }
        return -1;
    }
    /**
     * This method returns the number of the cols
     * @return cols, cols is the number of the cols
     */
    public int getCols() {return cols;}
    /**
     * This method returns the spawn row of the piece
     * @return spawnRow, spawnRow is the spawn row
     */
    public int getSpawnRow() {return spawnRow;}

    /**
     * This method returns where is the tile
     * @param x, x is the X coordinate where the tile is located
     * @param y, y is the Y coordinate where the tile is located
     * @param rotation, rotation is the number to rotate
     * @return
     */
    public boolean isTile(int x, int y, int rotation) {
        return tiles[rotation][y * dimension + x];
    }

    /**
     * This method returns if a tile has a silver border
     * @return boolean
     */
    public boolean isRemovable(){
        if (borderType.equals("Plateado")) return false;
        return true;
    }
}
