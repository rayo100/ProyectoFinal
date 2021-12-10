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
/**
 * La clase Tetris es la encargada de la vizualización
 * del juego, apodos, funcionalidad de los botones y
 * eleccion de modo de juego
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 09, 2021)
 */

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
	private static Tetris tetris;

	public static boolean isTwoPlayer = false;

	/**
	 * Este metodo
	 * @param main, es el JFrame principal del juego
	 */
	public static void loadGame(POOBtrizGUI main){
		tetris = new Tetris(main, "POOBtriz game");
		Runnable runnable = () -> tetris.startGame1();
		Thread hilo = new Thread(runnable);
		hilo.start();
		if(isTwoPlayer) loadGame2();
	}

	/*
	 * Este metodo
	 */
	private static void loadGame2(){
		Runnable runnable = () -> tetris.startGame2();
		Thread hilo2 = new Thread(runnable);
		hilo2.start();
	}

	/*
	 * Este metodo crea el juego de lo jugadores, ya sean dos o uno
	 * @param principal, es el JFrame principal del juego
	 * @param title, es el titulo que se le dá a la ventana del JFrame
	 */
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

	/*
	 * Este metodo prepara los elementos que iran en el
	 * JDialog donde los usuarios juegan
	 */
	private void prepareElementos(){
		if(isTwoPlayer) ancho += BoardPanel.PANEL_WIDTH + 50;
		setPreferredSize(new Dimension(ancho, ALTO));
		setModalityType(ModalityType.MODELESS);
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

	/*
	 * Este metodo carga los elementos de los juagadores
	 * ya sea para uno o dos
	 */
	private void cargarElementos(){
		this.board1 = new BoardPanel(game1);
		this.side = new SidePanel(game1,this);
		if(isTwoPlayer) this.board2 = new BoardPanel(game2);
	}

	/*
	 * Este metodo permite a los jugadores elegir el color de su
	 * tablero antes de iniciar el juego
	 */
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

	/*
	 * Este metodo permite agregar los tableros del juego
	 * ya sea para uno o dos jugadores
	 */
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

	/*
	 * Este metodo permite al o los juagadores a realizar
	 * los movimientos y posibles acciones con ordenador
	 */
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
					case KeyEvent.VK_DOWN:
						if(isTwoPlayer) game2.caseSPressed();
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

	/*
	 * Este metodo realiza las acciones que el o los
	 * jugadores realicen en el teclado al jugar
	 * @param e, es el que indica que se produjo una
	 * 			 pulsacion en el teclado
	 */
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

	/**
	 * Este metodo retorna el juego uno
	 * @return
	 */
	public Game getMaingame(){return this.game1;}
	/**
	 * Este metodo inicia el juego para el primer usuario
	 */
	public void startGame1() {game1.startGame();}

	/**
	 * Este metodo inicia el juego para el segundo usuario
	 */
	public void startGame2(){game2.startGame();}

	/**
	 * Este metodo libera todos los recursos
	 * de pantalla usados por esta ventana
	 */
	public void Dispose(){
		main.setVisible(true);
		super.dispose();
	}

	/**
	 * Este metodo retorna la cadena con el apodo de un jugador
	 * @return nickname, es la cadena con el apodo del jugador
	 */
	public String getNickname(){
		nickname = JOptionPane.showInputDialog(null,
				"Player # 1 Nickname.", "Players Information",
				JOptionPane.PLAIN_MESSAGE);
		return nickname;
	}

	/**
	 * Este metodo retorna una lista de cadenas con los
	 * apodos de los jugadores
	 * @return nicknames, es la lista de cadenas con los apodos
	 */
	public String[] getNicknames(){
		String nickname1, nickname2;
		nickname1 = JOptionPane.showInputDialog(null, "Player # 1 Nickname.",
				"Players Information", JOptionPane.PLAIN_MESSAGE);
		nickname2 = JOptionPane.showInputDialog(null, "Player # 2 Nickname.",
				"Players Information", JOptionPane.PLAIN_MESSAGE);
		String[] nicknames = {nickname1, nickname2};
		return nicknames;
	}

	/**
	 * Este metodo retorna el numero de buffos elegidos
	 * para el juego
	 * @return buffos, es el numero inicial de buffos
	 */
	public int getBuffos(){return main.getBuffos();}

	/**
	 * Este metodo retorna el JPanel del tablero
	 * del jugador numero uno
	 * @return board1, es el tablero del jugador uno
	 */
	public BoardPanel getBoard1(){return board1;}

	/**
	 * Este metodo retorna el JPanel del tablero
	 * del jugador numero dos
	 * @return board2, es el tablero del jugador dos
	 */
	public BoardPanel getBoard2(){return board2;}

	/**
	 * Este metodo retorna el JPanel de la informacion
	 * del juego
	 * @return side, JPanel de la informacion del juego
	 */
	public SidePanel getSide(){return side;}

	/**
	 * Este metodo retorna el apodo del jugador
	 * @return nickname, es el apodo el jugador
	 */
	public String getnickname(){return nickname;}

	/**
	 * Este metodo retorna el puntaje del jugador uno
	 * @return puntaje jugador uno
	 * @throws TetrisException, "El juego aun no a terminado"
	 */
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
