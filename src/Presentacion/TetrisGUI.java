package Presentacion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TetrisGUI extends JFrame {
    //Tamano ventana
    private final int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 100;
    private final int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
    //Menu
    private JMenuBar menuExit;
    private JMenu archivo;
    private JMenu records;
    private JMenu credits;
    private JMenuItem newGame;
    private JMenuItem saveGame;
    private JMenuItem openGame;
    private JMenuItem importGame;
    private JMenuItem exportGame;
    private JMenuItem exitGame;
    private JMenuItem worldRecord;
    private JFileChooser fileChooser;
    //Panel bottons
    private JPanel panelBuffos;
    private JLabel numberBuffos;
    private JSpinner spinnerBuffos;
    private JButton start;
    private JButton exit;
    private JPanel botones;
    //Panel images
    private JPanel iconTetris;
    private ImageIcon icon;
    private JLabel labelIcon;
    //Mode
    private JComboBox comboMode;
    //Velocity
    private JComboBox comboVelocity;


    public TetrisGUI(String title) {
        super(title);
        prepareElementosMain();
        prepareAccionesMain();
    }

    public static void main(String[] args) {
        TetrisGUI gui = new TetrisGUI("Tetris Game");
        gui.setVisible(true);
    }
    private void prepareElementosMain(){
        setPreferredSize(new Dimension(ANCHO,ALTO));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        prepareElementosIcono();
        prepareElementosBotones();
        prepareElementosMenu();
        agregueElementosBotones();
        agregueElementosMenu1();
        agregueElementosMenu2();
        configurationButtons();
        prepareElementosChooser();
    }

    private void prepareElementosIcono(){
        iconTetris = new JPanel();
        icon = new ImageIcon("tetris.jpg");
        labelIcon = new JLabel(null,icon ,SwingConstants.CENTER);
        iconTetris.add(labelIcon);
        iconTetris.setBackground(Color.BLACK);
    }
    private void prepareElementosBotones(){
        botones = new JPanel(new GridLayout(1,5));
        comboMode = new JComboBox();
        comboVelocity = new JComboBox();
        panelBuffos = new JPanel(new GridLayout(1,2));
        numberBuffos = new JLabel("# Buffos:");
        spinnerBuffos = new JSpinner();
        spinnerBuffos.setModel(new SpinnerNumberModel(0, 0, 50, 1));
        start = new JButton("Start");
        start.setEnabled(true);
        exit = new JButton("Exit");
    }
    private void prepareElementosMenu(){
        menuExit = new JMenuBar();
        archivo = new JMenu("File");
        records = new JMenu("Records");
        credits = new JMenu("Credits");
        newGame = new JMenuItem("New");
        saveGame = new JMenuItem("Save");
        openGame = new JMenuItem("Open");
        importGame = new JMenuItem("Import");
        exportGame = new JMenuItem("Export");
        exitGame = new JMenuItem("Exit");
        worldRecord = new JMenuItem("World");
    }

    private void agregueElementosBotones() {
        panelBuffos.add(numberBuffos);
        panelBuffos.add(spinnerBuffos);
        botones.add(comboMode);
        botones.add(panelBuffos);
        botones.add(start);
        botones.add(comboVelocity);
        botones.add(exit);
        add(iconTetris, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }
    private void agregueElementosMenu1(){
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
        records.add(worldRecord);
    }
    private void agregueElementosMenu2(){
        menuExit.add(archivo);
        menuExit.add(records);
        menuExit.add(credits);
        setJMenuBar(menuExit);
        comboMode.addItem("Game Mode");
        comboMode.addItem("Player");
        comboMode.addItem("Player vs Player");
        comboMode.addItem("Player vs Machine");
        comboVelocity.addItem("Velocity");
        comboVelocity.addItem("Accelerated");
        comboVelocity.addItem("Uniform - Easy");
        comboVelocity.addItem("Uniform - Hard");
    }
    private void configurationButtons(){
        comboMode.setBackground(Color.WHITE);
        start.setBackground(Color.WHITE);
        comboVelocity.setBackground(Color.WHITE);
        exit.setBackground(Color.WHITE);
    }

    private void prepareAccionesMain(){
        prepareAccionesMenu();
    }

    private void prepareAccionesMenu() {
        exit.addActionListener(e -> salga());
        exitGame.addActionListener(e -> salga());
        saveGame.addActionListener(e -> save());
        openGame.addActionListener(e -> open());
        start.addActionListener(e -> startGame());
        //setComboMode();
    }
    private void setComboMode(){
        comboMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int aux = comboMode.getSelectedIndex();
                int aux2 = comboVelocity.getSelectedIndex();
                if(aux == 0 || aux2 == 0){
                    start.setEnabled(false);
                }
                else{start.setEnabled(true);}
            }
        });
    }

    private void startGame(){
        setVisible(false);
        Tetris.loadGame(this);
    }

    private void irACreditos() {
        setVisible(false);
        Credits frame = new Credits(this,"Credits");
        frame.setVisible(true);
    }


    private void prepareElementosChooser() {
        fileChooser = new JFileChooser();
    }

    public void save() {
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

    public void salga() {
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

    public int getBuffos(){
        int noBuffos = (Integer) spinnerBuffos.getValue();
        return noBuffos;
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