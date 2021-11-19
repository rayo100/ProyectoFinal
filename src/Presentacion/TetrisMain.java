package Presentacion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TetrisMain extends JFrame {
    //Tamano ventana
    private final int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    private final int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
    private JPanel mainPanel;
    //Menu
    private JMenuBar menuExit;
    private JMenu archivo;
    private JMenuItem saveGame;
    private JMenuItem openGame;
    private JMenuItem exitGame;
    private JFileChooser fileChooser;
    //Panel bottons
    private JButton mode;
    private JButton credits;
    private JButton exit;
    private JButton load;
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
        mainPanel = new JPanel(new GridLayout(2,1));
        iconTetris = new JPanel();
        icon = new ImageIcon("tetris.jpg");
        labelIcon = new JLabel(null,icon,SwingConstants.CENTER);
        iconTetris.add(labelIcon);
        botones = new JPanel(new GridLayout(1,4));
        comboMode = new JComboBox();
        load = new JButton("Load");
        credits = new JButton("Credits");
        exit = new JButton("Exit");
        menuExit = new JMenuBar();
        archivo = new JMenu("File");
        menuExit.add(archivo);
        saveGame = new JMenuItem("Save");
        openGame = new JMenuItem("Open");
        exitGame = new JMenuItem("Exit");
    }

    private void agregueElementosM1(){
        botones.add(comboMode);
        botones.add(load);
        botones.add(credits);
        botones.add(exit);
        mainPanel.add(iconTetris);
        mainPanel.add(botones);
        add(iconTetris,BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
        archivo.add(openGame);
        archivo.addSeparator();
        archivo.add(saveGame);
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




}