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
    private JPanel information2;
    private JTextField textTime;
    private JLabel time;
    private JTextField textScore;
    private JLabel score;
    private JTextField textNickname;
    private JLabel nickname;
    private JPanel principal;
    private JPanel principal2;
    private JPanel principal3;
    //Tamano ventana
    private final int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
    private final int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height - 100;

    public Tetris1(String title) {
        super(title);
        prepareElementos();
    }
    public void prepareElementos(){
        setPreferredSize(new Dimension(ANCHO,ALTO));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLocationRelativeTo();
        pack();
        setResizable(false);
        setLayout(new BorderLayout());
        Font font = new Font("Arial Bold", Font.ITALIC, 16);
        Font font2 = new Font("Arial", Font.BOLD, 16);
        options = new JPanel(new GridLayout(2,2));
        options.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Opcions")));
        start = new JButton("Start");
        start.setFont(font2);
        restart = new JButton("Restart");
        restart.setFont(font2);
        save = new JButton("Save");
        save.setFont(font2);
        help = new JButton("Help");
        help.setFont(font2);
        options.add(start);
        options.add(restart);
        options.add(save);
        options.add(help);

        configurations = new JPanel(new GridLayout(3,1));
        configurations.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Configurations")));
        level = new JLabel("Level: ");
        level.setFont(font);
        level.setHorizontalAlignment(SwingConstants.CENTER);
        velocity = new JLabel("Velocity: ");
        velocity.setFont(font);
        velocity.setHorizontalAlignment(SwingConstants.CENTER);
        color = new JLabel("Color: ");
        color.setFont(font);
        color.setHorizontalAlignment(SwingConstants.CENTER);
        comboLevel = new JComboBox();
        comboLevel.addItem("Easy");
        comboLevel.addItem("Medium");
        comboLevel.addItem("Hard");
        comboVelocity = new JComboBox();
        comboVelocity.addItem("Slow");
        comboVelocity.addItem("Fast");
        comboColor = new JComboBox();
        comboColor.addItem("Unicolor");
        comboColor.addItem("Multicolor");
        comboColor.addItem("Black and White");

        configurations.add(level);
        configurations.add(comboLevel);
        configurations.add(velocity);
        configurations.add(comboVelocity);
        configurations.add(color);
        configurations.add(comboColor);

        figura = new JPanel(new GridLayout(1,1));
        nextFigura = new JLabel("Next Figure");
        nextFigura.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Figures")));
        nextFigura.setFont(font);
        nextFigura.setHorizontalAlignment(SwingConstants.CENTER);
        nextFigura.setVerticalAlignment(SwingConstants.NORTH);
        figura.add(nextFigura);

        information = new JPanel(new GridLayout(3,1));
        information.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Player # 1")));
        time = new JLabel("Time: ");
        time.setFont(font);
        time.setHorizontalAlignment(SwingConstants.CENTER);
        score = new JLabel("Score: ");
        score.setFont(font);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        nickname = new JLabel("Nickname: ");
        nickname.setFont(font);
        nickname.setHorizontalAlignment(SwingConstants.CENTER);
        textTime = new JTextField();
        textScore = new JTextField();
        textNickname = new JTextField();

        information.add(nickname);
        information.add(textNickname);
        information.add(time);
        information.add(textTime);
        information.add(score);
        information.add(textScore);

        information2 = new JPanel(new GridLayout(3,1));
        information2.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Player # 2")));
        time = new JLabel("Time: ");
        time.setFont(font);
        time.setHorizontalAlignment(SwingConstants.CENTER);
        score = new JLabel("Score: ");
        score.setFont(font);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        nickname = new JLabel("Nickname: ");
        nickname.setFont(font);
        nickname.setHorizontalAlignment(SwingConstants.CENTER);
        textTime = new JTextField();
        textScore = new JTextField();
        textNickname = new JTextField();

        information2.add(nickname);
        information2.add(textNickname);
        information2.add(time);
        information2.add(textTime);
        information2.add(score);
        information2.add(textScore);

        principal = new JPanel(new GridLayout(2,2));
        principal.add(configurations);
        principal.add(figura);
        principal.add(options);
        principal2 = new JPanel(new GridLayout(2,1));
        principal2.add(information);
        principal2.add(information2);

        principal3 = new JPanel(new GridLayout(2,1));
        principal3.add(principal);
        principal3.add(principal2);

        tablero = new JPanel(new GridLayout(1,2));
        tablero.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Board")));

        add(principal3, BorderLayout.WEST);
        add(tablero, BorderLayout.CENTER);

    }

}
