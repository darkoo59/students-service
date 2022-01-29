package controller.subject;

import model.Professor;
import view.entity.abstract_model.list_model.ProfessorListModel;
import view.Screen;
import view.entity.subject.SubjectEditDialog;

import java.awt.Color;

import javax.swing.*;

public class AddProfessorToSubjectController {

    public AddProfessorToSubjectController() {

    }

    public void addNewProfessorToSubject(SubjectEditDialog editDialog) {
        JList selection = new JList(new ProfessorListModel());
        selection.setSelectionBackground(new Color(232,57,95));
        selection.setSelectionForeground(Color.white);
        
        int result = JOptionPane.showConfirmDialog(null, new JScrollPane(selection), Screen.getInstance().getResourceBundle().getString("chooseSubject"),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == 0) {
            Professor professor = ((ProfessorListModel) selection.getModel()).getObjectAt(selection.getSelectedIndex());
            ((JTextField) editDialog.getFieldsReferences().get(3)).setText(professor.getFirstName() + " " + professor.getLastName());
            editDialog.setChoosenProfessor(professor.getIdNumber());
            editDialog.switchAddDeleteButtons();
        }
    }
}
