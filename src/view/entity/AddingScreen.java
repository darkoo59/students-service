package view.entity;

import view.entity.custom.EnterExitPanel;
import view.entity.custom.CustomLabel;

import javax.swing.*;
import java.util.ArrayList;

public abstract class AddingScreen extends JDialog {

    protected ArrayList<JComponent> fieldsReferences;

    public abstract JTextField getTextField(int index);

    public abstract JComboBox getComboBox(int index);

    public abstract ArrayList<JComponent> getFieldsReferences();

    public abstract EnterExitPanel getTenex();

    public abstract ArrayList<CustomLabel> getLabelReferences();

    public void dispose() {
        super.dispose();
    }
}
