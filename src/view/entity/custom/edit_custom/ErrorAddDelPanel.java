package view.entity.custom.edit_custom;

import view.ListenerHandler;

import javax.swing.*;
import java.awt.*;

public class ErrorAddDelPanel extends JPanel {
    private AddDeleteButtons addDeleteButtons;

    public ErrorAddDelPanel(JTextField field, JLabel label) {
        addDeleteButtons = new AddDeleteButtons();
        addDeleteButtons.setListeners(ListenerHandler.deleteEntityFromFieldListener(), ListenerHandler.addEntityToFieldListener());
        this.setPreferredSize(new Dimension(field.getPreferredSize().width + addDeleteButtons.getPreferredSize().width,
                field.getPreferredSize().height + label.getPreferredSize().height));
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        JPanel upperWrapper = new JPanel();
        upperWrapper.setLayout(new BoxLayout(upperWrapper, BoxLayout.X_AXIS));
        upperWrapper.add(field);
        upperWrapper.add(addDeleteButtons);

        this.add(upperWrapper);
        this.add(Box.createVerticalStrut(2));
        this.add(label);
    }

    public void setEmptyFieldCase() {
        addDeleteButtons.getBtnAdd().setEnabled(true);
        addDeleteButtons.getBtnDelete().setEnabled(false);
    }

    public void setFullFieldCase() {
        addDeleteButtons.getBtnAdd().setEnabled(false);
        addDeleteButtons.getBtnDelete().setEnabled(true);
    }
}
