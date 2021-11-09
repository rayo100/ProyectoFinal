package Presentacion;

import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame {
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
    private JTextField textTime;
    private JLabel time;
    private JTextField textScore;
    private JLabel score;
    private JTextField textNickname;
    private JLabel nickname;

    private JPanel principal;
    private JPanel principal2;
    private JPanel principal3;

    private Tetris(String title) {
        super(title);
        prepareElementos();
    }
    public static void main(String[] args) {
        Tetris frame = new Tetris("Tetris Game");
        frame.setVisible(true);
    }
    public void prepareElementos(){
        setPreferredSize(new Dimension(1000,800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        pack();
        setResizable(false);
        setLayout(new BorderLayout());
        options = new JPanel(new GridLayout(2,2));
        start = new JButton("Start");
        restart = new JButton("Restart");
        save = new JButton("Save");
        help = new JButton("Help");
        options.add(start);
        options.add(restart);
        options.add(save);
        options.add(help);

        configurations = new JPanel(new GridLayout(3,1));
        level = new JLabel("Level: ");
        level.setHorizontalAlignment(SwingConstants.CENTER);
        velocity = new JLabel("Velocity: ");
        velocity.setHorizontalAlignment(SwingConstants.CENTER);
        color = new JLabel("Color: ");
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
        nextFigura.setHorizontalAlignment(SwingConstants.CENTER);
        nextFigura.setVerticalAlignment(SwingConstants.NORTH);
        figura.add(nextFigura);

        information = new JPanel(new GridLayout(3,1));
        time = new JLabel("Time: ");
        time.setHorizontalAlignment(SwingConstants.CENTER);
        score = new JLabel("Score: ");
        score.setHorizontalAlignment(SwingConstants.CENTER);
        nickname = new JLabel("Nickname: ");
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

        principal = new JPanel(new GridLayout(1,2));
        principal.add(configurations);
        principal.add(figura);
        principal2 = new JPanel(new GridLayout(2,1));
        principal2.add(options);
        principal2.add(information);

        principal3 = new JPanel(new GridLayout(2,1));
        principal3.add(principal);
        principal3.add(principal2);

        add(principal3,BorderLayout.WEST);
        //add(tablero,BorderLayout.CENTER);

    }

}
