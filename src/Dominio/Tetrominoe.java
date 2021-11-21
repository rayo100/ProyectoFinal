package Dominio;

import java.awt.*;

public class Tetrominoe {
    private final boolean[][] TYPEZ = {
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
    };
    private final boolean[][] TYPEI ={
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
    };

    private final boolean[][] TYPET = {
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
    };
    private final boolean[][] TYPEL = {
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
    };
    private final boolean[][] TYPEO ={
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
    };

    private int rows;
    private int cols;
    private int currentCol;
    private int currentrow;
    private int rotation;


    /*public int getDimension() {
        return 0;
    }

    public boolean isTile(int col, int row, int rotation) {
        return false;
    }

    public Color getBaseColor() {
        return null;
    }

    public int getLeftInset(int rotation) {
        return 0;
    }

    public int getRightInset(int rotation) {
        return 0;
    }

    public int getTopInset(int rotation) {
        return 0;
    }

    public int getBottomInset(int rotation) {
        return 0;
    }

    public Color getLightColor() {
        return null;
    }

    public Color getDarkColor() {
        return null;
    }*/
}
