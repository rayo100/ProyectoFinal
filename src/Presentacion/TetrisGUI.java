package Presentacion;
import javax.swing.*;
import java.awt.*;


public class TetrisGUI extends JFrame {
    private JButton play;
    private JButton credits;
    private JButton exit;
    private JPanel botones;
    private TetrisGUI() {
        super("Tetris Game");
        prepareElementos();
    }

    public static void main(String[] args) {
        TetrisGUI gui = new TetrisGUI();
        gui.setVisible(true);
    }
    public void prepareAcciones(){

    }
    public void prepareElementos(){
        setPreferredSize(new Dimension(600,200));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        botones = new JPanel(new GridLayout(1,3));
        play = new JButton("play");
        credits = new JButton("credits");
        exit = new JButton("exit");
        botones.add(play);
        botones.add(credits);
        botones.add(exit);
        add(botones,BorderLayout.CENTER);
    }

}