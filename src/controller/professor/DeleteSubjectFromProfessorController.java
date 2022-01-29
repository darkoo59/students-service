package controller.professor;

import model.database.DataModel;
import view.Screen;
import view.entity.table.Table;
import view.entity.professor.ProfessorEditSubjectsPanel;

import javax.swing.*;

public class DeleteSubjectFromProfessorController {
    public static Table professorSubjectsTable;

    public DeleteSubjectFromProfessorController(ProfessorEditSubjectsPanel editProfessorSubjectsPanel) {
        int resp = JOptionPane.showConfirmDialog(null, Screen.getInstance().getResourceBundle().getString("areYouSure")
                , Screen.getInstance().getResourceBundle().getString("btnRemoveSubject"), JOptionPane.YES_NO_OPTION);
        if (editProfessorSubjectsPanel.getProfessorSubjectsTable().getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("notSelectedSubject"));
            return;
        }
        if (resp == 1)
            return;
        String selectedSubjectId = editProfessorSubjectsPanel.getSelectedSubjectId();
        DataModel model = DataModel.getInstance();
        Boolean success = model.removeSubjectFromProfessorSubjects(selectedSubjectId,
                Screen.getInstance().getMainTab().getSelectedProfessorId());
        DataModel.getInstance().deleteProfessorFromSubject(Screen.getInstance().getMainTab().getSelectedProfessorId(), selectedSubjectId);
        if (success)
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("tabSubject") + " " + selectedSubjectId +
                    Screen.getInstance().getResourceBundle().getString("successDelete"));
        else
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("tabSubject") + " " + selectedSubjectId +
                    Screen.getInstance().getResourceBundle().getString("notFounded"));
    }
}
