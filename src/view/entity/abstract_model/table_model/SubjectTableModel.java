package view.entity.abstract_model.table_model;

import model.database.DataModel;
import utils.Constants;
import view.Screen;
import view.entity.table.Table;

import javax.swing.table.AbstractTableModel;

public class SubjectTableModel extends AbstractTableModel {
    @Override
    public int getRowCount() {
        return DataModel.getInstance().getSubjects().size();
    }

    @Override
    public int getColumnCount() {
        return Constants.subjectColumnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return DataModel.getInstance().getSubjects().get(rowIndex).getDataAt(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return Constants.subjectColumnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // TODO Auto-generated method stub
        return getValueAt(0, columnIndex).getClass();
    }

    public String getSelectedSubjectId() {
        Table table = Screen.getInstance().getMainTab().getSubjectTable();
        return (String) table.getValueAt(table.getSelectedRow(), 0);
    }
}
