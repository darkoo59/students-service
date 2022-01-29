package view.toolbar;

import view.entity.abstract_model.table_model.ProfessorTableModel;
import view.entity.abstract_model.table_model.StudentTableModel;
import view.entity.abstract_model.table_model.SubjectTableModel;
import utils.PropertyFactory;
import view.entity.filters.ProfessorRowFilter;
import view.entity.filters.StudentRowFilter;
import view.entity.filters.SubjectRowFilter;
import view.Screen;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class ToolbarSearch extends JTextField implements DocumentListener {

    public ToolbarSearch() {
        super();
        setPreferredSize(new Dimension(130, 30));
        setMaximumSize(new Dimension(130, 30));
        PropertyFactory.addBlackBorder(this, 2);
        getDocument().addDocumentListener(this);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateSearch();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateSearch();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateSearch();
    }

    private void updateSearch() {
        int currentTab = Screen.getInstance().getSelectedTab();
        String text = getText();

        if (currentTab == 0) {
            StudentTableModel tableModel = (StudentTableModel) Screen.getInstance().getMainTab().getStudentTable().getModel();
            TableRowSorter<StudentTableModel> rowSorter = (TableRowSorter<StudentTableModel>) Screen.getInstance().getMainTab().getStudentTable().getRowSorter();
            StudentRowFilter studentRowFilter = (StudentRowFilter) rowSorter.getRowFilter();
            studentRowFilter.setFilterWord(text);
            tableModel.fireTableDataChanged();
        } else if (currentTab == 2) {
            SubjectTableModel tableModel = (SubjectTableModel) Screen.getInstance().getMainTab().getSubjectTable().getModel();
            TableRowSorter<SubjectTableModel> rowSorter = (TableRowSorter<SubjectTableModel>) Screen.getInstance().getMainTab().getSubjectTable().getRowSorter();
            SubjectRowFilter subjectRowFilter = (SubjectRowFilter) rowSorter.getRowFilter();
            subjectRowFilter.setFilterWord(text);
            tableModel.fireTableDataChanged();
        } else if (currentTab == 1) {
            ProfessorTableModel tableModel = (ProfessorTableModel) Screen.getInstance().getMainTab().getProfessorTable().getModel();
            TableRowSorter<ProfessorTableModel> rowSorter = (TableRowSorter<ProfessorTableModel>) Screen.getInstance().getMainTab().getProfessorTable().getRowSorter();
            ProfessorRowFilter subjectRowFilter = (ProfessorRowFilter) rowSorter.getRowFilter();
            subjectRowFilter.setFilterWord(text);
            tableModel.fireTableDataChanged();
        }

    }
}
