package Presentacion;

import Dominio.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;

public class SidePanel extends JPanel {
	
	private static final int TILE_SIZE = BoardPanel.TILE_SIZE >> 1;
	private static final int SHADE_WIDTH = BoardPanel.SHADE_WIDTH >> 1;
	private static final int TILE_COUNT = 5;
	private static final int SQUARE_CENTER_X = 130;
	private static final int SQUARE_CENTER_Y = 65;
	private static final int SQUARE_SIZE = (TILE_SIZE * TILE_COUNT >> 1);
	private static final int SMALL_INSET = 20;
	private static final int LARGE_INSET = 20;
	private static final int LARGE_INSET2 = LARGE_INSET +150;
	private static final int STATS_INSET = 120;
	private static final int CONTROLS_INSET = 280;
	private static final int TEXT_STRIDE = 25;
	private static final Font SMALL_FONT = new Font("Times New Roman", Font.BOLD, 14);
	private static final Font LARGE_FONT = new Font("Times New Roman", Font.BOLD, 16);
	private static final Color DRAW_COLOR = Color.BLACK;
	private Game game;
	private String nickname;
	private int noBuffos;
	private Clock time;
	
	public SidePanel(Game game, Tetris tetris) {
		this.game = game;
		nickname = tetris.getNickname();
		noBuffos = tetris.getBuffos();
		setPreferredSize(new Dimension(BoardPanel.PANEL_WIDTH,
				BoardPanel.PANEL_HEIGHT));
		setBackground(Color.WHITE);
		setLayout(null);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(DRAW_COLOR);
		int offset;
		g.setFont(LARGE_FONT);
		g.drawString("Stats", SMALL_INSET, offset = STATS_INSET);
		g.setFont(SMALL_FONT);
		g.drawString("Nickname: " + nickname, LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("Buffos: " + noBuffos, LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("Level: " + game.getLevel(), LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("Score: " + game.getScore(), LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("Velocity: " + game.getGameSpeed(), LARGE_INSET, offset += TEXT_STRIDE);
		g.setFont(LARGE_FONT);
		g.drawString("Controls player 1", SMALL_INSET, offset = CONTROLS_INSET);
		g.setFont(SMALL_FONT);
		player1(offset,g);
		if (Tetris.isTwoPlayer){
			g.drawString("Controls player 2", LARGE_INSET2, offset = CONTROLS_INSET);
			player2(offset,g);
		}
		if(!game.isNewGame()) drawNextPiece(g);
	}

	private void player1(int offset, Graphics g){
		g.drawString("A - Move Left", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("D - Move Right", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("W - Rotate piece", LARGE_INSET,offset += TEXT_STRIDE);
		g.drawString("S - Drop", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("P - Pause Game", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("O - Save Game", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("R - Reset Game", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString(". - Use a Buffo", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("I - Exit Game", LARGE_INSET, offset += TEXT_STRIDE);
	}
	private void player2(int offset, Graphics g){
		g.drawString("A - Move Left", LARGE_INSET2, offset += TEXT_STRIDE);
		g.drawString("D - Move Right", LARGE_INSET2, offset += TEXT_STRIDE);
		g.drawString("W - Rotate piece", LARGE_INSET2,offset += TEXT_STRIDE);
		g.drawString("S - Drop", LARGE_INSET2, offset += TEXT_STRIDE);
		g.drawString("P - Pause Game", LARGE_INSET2, offset += TEXT_STRIDE);
		g.drawString("O - Save Game", LARGE_INSET2, offset += TEXT_STRIDE);
		g.drawString("R - Reset Game", LARGE_INSET2, offset += TEXT_STRIDE);
		g.drawString(". - Use a Buffo", LARGE_INSET2, offset += TEXT_STRIDE);
		g.drawString("I - Exit Game", LARGE_INSET2, offset += TEXT_STRIDE);
	}

	private void drawNextPiece(Graphics g){
		g.setFont(LARGE_FONT);
		g.drawString("Next Piece:", SMALL_INSET, 70);
		g.drawRect(SQUARE_CENTER_X - SQUARE_SIZE, SQUARE_CENTER_Y - SQUARE_SIZE, SQUARE_SIZE * 2, SQUARE_SIZE * 2);

		TetrominoeC type = game.getNextPieceType();
		if(!game.isGameOver() && type != null) {
			int cols = type.getCols();
			int rows = type.getRows();
			int dimension = type.getDimension();
			int startX = (SQUARE_CENTER_X - (cols * TILE_SIZE / 2));
			int startY = (SQUARE_CENTER_Y - (rows * TILE_SIZE / 2));
			int top = type.getTopInset(0);
			int left = type.getLeftInset(0);
			for(int row = 0; row < dimension; row++) {
				for(int col = 0; col < dimension; col++) {
					if(type.isTile(col, row, 0)) {
						drawTile(type, startX + ((col - left) * TILE_SIZE), startY + ((row - top) * TILE_SIZE), g);
					}
				}
			}
		}
	}
	private void drawTile(TetrominoeC type, int x, int y, Graphics g) {
		g.setColor(type.getBaseColor());
		g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
		g.setColor(type.getDarkColor());
		g.fillRect(x, y + TILE_SIZE - SHADE_WIDTH, TILE_SIZE, SHADE_WIDTH);
		g.fillRect(x + TILE_SIZE - SHADE_WIDTH, y, SHADE_WIDTH, TILE_SIZE);
		g.setColor(type.getLightColor());
		for(int i = 0; i < SHADE_WIDTH; i++) {
			g.drawLine(x, y + i, x + TILE_SIZE - i - 1, y + i);
			g.drawLine(x + i, y, x + i, y + TILE_SIZE - i - 1);
		}
	}

}
