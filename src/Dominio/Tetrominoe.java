package Dominio;

import Ayudas.TileType;
import Presentacion.Board;

import java.awt.*;
import java.util.Random;


public enum Tetrominoe {
    TYPEI(new Color(Board.COLORMIN, Board.COLORMAX, Board.COLORMAX), 4, 4, 1, new boolean[][] {
        {
                false,	false,	false,	false,
                true,	true,	true,	true,
                false,	false,	false,	false,
                false,	false,	false,	false,
        },
        {
                false,	false,	true,	false,
                false,	false,	true,	false,
                false,	false,	true,	false,
                false,	false,	true,	false,
        },
        {
                false,	false,	false,	false,
                false,	false,	false,	false,
                true,	true,	true,	true,
                false,	false,	false,	false,
        },
        {
                false,	true,	false,	false,
                false,	true,	false,	false,
                false,	true,	false,	false,
                false,	true,	false,	false,
        }
    }),
    /**
     * Piece TypeL.
     */
    TYPEL(new Color(Board.COLORMAX, 127, Board.COLORMIN), 3, 3, 2, new boolean[][] {
            {
                    false,	false,	true,
                    true,	true,	true,
                    false,	false,	false,
            },
            {
                    false,	true,	false,
                    false,	true,	false,
                    false,	true,	true,
            },
            {
                    false,	false,	false,
                    true,	true,	true,
                    true,	false,	false,
            },
            {
                    true,	true,	false,
                    false,	true,	false,
                    false,	true,	false,
            }
    }),

    /**
     * Piece TypeO.
     */
    TYPEO(new Color(Board.COLORMAX, Board.COLORMAX, Board.COLORMIN), 2, 2, 2, new boolean[][] {
            {
                    true,	true,
                    true,	true,
            },
            {
                    true,	true,
                    true,	true,
            },
            {
                    true,	true,
                    true,	true,
            },
            {
                    true,	true,
                    true,	true,
            }
    }),

    /**
     * Piece TypeS.
     */
    TYPES(new Color(Board.COLORMIN, Board.COLORMAX, Board.COLORMIN), 3, 3, 2, new boolean[][] {
            {
                    false,	true,	true,
                    true,	true,	false,
                    false,	false,	false,
            },
            {
                    false,	true,	false,
                    false,	true,	true,
                    false,	false,	true,
            },
            {
                    false,	false,	false,
                    false,	true,	true,
                    true,	true,	false,
            },
            {
                    true,	false,	false,
                    true,	true,	false,
                    false,	true,	false,
            }
    }),

    /**
     * Piece TypeT.
     */
    TYPET(new Color(128, Board.COLORMIN, 128), 3, 3, 2, new boolean[][] {
            {
                    false,	true,	false,
                    true,	true,	true,
                    false,	false,	false,
            },
            {
                    false,	true,	false,
                    false,	true,	true,
                    false,	true,	false,
            },
            {
                    false,	false,	false,
                    true,	true,	true,
                    false,	true,	false,
            },
            {
                    false,	true,	false,
                    true,	true,	false,
                    false,	true,	false,
            }
    }),

    ;
    private final Color baseColor;
    private final Color lightColor;
    private final Color darkColor;
    private final int dimension;
    private final boolean[][] tiles;
    private final int cols;
    private final int rows;
    private final int spawnCol;
    private final int spawnRow;

    Tetrominoe(Color color, int dimension, int cols, int rows, boolean[][] tiles) {
        this.baseColor = color;
        this.lightColor = Color.BLACK;
        this.darkColor = Color.BLACK;
        this.dimension = dimension;
        this.tiles = tiles;
        this.cols = cols;
        this.rows = rows;

        this.spawnCol = 5 - (dimension >> 1);
        this.spawnRow = getTopInset(0);
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

    public int getSpawnRow() {
        return spawnRow;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isTile(int x, int y, int rotation) {
        return tiles[rotation][y * dimension + x];
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
        /*
         * Loop through from right to left until we find a tile then return
         * the column.
         */
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
        /*
         * Loop through from top to bottom until we find a tile then return
         * the row.
         */
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
    public Tetrominoe makePiece(){
        Random r = new Random();
        int tetrominotypesCount = Tetrominoe.values().length;
        Tetrominoe ficha = Tetrominoe.values()[r.nextInt(tetrominotypesCount)];
        return ficha;
    }
}