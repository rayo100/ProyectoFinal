package Presentacion;

import javax.swing.*;
import java.awt.*;

public class Mode extends JFrame {
    private final JPanel menuPrincipal;
    //Tamano ventana
    private final int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width / 3;
    private final int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height / 3;
    // Panel Mode
    private JPanel panelMode;
    private JButton onePlayer;
    private JButton twoPlayers;
    private JButton playerVsMachine;
    private JButton returnMode;

    public Mode(String title, JPanel menu) {
        super(title);
        menuPrincipal = menu;
        prepareElementos();
        prepareAcciones();
    }

    public void prepareElementos() {
        setPreferredSize(new Dimension(ANCHO,ALTO));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        panelMode = new JPanel();
        panelMode.setLayout(null);
        onePlayer = new JButton("A Player");
        onePlayer.setBounds((this.getWidth()/2)-140,30,280,30);
        twoPlayers = new JButton("Player Vs Player");
        twoPlayers.setBounds((this.getWidth()/2)-140,80,280,30);
        playerVsMachine = new JButton("Player Vs Machine");
        playerVsMachine.setBounds((this.getWidth()/2)-140,130,280,30);
        returnMode = new JButton("Return");
        returnMode.setBounds(380,200,80,30);
        panelMode.add(onePlayer);
        panelMode.add(twoPlayers);
        panelMode.add(playerVsMachine);
        panelMode.add(returnMode);
        add(panelMode);
        panelMode.setVisible(true);
    }

    private void prepareAcciones() {
        returnMode.addActionListener(e -> volverDeMode());
        onePlayer.addActionListener(e -> prepareTablero());
    }

    private void volverDeMode(){
        setVisible(false);
        TetrisMain frame = new TetrisMain("Tetris");
        frame.setVisible(true);
    }

    private void prepareTablero(){
        setVisible(false);
        Tetris1 frame = new Tetris1("Tetris Game");
        frame.setVisible(true);
    }

}
