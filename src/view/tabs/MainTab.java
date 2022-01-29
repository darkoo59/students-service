package view.tabs;

import interfaces.IAddingController;
import interfaces.IDeleteController;
import interfaces.IEditingController;
import model.database.DataModel;
import utils.PropertyFactory;
import view.entity.abstract_model.table_model.DepartmentTableModel;
import view.entity.abstract_model.table_model.ProfessorTableModel;
import view.entity.abstract_model.table_model.StudentTableModel;
import view.entity.abstract_model.table_model.SubjectTableModel;
import view.Screen;
import view.entity.department.DepartmentEditDialog;
import view.entity.subject.SubjectEditDialog;
import view.entity.table.Table;
import view.entity.AddingScreen;
import view.entity.EditingScreen;

import javax.swing.*;
import java.awt.*;

public class MainTab extends JTabbedPane {

    private IAddingController addingController;
    private IDeleteController deleteController;
    private IEditingController editingController;
    private Table studentTable;
    private Table professorTable;
    private Table subjectTable;
    private Table departmentTable;

    public MainTab(IAddingController addingController, IEditingController editingController,
                   IDeleteController deleteController) {
        super();
        this.addingController = addingController;
        this.editingController = editingController;
        this.deleteController = deleteController;

        studentTable = new Table(new StudentTableModel());
        professorTable = new Table(new ProfessorTableModel());
        subjectTable = new Table(new SubjectTableModel());
        departmentTable = new Table(new DepartmentTableModel());

        DataModel.getInstance().setTableObserver(studentTable);




        add("Student", createPaddingAroundTable(studentTable));
        add("Profesor", createPaddingAroundTable(professorTable));
        add("Predmet", createPaddingAroundTable(subjectTable));
        add("Katedra", createPaddingAroundTable(departmentTable));

    }

    private JPanel createPaddingAroundTable(Table table) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
        panel.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
        panel.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
        panel.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    public void changeLanguage() {
        this.setTitleAt(0, Screen.getInstance().getResourceBundle().getString("tabStudent"));
        this.setTitleAt(1, Screen.getInstance().getResourceBundle().getString("tabProfessor"));
        this.setTitleAt(2, Screen.getInstance().getResourceBundle().getString("tabSubject"));
        this.setTitleAt(3, Screen.getInstance().getResourceBundle().getString("tabDepartment"));

        studentTable.getColumnModel().getColumn(0).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colStudent0"));
        studentTable.getColumnModel().getColumn(1).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colStudent1"));
        studentTable.getColumnModel().getColumn(2).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colStudent2"));
        studentTable.getColumnModel().getColumn(3).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colStudent3"));
        studentTable.getColumnModel().getColumn(4).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colStudent4"));
        studentTable.getColumnModel().getColumn(5).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colStudent5"));

        professorTable.getColumnModel().getColumn(0).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colProfessor0"));
        professorTable.getColumnModel().getColumn(1).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colProfessor1"));
        professorTable.getColumnModel().getColumn(2).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colProfessor2"));
        professorTable.getColumnModel().getColumn(3).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colProfessor3"));
        professorTable.getColumnModel().getColumn(4).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colProfessor4"));

        subjectTable.getColumnModel().getColumn(0).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colSubject0"));
        subjectTable.getColumnModel().getColumn(1).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colSubject1"));
        subjectTable.getColumnModel().getColumn(2).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colSubject2"));
        subjectTable.getColumnModel().getColumn(3).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colSubject3"));
        subjectTable.getColumnModel().getColumn(4).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colSubject4"));
        
        departmentTable.getColumnModel().getColumn(0).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colDepartment0"));
        departmentTable.getColumnModel().getColumn(1).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colDepartment1"));
        departmentTable.getColumnModel().getColumn(2).setHeaderValue(Screen.getInstance().getResourceBundle().getString("colDepartment2"));


    }

    public String getSelectedStudentIndex() {
        StudentTableModel model = (StudentTableModel) studentTable.getModel();
        return model.selectedStudentIndex();
    }

    public String getSelectedProfessorId() {
        ProfessorTableModel model = (ProfessorTableModel) professorTable.getModel();
        return model.getSelectedId();
    }

    public String getSelectedSubjectId() {
        SubjectTableModel model = (SubjectTableModel) subjectTable.getModel();
        return model.getSelectedSubjectId();
    }

    public String getSelectedDepartmentId() {
        DepartmentTableModel model = (DepartmentTableModel) departmentTable.getModel();
        return (String)model.getValueAt(departmentTable.getSelectedRow(),0);
    }

    public void addNewEntity(AddingScreen dialog) {
        this.addingController.addNewEntity(dialog);
    }

    public void editNewEntity(EditingScreen dialog) {
        additionalPreparation(dialog);
        this.editingController.editEntity(dialog);
    }

    public void deleteEntity() {
        deleteController.deleteEntity();
    }

    public void setAddingController(IAddingController controller) {
        this.addingController = controller;
    }

    public void setEditingController(IEditingController controller) {
        this.editingController = controller;
    }

    public void setDeleteController(IDeleteController controller) {
        this.deleteController = controller;
    }

    public IAddingController getAddingController() {
        return addingController;
    }

    public IDeleteController getDeleteController() {
        return deleteController;
    }

    public IEditingController getEditingController() {
        return editingController;
    }

    public Table getStudentTable() {
        return studentTable;
    }

    public Table getProfessorTable() {
        return professorTable;
    }

    public Table getSubjectTable() {
        return subjectTable;
    }

    public Table getDepartmentTable() {
        return departmentTable;
    }

    public void setDepartmentTable(Table departmentTable) {
        this.departmentTable = departmentTable;
    }

    public void additionalPreparation(EditingScreen dialog) {
        if(dialog instanceof SubjectEditDialog) {
            SubjectEditDialog subjectEditDialog = ((SubjectEditDialog)dialog);
            subjectEditDialog.switchProfessorNameWithId();
        } else if(dialog instanceof DepartmentEditDialog) {
            DepartmentEditDialog departmentEditDialog = ((DepartmentEditDialog) dialog);
            departmentEditDialog.switchProfessorNameWithId();
        }
    }
}
