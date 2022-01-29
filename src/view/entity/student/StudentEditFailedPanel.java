package view.entity.student;

import controller.student.AddFailedSubjectController;
import controller.student.DeleteFailedSubjectController;
import controller.student.TakingExamController;
import model.Subject;
import view.entity.abstract_model.table_model.FailedSubjectsTableModel;
import view.entity.custom.edit_custom.FailedSubjectsButtons;
import view.ListenerHandler;
import view.entity.table.Table;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentEditFailedPanel extends JPanel {
    private Table failedSubjectsTable;
    private DeleteFailedSubjectController deleteController;
    private AddFailedSubjectController addingController;
    private TakingExamController examController;

    public StudentEditFailedPanel(AddFailedSubjectController addingController, DeleteFailedSubjectController deleteController, TakingExamController examController) {
        super();
        this.addingController = addingController;
        this.deleteController = deleteController;
        this.examController = examController;
        setPreferredSize(new Dimension(200, 800));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        FailedSubjectsButtons buttons = new FailedSubjectsButtons();
        add(buttons);

        failedSubjectsTable = new Table(new FailedSubjectsTableModel());

        add(new JScrollPane(failedSubjectsTable));

        buttons.getButtonDelete().addActionListener(ListenerHandler.getButtonDeleteFailedSubjectListener(this));
        buttons.getButtonAdd().addActionListener(ListenerHandler.getAddFailedSubjectListener(this));
        buttons.getButtonTakingExam().addActionListener(ListenerHandler.getButtonTakingExamListener(this));
        setVisible(true);
    }

    public String getSelectedSubjectId() {
        FailedSubjectsTableModel model = (FailedSubjectsTableModel) failedSubjectsTable.getModel();
        return (String) model.getValueAt(failedSubjectsTable.getSelectedRow(), 0);
    }

    public void setDeleteController(DeleteFailedSubjectController deleteController) {
        this.deleteController = deleteController;
    }

    public void setAddingController(AddFailedSubjectController addingController) {
        this.addingController = addingController;
    }

    public void setExamController(TakingExamController examController) {
        this.examController = examController;
    }


    public DeleteFailedSubjectController getDeleteController() {
        return deleteController;
    }

    public AddFailedSubjectController getAddingController() {
        return addingController;
    }

    public TakingExamController getExamController() {
        return examController;
    }

    public void setFailedSubjectsTable(Table failedSubjectsTable) {
        this.failedSubjectsTable = failedSubjectsTable;
    }

    public Table getFailedSubjectsTable() {
        return failedSubjectsTable;
    }

    public static String[][] getSubjectsData(ArrayList<Subject> subjects) {
        String[][] data = new String[subjects.size()][5];
        int i = 0;
        for (Subject subject : subjects) {
            data[i][0] = subject.getSubjectId();
            data[i][1] = subject.getSubjectName();
            data[i][2] = Integer.toString(subject.getEspb());
            data[i][3] = Integer.toString(subject.getYearOfStudy());
            data[i][4] = subject.getSemester().getValue();
            i++;
        }
        return data;
    }

    public void addFailedSubject() {
        addingController.addNewFailedSubject();
    }
}
