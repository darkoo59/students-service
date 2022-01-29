package controller.subject;

import controller.EntityValidator;
import exceptions.InvalidFieldException;
import interfaces.IEditingController;
import model.database.DataModel;
import model.Professor;
import model.Subject;
import utils.Constants.Semester;
import utils.EnumConversion;
import view.Screen;
import view.entity.EditingScreen;
import view.entity.subject.SubjectEditDialog;

import javax.swing.*;
import java.util.Vector;

public class EditingSubjectController implements IEditingController {

    @Override
    public void editEntity(EditingScreen dialog) {
        // TODO Auto-generated method stub
        try {
            validate(dialog);
            String subjectIndexBeforeEdit = Screen.getInstance().getMainTab().getSelectedSubjectId();
            Subject subjectBeforeEdit = DataModel.getInstance().getSubjectById(subjectIndexBeforeEdit);
            String professorId = "";
            if(subjectBeforeEdit.getProfessor() != null)
            	professorId = subjectBeforeEdit.getProfessor().getIdNumber();
            Subject subject = getEditedSubject(dialog);
         
            DataModel model = DataModel.getInstance();
            model.setEditedSubject(subjectIndexBeforeEdit, subject);
            Professor professor = subject.getProfessor();
            if(professor != null)
            	professor.addSubject(subject);
            else {
            	model.removeSubjectFromProfessorSubjects(subjectIndexBeforeEdit, professorId);
            }
            JOptionPane.showMessageDialog(dialog, Screen.getInstance().getResourceBundle().getString("successEditSubject"));
            dialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog((JDialog) dialog, e.getMessage(), Screen.getInstance().getResourceBundle().getString("error"), JOptionPane.WARNING_MESSAGE);
        }
    }

    public static String[] findSubjectDataForFields(Subject subject, SubjectEditDialog dialog) {
        String data[] = {"", "", "", "", "", ""};
        data[0] = subject.getSubjectId();
        data[1] = subject.getSubjectName();
        data[2] = subject.getSemester().getValue();
        if (subject.getProfessor() != null) {
            data[3] = subject.getProfessor().getFirstName() + " " + subject.getProfessor().getLastName();
            dialog.setChoosenProfessor(subject.getProfessor().getIdNumber());
        } else {
            dialog.setChoosenProfessor("");
        }
        data[4] = Integer.toString(subject.getEspb());
        data[5] = Integer.toString(subject.getYearOfStudy());

        return data;
    }

    public Subject getEditedSubject(EditingScreen dialog) {
        String subjectId = dialog.getTextField(0).getText();
        String subjectName = dialog.getTextField(1).getText();
        Semester semester = EnumConversion.stringToSemester(dialog.getComboBox(2).getSelectedItem().toString());
        int yearOfStudy = Integer.parseInt(dialog.getComboBox(5).getSelectedItem().toString());
        Professor professor = DataModel.getInstance().getProfessorById(dialog.getTextField(3).getText());
        System.out.println(professor);
        int espb = Integer.parseInt(dialog.getTextField(4).getText());

        return new Subject(subjectId, subjectName, semester, yearOfStudy, professor, espb);
    }


    @Override
    public void validate(EditingScreen dialog) throws InvalidFieldException {
        // TODO Auto-generated method stub
        EntityValidator validator = new EntityValidator();
        Vector<JComponent> fields = dialog.getFieldsReferences();
        for (int i = 0; i < fields.size(); i++) {
            if (i == 2 || i == 3 || i == 5)
                continue;
            JTextField field = (JTextField) fields.get(i);
            if (field.getText().trim().equals(""))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("emptyField"));
            if (!validator.isValidNumberField(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("numberFormat"));
            validator.setEmptyMessage(field);
        }
    }

}

