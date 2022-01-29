package view.entity.abstract_model.table_model;

import model.database.DataModel;
import utils.Constants;

import javax.swing.table.AbstractTableModel;

public class DepartmentTableModel extends AbstractTableModel {
    @Override
    public int getRowCount() {
        return DataModel.getInstance().getDepartments().size();
    }

    @Override
    public int getColumnCount() {
        return Constants.departmentColumnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return DataModel.getInstance().getDepartments().get(rowIndex).getDataAt(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return Constants.getDepartmentColumnNames(column);
    }
}
