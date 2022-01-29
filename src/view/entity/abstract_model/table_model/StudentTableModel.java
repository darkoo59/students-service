package view.entity.abstract_model.table_model;

import model.database.DataModel;
import utils.Constants;
import view.Screen;
import view.entity.table.Table;

import javax.swing.table.AbstractTableModel;

public class StudentTableModel extends AbstractTableModel {
    @Override
    public int getRowCount() {
        return DataModel.getInstance().getStudents().size();
    }

    @Override
    public int getColumnCount() {
        return Constants.studentColumnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return DataModel.getInstance().getStudents().get(rowIndex).getDataAt(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return Constants.studentColumnNames[column];
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // TODO Auto-generated method stub
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Integer.class;
            case 4:
                return String.class;
            case 5:
                return Double.class;
        }
        return super.getColumnClass(columnIndex);
    }

    public String selectedStudentIndex() {
        Table table = Screen.getInstance().getMainTab().getStudentTable();
        if (table.getSelectedRow() == -1) return "";
        return (String) table.getValueAt(table.getSelectedRow(), 0);
    }

}
