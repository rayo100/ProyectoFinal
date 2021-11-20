package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class NewFigurePanel extends JPanel {

    private static final int TILESIZE = Board.TILESIZE / 2;
    private static final int SHADEWIDTH = Board.SHADEWIDTH >> 1;
    private static final int TILECOUNT = 5;
    private static final int SQUARECENTER_X = 130;
    private static final int SQUARECENTER_Y = 65;
    private static final int SQUARESIZE = (TILESIZE * TILECOUNT >> 1);
    private static final int SMALL_INSET = 20;
    private static final int LARGE_INSET = 40;
    private static final int STATS_INSET = 175;
    private static final int CONTROLS_INSET = 300;
    private static final int TEXT_STRIDE = 25;
    private static final Font SMALL_FONT = new Font("Arial", Font.BOLD, 11);
    private static final Font LARGE_FONT = new Font("Arial Bold", Font.BOLD, 13);
    private static final Color DRAW_COLOR = Color.BLACK;
    private Tetris1 tetris;

    /**
     * Creates a new SidePanel and sets it's display properties.
     * @param tetris The Tetris instance to use.
     */
    public NewFigurePanel(Tetris1 tetris) {
        this.tetris = tetris;
        setPreferredSize(new Dimension(Board.PANELWIDTH, Board.PANELHEIGHT));
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(DRAW_COLOR);
        int offset;

        /*
         * Draw the "Stats" category.
         */
        g.setFont(LARGE_FONT);
        g.drawString("Stats", SMALL_INSET, offset = STATS_INSET);
        g.setFont(SMALL_FONT);
        //g.drawString("Level: " + tetris.getLevel(), LARGE_INSET, offset += TEXT_STRIDE);
        //g.drawString("Score: " + tetris.getScore(), LARGE_INSET, offset += TEXT_STRIDE);

        /*
         * Draw the "Controls" category.
         */
        g.setFont(LARGE_FONT);
        g.drawString("Controls Player one", SMALL_INSET, offset = CONTROLS_INSET);
        g.setFont(SMALL_FONT);
        g.drawString("A - Move Left", LARGE_INSET, offset += TEXT_STRIDE);
        g.drawString("D - Move Right", LARGE_INSET, offset += TEXT_STRIDE);
        g.drawString("Q - Rotate Anticlockwise", LARGE_INSET, offset += TEXT_STRIDE);
        g.drawString("E - Rotate Clockwise", LARGE_INSET, offset += TEXT_STRIDE);
        g.drawString("S - Drop", LARGE_INSET, offset += TEXT_STRIDE);
        g.drawString("P - Pause Game", LARGE_INSET, offset += TEXT_STRIDE);

        /*
         * Draw the next piece preview box.
         */
        g.setFont(LARGE_FONT);
        g.drawString("Next Piece:", SMALL_INSET, 70);
        g.drawRect(SQUARECENTER_X - SQUARESIZE, SQUARECENTER_Y - SQUARESIZE, SQUARESIZE * 2, SQUARESIZE * 2);

        /*
         * Draw a preview of the next piece that will be spawned. The code is pretty much
         * identical to the drawing code on the board, just smaller and centered, rather
         * than constrained to a grid.
         */
        //TileType type = tetris.getNextPieceType();
//        if(!tetris.isGameOver() && type != null) {
//            /*
//             * Get the size properties of the current piece.
//             */
//            int cols = type.getCols();
//            int rows = type.getRows();
//            int dimension = type.getDimension();
//
//            /*
//             * Calculate the top left corner (origin) of the piece.
//             */
//            int startX = (SQUARE_CENTER_X - (cols * TILE_SIZE / 2));
//            int startY = (SQUARE_CENTER_Y - (rows * TILE_SIZE / 2));
//
//            /*
//             * Get the insets for the preview. The default
//             * rotation is used for the preview, so we just use 0.
//             */
//            int top = type.getTopInset(0);
//            int left = type.getLeftInset(0);
//
//            /*
//             * Loop through the piece and draw it's tiles onto the preview.
//             */
//            for(int row = 0; row < dimension; row++) {
//                for(int col = 0; col < dimension; col++) {
//                    if(type.isTile(col, row, 0)) {
//                        drawTile(type, startX + ((col - left) * TILE_SIZE), startY + ((row - top) * TILE_SIZE), g);
//                    }
//                }
//            }
//        }

    }

    /**
     * Draws a tile onto the preview window.
     * @param type The type of tile to draw.
     * @param x The x coordinate of the tile.
     * @param y The y coordinate of the tile.
     * @param g The graphics object.
     */
//    private void drawTile(TileType type, int x, int y, Graphics g) {
//        /*
//         * Fill the entire tile with the base color.
//         */
//        g.setColor(type.getBaseColor());
//        g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
//
//        /*
//         * Fill the bottom and right edges of the tile with the dark shading color.
//         */
//        g.setColor(type.getDarkColor());
//        g.fillRect(x, y + TILE_SIZE - SHADE_WIDTH, TILE_SIZE, SHADE_WIDTH);
//        g.fillRect(x + TILE_SIZE - SHADE_WIDTH, y, SHADE_WIDTH, TILE_SIZE);
//
//        /*
//         * Fill the top and left edges with the light shading. We draw a single line
//         * for each row or column rather than a rectangle so that we can draw a nice
//         * looking diagonal where the light and dark shading meet.
//         */
//        g.setColor(type.getLightColor());
//        for(int i = 0; i < SHADE_WIDTH; i++) {
//            g.drawLine(x, y + i, x + TILE_SIZE - i - 1, y + i);
//            g.drawLine(x + i, y, x + i, y + TILE_SIZE - i - 1);
//        }
//    }

}
