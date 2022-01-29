package view.entity.custom.edit_custom;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AddDeleteButtons extends JPanel {

    private JButton btnAdd;
    private JButton btnDelete;

    public AddDeleteButtons() {
        super();

        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
        btnAdd = new JButton("+");
        btnDelete = new JButton("-");
        btnDelete.setEnabled(false);

        add(btnAdd);
        add(Box.createHorizontalStrut(3));
        add(btnDelete);

    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public void setListeners(ActionListener deleteListener, ActionListener confirmListener) {
        btnAdd.addActionListener(confirmListener);
        btnDelete.addActionListener(deleteListener);
    }
}
