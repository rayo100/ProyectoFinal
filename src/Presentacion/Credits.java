package Presentacion;

import javax.swing.*;

public class Credits extends JDialog {
    public Credits(TetrisMain main,String title) {
        super(main,title);
        prepareElementos();
        prepareAcciones();
    }
    private void prepareElementos() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setResizable(false);
    }
    private void prepareAcciones() {

    }


}
