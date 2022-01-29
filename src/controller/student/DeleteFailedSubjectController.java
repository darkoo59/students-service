package controller.student;

import model.database.DataModel;
import view.Screen;
import view.entity.table.Table;
import view.entity.student.StudentEditFailedPanel;

import javax.swing.*;

public class DeleteFailedSubjectController {
    public static Table failedSubjectsTable;

    public void deleteFailedSubject(StudentEditFailedPanel editFailedPanel) {
        int resp = JOptionPane.showConfirmDialog(null, Screen.getInstance().getResourceBundle().getString("areYouSureSubject"),
                Screen.getInstance().getResourceBundle().getString("deleteFailedSubject"), JOptionPane.YES_NO_OPTION);
        if (resp == 1)
            return;
        String selectedSubjectId = editFailedPanel.getSelectedSubjectId();
        DataModel model = DataModel.getInstance();
        Boolean success = model.removeFailedSubjectFromStudentSubjects(selectedSubjectId,
                Screen.getInstance().getMainTab().getSelectedStudentIndex());
        if (success) {
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("tabSubject") + " " + selectedSubjectId +
                    Screen.getInstance().getResourceBundle().getString("successDelete"));
        } else
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("tabSubject") + " " + selectedSubjectId +
                    Screen.getInstance().getResourceBundle().getString("successDelete"));
    }
}