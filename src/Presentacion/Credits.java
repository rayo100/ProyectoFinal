package Presentacion;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * La clase Credits es un JDialog donde nos aparecerán
 * los creadores del juego, los diseñadores y demás
 * información
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 09, 2021)
 */

public class Credits extends JDialog {
    private POOBtrizGUI main;

    /**
     * Este metdoo crea un JDialog
     * @param main, es el JFrame principal del juego
     * @param title, es el titulo que se le pondrá al JDialog
     */
    public Credits(POOBtrizGUI main, String title) {
        super(main,title);
        this.main = main;
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Este metodo prepara los elementos que iran dentro del JDialog
     */
    private void prepareElementos() {
        pack();
        setResizable(false);
    }

    /**
     * Este metodo prepara las acciones que se podran hacer dentro del JDialog
     */
    private void prepareAcciones() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                main.setVisible(true);
            }
        });
    }


}
