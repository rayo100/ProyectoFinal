package Dominio;

import Presentacion.BoardPanel;
import java.awt.*;
import java.util.Random;
/**
 * La clase TetrominoeC es donde creamos las
 * piezas del juego y sus bordados
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 09, 2021)
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
     * Crea aleatoriamente las posibles piezas y sus bordeados
     */
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

    /*
     * Este metodo asigna los colores de los bordes de las piezas
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
     * Este metodo retorna el color de la base de la pieza
     * @return baseColor, es el color de la base de la pieza
     */
    public Color getBaseColor() {
        return baseColor;
    }

    /**
     * Este metodo retorna el color claro de la pieza
     * @return lightColor, es el color claro de la pieza
     */
    public Color getLightColor() {return lightColor;}
    /**
     * Este metodo retorna el color oscuro de la pieza
     * @return darkColor, es el color oscuro de la pieza
     */
    public Color getDarkColor() {return darkColor;}

    /**
     * Este metodo retorna la dimension de la pieza
     * @return dimension, es la dimension de la pieza
     */
    public int getDimension() {return dimension;}

    /**
     * Este metodo retorna
     * @return
     */
    public int getSpawnColumn() {return spawnCol;}

    /**
     * Este metodo retorna el numero de filas
     * @return rows, es el numero de filas
     */
    public int getRows() {return rows;}

    /**
     * Este metodo retorna si hay espacio a la izquierda para rotar
     * @param rotation, numero de rotacion
     * @return, numero de espacios a la izquierda
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
     * Este metodo retorna si hay espacio a la derecha para rotar
     * @param rotation, numero de rotacion
     * @return, numero de espacios a la derecha
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
     * Este metodo retorna si hay espacio arriba para rotar
     * @param rotation, numero de rotacion
     * @return, numero de espacios arriba
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
     * Este metodo retorna si hay espacio abajo para rotar
     * @param rotation, numero de rotacion
     * @return, numero de espacios abajo
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
     * Este metodo retotna el numero de columnas
     * @return cols, es el numero de columnas
     */
    public int getCols() {return cols;}
    /**
     * Este metodo retorna
     * @return
     */
    public int getSpawnRow() {return spawnRow;}

    /**
     * Este metodo retorna si se puede rotar la pieza donde está
     * @param x, es la coordenada X donde se encuentra la pieza
     * @param y, es la coordenada Y donde se encuentra la pieza
     * @param rotation, numero de rotacion
     * @return True or False
     */
    public boolean isTile(int x, int y, int rotation) {
        return tiles[rotation][y * dimension + x];
    }

    /**
     * Este metodo retorna si una pieza tiene borde plateado
     * @return True or False
     */
    public boolean isRemovable(){
        if (borderType.equals("Plateado")) return false;
        return true;
    }
}
