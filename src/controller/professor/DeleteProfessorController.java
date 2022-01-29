package controller.professor;

import interfaces.IDeleteController;
import model.database.DataModel;
import model.Professor;
import view.Screen;

import javax.swing.*;

public class DeleteProfessorController implements IDeleteController {
    @Override
    public void deleteEntity() {
        if (Screen.getInstance().getMainTab().getProfessorTable().getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("notSelectedProfessor"),
                    Screen.getInstance().getResourceBundle().getString("deletingProfessorTitle"), JOptionPane.WARNING_MESSAGE);
            return;
        }
        int resp = JOptionPane.showConfirmDialog(null, Screen.getInstance().getResourceBundle().getString("questionDeletingProfessor"),
                Screen.getInstance().getResourceBundle().getString("areYouSureProfessor"), JOptionPane.YES_NO_OPTION);
        if (resp != 0) return;
        String id = Screen.getInstance().getMainTab().getSelectedProfessorId();
        DataModel dataModel = DataModel.getInstance();
        Professor professor = dataModel.getProfessorById(id);
        boolean success = dataModel.removeProfessorById(id);

        if (success)
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("tabProfessor") + " " + professor.getFirstName() + " " + professor.getLastName() + Screen.getInstance().getResourceBundle().getString("successDelete"));
        else
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("tabProfessor") + " " + professor.getFirstName() + " " + professor.getLastName() + Screen.getInstance().getResourceBundle().getString("notFounded"));
    }
}
