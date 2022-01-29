package view.tabs;


import controller.professor.AddSubjectToProfessorController;
import controller.professor.EditingProfessorController;
import model.database.DataModel;
import model.Professor;
import view.Screen;
import view.entity.professor.ProfessorEditInfoPanel;
import view.entity.professor.ProfessorEditSubjectsPanel;

import javax.swing.*;

public class ProfessorEditTab extends JTabbedPane {
    private ProfessorEditInfoPanel editInfo;
    private ProfessorEditSubjectsPanel editSubjects;

    public ProfessorEditTab() {
        super();
        editInfo = new ProfessorEditInfoPanel();
        editSubjects = new ProfessorEditSubjectsPanel();
        editSubjects.setAddingController(new AddSubjectToProfessorController());
        String professorId = Screen.getInstance().getMainTab().getSelectedProfessorId();
        DataModel instance = DataModel.getInstance();
        Professor professor = instance.getProfessorById(professorId);
        String[] professorData = EditingProfessorController.findProfessorDataForFields(professor);
        for (int i = 0; i < 10; i++)
            editInfo.setTextField(i, professorData[i]);
        add(Screen.getInstance().getResourceBundle().getString("info"), editInfo);
        add(Screen.getInstance().getResourceBundle().getString("subjects"), editSubjects);
    }

    public ProfessorEditInfoPanel getToolbarEditProfessorInfo() {
        return editInfo;
    }

    public ProfessorEditSubjectsPanel getEditSubjects() {
        return editSubjects;
    }


}
