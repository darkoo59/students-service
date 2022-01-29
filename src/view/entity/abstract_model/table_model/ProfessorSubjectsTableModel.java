package view.entity.abstract_model.table_model;

import model.database.DataModel;
import model.Professor;
import utils.Constants;
import view.Screen;

import javax.swing.table.AbstractTableModel;

public class ProfessorSubjectsTableModel extends AbstractTableModel {
    @Override
    public int getRowCount() {
        String selectedRow = Screen.getInstance().getMainTab().getSelectedProfessorId();
        Professor professor = DataModel.getInstance().getProfessorById(selectedRow);
        return professor.getSubjects().size();
    }

    @Override
    public int getColumnCount() {
        return Constants.subjectColumnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String selectedRow = Screen.getInstance().getMainTab().getSelectedProfessorId();
        Professor professor = DataModel.getInstance().getProfessorById(selectedRow);
        return professor.getSubjects().get(rowIndex).getDataAt(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return Constants.getProfessorSubjectColumnName(column);
    }
}
