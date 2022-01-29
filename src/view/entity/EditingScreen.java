package view.entity;

import model.database.DataModel;
import view.entity.custom.EnterExitPanel;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

public abstract class EditingScreen extends JDialog implements WindowListener {

    public abstract JTextField getTextField(int index);

    public abstract JComboBox getComboBox(int index);

    public abstract Vector<JComponent> getFieldsReferences();

    public abstract EnterExitPanel getEnterExit();

    public void dispose() {
        super.dispose();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        DataModel.getInstance().notifyTable();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
