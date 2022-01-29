package view.entity.abstract_model.table_model;

import model.database.DataModel;
import utils.Constants;
import view.Screen;
import view.entity.table.Table;

import javax.swing.table.AbstractTableModel;

public class ProfessorTableModel extends AbstractTableModel {
    @Override
    public int getRowCount() {
        return DataModel.getInstance().getProfessors().size();
    }

    @Override
    public int getColumnCount() {
        return Constants.professorColumnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return DataModel.getInstance().getProfessors().get(rowIndex).getDataAt(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // TODO Auto-generated method stub
        return getValueAt(0, columnIndex).getClass();
    }

    public String getSelectedId() {
        Table table = Screen.getInstance().getMainTab().getProfessorTable();
        if (table.getSelectedRow() == -1) return "";
        return DataModel.getInstance().getProfessorIdFromEmail((String) table.getValueAt(table.getSelectedRow(), 3));
    }

    @Override
    public String getColumnName(int column) {
        return Constants.professorColumnNames[column];
    }
}
