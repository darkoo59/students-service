package controller;

import exceptions.InvalidFieldException;
import model.Department;
import model.database.DataModel;
import model.Professor;
import model.Student;
import model.Subject;
import utils.Constants;
import view.Screen;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;

public class EntityValidator {

    private String[] numberFields = Constants.getNumberFields();

    public EntityValidator() {

    }

    public void throwInvalidValidation(JTextField field, String message) throws InvalidFieldException {
        field.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        setErrorMessage(field, message);
        throw new InvalidFieldException(message);
    }

    private void setErrorMessage(JTextField field, String message) {
        JPanel panel = (JPanel) field.getParent();
        Component[] children = panel.getComponents();
        ((JLabel) children[2]).setText(message);
    }

    public void setEmptyMessage(JTextField field) {
        field.setBorder(BorderFactory.createLineBorder(Color.gray));
        setErrorMessage(field, "");
    }

    public boolean isValidNumberField(JTextField field) {
        if (!Arrays.asList(numberFields).contains(field.getName()))
            return true;
        try {
            Integer.parseInt(field.getText());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isValidDate(JTextField dateField) {
        try {
            LocalDate.parse(dateField.getText());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isValidAdressNumber(JTextField adressField) {
        String text = adressField.getText();
        String[] txtSpl = text.split(":");
        if (txtSpl.length != 4
                || txtSpl[0].trim().equals("")
                || txtSpl[1].trim().equals("")
                || txtSpl[2].trim().equals("")
                || txtSpl[3].trim().equals("")) {
            return false;
        }
        return true;
    }

    public boolean isValidIndexNumber(JTextField field) {
        DataModel database = DataModel.getInstance();
        Student student = database.getStudentById(field.getText());
        if (student == null) return true;
        return false;
    }

    public boolean isValidIndexNumberForEditing(JTextField field) {
        String selectedStudentIndex = Screen.getInstance().getMainTab().getSelectedStudentIndex();
        DataModel database = DataModel.getInstance();
        Student student = database.getStudentById(field.getText());
        if (student == null) return true;
        else if (student.getIndexNumber() == selectedStudentIndex) return true;
        return false;
    }

    public boolean isValidLBO(JTextField field) {
        DataModel database = DataModel.getInstance();
        Professor professor = database.getProfessorById(field.getText());
        if (professor == null) return true;
        return false;
    }

    public boolean isValidLBOForEditing(JTextField field) {
        String selectedProfessorIndex = Screen.getInstance().getMainTab().getSelectedProfessorId();
        DataModel database = DataModel.getInstance();
        Professor professor = database.getProfessorById(field.getText());
        if (professor == null) return true;
        else if (professor.getIdNumber() == selectedProfessorIndex) return true;
        return false;
    }


    public boolean isValidSubjectId(JTextField field) {
        DataModel database = DataModel.getInstance();
        Subject subject = database.getSubjectById(field.getText());
        if (subject == null) return true;
        return false;
    }

    public boolean isValidProfessor(JTextField field) {
        DataModel database = DataModel.getInstance();
        Professor professor = database.getProfessorById(field.getText());
        if (professor == null) return false;
        return true;
    }

    public boolean isValidDepartment(JTextField field) {
        DataModel database = DataModel.getInstance();
        Department department = database.getDepartmentById(field.getText());
        if(department == null) return false;
        return true;
    }


}
