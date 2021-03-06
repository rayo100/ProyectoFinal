package Presentacion;

import Dominio.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * La clase BoardPanel es la encargada de mostrar al o los
 * jugadores los tableros de juego
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 09, 2021)
 */

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
	private Game game;
	private Board board;

	/**
	 * Este metodo es el que crea el JPanel en el
	 * cual se muestra el tablero de juego
	 * @param game, es el motor del juego
	 */
	public BoardPanel(Game game) {
		this.game = game;
		board = new Board(this);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.BLACK);
	}

	/**
	 * Este metodo se encarga de pintar todas las componentes
	 * del tablero del juego
	 * @param g, es la grafica de los componentes
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.translate(BORDER_WIDTH, BORDER_WIDTH);
		
		if(game.isPaused()) {
			drawCase1(g);
		} else if(game.isNewGame() || game.isGameOver()) {
			drawCase2(g);
		} else {
			drawSquares(g);
			drawBoard(g);
		}
		g.setColor(Color.WHITE);
		g.drawRect(MOVEMENT, MOVEMENT, TILE_SIZE * COL_COUNT, TILE_SIZE * VISIBLE_ROW_COUNT);
	}

	/*
	 * Este metodo dibuja los cuadrados de las figuras
	 * que apareceran en el tablero
	 * @param g, es la grafica de los componentes
	 */
	private void drawSquares(Graphics g){
		for(int x = 0; x < COL_COUNT; x++) {
			for(int y = HIDDEN_ROW_COUNT; y < ROW_COUNT; y++) {
				TetrominoeC tile = board.getTile(x, y);
				if(tile != null) {
					drawTile(tile, x * TILE_SIZE, (y - HIDDEN_ROW_COUNT) * TILE_SIZE, g);
				}
			}
		}
		TetrominoeC type = game.getPieceType();
		int pieceCol = game.getPieceCol();
		int pieceRow = game.getPieceRow();
		int rotation = game.getPieceRotation();

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
			if(board.isValidAndEmpty(type, pieceCol, lowest, rotation)) {
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

	/*
	 * Este metodo dibuja el caso de pausa del juego
	 * @param g, es la grafica de los componentes
	 */
	private void drawCase1(Graphics g){
		g.setFont(LARGE_FONT);
		g.setColor(Color.BLACK);
		String msg = "PAUSED";
		g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2 + 10, CENTER_Y);
	}

	/*
	 * Este memtodo dibuja los casos de inicio y fin del juego
	 * @param g, es la grafica de los componentes
	 */
	private void drawCase2(Graphics g){
		g.setFont(LARGE_FONT);
		g.setColor(Color.BLACK);
		String msg = game.isNewGame() ? "TETRIS GAME" : "GAME OVER";
		g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2 + 10, 150);
		g.setFont(SMALL_FONT);
		msg = "Press E to Play" + (game.isNewGame() ? "" : " Again");
		g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2 + 10, 300);
	}

	/*
	 * Este metodo dibuja la ficha a aparecer en el tablero
	 * @param type, es el tipo de tetromino que ser?? el pr??ximo a salir
	 * @param x, es la posicion en X donde lo dibujaremos en la gr??fica
	 * @param y, es la posicion en Y donde lo dibujaremos en la gr??fica
	 * @param g, es la grafica de los componentes
	 */
	private void drawTile(TetrominoeC type, int x, int y, Graphics g) {
		drawTile(type.getBaseColor(), type.getLightColor(), type.getDarkColor(), x, y, g);
	}

	/*
	 * Este metodo dibuja las rectas o cuadrados del tablero de juego
	 * @param g, es la grafica de los componentes
	 */
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

	/*
	 * Este metodo la ficha a aparecer en el tablero con sus
	 * respectivos colores de la ficha y el borde
	 * @param base, es el color base de la pieza
	 * @param light, es el color claro de la pieza
	 * @param dark, es el color oscurp de la pieza
	 * @param x, es la posicion en X donde lo dibujaremos en la gr??fica
	 * @param y, es la posicion en Y donde lo dibujaremos en la gr??fica
	 * @param g, es la grafica de los componentes
	 */
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

	/**
	 * Este metodo retorna el tablero
	 * @return board, es el tablero del juego
	 */
	public Board getBoard(){
		return board;
	}
}
