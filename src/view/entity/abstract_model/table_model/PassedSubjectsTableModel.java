package view.entity.abstract_model.table_model;

import model.database.DataModel;
import model.Mark;
import model.Subject;
import utils.Constants;
import view.Screen;

import javax.swing.table.AbstractTableModel;

public class PassedSubjectsTableModel extends AbstractTableModel {
    @Override
    public int getRowCount() {
        String studIndex = Screen.getInstance().getMainTab().getSelectedStudentIndex();
        int length = DataModel.getInstance().getStudentById(studIndex).getPassedSubjects().size();
        return length;
    }

    @Override
    public int getColumnCount() {
        return Constants.passedExamsColumnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String studIndex = Screen.getInstance().getMainTab().getSelectedStudentIndex();
        Subject subject = DataModel.getInstance().getStudentById(studIndex).getPassedSubjects().get(rowIndex);
        Mark mark = DataModel.getInstance().getMarkByStudentAndSubject(studIndex, subject.getSubjectId());
        return mark.getDataAt(columnIndex);
    }

    public String getSelectedSubjectId(int rowIndex) {
        String studentIndex = Screen.getInstance().getMainTab().getSelectedStudentIndex();
        Subject subject = DataModel.getInstance().getStudentById(studentIndex).getPassedSubjects().get(rowIndex);
        return subject.getSubjectId();
    }

    @Override
    public String getColumnName(int column) {
        // return Constants.passedExamsColumnNames[column];
        return Constants.getPassedExamColumnName(column);
    }
}
