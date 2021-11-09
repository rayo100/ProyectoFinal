package Presentacion;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.Font;

public class TetrisGUI extends JFrame {
    //Panel botones
    private JButton play;
    private JButton credits;
    private JButton exit;
    private JPanel botones;
    //Panel textos
    private JPanel textos;
    private JLabel titulo;

    private TetrisGUI(String title) {
        super(title);
        prepareElementos();
    }

    public static void main(String[] args) {
        TetrisGUI gui = new TetrisGUI("Tetris Game");
        gui.setVisible(true);
    }
    public void prepareAcciones(){

    }
    public void prepareElementos(){
        setPreferredSize(new Dimension(600,140));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setResizable(false);
        setLayout(new BorderLayout());
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        textos = new JPanel(new GridLayout(1,1));
        textos.setBackground(Color.CYAN);
        titulo = new JLabel("WELCOME TO TETRIS GAME");
        titulo.setFont(font);
        botones = new JPanel(new GridLayout(1,3));
        botones.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Opcions")));
        play = new JButton("Play");
        //play.setMinimumSize(new Dimension(20,20));
        //play.setMaximumSize(new Dimension(150,60));
        //play.setPreferredSize(new Dimension(60,30));
        credits = new JButton("Credits");
        exit = new JButton("Exit");
        textos.add(titulo);
        botones.add(play);
        botones.add(credits);
        botones.add(exit);
        add(textos,BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
    }


}