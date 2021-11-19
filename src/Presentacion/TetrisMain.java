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
        prepareElementosMenu();
        prepareElementosM1();
        prepareElementosM2();
        prepareElementosChooser();

    }
    private void prepareElementosMenu() {
        menuExit = new JMenuBar();
        archivo = new JMenu("File");
        menuExit.add(archivo);
        saveGame = new JMenuItem("Save");
        openGame = new JMenuItem("Open");
        exitGame = new JMenuItem("Exit");
        archivo.add(openGame);
        archivo.addSeparator();
        archivo.add(saveGame);
        archivo.addSeparator();
        archivo.add(exitGame);
        setJMenuBar(menuExit);
    }

    private void prepareElementosM1(){
        mainPanel = new JPanel(new GridLayout(2,1));
        iconTetris = new JPanel();
        icon = new ImageIcon("tetris.jpg");
        labelIcon = new JLabel(null,icon,SwingConstants.CENTER);
        iconTetris.add(labelIcon);
        botones = new JPanel(new GridLayout(1,4));
        mode = new JButton("Mode");
        load = new JButton("Load");
        credits = new JButton("Credits");
        exit = new JButton("Exit");
    }
    private void prepareElementosM2(){
        botones.add(mode);
        botones.add(load);
        botones.add(credits);
        botones.add(exit);
        mainPanel.add(iconTetris);
        mainPanel.add(botones);
        add(iconTetris,BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
    }
    private void prepareAccionesMain(){
        prepareAccionesMode();
        prepareAccionesMenu();
    }

    private void prepareAccionesMode(){
        mode.addActionListener(e -> prepareMode());
        credits.addActionListener(e -> irACreditos());
        exit.addActionListener(e -> salga());
    }
    private void prepareAccionesMenu() {
        exitGame.addActionListener(e -> salga());
        saveGame.addActionListener(e -> save());
        openGame.addActionListener(e -> open());
    }

    private void prepareMode() {
        setVisible(false);
        Mode frame = new Mode("Game Mode", mainPanel);
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
            JOptionPane.showMessageDialog(null, "Archivo guardado: " + archivo.getName() + "\nFuncionalidad Salvar en construccion");
        }
    }

    private void open() {
        int action = fileChooser.showOpenDialog(openGame);
        if (action == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "Archivo cargado: " + archivo.getName() + "\nFuncionalidad Abir en construccion");
        }
    }

    private void salga() {
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}