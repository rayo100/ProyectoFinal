package Presentacion;

import Dominio.Game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Tetris extends JDialog {

	private final int ANCHO = 566;
	private final int ALTO = 568;
	private BoardPanel board;
	private SidePanel side;
	private TetrisMain main;
	private Game game;

	public static void loadGame(TetrisMain main){
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Tetris tetris = new Tetris(main, "Tetris Game");
				tetris.startGame();
			}
		};
		Thread hilo = new Thread(runnable);
		hilo.start();
	}

	private Tetris(TetrisMain principal, String title) {
		super(principal,title);
		this.main = principal;
		game = Game.getGame(this);
		prepareElementos();
	}

	private void prepareElementos(){
		setPreferredSize(new Dimension(ANCHO,ALTO));
		setLayout(new BorderLayout());
		setResizable(false);
		cargarElementos();
		configurarElementos();
		agregarElementos();
		prepararAcciones();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void cargarElementos(){
		this.board = new BoardPanel(game);
		this.side = new SidePanel(game,this);
	}
	private void configurarElementos(){

		Color color = JColorChooser.showDialog(null, "Choose a color", Color.WHITE);
		board.setBackground(color);
		board.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 8, 8),
				new TitledBorder("Board")));
		side.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),
				new TitledBorder("Game Info")));
	}

	private void agregarElementos(){
		add(board, BorderLayout.CENTER);
		add(side, BorderLayout.WEST);
	}

	private void prepararAcciones(){
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyCases(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_S:
						game.caseSPressed();
						break;
				}
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Dispose();
			}
		});
	}
	private void keyCases(KeyEvent e){
		switch(e.getKeyCode()) {
			case KeyEvent.VK_S:
				game.caseS();
				break;
			case KeyEvent.VK_A:
				game.caseA();
				break;
			case KeyEvent.VK_W:
				game.caseW();
				break;
			case KeyEvent.VK_P:
				game.caseP();
				break;
			case KeyEvent.VK_D:
				game.caseD();
				break;
			case KeyEvent.VK_E:
				game.caseE();
				break;
			case KeyEvent.VK_O:
				game.caseO();
				break;
			case KeyEvent.VK_R:
				game.caseR();
				break;
			case KeyEvent.VK_I:
				game.caseI();
				break;
//			case KeyEvent.VK_PERIOD:
//				game.casePeriod();
//				break;
		}
	}
	public void startGame() {
		game.startGame();
	}

	public void Dispose(){
		main.setVisible(true);
		super.dispose();
	}

	public String getNickname(){
		String nickname;
		nickname = JOptionPane.showInputDialog(null,
				"Player # 1 Nickname.", "Players Information",
				JOptionPane.PLAIN_MESSAGE);
		return nickname;
	}

	public int getBuffos(){
		return main.getBuffos();
	}
	public BoardPanel getBoard(){
		return board;
	}
	public SidePanel getSide(){
		return side;
	}

}
