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
    //Panel botones
    private JButton play;
    private JButton credits;
    private JButton exit;
    private JPanel botones;
    //Panel imagen
    private JPanel iconTetris;
    private ImageIcon icon;
    private JLabel labelIcon;

    private TetrisMain(String title) {
        super(title);
        prepareElementosMain();
        prepareAcciones();
    }

    public static void main(String[] args) {
        TetrisMain gui = new TetrisMain("Tetris1 Game");
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
        iconTetris = new JPanel();
        icon = new ImageIcon("tetris.jpg");
        labelIcon = new JLabel(null,icon,SwingConstants.CENTER);
        iconTetris.add(labelIcon);
        botones = new JPanel(new GridLayout(1,3));
        botones.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Opciones")));
        play = new JButton("Play");
        credits = new JButton("Credits");
        exit = new JButton("Exit");
    }
    private void prepareElementosM2(){
        botones.add(play);
        botones.add(credits);
        botones.add(exit);
        add(iconTetris,BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
    }
    private void prepareAcciones(){
        play.addActionListener(e -> prepareTablero());
        credits.addActionListener(e -> irACreditos());
        exit.addActionListener(e -> salga());
    }
    private void prepareTablero(){
        setVisible(false);
        Tetris1 frame = new Tetris1("Tetris1 Game");
        frame.setVisible(true);
    }
    private void irACreditos() {
        setVisible(false);
        Creditos frame = new Creditos("Creditos");
        frame.setVisible(true);
    }
    private void salga() {
        System.exit(0);
    }
}