package view.entity.custom;


import view.ListenerHandler;
import view.Screen;

import javax.swing.*;


public class EnterExitPanel extends JPanel {

    private JButton buttonConfirm;
    private JButton buttonCancel;

    public EnterExitPanel() {
        super();
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);


        buttonConfirm = new JButton(Screen.getInstance().getResourceBundle().getString("btnConfirm"));
        buttonConfirm.setEnabled(false);
        buttonCancel = new JButton(Screen.getInstance().getResourceBundle().getString("btnCancel"));

        buttonConfirm.addActionListener(ListenerHandler.getButtonConfirmListener(buttonConfirm));
        buttonCancel.addActionListener(ListenerHandler.getButtonCancelListener(buttonCancel));

        add(buttonConfirm);
        add(Box.createHorizontalStrut(50));
        add(buttonCancel);
    }


    public JButton getButtonConfirm() {
        return buttonConfirm;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }
}
