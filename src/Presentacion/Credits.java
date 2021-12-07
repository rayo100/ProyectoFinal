package Presentacion;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Credits extends JDialog {
    private TetrisGUI main;

    public Credits(TetrisGUI main, String title) {
        super(main,title);
        this.main = main;
        prepareElementos();
        prepareAcciones();
    }
    private void prepareElementos() {
        pack();
        setResizable(false);
    }
    private void prepareAcciones() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                main.setVisible(true);
            }
        });
    }


}
