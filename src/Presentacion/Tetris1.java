package Presentacion;

import Dominio.Tetrominoe;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.Font;

public class Tetris1 extends JFrame {
    //Panel NewFigure
    NewFigurePanel newFigure;
    //Panel Board
    Board board;
    //Tamano ventana
    private final int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width - 800;
    private final int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height - 200;

    public Tetris1(String title) {
        super(title);
        prepareElementos();
    }
    public static void main(String[] args){
        Tetris1 gui = new Tetris1("prueba");
        gui.setVisible(true);
    }
<<<<<<< HEAD
    private void cargueElementos1(){
        options = new JPanel(new GridLayout(2,2));
        start = new JButton("Start");
        restart = new JButton("Restart");
        save = new JButton("Save");
        help = new JButton("Help");
        configurations = new JPanel(new GridLayout(3,1));
        level = new JLabel("Level: ");
        velocity = new JLabel("Velocity: ");
        color = new JLabel("Color: ");
        comboLevel = new JComboBox();
        comboVelocity = new JComboBox();
        comboColor = new JComboBox();
        figura = new JPanel(new GridLayout(1,1));
        nextFigura = new JLabel("Next Figure");
    }
    private void cargueElementos2(){
        information = new JPanel(new GridLayout(3,1));
        textTime = new JTextField();
        textScore = new JTextField();
        textNickname = new JTextField();
        time = new JLabel("Time: ");
        score = new JLabel("Score: ");
        nickname = new JLabel("Nickname: ");
        principal = new JPanel(new GridLayout(2,2));
        principal2 = new JPanel(new GridLayout(2,1));
        principal3 = new JPanel(new GridLayout(2,1));
        tablero = new Board(this);
        textTime = new JTextField();
        textScore = new JTextField();
        textNickname = new JTextField();
    }
    private void configureElementos1(){
        options.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),
                new TitledBorder("Opcions")));
        configurations.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),
                new TitledBorder("Configurations")));
        start.setFont(font2);
        restart.setFont(font2);
        save.setFont(font2);
        help.setFont(font2);
        level.setFont(font);
        level.setHorizontalAlignment(SwingConstants.CENTER);
        velocity.setFont(font);
        velocity.setHorizontalAlignment(SwingConstants.CENTER);
        color.setFont(font);
        color.setHorizontalAlignment(SwingConstants.CENTER);
        nextFigura.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Figures")));
        nextFigura.setFont(font);
        nextFigura.setHorizontalAlignment(SwingConstants.CENTER);
        nextFigura.setVerticalAlignment(SwingConstants.NORTH);
    }
    private void configureElementos2(){
        information.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Player # 1")));
        time.setFont(font);
        time.setHorizontalAlignment(SwingConstants.CENTER);
        score.setFont(font);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        nickname.setFont(font);
        nickname.setHorizontalAlignment(SwingConstants.CENTER);
        time.setFont(font);
        time.setHorizontalAlignment(SwingConstants.CENTER);
        score.setFont(font);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        nickname.setFont(font);
        nickname.setHorizontalAlignment(SwingConstants.CENTER);
        tablero.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Board")));

    }
    private void agregueElementos1(){
        options.add(start);
        options.add(restart);
        options.add(save);
        options.add(help);
        configurations.add(level);
        configurations.add(comboLevel);
        configurations.add(velocity);
        configurations.add(comboVelocity);
        configurations.add(color);
        configurations.add(comboColor);
        comboLevel.addItem("Easy");
        comboLevel.addItem("Medium");
        comboLevel.addItem("Hard");
        comboVelocity.addItem("Slow");
        comboVelocity.addItem("Fast");
        comboColor.addItem("Unicolor");
        comboColor.addItem("Multicolor");
        comboColor.addItem("Black and White");

    }
    private void agregueElementos2(){
        figura.add(nextFigura);
        information.add(nickname);
        information.add(textNickname);
        information.add(time);
        information.add(textTime);
        information.add(score);
        information.add(textScore);
        principal.add(configurations);
        principal.add(figura);
        //principal.add(options);
        principal2.add(information);
        //principal3.add(principal);
        //principal3.add(principal2);
        add(principal3, BorderLayout.WEST);
        add(tablero, BorderLayout.CENTER);
    }
=======
>>>>>>> d245e522bacd2d1624e027072f4ae245272e4339
    public void prepareElementos(){
        setPreferredSize(new Dimension(ANCHO,ALTO));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLayout(new BorderLayout());
        cargueElementos();
        configureElementos();
        agregueElementos();
    }
    private void cargueElementos(){
        newFigure = new NewFigurePanel(this);
        board = new Board(this);
    }
    private void configureElementos(){
        board.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),
                new TitledBorder("Board")));
        newFigure.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),
                new TitledBorder("Game Info")));
    }
    private void agregueElementos(){
        add(board,BorderLayout.CENTER);
        add(newFigure,BorderLayout.WEST);
    }
    public boolean isPaused(){
        return false;
    }

    public boolean isNewGame() {
        return false;
    }

    public boolean isGameOver() {
        return false;
    }

    public Tetrominoe getPieceType() {
        return null;
    }

    public int getPieceCol() {
        return 0;
    }

    public int getPieceRow() {
        return 0;
    }

    public int getPieceRotation() {
        return 0;
    }
}
