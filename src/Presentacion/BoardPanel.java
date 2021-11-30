package Presentacion;

import Dominio.Tetrominoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {

	public static final int COLOR_MIN = 35;
	public static final int COLOR_MAX = 255 - COLOR_MIN;
	private static final int BORDER_WIDTH = 5;
	public static final int COL_COUNT = 10;
	private static final int VISIBLE_ROW_COUNT = 20;
	private static final int HIDDEN_ROW_COUNT = 2;
	public static final int ROW_COUNT = VISIBLE_ROW_COUNT + HIDDEN_ROW_COUNT;
	public static final int TILE_SIZE = 24;
	public static final int SHADE_WIDTH = 4;
	private static final int CENTER_X = COL_COUNT * TILE_SIZE / 2;
	private static final int CENTER_Y = VISIBLE_ROW_COUNT * TILE_SIZE / 2;
	public static final int PANEL_WIDTH = COL_COUNT * TILE_SIZE + BORDER_WIDTH * 2;
	public static final int PANEL_HEIGHT = VISIBLE_ROW_COUNT * TILE_SIZE + BORDER_WIDTH * 2;
	private final int MOVEMENT = 25;
	private static final Font LARGE_FONT = new Font("Times New Roman", Font.BOLD, 18);
	private static final Font SMALL_FONT = new Font("Times New Roman", Font.BOLD, 14);
	private Tetris tetris;
	private Tetrominoe[][] tiles;
		
	public BoardPanel(Tetris tetris) {
		this.tetris = tetris;
		this.tiles = new Tetrominoe[ROW_COUNT][COL_COUNT];
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.BLACK);
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
				setTile(col, row + 1, getTile(col, row));
			}
		}
		return true;
	}
	
	private boolean isOccupied(int x, int y) {
		return tiles[y][x] != null;
	}
	
	private void setTile(int  x, int y, Tetrominoe type) {
		tiles[y][x] = type;
	}
		
	private Tetrominoe getTile(int x, int y) {
		return tiles[y][x];
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.translate(BORDER_WIDTH, BORDER_WIDTH);
		
		if(tetris.isPaused()) {
			drawCase1(g);
		} else if(tetris.isNewGame() || tetris.isGameOver()) {
			drawCase2(g);
		} else {
			drawSquares(g);
			drawBoard(g);
		}
		g.setColor(Color.WHITE);
		g.drawRect(MOVEMENT, MOVEMENT, TILE_SIZE * COL_COUNT, TILE_SIZE * VISIBLE_ROW_COUNT);
	}

	private void drawSquares(Graphics g){
		for(int x = 0; x < COL_COUNT; x++) {
			for(int y = HIDDEN_ROW_COUNT; y < ROW_COUNT; y++) {
				Tetrominoe tile = getTile(x, y);
				if(tile != null) {
					drawTile(tile, x * TILE_SIZE, (y - HIDDEN_ROW_COUNT) * TILE_SIZE, g);
				}
			}
		}
		Tetrominoe type = tetris.getPieceType();
		int pieceCol = tetris.getPieceCol();
		int pieceRow = tetris.getPieceRow();
		int rotation = tetris.getPieceRotation();

		for(int col = 0; col < type.getDimension(); col++) {
			for(int row = 0; row < type.getDimension(); row++) {
				if(pieceRow + row >= 2 && type.isTile(col, row, rotation)) {
					drawTile(type, (pieceCol + col) * TILE_SIZE, (pieceRow + row - HIDDEN_ROW_COUNT) * TILE_SIZE, g);
				}
			}
		}

		Color base = type.getBaseColor();
		base = new Color(base.getRed(), base.getGreen(), base.getBlue(), 20);
		for(int lowest = pieceRow; lowest < ROW_COUNT; lowest++) {
			if(isValidAndEmpty(type, pieceCol, lowest, rotation)) {
				continue;
			}

			lowest--;

			for(int col = 0; col < type.getDimension(); col++) {
				for(int row = 0; row < type.getDimension(); row++) {
					if(lowest + row >= 2 && type.isTile(col, row, rotation)) {
						drawTile(base, base.brighter(), base.darker(), (pieceCol + col) * TILE_SIZE, (lowest + row - HIDDEN_ROW_COUNT) * TILE_SIZE, g);
					}
				}
			}

			break;
		}
	}

	private void drawCase1(Graphics g){
		g.setFont(LARGE_FONT);
		g.setColor(Color.BLACK);
		String msg = "PAUSED";
		g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2 + 10, CENTER_Y);
	}
	private void drawCase2(Graphics g){
		g.setFont(LARGE_FONT);
		g.setColor(Color.BLACK);
		String msg = tetris.isNewGame() ? "TETRIS GAME" : "GAME OVER";
		g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2 + 10, 150);
		g.setFont(SMALL_FONT);
		msg = "Press E to Play" + (tetris.isNewGame() ? "" : " Again");
		g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2 + 10, 300);
	}

	private void drawTile(Tetrominoe type, int x, int y, Graphics g) {
		drawTile(type.getBaseColor(), type.getLightColor(), type.getDarkColor(), x, y, g);
	}
	private void drawBoard(Graphics g){
		g.setColor(Color.DARK_GRAY);
		for(int col = 0; col < COL_COUNT; col++){
			for(int row = 0; row < VISIBLE_ROW_COUNT; row++){
				g.drawLine(MOVEMENT,row*TILE_SIZE+MOVEMENT,COL_COUNT*TILE_SIZE+MOVEMENT,row*TILE_SIZE+MOVEMENT);
				g.drawLine(col*TILE_SIZE+MOVEMENT,MOVEMENT,col*TILE_SIZE+MOVEMENT,
						VISIBLE_ROW_COUNT*TILE_SIZE+MOVEMENT);
			}
		}
		g.setColor(Color.BLACK);
		g.drawRect(MOVEMENT,MOVEMENT,(TILE_SIZE*COL_COUNT),(TILE_SIZE*COL_COUNT));
	}
	private void drawTile(Color base, Color light, Color dark, int x, int y, Graphics g) {
		
		g.setColor(base);
		g.fillRect(x+MOVEMENT, y+MOVEMENT, TILE_SIZE, TILE_SIZE);
		g.setColor(Color.BLACK);
		g.fillRect(x+MOVEMENT, y+MOVEMENT + TILE_SIZE - SHADE_WIDTH, TILE_SIZE, SHADE_WIDTH);
		g.fillRect(x + TILE_SIZE - SHADE_WIDTH+MOVEMENT, y+MOVEMENT, SHADE_WIDTH, TILE_SIZE);
		
		g.setColor(light);
		for(int i = 0; i < SHADE_WIDTH; i++) {
			g.drawLine(x+MOVEMENT, y+MOVEMENT + i, x+MOVEMENT + TILE_SIZE - i - 1, y +MOVEMENT+ i);
			g.drawLine(x+MOVEMENT + i, y+MOVEMENT, x + i+MOVEMENT, y +MOVEMENT+ TILE_SIZE - i - 1);
		}
	}

}
