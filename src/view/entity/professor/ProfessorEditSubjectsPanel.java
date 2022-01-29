package view.entity.professor;

import controller.professor.AddSubjectToProfessorController;
import controller.professor.DeleteSubjectFromProfessorController;
import view.entity.abstract_model.table_model.ProfessorSubjectsTableModel;
import view.entity.custom.edit_custom.ProfessorSubjectsButtons;
import view.ListenerHandler;
import view.entity.table.Table;

import javax.swing.*;
import java.awt.*;

public class ProfessorEditSubjectsPanel extends JPanel {
    private Table professorSubjectsTable;
    private ProfessorSubjectsButtons professorSubjectsButtons;
    private AddSubjectToProfessorController addingController;
    private DeleteSubjectFromProfessorController deleteController;

    public ProfessorEditSubjectsPanel() {
        super();
        setPreferredSize(new Dimension(200, 800));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        professorSubjectsButtons = new ProfessorSubjectsButtons();
        professorSubjectsTable = new Table(new ProfessorSubjectsTableModel());
        add(Box.createVerticalStrut(10));
        add(professorSubjectsButtons);
        add(Box.createVerticalStrut(10));
        add(new JScrollPane(professorSubjectsTable));

        professorSubjectsButtons.getBtnAddSubject().addActionListener(ListenerHandler.getAddSubjectToProfessorListener(this));
        professorSubjectsButtons.getBtnDeleteSubject().addActionListener(ListenerHandler.getDeleteSubjectFromProfessorListener(this));

        setVisible(true);
    }

    public void setAddingController(AddSubjectToProfessorController addingController) {
        this.addingController = addingController;
    }

    public void setDeleteController(DeleteSubjectFromProfessorController deleteController) {
        this.deleteController = deleteController;
    }

    public void addSubject() {
        this.addingController.addNewSubject();
    }

    public Table getProfessorSubjectsTable() {
        return professorSubjectsTable;
    }

    public String getSelectedSubjectId() {
        ProfessorSubjectsTableModel model = (ProfessorSubjectsTableModel) professorSubjectsTable.getModel();
        return (String) model.getValueAt(professorSubjectsTable.getSelectedRow(), 0);
    }


}
