package Presentacion;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.Font;

public class Tetris1 extends JFrame {
    //Panel options
    private JPanel options;
    private JButton start;
    private JButton restart;
    private JButton help;
    private JButton save;
    //Panel configurations
    private JPanel configurations;
    private JLabel level;
    private JComboBox comboLevel;
    private JLabel velocity;
    private JComboBox comboVelocity;
    private JLabel color;
    private JComboBox comboColor;
    //Panel tablero
    private JPanel tablero;
    //Panel figura
    private JPanel figura;
    private JLabel nextFigura;
    //Panel information
    private JPanel information;
    //private JPanel information2;
    private JTextField textTime;
    private JLabel time;
    private JTextField textScore;
    private JLabel score;
    private JTextField textNickname;
    private JLabel nickname;
    private JPanel principal;
    private JPanel principal2;
    private JPanel principal3;
    private Font font = new Font("Arial Bold", Font.ITALIC, 16);
    private Font font2 = new Font("Arial", Font.BOLD, 16);
    //Tamano ventana
    private final int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
    private final int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height - 100;

    public Tetris1(String title) {
        super(title);
        prepareElementos();
    }
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
        tablero = new JPanel(new GridLayout(1,2));
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
        principal.add(options);
        principal2.add(information);
        principal3.add(principal);
        principal3.add(principal2);
        add(principal3, BorderLayout.WEST);
        add(tablero, BorderLayout.CENTER);
    }
    public void prepareElementos(){
        setPreferredSize(new Dimension(ANCHO,ALTO));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLayout(new BorderLayout());
        cargueElementos1();
        cargueElementos2();
        configureElementos1();
        configureElementos2();
        agregueElementos1();
        agregueElementos2();
        //information2 = new JPanel(new GridLayout(3,1));
        //information2.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Player # 2")));
        //information2.add(nickname);
        //information2.add(textNickname);
        //information2.add(time);
        //information2.add(textTime);
        //information2.add(score);
        //information2.add(textScore);
        //principal2.add(information2);
    }

}
