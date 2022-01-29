package controller.student;

import interfaces.IDeleteController;
import model.database.DataModel;
import view.Screen;

import javax.swing.*;

public class DeleteStudentController implements IDeleteController {
    @Override
    public void deleteEntity() {
        if (Screen.getInstance().getMainTab().getStudentTable().getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("notSelectedStudent"),
                    Screen.getInstance().getResourceBundle().getString("deletingStudentTitle"), JOptionPane.WARNING_MESSAGE);
            return;
        }
        int resp = JOptionPane.showConfirmDialog(null, Screen.getInstance().getResourceBundle().getString("questionDeletingStudent"),
                Screen.getInstance().getResourceBundle().getString("areYouSureStudent"), JOptionPane.YES_NO_OPTION);
        if (resp != 0) return;
        String id = Screen.getInstance().getMainTab().getSelectedStudentIndex();
        boolean success = DataModel.getInstance().removeStudentByIndex(id);
        if (success)
            JOptionPane.showMessageDialog(null, "Student " + id + Screen.getInstance().getResourceBundle().getString("successDelete"));
        else
            JOptionPane.showMessageDialog(null, "Student " + id + Screen.getInstance().getResourceBundle().getString("notFounded"));
    }
}
