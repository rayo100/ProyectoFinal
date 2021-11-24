package Presentacion;

import Dominio.Clock;
import Dominio.Tetrominoe;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.*;

public class Tetris extends JFrame {

	private static final long FRAME_TIME = 1000L / 50L;
	private static final int TYPE_COUNT = Tetrominoe.values().length;
	private BoardPanel board;
	private SidePanel side;
	private boolean isPaused;
	private boolean isNewGame;
	private boolean isGameOver;
	private int level;
	private int score;
	private Random random;
	private Clock logicTimer;
	private Tetrominoe currentType;
	private Tetrominoe nextType;
	private int currentCol;
	private int currentRow;
	private int currentRotation;
	private int dropCooldown;
	private float gameSpeed;
	//private TetrisMain main;
	private Tetris(String title) {
		super(title);
		//this.main = main;
		prepareElementos();
		setVisible(true);
	}
	public static void main(String[] args){
		Tetris gui = new Tetris("Tetris Game");
		gui.startGame();

	}

	private void prepareElementos(){
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		cargarElementos();
		//configurarElementos();
		agregarElementos();
		prepararAcciones();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void cargarElementos(){
		this.board = new BoardPanel(this);
		this.side = new SidePanel(this);
	}
	private void configurarElementos(){
		side.setVisible(true);
	}

	private void agregarElementos(){
		add(board, BorderLayout.EAST);
		add(side, BorderLayout.CENTER);
	}

	private void prepararAcciones(){
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_S:
						if(!isPaused && dropCooldown == 0) {
							logicTimer.setCyclesPerSecond(25.0f);
						}
						break;
					case KeyEvent.VK_A:
						if(!isPaused && board.isValidAndEmpty(currentType, currentCol - 1, currentRow, currentRotation)) {
							currentCol--;
						}
						break;
					case KeyEvent.VK_D:
						if(!isPaused && board.isValidAndEmpty(currentType, currentCol + 1, currentRow, currentRotation)) {
							currentCol++;
						}
						break;
					case KeyEvent.VK_Q:
						if(!isPaused) {
							rotatePiece((currentRotation == 0) ? 3 : currentRotation - 1);
						}
						break;
					case KeyEvent.VK_E:
						if(!isPaused) {
							rotatePiece((currentRotation == 3) ? 0 : currentRotation + 1);
						}
						break;
					case KeyEvent.VK_P:
						if(!isGameOver && !isNewGame) {
							isPaused = !isPaused;
							logicTimer.setPaused(isPaused);
						}
						break;
					case KeyEvent.VK_ENTER:
						if(isGameOver || isNewGame) {
							resetGame();
						}
						break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_S:
						logicTimer.setCyclesPerSecond(gameSpeed);
						logicTimer.reset();
						break;
				}

			}

		});
	}

	private void startGame() {
		this.random = new Random();
		this.isNewGame = true;
		this.gameSpeed = 1.0f;
		this.logicTimer = new Clock(gameSpeed);
		logicTimer.setPaused(true);
		while(true) {
			long start = System.nanoTime();
			logicTimer.update();
			if(logicTimer.hasElapsedCycle()) {
				updateGame();
			}
			if(dropCooldown > 0) {
				dropCooldown--;
			}
			renderGame();
			long delta = (System.nanoTime() - start) / 1000000L;
			if(delta < FRAME_TIME) {
				try {
					Thread.sleep(FRAME_TIME - delta);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void updateGame() {
		if(board.isValidAndEmpty(currentType, currentCol, currentRow + 1, currentRotation)) {
			currentRow++;
		} else {
			board.addPiece(currentType, currentCol, currentRow, currentRotation);
			int cleared = board.checkLines();
			if(cleared > 0) {
				score += 50 << cleared;
			}
			gameSpeed += 0.035f;
			logicTimer.setCyclesPerSecond(gameSpeed);
			logicTimer.reset();
			dropCooldown = 25;
			level = (int)(gameSpeed * 1.70f);
			
			/*
			 * Spawn a new piece to control.
			 */
			spawnPiece();
		}		
	}
	
	/**
	 * Forces the BoardPanel and SidePanel to repaint.
	 */
	private void renderGame() {
		board.repaint();
		side.repaint();
	}
	
	/**
	 * Resets the game variables to their default values at the start
	 * of a new game.
	 */
	private void resetGame() {
		this.level = 1;
		this.score = 0;
		this.gameSpeed = 1.0f;
		this.nextType = Tetrominoe.values()[random.nextInt(TYPE_COUNT)];
		this.isNewGame = false;
		this.isGameOver = false;		
		board.clear();
		logicTimer.reset();
		logicTimer.setCyclesPerSecond(gameSpeed);
		spawnPiece();
	}
		
	/**
	 * Spawns a new piece and resets our piece's variables to their default
	 * values.
	 */
	private void spawnPiece() {
		/*
		 * Poll the last piece and reset our position and rotation to
		 * their default variables, then pick the next piece to use.
		 */
		this.currentType = nextType;
		this.currentCol = currentType.getSpawnColumn();
		this.currentRow = currentType.getSpawnRow();
		this.currentRotation = 0;
		this.nextType = Tetrominoe.values()[random.nextInt(TYPE_COUNT)];
		
		/*
		 * If the spawn point is invalid, we need to pause the game and flag that we've lost
		 * because it means that the pieces on the board have gotten too high.
		 */
		if(!board.isValidAndEmpty(currentType, currentCol, currentRow, currentRotation)) {
			this.isGameOver = true;
			logicTimer.setPaused(true);
		}		
	}

	/**
	 * Attempts to set the rotation of the current piece to newRotation.
	 * @param newRotation The rotation of the new peice.
	 */
	private void rotatePiece(int newRotation) {
		/*
		 * Sometimes pieces will need to be moved when rotated to avoid clipping
		 * out of the board (the I piece is a good example of this). Here we store
		 * a temporary row and column in case we need to move the tile as well.
		 */
		int newColumn = currentCol;
		int newRow = currentRow;
		
		/*
		 * Get the insets for each of the sides. These are used to determine how
		 * many empty rows or columns there are on a given side.
		 */
		int left = currentType.getLeftInset(newRotation);
		int right = currentType.getRightInset(newRotation);
		int top = currentType.getTopInset(newRotation);
		int bottom = currentType.getBottomInset(newRotation);
		
		/*
		 * If the current piece is too far to the left or right, move the piece away from the edges
		 * so that the piece doesn't clip out of the map and automatically become invalid.
		 */
		if(currentCol < -left) {
			newColumn -= currentCol - left;
		} else if(currentCol + currentType.getDimension() - right >= BoardPanel.COL_COUNT) {
			newColumn -= (currentCol + currentType.getDimension() - right) - BoardPanel.COL_COUNT + 1;
		}
		
		/*
		 * If the current piece is too far to the top or bottom, move the piece away from the edges
		 * so that the piece doesn't clip out of the map and automatically become invalid.
		 */
		if(currentRow < -top) {
			newRow -= currentRow - top;
		} else if(currentRow + currentType.getDimension() - bottom >= BoardPanel.ROW_COUNT) {
			newRow -= (currentRow + currentType.getDimension() - bottom) - BoardPanel.ROW_COUNT + 1;
		}
		
		/*
		 * Check to see if the new position is acceptable. If it is, update the rotation and
		 * position of the piece.
		 */
		if(board.isValidAndEmpty(currentType, newColumn, newRow, newRotation)) {
			currentRotation = newRotation;
			currentRow = newRow;
			currentCol = newColumn;
		}
	}
	
	/**
	 * Checks to see whether or not the game is paused.
	 * @return Whether or not the game is paused.
	 */
	public boolean isPaused() {
		return isPaused;
	}
	
	/**
	 * Checks to see whether or not the game is over.
	 * @return Whether or not the game is over.
	 */
	public boolean isGameOver() {
		return isGameOver;
	}
	
	/**
	 * Checks to see whether or not we're on a new game.
	 * @return Whether or not this is a new game.
	 */
	public boolean isNewGame() {
		return isNewGame;
	}
	
	/**
	 * Gets the current score.
	 * @return The score.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Gets the current level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Gets the current type of piece we're using.
	 * @return The piece type.
	 */
	public Tetrominoe getPieceType() {
		return currentType;
	}
	
	/**
	 * Gets the next type of piece we're using.
	 * @return The next piece.
	 */
	public Tetrominoe getNextPieceType() {
		return nextType;
	}
	
	/**
	 * Gets the column of the current piece.
	 * @return The column.
	 */
	public int getPieceCol() {
		return currentCol;
	}
	
	/**
	 * Gets the row of the current piece.
	 * @return The row.
	 */
	public int getPieceRow() {
		return currentRow;
	}
	
	/**
	 * Gets the rotation of the current piece.
	 * @return The rotation.
	 */
	public int getPieceRotation() {
		return currentRotation;
	}

	/**
	 * Entry-point of the game. Responsible for creating and starting a new
	 * game instance.
	 * @param args Unused.
	 */

}
