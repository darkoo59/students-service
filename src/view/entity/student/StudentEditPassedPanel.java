package view.entity.student;

import controller.student.AddPassedSubjectController;
import view.entity.abstract_model.table_model.PassedSubjectsTableModel;
import view.entity.custom.edit_custom.PassedExamInfoPanel;
import view.entity.custom.edit_custom.PassedSubjectsButtons;
import view.entity.table.Table;

import javax.swing.*;
import java.awt.*;

public class StudentEditPassedPanel extends JPanel {
    private Table passedSubjectsTable;
    private AddPassedSubjectController addPassedSubjectController;
    private PassedExamInfoPanel infoPanel;

    public StudentEditPassedPanel(AddPassedSubjectController addPassedSubjectController) {
        super();
        this.addPassedSubjectController = addPassedSubjectController;
        passedSubjectsTable = new Table(new PassedSubjectsTableModel());
        infoPanel = new PassedExamInfoPanel();

        setPreferredSize(new Dimension(200, 800));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        PassedSubjectsButtons buttons = new PassedSubjectsButtons(addPassedSubjectController);
        add(buttons);
        add(new JScrollPane(passedSubjectsTable));
        add(infoPanel);
        setVisible(true);
    }

    public AddPassedSubjectController getAddPassedSubjectController() {
        return addPassedSubjectController;
    }

    public void setAddPassedSubjectController(AddPassedSubjectController addPassedSubjectController) {
        this.addPassedSubjectController = addPassedSubjectController;
    }

    public Table getPassedSubjectsTable() {
        return passedSubjectsTable;
    }

    public void setESPBAndAverage() {
        addPassedSubjectController.setESPBAndAverage(infoPanel);
    }


}
