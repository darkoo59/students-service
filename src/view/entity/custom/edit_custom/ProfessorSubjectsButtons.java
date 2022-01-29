package view.entity.custom.edit_custom;

import view.Screen;

import javax.swing.*;
import java.awt.*;

public class ProfessorSubjectsButtons extends JPanel {
    private JButton btnAddSubject;
    private JButton btnDeleteSubject;

    public ProfessorSubjectsButtons() {
        super();
        btnAddSubject = new JButton(Screen.getInstance().getResourceBundle().getString("btnAddSubject"));
        btnDeleteSubject = new JButton(Screen.getInstance().getResourceBundle().getString("btnRemoveSubject"));

        setMaximumSize(new Dimension(Screen.getInstance().getWidth(), 40));
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);


        add(Box.createHorizontalStrut(20));
        add(btnAddSubject);
        add(Box.createHorizontalStrut(20));
        add(btnDeleteSubject);
    }

    public JButton getBtnAddSubject() {
        return btnAddSubject;
    }

    public JButton getBtnDeleteSubject() {
        return btnDeleteSubject;
    }


}
