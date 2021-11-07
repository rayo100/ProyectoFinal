package Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test2 extends JFrame{
    private JPanel panelMain;
    private JLabel titulo;
    private JButton creditsButton;
    private JButton exitButton;
    private JButton playButton;

    public Test2(){
        super("Probando");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        this.pack();
    }

    public static void main(String[] args) {
        Test2 gui = new Test2();
        gui.setVisible(true);
    }
}
