package view.entity.abstract_model.table_model;

import model.database.DataModel;
import utils.Constants;
import view.Screen;
import view.entity.student.StudentEditFailedPanel;

import javax.swing.table.AbstractTableModel;

public class FailedSubjectsTableModel extends AbstractTableModel {

    public FailedSubjectsTableModel() {
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return StudentEditFailedPanel.getSubjectsData(DataModel.getInstance()
                .getStudentById(Screen.getInstance().getMainTab().getSelectedStudentIndex()).getFailedSubjects()).length;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return Constants.subjectColumnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return StudentEditFailedPanel.getSubjectsData(DataModel.getInstance()
                .getStudentById(Screen.getInstance().getMainTab().getSelectedStudentIndex()).getFailedSubjects())[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        //return Constants.subjectColumnNames[column];
        return Constants.getFailedColumnName(column);
    }

}
