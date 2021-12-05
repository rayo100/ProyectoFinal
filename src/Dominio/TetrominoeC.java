package Dominio;

import Presentacion.BoardPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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

    public TetrominoeC(){
        Random r = new Random();
        int typePiece = r.nextInt(5);
        this.borderType = BORDERTYPES[r.nextInt(4)];
        this.baseColor = COLORS[typePiece];
        assignColors();
        this.dimension = DIMENSIONS[typePiece];
        this.tiles = types[typePiece];
        this.cols = COLUMNS[typePiece];
        this.rows = ROWS[typePiece];
        this.spawnCol = 5 - (dimension >> 1);
        this.spawnRow = getTopInset(0);
    }

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

    public Color getBaseColor() {
        return baseColor;
    }
    public Color getLightColor() {
        return lightColor;
    }

    public Color getDarkColor() {
        return darkColor;
    }
    public int getDimension() {
        return dimension;
    }

    public int getSpawnColumn() {
        return spawnCol;
    }
    public int getRows() {
        return rows;
    }
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

    public int getCols() {
        return cols;
    }

    public int getSpawnRow() {
        return spawnRow;
    }

    public boolean isTile(int x, int y, int rotation) {
        return tiles[rotation][y * dimension + x];
    }
    public boolean isRemovable(){
        if (borderType.equals("Plateado")) return false;
        return true;
    }
}
