package view.entity.table;

import view.entity.abstract_model.table_model.ProfessorTableModel;
import view.entity.abstract_model.table_model.StudentTableModel;
import view.entity.abstract_model.table_model.SubjectTableModel;
import view.entity.filters.ProfessorRowFilter;
import view.entity.filters.StudentRowFilter;
import view.entity.filters.SubjectRowFilter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import java.awt.Font;
import java.util.Comparator;

public class Table extends JTable {
    public Table(AbstractTableModel tableModel) {
        super();
        setModel(tableModel);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setColumnSelectionAllowed(false);
        //DODAO
        setSelectionBackground(new Color(232,57,95));
        setSelectionForeground(Color.white);
        setShowVerticalLines(false);
        setShowGrid(false);
        
        getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        getTableHeader().setOpaque(false);
        getTableHeader().setBackground(new Color(32,136,203));
        getTableHeader().setForeground(new Color(255,255,255));
        setRowHeight(20);
        //
        centerText();
        if (tableModel instanceof StudentTableModel) {
            setAutoCreateRowSorter(true);
            TableRowSorter<StudentTableModel> rowSorter = new TableRowSorter<StudentTableModel>((StudentTableModel) tableModel);
            rowSorter.setComparator(0, new Comparator<String>() {

                @Override
                public int compare(String o1, String o2) {
                    // TODO Auto-generated method stub
                    int value1 = Integer.parseInt("0" + o1.replaceAll("[^0-9]", ""));
                    int value2 = Integer.parseInt("0" + o2.replaceAll("[^0-9]", ""));
                    if (value1 < value2)
                        return -1;
                    else if (value1 == value2)
                        return 0;
                    else
                        return 1;
               }

            });
            rowSorter.setRowFilter(new StudentRowFilter());
            rowSorter.setSortsOnUpdates(true);
            setRowSorter(rowSorter);

        } else if (tableModel instanceof SubjectTableModel) {
        	setAutoCreateRowSorter(true);
            TableRowSorter<SubjectTableModel> rowSorter = new TableRowSorter<SubjectTableModel>((SubjectTableModel) tableModel);
            rowSorter.setComparator(0, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
                    int value1 = Integer.parseInt("0" + o1.replaceAll("[^0-9]", ""));
                    int value2 = Integer.parseInt("0" + o2.replaceAll("[^0-9]", ""));
                    if (value1 < value2)
                        return -1;
                    else if (value1 == value2)
                        return 0;
                    else
                        return 1;
				}
            	
			});
            
            
            rowSorter.setRowFilter(new SubjectRowFilter<>());
            rowSorter.setSortsOnUpdates(true);
            setRowSorter(rowSorter);
        } else if (tableModel instanceof ProfessorTableModel) {
            TableRowSorter<ProfessorTableModel> rowSorter = new TableRowSorter<>((ProfessorTableModel) tableModel);
            rowSorter.setRowFilter(new ProfessorRowFilter<>());
            rowSorter.setSortsOnUpdates(true);
            setRowSorter(rowSorter);
        }
    }

	private void centerText() {
        for (int i = 0; i < getModel().getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
