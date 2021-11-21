package Presentacion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import Dominio.*;

public class TetrisMain extends JFrame {
    //Tamano ventana
    private final int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 100;
    private final int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
    private JPanel mainPanel;
    //Menu
    private JMenuBar menuExit;
    private JMenu archivo;
    private JMenuItem newGame;
    private JMenuItem saveGame;
    private JMenuItem openGame;
    private JMenuItem importGame;
    private JMenuItem exportGame;
    private JMenuItem exitGame;
    private JFileChooser fileChooser;
    //Panel bottons
    private JPanel panelBuffos;
    private JLabel numberBuffos;
    private JSpinner spinnerBuffos;
    private JButton start;
    private JButton credits;
    private JButton exit;
    private JButton players;
    private JPanel botones;
    //Panel images
    private JPanel iconTetris;
    private ImageIcon icon;
    private JLabel labelIcon;
    //Mode
    private JComboBox comboMode;

    public TetrisMain(String title) {
        super(title);
        prepareElementosMain();
        prepareAccionesMain();
    }

    public static void main(String[] args) {
        TetrisMain gui = new TetrisMain("Tetris Game");
        gui.setVisible(true);
    }
    private void prepareElementosMain(){
        setPreferredSize(new Dimension(ANCHO,ALTO));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        prepareElementosM1();
        agregueElementosM1();
        prepareElementosChooser();
    }

    private void prepareElementosM1(){
        iconTetris = new JPanel();
        icon = new ImageIcon("tetris.jpg");
        labelIcon = new JLabel(null,icon ,SwingConstants.CENTER);
        iconTetris.add(labelIcon);
        iconTetris.setBackground(Color.BLACK);
        botones = new JPanel(new GridLayout(1,5));
        comboMode = new JComboBox();
        players = new JButton("Players");
        credits = new JButton("Credits");
        panelBuffos = new JPanel(new GridLayout(1,2));
        numberBuffos = new JLabel("# Buffos:");
        spinnerBuffos = new JSpinner();
        spinnerBuffos.setModel(new SpinnerNumberModel(0, 0, 50, 1));
        start = new JButton("Start");
        exit = new JButton("Exit");
        menuExit = new JMenuBar();
        archivo = new JMenu("File");
        newGame = new JMenuItem("New");
        saveGame = new JMenuItem("Save");
        openGame = new JMenuItem("Open");
        importGame = new JMenuItem("Import");
        exportGame = new JMenuItem("Export");
        exitGame = new JMenuItem("Exit");
    }

    private void agregueElementosM1(){
        panelBuffos.add(numberBuffos);
        panelBuffos.add(spinnerBuffos);
        botones.add(comboMode);
        botones.add(players);
        botones.add(panelBuffos);
        botones.add(credits);
        botones.add(start);
        botones.add(exit);
        add(iconTetris,BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
        menuExit.add(archivo);
        archivo.add(newGame);
        archivo.addSeparator();
        archivo.add(openGame);
        archivo.addSeparator();
        archivo.add(saveGame);
        archivo.addSeparator();
        archivo.add(importGame);
        archivo.addSeparator();
        archivo.add(exportGame);
        archivo.addSeparator();
        archivo.add(exitGame);
        setJMenuBar(menuExit);
        comboMode.addItem("Game Mode");
        comboMode.addItem("Player");
        comboMode.addItem("Player vs Player");
        comboMode.addItem("Player vs Machine");

    }


    private void prepareAccionesMain(){
        prepareAccionesMenu();
    }

    private void prepareAccionesMenu() {
        credits.addActionListener(e -> irACreditos());
        exit.addActionListener(e -> salga());
        exitGame.addActionListener(e -> salga());
        saveGame.addActionListener(e -> save());
        openGame.addActionListener(e -> open());
        start.addActionListener(e -> startGame());

        //players.addActionListener(e -> player());
    }
    private void startGame(){
        setVisible(false);
        Tetris1 frame = new Tetris1(this,"Tetris Game");
        frame.setVisible(true);
    }

    private void irACreditos() {
        setVisible(false);
        Credits frame = new Credits("Credits");
        frame.setVisible(true);
    }


    private void prepareElementosChooser() {
        fileChooser = new JFileChooser();
    }

    private void save() {
        int action = fileChooser.showSaveDialog(saveGame);
        if (action == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "File "+ archivo.getName() +" saved" + "\nFuncionalidad Salvar en construccion");
        }
    }

    private void open() {
        int action = fileChooser.showOpenDialog(openGame);
        if (action == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "File " + archivo.getName() + " opened "  +"\nFuncionalidad Abir en construccion");
        }
    }

    private void salga() {
        if (JOptionPane.showConfirmDialog(rootPane, "Do you want to log out?",
                "Get out of the system", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void vsMachine(){
        JOptionPane.showInputDialog(null, "Player Nickname.", "Player Information", JOptionPane.PLAIN_MESSAGE);
        String [] options= {"Principiant", "Expert", "Cancel"};
        JOptionPane.showOptionDialog(null, "Choose machine level.", "Machine Level",
                0, JOptionPane.QUESTION_MESSAGE, null, options, "Principiant");
    }
    private void playerVsPlayer(){
        JOptionPane.showInputDialog(null, "Player # 1 Nickname.", "Players Information", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showInputDialog(null, "Player # 2 Nickname.", "Players Information", JOptionPane.PLAIN_MESSAGE);
    }
    public String player(){
        String nickname = (JOptionPane.showInputDialog(null, "Player Nickname.", "Player Information",
                JOptionPane.PLAIN_MESSAGE));
        return nickname;
    }
//    private void bottonPlayers() throws TetrisException{
//        String selected =(String) comboMode.getSelectedItem();
//        switch (selected) {
//
//            case KeyEvent.VK_P -> player();
//            case KeyEvent.VK_LEFT -> playerVsPlayer();
//            case KeyEvent.VK_RIGHT -> vsMachine();
//        }
//    }


}