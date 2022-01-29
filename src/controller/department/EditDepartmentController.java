package controller.department;

import controller.EntityValidator;
import exceptions.InvalidFieldException;
import interfaces.IEditingController;
import model.database.DataModel;
import model.Department;
import model.Professor;
import view.entity.abstract_model.list_model.DepartmentListModel;
import view.Screen;
import view.entity.subject.SubjectEditDialog;
import view.entity.table.Table;
import view.entity.department.DepartmentEditDialog;
import view.entity.EditingScreen;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.xml.crypto.Data;

import java.awt.Color;
import java.util.ArrayList;

public class EditDepartmentController implements IEditingController {
    @Override
    public void editEntity(EditingScreen dialog) {
        try {
            validate(dialog);
            Department department = createDepartmentObject(dialog);
            Table depTable = Screen.getInstance().getMainTab().getDepartmentTable();
            String depid = (String) depTable.getModel().getValueAt(depTable.getSelectedRow(), 0);
            DataModel.getInstance().editDepartment(department, depid);
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("departmentEditSuccess"));
            AbstractTableModel tm = (AbstractTableModel) depTable.getModel();
            tm.fireTableDataChanged();
            dialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void addDepartmentToField(DepartmentEditDialog departmentEdit) {
        if(departmentEdit.getTextField(2).getText().trim().equals(""))
            chooseHead(departmentEdit);
    }

    public void setFieldsValues(EditingScreen depWin) {
        Table depTable = Screen.getInstance().getMainTab().getDepartmentTable();
        Department dep = DataModel.getInstance().getDepartmentById((String) depTable.getValueAt(depTable.getSelectedRow(), 0));
        depWin.getTextField(0).setText(dep.getDepartmentId());
        depWin.getTextField(1).setText(dep.getDepartmentName());
        if (dep.getHeadOfTheDepartment() != null) {
            Professor professor = dep.getHeadOfTheDepartment();
            depWin.getTextField(2).setText(professor.getFirstName() + " " + professor.getLastName());
            ((DepartmentEditDialog)depWin).setChoosenProfessor(professor.getIdNumber());
        }
    }

    private Department createDepartmentObject(EditingScreen depWin) {
        String depId = depWin.getTextField(0).getText();
        String depName = depWin.getTextField(1).getText();
        String depProf = depWin.getTextField(2).getText();

        Professor professor = DataModel.getInstance().getProfessorById(depProf);
        return new Department(depId, depName, professor);
    }

    @Override
    public void validate(EditingScreen dialog) throws InvalidFieldException {
        EntityValidator validator = new EntityValidator();
        for (int i = 0; i < dialog.getFieldsReferences().size(); i++) {
            JTextField field = dialog.getTextField(i);
            if (i == 2 || i == 3) continue;
            if (field.getText().trim().equals(""))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("emptyField"));
            validator.setEmptyMessage(field);
        }
    }

    public void chooseHead(DepartmentEditDialog departmentEdit) {
        String depId = Screen.getInstance().getMainTab().getSelectedDepartmentId();
        ArrayList<Professor> filteredProfs =  DataModel.getInstance().filterProfessorForHeadOfDep(depId);
        JList lista = new JList(new DepartmentListModel(filteredProfs));
        lista.setSelectionBackground(new Color(232,57,95));
        lista.setSelectionForeground(Color.white);
        int result = JOptionPane.showConfirmDialog(null, new JScrollPane(lista), Screen.getInstance().getResourceBundle().getString("editHeadOfDepartmentTitle"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == 0 && lista.getSelectedIndex() != -1) {
            DepartmentListModel model = (DepartmentListModel) lista.getModel();
            if (lista.getSelectedIndex() == -1) return;
            Professor professor = DataModel.getInstance().getProfessorById(model.getObjectAt(lista.getSelectedIndex()).getIdNumber());
            departmentEdit.getTextField(2).setText(professor.getFirstName() + " " + professor.getLastName());
            departmentEdit.setChoosenProfessor(professor.getIdNumber());
            departmentEdit.switchAddDeleteButtons();
        }
    }

    public void addProfessorToDepartment(DepartmentEditDialog departmentEdit) {
        JList lista = new JList(new DepartmentListModel(DataModel.getInstance().filterProfessorsWithoutDepartment()));
        lista.setSelectionBackground(new Color(232,57,95));
        lista.setSelectionForeground(Color.white);
        int result = JOptionPane.showConfirmDialog(null, new JScrollPane(lista), Screen.getInstance().getResourceBundle().getString("editHeadOfDepartmentTitle"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == 0 && lista.getSelectedIndex() != -1) {
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("professorToList"));
            DepartmentListModel model = (DepartmentListModel) lista.getModel();
            if (lista.getSelectedIndex() == -1) return;
            Professor professor = DataModel.getInstance().getProfessorById(model.getObjectAt(lista.getSelectedIndex()).getIdNumber());
            String depId = Screen.getInstance().getMainTab().getSelectedDepartmentId();
            professor.setDepartment(depId);
        }
    }
}
