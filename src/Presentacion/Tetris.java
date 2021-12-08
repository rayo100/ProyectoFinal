package Presentacion;

import Dominio.Game;
import Dominio.TetrisException;

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
	private POOBtrizGUI main;
	private String nickname;
	private Game game1;
	private Game game2;

	public static boolean isTwoPlayer = true;


	public static void loadGame(POOBtrizGUI main){

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Tetris tetris = new Tetris(main, "POOBtriz game");
				tetris.startGame();
			}
		};
		Thread hilo = new Thread(runnable);
		hilo.start();
	}

	private Tetris(POOBtrizGUI principal, String title) {
		super(principal,title);
		this.main = principal;
		game1 = new Game(this,false);
		if (isTwoPlayer) {
			game1 = new Game(this,false);
			game2 = new Game(this,isTwoPlayer);
		}

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
		this.board1 = new BoardPanel(game1);
		this.side = new SidePanel(game1,this);
		if(isTwoPlayer) this.board2 = new BoardPanel(game2);
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
		if(isTwoPlayer) {
			add(board1);
			add(side);
			add(board2);
		}
		else {
			add(side);
			add(board1);
		}
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
						game1.caseSPressed();
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
				game1.caseS();
				break;
			case KeyEvent.VK_A:
				game1.caseA();
				break;
			case KeyEvent.VK_W:
				game1.caseW();
				break;
			case KeyEvent.VK_P:
				game1.caseP();
				if(isTwoPlayer) game2.caseP();
				break;
			case KeyEvent.VK_D:
				game1.caseD();
				break;
			case KeyEvent.VK_E:
				game1.caseE();
				if (isTwoPlayer) game2.caseE();
				break;
			case KeyEvent.VK_O:
				game1.caseO();
				break;
			case KeyEvent.VK_R:
				game1.caseR();
				if (isTwoPlayer) game2.caseR();
				break;
			case KeyEvent.VK_I:
				game1.caseI();
				if(isTwoPlayer) game2.caseI();
				break;
			case KeyEvent.VK_LEFT:
				if (isTwoPlayer) game2.caseA();
				break;
			case KeyEvent.VK_DOWN:
				if(isTwoPlayer)game2.caseS();
				break;
			case KeyEvent.VK_UP:
				if(isTwoPlayer)game2.caseW();
				break;
			case KeyEvent.VK_RIGHT:
				if(isTwoPlayer)game2.caseD();
				break;
//			case KeyEvent.VK_PERIOD:
//				game.casePeriod();
//				break;
		}
	}



	public Game getMaingame(){
		return this.game1;
	}
	public void startGame() {
		game1.startGame();
		if(isTwoPlayer) game2.startGame();
	}

	public void Dispose(){
		main.setVisible(true);
		super.dispose();
	}

	public String getNickname(){
		nickname = JOptionPane.showInputDialog(null,
				"Player # 1 Nickname.", "Players Information",
				JOptionPane.PLAIN_MESSAGE);
		return nickname;
	}
	public String[] getNicknames(){
		String nickname1, nickname2;
		nickname1 = JOptionPane.showInputDialog(null, "Player # 1 Nickname.",
				"Players Information", JOptionPane.PLAIN_MESSAGE);
		nickname2 = JOptionPane.showInputDialog(null, "Player # 2 Nickname.",
				"Players Information", JOptionPane.PLAIN_MESSAGE);
		String[] nicknames = {nickname1, nickname2};
		return nicknames;
	}

	public int getBuffos(){
		return main.getBuffos();
	}
	public BoardPanel getBoard1(){
		return board1;
	}

	public BoardPanel getBoard2(){
		return board2;
	}
	public SidePanel getSide(){
		return side;
	}
	public String getnickname(){
		return nickname;
	}
	public int getScore() throws TetrisException {
		if (game1.isGameOver()) return game1.getScore();
		else{
			throw new TetrisException(TetrisException.NO_GAME_FINISHED);
		}
	}


//	private void vsMachine(){
//		JOptionPane.showInputDialog(null, "Player Nickname.", "Player Information", JOptionPane.PLAIN_MESSAGE);
//		String [] options= {"Principiant", "Expert", "Cancel"};
//		JOptionPane.showOptionDialog(null, "Choose machine level.", "Machine Level",
//				0, JOptionPane.QUESTION_MESSAGE, null, options, "Principiant");
//	}
}
