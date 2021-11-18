package Presentacion;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TetrisMain extends JFrame {
    //Tamano ventana
    private final int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    private final int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
    private JPanel mainPanel;
    //Panel bottons
    private JButton mode;
    private JButton credits;
    private JButton exit;
    private JPanel botones;
    //Panel images
    private JPanel iconTetris;
    private ImageIcon icon;
    private JLabel labelIcon;

    public TetrisMain(String title) {
        super(title);
        prepareElementosMain();
        prepareAcciones();
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
        prepareElementosM2();
    }
    private void prepareElementosM1(){
        mainPanel = new JPanel(new GridLayout(2,1));
        iconTetris = new JPanel();
        icon = new ImageIcon("tetris.jpg");
        labelIcon = new JLabel(null,icon,SwingConstants.CENTER);
        iconTetris.add(labelIcon);
        botones = new JPanel();
        botones.setLayout(null);
        botones.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Opciones")));
        mode = new JButton("Mode");
        mode.setBounds(120 ,100,220,30);
        credits = new JButton("Credits");
        credits.setBounds(360,100,220,30);
        exit = new JButton("Exit");
        exit.setBounds(600,100,220,30);
    }
    private void prepareElementosM2(){
        botones.add(mode);
        botones.add(credits);
        botones.add(exit);
        mainPanel.add(iconTetris);
        mainPanel.add(botones);
        add(mainPanel,BorderLayout.CENTER);
    }
    private void prepareAcciones(){
        mode.addActionListener(e -> prepareMode());
        credits.addActionListener(e -> irACreditos());
        exit.addActionListener(e -> salga());
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
    private void salga() {
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}