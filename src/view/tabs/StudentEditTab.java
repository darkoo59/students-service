package view.tabs;

import controller.student.*;
import model.database.DataModel;
import model.Student;
import view.Screen;
import view.entity.student.StudentEditFailedPanel;
import view.entity.student.StudentEditInfoPanel;
import view.entity.student.StudentEditPassedPanel;

import javax.swing.*;

public class StudentEditTab extends JTabbedPane {
    private StudentEditInfoPanel editInfo;
    private StudentEditFailedPanel editFailed;
    private StudentEditPassedPanel editPassed;

    public StudentEditTab(AddPassedSubjectController addPassedSubjectController, AddFailedSubjectController addingController, DeleteFailedSubjectController deleteController, TakingExamController examController) {
        super();
        editInfo = new StudentEditInfoPanel();
        editFailed = new StudentEditFailedPanel(addingController, deleteController, examController);
        editPassed = new StudentEditPassedPanel(addPassedSubjectController);
        String studentIndex = Screen.getInstance().getMainTab().getSelectedStudentIndex();
        DataModel instance = DataModel.getInstance();
        Student student = instance.getStudentById(studentIndex);
        String studentData[] = EditingStudentController.findStudentDataForFields(student);
        for (int i = 0; i < 8; i++)
            editInfo.setTextField(i, studentData[i]);

        editInfo.setComboBox(8, studentData[8]);
        editInfo.setComboBox(9, studentData[9]);
        add(Screen.getInstance().getResourceBundle().getString("info"), editInfo);
        add(Screen.getInstance().getResourceBundle().getString("passed"), editPassed);
        add(Screen.getInstance().getResourceBundle().getString("failed"), editFailed);

    }

    public StudentEditInfoPanel getToolbarEditStudentInfo() {
        return editInfo;
    }

    public StudentEditFailedPanel getToolbarEditStudentFailed() {
        return editFailed;
    }

    public StudentEditPassedPanel getToolbarEditStudentPassed() {
        return editPassed;
    }
}
