package controller.subject;

import interfaces.IDeleteController;
import model.database.DataModel;
import view.Screen;

import javax.swing.*;

public class DeleteSubjectController implements IDeleteController {
    @Override
    public void deleteEntity() {
        if (Screen.getInstance().getMainTab().getSubjectTable().getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("notSelectedSubject"),
                    Screen.getInstance().getResourceBundle().getString("deletingSubjectTitle"), JOptionPane.WARNING_MESSAGE);
            return;
        }
        int resp = JOptionPane.showConfirmDialog(null, Screen.getInstance().getResourceBundle().getString("questionDeletingSubject"),
                Screen.getInstance().getResourceBundle().getString("areYouSureSubject"), JOptionPane.YES_NO_OPTION);
        if (resp != 0) return;
        String id = Screen.getInstance().getMainTab().getSelectedSubjectId();
        boolean success = DataModel.getInstance().removeSubjectById(id);
        if (success)
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("tabSubject") + " " + id + Screen.getInstance().getResourceBundle().getString("successDelete"));
        else
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("tabSubject") + " " + id + Screen.getInstance().getResourceBundle().getString("notFounded"));
    }
}
