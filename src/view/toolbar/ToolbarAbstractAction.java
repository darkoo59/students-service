package view.toolbar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class ToolbarAbstractAction extends AbstractAction {

    public ToolbarAbstractAction(char key) {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(key, KeyEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
