package Dominio;

import Presentacion.Board;

import java.awt.*;

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

    private int getTopInset(int rotation) {
        return 0;
    }
}