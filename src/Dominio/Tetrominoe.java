package Dominio;

import java.awt.*;

public class Tetrominoe {
    public int getDimension() {
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
    }
}
