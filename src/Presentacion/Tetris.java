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

	private int ancho = 600;
	private final int ALTO = 568;
	private BoardPanel board1;
	private SidePanel side;
	private BoardPanel board2;
	private TetrisGUI main;
	private Game game;
	public static boolean isTwoPlayer = false;

	public static void loadGame(TetrisGUI main){

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


	private Tetris(TetrisGUI principal, String title) {
		super(principal,title);
		this.main = principal;
		game = Game.getGame(this);
		prepareElementos();
	}

	private void prepareElementos(){
		if(isTwoPlayer) ancho += BoardPanel.PANEL_WIDTH + 50;
		setPreferredSize(new Dimension(ancho, ALTO));
		setLayout(new GridLayout());
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
		this.board1 = new BoardPanel(game);
		this.side = new SidePanel(game,this);
		if(isTwoPlayer) this.board2 = new BoardPanel(game);
	}
	private void configurarElementos(){

		Color color = JColorChooser.showDialog(null, "Choose a color", Color.WHITE);
		board1.setBackground(color);
		board1.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 8, 8),
				new TitledBorder("Board")));
		side.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),
				new TitledBorder("Game Info")));
		if (isTwoPlayer){
			color = JColorChooser.showDialog(null, "Choose a color", Color.WHITE);
			board2.setBackground(color);
			board2.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 8, 8),
					new TitledBorder("Board")));
		}
	}

	private void agregarElementos(){
		if(isTwoPlayer) add(board2);
		add(side);
		add(board1);
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
	public BoardPanel getBoard1(){
		return board1;
	}
	public SidePanel getSide(){
		return side;
	}

}
